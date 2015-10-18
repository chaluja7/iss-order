package cz.cvut.iss.test;

import cz.cvut.iss.model.Invoice;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jakubchalupa
 * @since 18.10.15
 */
public class RemoteAccountingTest extends IssOrderTest {

    @EndpointInject(uri = "direct:accounting")
    protected ProducerTemplate accounting;

    @Test
    public void testAccounting() {
        Object accountedOrder = accounting.requestBodyAndHeader(null, "orderId", 1);
        Assert.assertNotNull(accountedOrder);
        Assert.assertTrue(accountedOrder instanceof Invoice);

        Invoice accounted = (Invoice) accountedOrder;
        Assert.assertEquals(accounted.getStatus(), "ISSUED");
    }

}
