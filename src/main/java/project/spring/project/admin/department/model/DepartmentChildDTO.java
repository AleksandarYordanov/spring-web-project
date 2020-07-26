package project.spring.project.admin.department.model;

import project.spring.project.admin.category.model.CategoryDTO;
import project.spring.project.admin.category.model.CategorySelfDTO;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;

public class DepartmentChildDTO {

    private Long id;

    private String name;
    private String icon;
    private boolean active;
    private Timestamp addedDate;
    private Integer position;
    private List<CategorySelfDTO> categories;

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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public List<CategorySelfDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategorySelfDTO> categories) {
        this.categories = categories;
    }
}
