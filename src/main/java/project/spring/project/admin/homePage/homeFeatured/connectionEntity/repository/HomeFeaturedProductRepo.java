package project.spring.project.admin.homePage.homeFeatured.connectionEntity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.spring.project.admin.homePage.homeFeatured.connectionEntity.model.HomeFeaturedProductEntity;
import project.spring.project.admin.homePage.homeFeatured.connectionEntity.model.HomeFeaturedProductPK;

@Repository
public interface HomeFeaturedProductRepo extends JpaRepository<HomeFeaturedProductEntity, HomeFeaturedProductPK> {

}
