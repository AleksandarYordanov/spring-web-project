package project.spring.project.admin.connectionEntities.typeProduct.service;

import project.spring.project.admin.connectionEntities.typeProduct.model.TypeProductDTO;
import project.spring.project.admin.connectionEntities.typeProduct.model.TypeProductPK;

import java.util.List;

public interface TypeProductService {
    List<TypeProductDTO> getAll();
    void create(TypeProductDTO typeProductDTO);

    void deleteById(TypeProductPK typeProductPK);

    TypeProductDTO getById(TypeProductPK id);

    void update(TypeProductDTO typeProductDTO);
}
