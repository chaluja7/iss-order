package cz.cvut.iss.model;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author jakubchalupa
 * @since 28.09.15
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Order {

    @XmlTransient
    private Long id;

    @XmlElement(required = true)
    private Address address;

    @XmlElement(required = true)
    private List<OrderItem> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
