package project.spring.project.admin.homePage.topSlider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.spring.project.admin.category.model.CategoryEntity;
import project.spring.project.admin.homePage.topSlider.model.TopSliderEntity;

import java.util.List;

@Repository
public interface TopSliderRepository extends JpaRepository<TopSliderEntity, Long> {

    List<TopSliderEntity> findAllByActiveOrderByPosition(boolean active);
}
