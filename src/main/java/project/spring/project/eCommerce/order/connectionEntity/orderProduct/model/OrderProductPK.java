package project.spring.project.eCommerce.order.connectionEntity.orderProduct.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderProductPK implements Serializable {


    private Long orderId;

    private Long productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProductPK departmentCategoryPK = (OrderProductPK) o;
        return Objects.equals(orderId, departmentCategoryPK.orderId) &&
                Objects.equals(productId, departmentCategoryPK.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }

    public Long getTypeId() {
        return orderId;
    }

    public void setTypeId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long categoryId) {
        this.productId = categoryId;
    }
}
