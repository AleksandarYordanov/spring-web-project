package project.spring.project.eCommerce.order.connectionEntity.orderProduct.service;

import project.spring.project.eCommerce.order.connectionEntity.orderProduct.model.OrderProductDTO;
import project.spring.project.eCommerce.order.connectionEntity.orderProduct.model.OrderProductPK;

import java.util.List;

public interface OrderProductService {
    List<OrderProductDTO> getAll();
    void create(OrderProductDTO orderProductDTO);

    void deleteById(OrderProductPK orderProductPK);

    OrderProductDTO getById(OrderProductPK id);

    void update(OrderProductDTO orderProductDTO);
}
