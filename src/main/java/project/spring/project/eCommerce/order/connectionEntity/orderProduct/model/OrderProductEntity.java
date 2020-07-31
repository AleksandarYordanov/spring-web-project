package project.spring.project.eCommerce.order.connectionEntity.orderProduct.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name="OrderProductEntity")
@Table(name="orders_products")
public class OrderProductEntity implements Serializable {


    @EmbeddedId
    private OrderProductPK id;

    @Column
    private int quantity;

    public OrderProductPK getId() {
        return id;
    }

    public void setId(OrderProductPK id) {
        this.id = id;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
