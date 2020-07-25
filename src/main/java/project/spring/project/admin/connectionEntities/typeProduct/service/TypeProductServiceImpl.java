package project.spring.project.admin.connectionEntities.typeProduct.service;

import org.springframework.stereotype.Service;
import project.spring.project.admin.connectionEntities.typeProduct.model.TypeProductDTO;
import project.spring.project.admin.connectionEntities.typeProduct.model.TypeProductEntity;
import project.spring.project.admin.connectionEntities.typeProduct.model.TypeProductMapper;
import project.spring.project.admin.connectionEntities.typeProduct.model.TypeProductPK;
import project.spring.project.admin.connectionEntities.typeProduct.repository.TypeProductRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeProductServiceImpl implements TypeProductService {
    private final TypeProductRepo typeProductRepo;

    public TypeProductServiceImpl(TypeProductRepo typeProductRepo) {
        this.typeProductRepo = typeProductRepo;
    }


    @Override
    public List<TypeProductDTO> getAll() {
        return typeProductRepo.
                findAll().
                stream().
                map(TypeProductMapper.INSTANCE::mapTypeProductEntityToDto).
                collect(Collectors.toList());
    }

    @Override
    public void create(TypeProductDTO typeProductDto) {
        TypeProductEntity departmentCategoryEntity = TypeProductMapper.INSTANCE.mapTypeProductDtoToEntity(typeProductDto);
        typeProductRepo.save(departmentCategoryEntity);
    }

    @Override
    public void deleteById(TypeProductPK departmentCategoryPK) {
        typeProductRepo.deleteById(departmentCategoryPK);
    }


    @Override
    public TypeProductDTO getById(TypeProductPK id) {
        TypeProductEntity departmentCategoryEntity = typeProductRepo.getOne(id);
        return TypeProductMapper.INSTANCE.mapTypeProductEntityToDto(departmentCategoryEntity);


    }

    @Override
    public void update(TypeProductDTO typeProductDto) {
        TypeProductEntity departmentCategoryEntity = TypeProductMapper.INSTANCE.mapTypeProductDtoToEntity(typeProductDto);
        typeProductRepo.save(departmentCategoryEntity);
    }
}
