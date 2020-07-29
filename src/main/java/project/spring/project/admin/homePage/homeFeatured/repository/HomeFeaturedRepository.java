package project.spring.project.admin.homePage.homeFeatured.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.spring.project.admin.homePage.homeFeatured.model.HomeFeaturedEntity;

import java.util.List;

@Repository
public interface HomeFeaturedRepository extends JpaRepository<HomeFeaturedEntity, Long> {

    List<HomeFeaturedEntity> findAllByActiveAndMainFalseOrderByPosition(boolean active);
    List<HomeFeaturedEntity> findAllByMain(boolean main);
}
