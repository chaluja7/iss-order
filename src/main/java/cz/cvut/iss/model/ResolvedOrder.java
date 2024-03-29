package cz.cvut.iss.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Resolved order - we do not want to have this fields generated in wsdl for create order.
 *
 * @author jakubchalupa
 * @since 04.10.15
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ResolvedOrder extends Order implements Cloneable<ResolvedOrder> {

    @XmlElement
    private Long id;

    @XmlElement
    private Long totalPrice;

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

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public ResolvedOrder getClone() {
        ResolvedOrder resolvedOrder = new ResolvedOrder();
        resolvedOrder.setId(id);
        resolvedOrder.setTotalPrice(totalPrice);
        if(getStatus() != null) resolvedOrder.setStatus(status.getClone());
        if(getAddress() != null) resolvedOrder.setAddress(getAddress().getClone());
        if(getItem() != null) resolvedOrder.setItem(getItem().getClone());

        return resolvedOrder;
    }

}
