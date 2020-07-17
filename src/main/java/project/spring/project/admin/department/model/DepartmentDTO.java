package project.spring.project.admin.department.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import project.spring.project.admin.connectionEntities.departmentCategory.DepartmentCategory;

import javax.persistence.Column;
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

    private Set<DepartmentCategory> departmentCategories = new HashSet<DepartmentCategory>();
}
