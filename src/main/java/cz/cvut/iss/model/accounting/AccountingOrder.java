package cz.cvut.iss.model.accounting;

import cz.cvut.iss.model.Address;

import java.util.List;

/**
 * Object for Accounting service
 *
 * @author jakubchalupa
 * @since 07.10.15
 */
public class AccountingOrder  {

    private Long id;

    private Address address;

    private List<AccountingItem> items;

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

    public List<AccountingItem> getItems() {
        return items;
    }

    public void setItems(List<AccountingItem> items) {
        this.items = items;
    }
}
