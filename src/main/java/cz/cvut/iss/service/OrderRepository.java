package cz.cvut.iss.service;

import cz.cvut.iss.exception.BadOrderBodyException;
import cz.cvut.iss.exception.NoSuchItemException;
import cz.cvut.iss.exception.NoSuchOrderException;
import cz.cvut.iss.model.Order;
import cz.cvut.iss.model.OrderItem;
import cz.cvut.iss.model.ResolvedOrder;
import cz.cvut.iss.model.accounting.AccountingItem;
import cz.cvut.iss.model.accounting.AccountingOrder;
import org.apache.camel.ExchangeProperty;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public final class OrderRepository implements OrderService {

    private ItemRepository itemRepository;

    private static final Map<Long, ResolvedOrder> ORDERS = new TreeMap<>();

    private static AtomicLong atomicLong = new AtomicLong(0);

    @Override
    public long create(Order order) throws BadOrderBodyException, NoSuchItemException {
        if(order == null || !order.isValid()) {
            throw new BadOrderBodyException(order);
        }

        if(!itemRepository.containsItemWithSku(order.getItem().getSku())) {
            throw new NoSuchItemException(order.getItem().getSku());
        }

        ResolvedOrder resolvedOrder = new ResolvedOrder();
        resolvedOrder.setItem(order.getItem().getClone());
        resolvedOrder.setAddress(order.getAddress().getClone());
        resolvedOrder.setId(atomicLong.incrementAndGet());
        ORDERS.put(resolvedOrder.getId(), resolvedOrder);

        return resolvedOrder.getId();
    }

    @Override
    public ResolvedOrder get(@ExchangeProperty("orderId") long id) throws NoSuchOrderException{
        if(ORDERS.containsKey(id)) {
            return ORDERS.get(id);
        }

        throw new NoSuchOrderException(id);
    }

    /**
     * accounting nechce dostavat nektere property, proto tato metoda, ktera se techto atributu zbavi
     */
    public AccountingOrder getForAccounting(@ExchangeProperty("orderId") long id) throws NoSuchOrderException{
        ResolvedOrder resolvedOrder = this.get(id);

        AccountingOrder accountingOrder = new AccountingOrder();
        accountingOrder.setId(resolvedOrder.getId());
        accountingOrder.setAddress(resolvedOrder.getAddress().getClone());

        List<AccountingItem> accountingItems = new ArrayList<>();
        AccountingItem accountingItem = new AccountingItem();
        accountingItem.setArticleId(itemRepository.getItemIdForSku(resolvedOrder.getItem().getSku()));
        accountingItem.setCount(resolvedOrder.getItem().getCount());
        accountingItem.setUnitPrice(resolvedOrder.getItem().getUnitPrice());

        accountingItems.add(accountingItem);
        accountingOrder.setItems(accountingItems);

        return accountingOrder;
    }

    public OrderItem getForInventory(@ExchangeProperty("orderId") long id) throws NoSuchOrderException{
        ResolvedOrder resolvedOrder = this.get(id);
        return resolvedOrder.getItem();
    }

    public void setTotalPrice(@ExchangeProperty("orderId") long id, @ExchangeProperty("orderTotalPrice") Long totalPrice) throws NoSuchOrderException {
        get(id).setTotalPrice(totalPrice);
    }

    public static void clear() {
        ORDERS.clear();
        atomicLong = new AtomicLong(0);
    }

    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    /**
     * Generates a new order structured as a {@link Map}
     */
    public Map<String, Object> generateOrderItem(@ExchangeProperty("orderId") long id) throws NoSuchOrderException{
        ResolvedOrder resolvedOrder = this.get(id);
        OrderItem o = resolvedOrder.getItem();
        Map<String, Object> answer = new HashMap<String, Object>();
        answer.put("sku", o.getSku());
        answer.put("count", o.getCount());
        answer.put("price", o.getUnitPrice());
        return answer;
    }

}
