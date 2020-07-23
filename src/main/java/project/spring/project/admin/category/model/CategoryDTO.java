package project.spring.project.admin.category.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import project.spring.project.admin.connectionEntities.departmentCategory.DepartmentCategoryDTO;
import project.spring.project.admin.connectionEntities.departmentCategory.DepartmentCategoryEntity;
import project.spring.project.admin.department.model.DepartmentDTO;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDTO {

    private Long id;

    private String name;

    private boolean active;

    private Timestamp addedDate;

    private Set<DepartmentDTO> departments = new HashSet<DepartmentDTO>();




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

    public Set<DepartmentDTO> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentDTO> departments) {
        this.departments = departments;
    }
}
