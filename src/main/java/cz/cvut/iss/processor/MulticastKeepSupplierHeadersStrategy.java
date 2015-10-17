package cz.cvut.iss.processor;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.Map;

/**
 * @author jakubchalupa
 * @since 17.10.15
 */
public class MulticastKeepSupplierHeadersStrategy implements AggregationStrategy {

    private static final String SUPPLIER_A_ITEM_PRICE = "supplierAItemPrice";

    private static final String SUPPLIER_B_ITEM_PRICE = "supplierBItemPrice";

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if(oldExchange == null) return newExchange;

        //musim spravne zachovat jit nasetovane supplierPrice hlavicky
        if(newExchange != null) {
            Map<String, Object> newHeaders = newExchange.getIn().getHeaders();
            Map<String, Object> oldHeaders = oldExchange.getIn().getHeaders();

            if(oldHeaders.get(SUPPLIER_A_ITEM_PRICE) == null && newHeaders.get(SUPPLIER_A_ITEM_PRICE) != null) {
                oldHeaders.put(SUPPLIER_A_ITEM_PRICE, newHeaders.get(SUPPLIER_A_ITEM_PRICE));
            }

            if(oldHeaders.get(SUPPLIER_B_ITEM_PRICE) == null && newHeaders.get(SUPPLIER_B_ITEM_PRICE) != null) {
                oldHeaders.put(SUPPLIER_B_ITEM_PRICE, newHeaders.get(SUPPLIER_B_ITEM_PRICE));
            }
        }

        return oldExchange;
    }

}
