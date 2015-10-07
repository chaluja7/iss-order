package cz.cvut.iss.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author jakubchalupa
 * @since 04.10.15
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderStatus implements Cloneable<OrderStatus> {

    @XmlElement
    private String resolution;

    @XmlElement
    private String description;

    public OrderStatus(String resolution, String description) {
        this.resolution = resolution;
        this.description = description;
    }

    public String getResolution() {
        return resolution;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "OrderStatus [resolution=" + resolution + ", description=" + description + "]";
    }

    @Override
    public OrderStatus getClone() {
        return new OrderStatus(resolution, description);
    }

}
