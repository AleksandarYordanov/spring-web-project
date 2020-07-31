package project.spring.project.eCommerce.order.connectionEntity.orderProduct.service;

import org.springframework.stereotype.Service;
import project.spring.project.eCommerce.order.connectionEntity.orderProduct.model.OrderProductDTO;
import project.spring.project.eCommerce.order.connectionEntity.orderProduct.model.OrderProductEntity;
import project.spring.project.eCommerce.order.connectionEntity.orderProduct.model.OrderProductMapper;
import project.spring.project.eCommerce.order.connectionEntity.orderProduct.model.OrderProductPK;
import project.spring.project.eCommerce.order.connectionEntity.orderProduct.repository.OrderProductRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderProductServiceImpl implements OrderProductService {
    private final OrderProductRepo orderProductRepo;

    public OrderProductServiceImpl(OrderProductRepo orderProductRepo) {
        this.orderProductRepo = orderProductRepo;
    }


    @Override
    public List<OrderProductDTO> getAll() {
        return orderProductRepo.
                findAll().
                stream().
                map(OrderProductMapper.INSTANCE::mapTypeProductEntityToDto).
                collect(Collectors.toList());
    }

    @Override
    public void create(OrderProductDTO orderProductDto) {
        OrderProductEntity departmentCategoryEntity = OrderProductMapper.INSTANCE.mapTypeProductDtoToEntity(orderProductDto);
        orderProductRepo.save(departmentCategoryEntity);
    }

    @Override
    public void deleteById(OrderProductPK departmentCategoryPK) {
        orderProductRepo.deleteById(departmentCategoryPK);
    }


    @Override
    public OrderProductDTO getById(OrderProductPK id) {
        OrderProductEntity departmentCategoryEntity = orderProductRepo.getOne(id);
        return OrderProductMapper.INSTANCE.mapTypeProductEntityToDto(departmentCategoryEntity);


    }

    @Override
    public void update(OrderProductDTO orderProductDto) {
        OrderProductEntity departmentCategoryEntity = OrderProductMapper.INSTANCE.mapTypeProductDtoToEntity(orderProductDto);
        orderProductRepo.save(departmentCategoryEntity);
    }
}
