package project.spring.project.admin.connectionEntities.departmentCategory;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DepartmentCategoryPK implements Serializable {

    @Column(name = "DEPARTMENT_ID")
    private Long department_id;
    @Column(name = "CATEGORY_ID")
    private Long category_id;
}
