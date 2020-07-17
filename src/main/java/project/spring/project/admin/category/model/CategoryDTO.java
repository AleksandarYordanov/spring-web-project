package project.spring.project.admin.category.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import project.spring.project.admin.connectionEntities.departmentCategory.DepartmentCategory;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    private Set<DepartmentCategory> departments = new HashSet<DepartmentCategory>();
}
