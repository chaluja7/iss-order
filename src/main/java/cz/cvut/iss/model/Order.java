package cz.cvut.iss.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author jakubchalupa
 * @since 28.09.15
 */
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    @XmlElement(required = true)
    private Address address;

    @XmlElement(required = true)
    private OrderItem item;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public OrderItem getItem() {
        return item;
    }

    public void setItem(OrderItem item) {
        this.item = item;
    }

    public boolean isValid() {
        return (address != null && address.isValid() && item != null && item.isValid());
    }

}
