package project.spring.project.admin.connectionEntities.departmentCategory;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;

public class DepartmentCategoryDTO {

    private DepartmentCategoryPK id;
    private int position;

    public DepartmentCategoryPK getId() {
        return id;
    }

    public void setId(DepartmentCategoryPK id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
