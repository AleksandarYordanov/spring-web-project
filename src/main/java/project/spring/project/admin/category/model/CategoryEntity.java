package project.spring.project.admin.category.model;


import lombok.Data;
import project.spring.project.admin.connectionEntities.departmentCategory.DepartmentCategory;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;


    @Column
    private boolean active;

    @Column
    private Timestamp addedDate;

    @OneToMany(mappedBy = "departmentEntity")
    private Set<DepartmentCategory> departments = new HashSet<DepartmentCategory>();
}
