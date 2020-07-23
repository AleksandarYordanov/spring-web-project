package project.spring.project.admin.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.spring.project.admin.product.model.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
