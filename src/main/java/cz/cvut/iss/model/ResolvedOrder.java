package cz.cvut.iss.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Resolved order - we do not want to have this fields generated in wsdl for create order.
 *
 * @author jakubchalupa
 * @since 04.10.15
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ResolvedOrder extends Order {

    @XmlElement
    private Long id;

    @XmlElement
    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
