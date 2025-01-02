package orders.models;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "order")
public class Order {
    @XmlAttribute(name = "id")
    private Integer id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "date")
    private String purchasedDate;
    @XmlElement(name = "count")
    private Integer count;

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(String purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", purchasedDate='" + purchasedDate + '\'' +
                ", count=" + count +
                '}';
    }
}
