package cz.cvut.iss.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author jakubchalupa
 * @since 28.09.15
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {

    @XmlElement(required = true)
    private Address address;

    @XmlElement(required = true)
    private List<OrderItem> items;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public boolean isValid() {
        if(address == null || !address.isValid() || items == null || items.isEmpty()) return false;

        for (OrderItem item : items) {
            if(!item.isValid()) return false;
        }

        return true;
    }

}
