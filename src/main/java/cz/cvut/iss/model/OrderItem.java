package cz.cvut.iss.model;

/**
 * @author jakubchalupa
 * @since 28.09.15
 */
public class OrderItem {

    private Long articleId;

    private Integer count;

    private Double unitPrice;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
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
}
