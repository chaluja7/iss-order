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
    private Long articleId;

    @XmlElement(required = true)
    private String sku;

    @XmlElement(required = true)
    private Integer count;

    @XmlElement(required = true)
    private Double unitPrice;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

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
        return articleId != null && sku != null && count != null && count > 0 && unitPrice != null && unitPrice >= 0;
    }

    @Override
    public OrderItem getClone() {
        OrderItem orderItem = new OrderItem();
        orderItem.setSku(sku);
        orderItem.setArticleId(articleId);
        orderItem.setCount(count);
        orderItem.setUnitPrice(unitPrice);

        return orderItem;
    }
}
