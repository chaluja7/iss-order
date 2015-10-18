package cz.cvut.iss.test;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jakubchalupa
 * @since 18.10.15
 */
public class RemoteSuppliersTest extends IssOrderTest {

    @EndpointInject(uri = "direct:supplier-a")
    protected ProducerTemplate supplierA;

    @EndpointInject(uri = "direct:supplier-b")
    protected ProducerTemplate supplierB;

    @Test
    public void testSupplierA() {
        Exchange request = supplierA.request("direct:supplier-a", exchange -> exchange.getIn().getHeaders().put("orderId", 1));

        Assert.assertNotNull(request);
        Assert.assertTrue(request.getOut().getHeaders().containsKey("supplierAItemPrice"));
        Assert.assertEquals(2.0, request.getOut().getHeaders().get("supplierAItemPrice"));
    }

    @Test
    public void testSupplierB() {
        Exchange request = supplierB.request("direct:supplier-b", exchange -> exchange.getIn().getHeaders().put("orderId", 1));

        Assert.assertNotNull(request);
        Assert.assertTrue(request.getOut().getHeaders().containsKey("supplierBItemPrice"));
        Assert.assertEquals(300.0, request.getOut().getHeaders().get("supplierBItemPrice"));
    }


}
