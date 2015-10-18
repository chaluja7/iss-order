package cz.cvut.iss.test;

import cz.cvut.iss.exception.BadOrderBodyException;
import cz.cvut.iss.exception.NoSuchItemException;
import cz.cvut.iss.model.Address;
import cz.cvut.iss.model.Order;
import cz.cvut.iss.model.OrderItem;
import cz.cvut.iss.service.OrderRepository;
import org.apache.camel.test.junit4.TestSupport;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author jakubchalupa
 * @since 18.10.15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/META-INF/spring/camel-context.xml"})
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class IssOrderTest extends TestSupport {

    @Autowired
    protected OrderRepository orderRepository;

    @Before
    public void setUp() throws BadOrderBodyException, NoSuchItemException {
        //create order with ID = 1
        Order testingOrder = createTestingOrder();
        orderRepository.create(testingOrder);
    }

    protected Order createTestingOrder() {
        Address address = new Address();
        address.setFirstName("Jakub");
        address.setLastName("Chalupa");
        address.setStreet("Pricna 15");
        address.setCity("Praha");
        address.setZipCode("11100");

        OrderItem orderItem = new OrderItem();
        orderItem.setSku("ubuntu");
        orderItem.setUnitPrice(5.0);
        orderItem.setCount(1);

        Order order = new Order();
        order.setAddress(address);
        order.setItem(orderItem);

        return order;
    }

}
