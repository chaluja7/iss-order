package cz.cvut.iss.model;

import java.util.List;

/**
 * @author jakubchalupa
 * @since 28.09.15
 */
public class Order {

    private Long id;

    private Address address;

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
}
