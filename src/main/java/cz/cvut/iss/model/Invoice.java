package cz.cvut.iss.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by frox on 5.10.15.
 */
public class Invoice {
    private Address address;
    private Long invoiceId;
    private ResolvedOrder order;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public ResolvedOrder getOrder() {
        return order;
    }

    public void setOrder(ResolvedOrder order) {
        this.order = order;
    }
}
