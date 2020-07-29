package project.spring.project.admin.homePage.promoBanners.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.spring.project.admin.homePage.promoBanners.model.PromoBannerEntity;

import java.util.List;

@Repository
public interface PromoBannerRepository extends JpaRepository<PromoBannerEntity, Long> {

    List<PromoBannerEntity> findAllByActiveOrderByPosition(boolean active);
}
