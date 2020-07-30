package project.spring.project.eCommerce.cart.connectionEntity.cartProduct.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name="CartProductEntity")
@Table(name="carts_products")
public class CartProductEntity implements Serializable {


    @EmbeddedId
    private CartProductPK id;

    @Column
    private int position;

    public CartProductPK getId() {
        return id;
    }

    public void setId(CartProductPK id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
