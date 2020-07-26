package project.spring.project.admin.connectionEntities.categoryType.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.spring.project.admin.connectionEntities.categoryType.model.CategoryTypeEntity;
import project.spring.project.admin.connectionEntities.categoryType.model.CategoryTypePK;

@Repository
public interface CategoryTypeRepo extends JpaRepository<CategoryTypeEntity, CategoryTypePK> {

}
