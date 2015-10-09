package cz.cvut.iss.service;

import cz.cvut.iss.exception.NoSuchOrderException;
import cz.cvut.iss.generated.supplierA.ItemRequest;
import cz.cvut.iss.model.OrderItem;
import org.apache.camel.ExchangeProperty;

/**
 * @author jakubchalupa
 * @since 05.10.15
 */
public final class SupplierAService {

    private OrderRepository orderRepository;

    public ItemRequest getAvailableRequest(@ExchangeProperty("orderId") long orderId) {
        try {
            OrderItem orderItem = orderRepository.get(orderId).getItem();

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
