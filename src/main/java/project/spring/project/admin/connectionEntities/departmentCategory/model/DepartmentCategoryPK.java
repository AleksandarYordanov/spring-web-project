package project.spring.project.admin.connectionEntities.departmentCategory.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DepartmentCategoryPK implements Serializable {


    private Long departmentId;

    private Long categoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentCategoryPK departmentCategoryPK = (DepartmentCategoryPK) o;
        return Objects.equals(departmentId, departmentCategoryPK.departmentId) &&
                Objects.equals(categoryId, departmentCategoryPK.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, categoryId);
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
