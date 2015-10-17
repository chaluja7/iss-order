package cz.cvut.iss.service;

import cz.cvut.iss.exception.NoSuchOrderException;
import cz.cvut.iss.model.OrderStatus;
import cz.cvut.iss.model.ResolvedOrder;
import org.apache.camel.ExchangeProperty;

/**
 * @author jakubchalupa
 * @since 04.10.15
 */
public final class OrderStatusProvider {

    private static final String RESOLUTION_CANCELLED = "CANCELLED";
    private static final String RESOLUTION_IN_PROCESS = "IN PROCESS";
    private static final String RESOLUTION_ACCOUNTED = "ACCOUNTED";
    private static final String RESOLUTION_CONFIRMED = "CONFIRMED";

    private OrderRepository orderRepository;

    public void inProcess(@ExchangeProperty("orderId") long orderId) throws NoSuchOrderException {
        setStatus(orderId, RESOLUTION_IN_PROCESS, "Contacting Inventory, Accounting and Shipment");
    }

    public void cancel(@ExchangeProperty("orderId") long orderId) throws NoSuchOrderException {
        setStatus(orderId, RESOLUTION_CANCELLED, "Items unavailable for given count or price");
    }

    public void cancelCauseAccounting(@ExchangeProperty("orderId") long orderId) throws NoSuchOrderException {
        setStatus(orderId, RESOLUTION_CANCELLED, "Error while contacting accounting");
    }

    public void account(@ExchangeProperty("orderId") long orderId) throws NoSuchOrderException {
        setStatus(orderId, RESOLUTION_ACCOUNTED, "The order is accounted");
    }

    public void confirm(@ExchangeProperty("orderId") long orderId) throws NoSuchOrderException {
        setStatus(orderId, RESOLUTION_CONFIRMED, "The order is ready for shipment");
    }

    private void setStatus(long orderId, String resolution, String description) throws NoSuchOrderException {
        ResolvedOrder order = orderRepository.get(orderId);
        if (order != null) {
            order.setStatus(new OrderStatus(resolution, description));
        }
    }

    private OrderStatusProvider() {
    }

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
