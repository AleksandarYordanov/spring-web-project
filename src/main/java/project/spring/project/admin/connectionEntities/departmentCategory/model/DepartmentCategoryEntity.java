package project.spring.project.admin.connectionEntities.departmentCategory.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="DepartmentCategoryEntity")
@Table(name="departments_categories")
public class DepartmentCategoryEntity implements Serializable {


    @EmbeddedId
    private DepartmentCategoryPK id;

    @Column
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
