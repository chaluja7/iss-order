package cz.cvut.iss.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.restlet.engine.header.Header;
import org.restlet.util.Series;

/**
 * @author jakubchalupa
 * @since 04.10.15
 */
public class VipHeaderProcessor implements Processor {

    private static final String VIP_HEADER = "X-Els-Vip";

    private static final String RESTLET_HEADERS = "org.restlet.http.headers";

    @Override
    public void process(Exchange exchange) throws Exception {
        String vipHeader = ((Series<Header>) exchange.getIn().getHeader(RESTLET_HEADERS)).getFirstValue(VIP_HEADER, true);
        if(vipHeader == null) vipHeader = "false";

        exchange.getIn().getHeaders().put(VIP_HEADER, vipHeader);
    }

}
