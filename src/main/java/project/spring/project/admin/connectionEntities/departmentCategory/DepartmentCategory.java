package project.spring.project.admin.connectionEntities.departmentCategory;

import project.spring.project.admin.category.model.CategoryEntity;
import project.spring.project.admin.department.model.DepartmentEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DepartmentCategory")
public class DepartmentCategory implements Serializable {


    @EmbeddedId
    private DepartmentCategoryPK id;

    @ManyToOne
    @MapsId("department_id") //This is the name of attr in EmployerDeliveryAgentPK class
    @JoinColumn(name = "DEPARTMENT_ID")
    private DepartmentEntity departmentEntity;

    @ManyToOne
    @MapsId("category_id")
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryEntity categoryEntity;

    @Column
    private int position;
}
