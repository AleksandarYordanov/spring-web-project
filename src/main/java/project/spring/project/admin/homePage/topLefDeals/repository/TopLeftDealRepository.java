package project.spring.project.admin.homePage.topLefDeals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.spring.project.admin.homePage.topLefDeals.model.TopLeftDealEntity;

import java.util.List;

@Repository
public interface TopLeftDealRepository extends JpaRepository<TopLeftDealEntity, Long> {

    List<TopLeftDealEntity> findAllByActiveOrderByPosition(boolean active);
}
