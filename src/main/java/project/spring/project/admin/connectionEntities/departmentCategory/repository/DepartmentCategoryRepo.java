package project.spring.project.admin.connectionEntities.departmentCategory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.spring.project.admin.connectionEntities.departmentCategory.model.DepartmentCategoryEntity;
import project.spring.project.admin.connectionEntities.departmentCategory.model.DepartmentCategoryPK;

@Repository
public interface DepartmentCategoryRepo extends JpaRepository<DepartmentCategoryEntity, DepartmentCategoryPK> {

}
