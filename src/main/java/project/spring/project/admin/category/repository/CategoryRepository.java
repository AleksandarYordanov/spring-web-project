package project.spring.project.admin.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.spring.project.admin.category.model.CategoryDTO;
import project.spring.project.admin.category.model.CategoryEntity;
import project.spring.project.admin.department.model.DepartmentDTO;
import project.spring.project.admin.department.model.DepartmentEntity;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
