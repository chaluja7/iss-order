package cz.cvut.iss.service;

import cz.cvut.iss.model.Order;
import org.apache.camel.ExchangeProperty;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

public final class OrderRepository {

    private static final Map<Long, Order> ORDERS = new TreeMap<>();
    private static AtomicLong atomicLong = new AtomicLong(0);

    public static void create(Order order) {
        order.setId(atomicLong.incrementAndGet());
        ORDERS.put(order.getId(), order);
    }

    public static Order get(@ExchangeProperty("orderId") long id) {
        return ORDERS.get(id);
    }

    public static void clear() {
        ORDERS.clear();
        atomicLong = new AtomicLong(0);
    }

    private OrderRepository() {
    }
}
