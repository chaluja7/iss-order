package cz.cvut.iss.service;

import cz.cvut.iss.exception.NoSuchOrderException;
import cz.cvut.iss.generated.supplierB.ItemRequest;
import cz.cvut.iss.model.OrderItem;
import cz.cvut.iss.model.ResolvedOrder;
import org.apache.camel.ExchangeProperty;

/**
 * @author jakubchalupa
 * @since 05.10.15
 */
public final class SupplierBService {

    private OrderRepository orderRepository;

    public ItemRequest getAvailableRequest(@ExchangeProperty("orderId") long orderId) {
        try {
            ResolvedOrder resolvedOrder = orderRepository.get(orderId);
            //TODO jakubchalupa - opravdu jen prvni polozka?
            OrderItem orderItem = resolvedOrder.getItems().get(0);

            ItemRequest itemRequest = new ItemRequest();

            itemRequest.setAmount(orderItem.getCount());
            itemRequest.setSku(orderItem.getSku());


            return itemRequest;
        } catch (NoSuchOrderException e) {
            return null;
        }
    }

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
