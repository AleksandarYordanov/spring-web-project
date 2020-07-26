package project.spring.project.admin.connectionEntities.categoryType.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CategoryTypePK implements Serializable {


    private Long categoryId;

    private Long typeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryTypePK categoryTypePK = (CategoryTypePK) o;
        return Objects.equals(categoryId, categoryTypePK.categoryId) &&
                Objects.equals(typeId, categoryTypePK.typeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, typeId);
    }


    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
