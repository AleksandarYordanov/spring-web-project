package project.spring.project.admin.category.model;


import lombok.Data;
import project.spring.project.admin.department.model.DepartmentEntity;
import project.spring.project.admin.type.model.TypeEntity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

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

    @ManyToMany
    @JoinTable( name = "departments_categories",
            joinColumns = @JoinColumn(referencedColumnName = "id", name = "categoryId"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id", name = "departmentId"))
    private List<DepartmentEntity> departments;

    @ManyToMany
    @JoinTable( name = "categories_types",
            joinColumns = @JoinColumn(referencedColumnName = "id", name = "categoryId"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id", name = "typeId"))
    private List<TypeEntity> types;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Timestamp getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Timestamp addedDate) {
        this.addedDate = addedDate;
    }

    public List<DepartmentEntity> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentEntity> departments) {
        this.departments = departments;
    }

    public List<TypeEntity> getTypes() {
        return types;
    }

    public void setTypes(List<TypeEntity> types) {
        this.types = types;
    }
}
