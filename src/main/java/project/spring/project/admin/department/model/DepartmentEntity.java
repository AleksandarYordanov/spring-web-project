package project.spring.project.admin.department.model;

import lombok.Data;
import project.spring.project.admin.category.model.CategoryEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

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

    @Column
    private Integer position;

    @ManyToMany
    @JoinTable( name = "departments_categories",
            joinColumns = @JoinColumn(referencedColumnName = "id", name = "departmentId"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id", name = "categoryId"))
    private List<CategoryEntity> categories;


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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }
}
