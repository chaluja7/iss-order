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
public class OrderItem implements Cloneable<OrderItem> {

    @XmlElement(required = true)
    private String sku;

    @XmlElement(required = true)
    private Integer count;

    @XmlElement(required = true)
    private Double unitPrice;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean isValid() {
        return sku != null && count != null && count > 0 && unitPrice != null && unitPrice >= 0;
    }

    // pro sql outputClass
    public OrderItem() {
    }

    public boolean isPresent() {
        return count > 0;
    }

    @Override
    public OrderItem getClone() {
        OrderItem orderItem = new OrderItem();
        orderItem.setSku(sku);
        orderItem.setCount(count);
        orderItem.setUnitPrice(unitPrice);

        return orderItem;
    }
}
