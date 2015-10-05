package cz.cvut.iss.service;

import cz.cvut.iss.exception.BadOrderBodyException;
import cz.cvut.iss.exception.NoSuchOrderException;
import cz.cvut.iss.model.Order;
import cz.cvut.iss.model.OrderItem;
import cz.cvut.iss.model.ResolvedOrder;
import org.apache.camel.ExchangeProperty;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

public final class OrderRepository implements OrderService {

    private static final Map<Long, ResolvedOrder> ORDERS = new TreeMap<>();

    private static AtomicLong atomicLong = new AtomicLong(0);

    @Override
    public long create(Order order) throws BadOrderBodyException{
        if(order == null || !order.isValid()) {
            throw new BadOrderBodyException(order);
        }

        ResolvedOrder resolvedOrder = new ResolvedOrder();
        resolvedOrder.setItems(order.getItems());
        resolvedOrder.setAddress(order.getAddress());
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
    public ResolvedOrder getForAccounting(@ExchangeProperty("orderId") long id) throws NoSuchOrderException{
        ResolvedOrder resolvedOrder = this.get(id);

        ResolvedOrder clone = resolvedOrder.getClone();
        clone.setStatus(null);
        clone.setTotalPrice(null);
        if(clone.getItems() != null) {
            for (OrderItem orderItem : clone.getItems()) {
                orderItem.setSku(null);
            }

        }

        return clone;
    }

    public void setTotalPrice(@ExchangeProperty("orderId") long id, @ExchangeProperty("orderTotalPrice") Long totalPrice) throws NoSuchOrderException {
        get(id).setTotalPrice(totalPrice);
    }

    public static void clear() {
        ORDERS.clear();
        atomicLong = new AtomicLong(0);
    }

    private OrderRepository() {
    }
}
