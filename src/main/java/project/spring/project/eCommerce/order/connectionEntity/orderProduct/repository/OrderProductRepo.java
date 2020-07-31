package project.spring.project.eCommerce.order.connectionEntity.orderProduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.spring.project.eCommerce.order.connectionEntity.orderProduct.model.OrderProductEntity;
import project.spring.project.eCommerce.order.connectionEntity.orderProduct.model.OrderProductPK;

@Repository
public interface OrderProductRepo extends JpaRepository<OrderProductEntity, OrderProductPK> {

}
