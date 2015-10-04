package cz.cvut.iss.service;

import cz.cvut.iss.amq.Producer;
import cz.cvut.iss.exception.BadOrderBodyException;
import cz.cvut.iss.exception.NoSuchOrderException;
import cz.cvut.iss.model.Order;
import org.apache.camel.ExchangeProperty;

import javax.jms.JMSException;
import javax.xml.bind.JAXBException;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

public final class OrderRepository implements OrderService {

    private static final Map<Long, Order> ORDERS = new TreeMap<>();
    private static AtomicLong atomicLong = new AtomicLong(0);

    @Override
    public long create(Order order) throws BadOrderBodyException, JAXBException, JMSException {
        if(order == null || !order.isValid()) {
            throw new BadOrderBodyException(order);
        }

        order.setId(atomicLong.incrementAndGet());
        ORDERS.put(order.getId(), order);

//        sendOrderToExpedition(order);

        return order.getId();
    }

    @Override
    public Order get(@ExchangeProperty("orderId") long id) throws NoSuchOrderException{
        if(ORDERS.containsKey(id)) {
            return ORDERS.get(id);
        }

        throw new NoSuchOrderException(id);
    }

    public static void clear() {
        ORDERS.clear();
        atomicLong = new AtomicLong(0);
    }

    private OrderRepository() {
    }

    private void sendOrderToExpedition(Order order) throws JAXBException, JMSException {
        Producer producer = new Producer();
        producer.produceOrder(order);
    }
}
