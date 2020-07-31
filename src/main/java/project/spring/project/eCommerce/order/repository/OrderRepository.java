package project.spring.project.eCommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.spring.project.eCommerce.order.model.OrderEntity;
import project.spring.project.user.model.UserEntity;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findOneByUserId(UserEntity userEntity);
    Optional<OrderEntity> findOneBySession(String session);
}
