package project.spring.project.eCommerce.cart.connectionEntity.cartProduct.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CartProductPK implements Serializable {


    private Long cartId;

    private Long productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartProductPK departmentCategoryPK = (CartProductPK) o;
        return Objects.equals(cartId, departmentCategoryPK.cartId) &&
                Objects.equals(productId, departmentCategoryPK.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, productId);
    }

    public Long getTypeId() {
        return cartId;
    }

    public void setTypeId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long categoryId) {
        this.productId = categoryId;
    }
}
