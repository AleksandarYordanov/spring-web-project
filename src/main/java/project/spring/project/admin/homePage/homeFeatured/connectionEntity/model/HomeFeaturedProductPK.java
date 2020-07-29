package project.spring.project.admin.homePage.homeFeatured.connectionEntity.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HomeFeaturedProductPK implements Serializable {


    private Long homeFeaturedId;

    private Long productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomeFeaturedProductPK departmentCategoryPK = (HomeFeaturedProductPK) o;
        return Objects.equals(homeFeaturedId, departmentCategoryPK.homeFeaturedId) &&
                Objects.equals(productId, departmentCategoryPK.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeFeaturedId, productId);
    }

    public Long getTypeId() {
        return homeFeaturedId;
    }

    public void setTypeId(Long homeFeaturedId) {
        this.homeFeaturedId = homeFeaturedId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long categoryId) {
        this.productId = categoryId;
    }
}
