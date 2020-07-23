package project.spring.project.admin.department.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import project.spring.project.admin.category.model.CategoryDTO;
import project.spring.project.admin.connectionEntities.departmentCategory.DepartmentCategoryDTO;
import project.spring.project.admin.connectionEntities.departmentCategory.DepartmentCategoryEntity;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentDTO {


    private Long id;
    @NotBlank
    private String name;
    private String icon;
    private boolean active;
    private Timestamp addedDate;
    private Integer position;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    private Set<CategoryDTO> categoryDTOS = new HashSet<CategoryDTO>();

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Set<CategoryDTO> getCategoryDTOS() {
        return categoryDTOS;
    }

    public void setCategoryDTOS(Set<CategoryDTO> categoryDTOS) {
        this.categoryDTOS = categoryDTOS;
    }
}
