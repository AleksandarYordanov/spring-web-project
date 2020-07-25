package project.spring.project.admin.connectionEntities.typeProduct.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TypeProductPK implements Serializable {


    private Long typeId;

    private Long productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeProductPK departmentCategoryPK = (TypeProductPK) o;
        return Objects.equals(typeId, departmentCategoryPK.typeId) &&
                Objects.equals(productId, departmentCategoryPK.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, productId);
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long categoryId) {
        this.productId = categoryId;
    }
}
