package cz.cvut.iss.test;

import cz.cvut.iss.model.Order;
import cz.cvut.iss.model.ResolvedOrder;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jakubchalupa
 * @since 18.10.15
 */
public class DirectOrderRoutesTest extends IssOrderTest {

    @EndpointInject(uri = "direct:new-order")
    protected ProducerTemplate newOrder;

    @EndpointInject(uri = "direct:find-order")
    protected ProducerTemplate findOrder;

    @Test
    public void testCreateAndGetOrder() {
        Object createdOrderIdObject = newOrder.requestBody(createTestingOrder());

        Assert.assertNotNull(createdOrderIdObject);
        Assert.assertTrue(createdOrderIdObject instanceof Long);

        Long createdOrderId = (Long) createdOrderIdObject;
        Object retrievedOrder = findOrder.requestBodyAndHeader(null, "orderId", createdOrderId);

        Assert.assertNotNull(retrievedOrder);
        Assert.assertTrue(retrievedOrder instanceof ResolvedOrder);

        ResolvedOrder resolvedOrder = (ResolvedOrder) retrievedOrder;
        Assert.assertEquals(resolvedOrder.getId(), createdOrderId);
        Assert.assertEquals(resolvedOrder.getStatus().getResolution(), "CONFIRMED");
    }

    @Test
    public void testGetOrder() {
        Object retrievedOrder = findOrder.requestBodyAndHeader(null, "orderId", 1);
        Assert.assertNotNull(retrievedOrder);
        Assert.assertTrue(retrievedOrder instanceof ResolvedOrder);

        ResolvedOrder resolvedOrder = (ResolvedOrder) retrievedOrder;
        Assert.assertEquals(1l, (long) resolvedOrder.getId());
        Assert.assertEquals("ubuntu", resolvedOrder.getItem().getSku());
    }

    @Test(expected = Exception.class)
    public void testCreateOrderBadBody() {
        newOrder.requestBody(null);
    }

    @Test(expected = Exception.class)
    public void testCreateFailedOrder() {
        Order testingOrder = createTestingOrder();
        testingOrder.getItem().setCount(99999);
        newOrder.sendBody(testingOrder);

        Object retrievedOrder = findOrder.requestBodyAndHeader(null, "orderId", orderRepository.getLastOrder().getId());

        Assert.assertNotNull(retrievedOrder);
        Assert.assertTrue(retrievedOrder instanceof ResolvedOrder);

        ResolvedOrder resolvedOrder = (ResolvedOrder) retrievedOrder;
        Assert.assertEquals(resolvedOrder.getStatus().getResolution(), "CANCELLED");
    }

}
