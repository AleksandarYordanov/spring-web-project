package project.spring.project.admin.connectionEntities.typeProduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.spring.project.admin.connectionEntities.typeProduct.model.TypeProductEntity;
import project.spring.project.admin.connectionEntities.typeProduct.model.TypeProductPK;

@Repository
public interface TypeProductRepo extends JpaRepository<TypeProductEntity, TypeProductPK> {

}
