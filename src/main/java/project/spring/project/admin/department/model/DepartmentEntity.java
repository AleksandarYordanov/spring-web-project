package project.spring.project.admin.department.model;

import lombok.Data;
import project.spring.project.admin.connectionEntities.departmentCategory.DepartmentCategory;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="departments")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String icon;

    @Column
    private boolean active;

    @Column
    private Timestamp addedDate;

    @OneToMany(mappedBy = "categoryEntity")
    private Set<DepartmentCategory> departmentCategories = new HashSet<DepartmentCategory>();

}
