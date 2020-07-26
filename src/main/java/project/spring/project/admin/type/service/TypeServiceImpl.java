package project.spring.project.admin.type.service;


import org.springframework.stereotype.Service;
import project.spring.project.admin.connectionEntities.typeProduct.model.TypeProductDTO;
import project.spring.project.admin.connectionEntities.typeProduct.model.TypeProductPK;
import project.spring.project.admin.connectionEntities.typeProduct.service.TypeProductService;
import project.spring.project.admin.product.model.ProductEntity;
import project.spring.project.admin.product.service.ProductService;
import project.spring.project.admin.type.model.*;
import project.spring.project.admin.type.repository.TypeRepository;
import project.spring.project.admin.utils.fileUpload.UploadFileService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;

    private final TypeProductService typeProductService;
    private final UploadFileService uploadFileService;

    public TypeServiceImpl(TypeRepository typeRepository, TypeProductService typeProductService, UploadFileService uploadFileService) {
        this.typeRepository = typeRepository;

        this.typeProductService = typeProductService;
        this.uploadFileService = uploadFileService;
    }


    @Override
    public List<TypeChildDTO> getAll() {
        return typeRepository.
                findAll().
                stream().
                map(TypeMapper.INSTANCE::mapTypeEntityToTypeChildDTO).
                collect(Collectors.toList());
    }



    @Override
    public void create(TypeSelfDTO type) {
        TypeEntity typeEntity= TypeMapper.INSTANCE.mapTypeSelfDTOToTypeEntity(type);
        typeRepository.save(typeEntity);
    }

    @Override
    public void create(TypeSelfDTO type, List<Long> productIds) {
        TypeEntity typeEntity= TypeMapper.INSTANCE.mapTypeSelfDTOToTypeEntity(type);
        typeEntity = typeRepository.save(typeEntity);

        setProductsAndReturnEntity(productIds, typeEntity);
    }

    private TypeEntity setProductsAndReturnEntity(List<Long> productsIds, TypeEntity type) {
        if (type.getProducts() == null){
          return   setProductsEntityNoProducts(productsIds,type);
        }else {
          return   setProductsEntityWithProducts(productsIds,type);
        }

    }

    private TypeEntity setProductsEntityWithProducts(List<Long> productsIds, TypeEntity type) {
        List<ProductEntity> currentProducts = type.getProducts();
        List<Long> currentProductsIds = new ArrayList<>();
        currentProducts.forEach(p -> currentProductsIds.add(p.getId()));
        //ids to be removed;
         currentProductsIds.removeAll(productsIds);

         currentProductsIds.forEach(id ->{
             TypeProductPK typeProductPK = new TypeProductPK();
             typeProductPK.setProductId(id);
             typeProductPK.setTypeId(type.getId());
             typeProductService.deleteById(typeProductPK);
         });

       return   setProductsEntityNoProducts(productsIds,type);
    }

    private TypeEntity setProductsEntityNoProducts(List<Long> productsIds, TypeEntity typeDTO) {

        TypeProductDTO typeProductDTO = new TypeProductDTO();
        TypeProductPK typeProductPK = new TypeProductPK();

        for (int i = 0; i < productsIds.size(); i++) {
            Long id = productsIds.get(i);
            typeProductPK.setTypeId(typeDTO.getId());
            typeProductPK.setProductId(id);
            typeProductDTO.setId(typeProductPK);
            typeProductDTO.setPosition(i);
            typeProductService.create(typeProductDTO);
        }
        return  typeRepository.getOne(typeDTO.getId());
    }



    @Override
    public TypeChildDTO getById(Long id) {
        TypeEntity typeEntity = typeRepository.getOne(id);

        TypeChildDTO typeChildDTO = TypeMapper.INSTANCE.mapTypeEntityToTypeChildDTO(typeEntity);
        typeChildDTO.getProducts().forEach(p->{
            p.setPhotos(uploadFileService.getAllPhotosForProduct(p.getId()));
        });

        return typeChildDTO;

    }

    @Override
    public void deleteById(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    public void update(TypeSelfDTO typeSelfDTO, List<Long> categoryIds) {
            TypeEntity typeEntity = typeRepository.getOne(typeSelfDTO.getId());

            typeEntity.setName(typeSelfDTO.getName());
            typeEntity.setActive(typeSelfDTO.isActive());
            typeRepository.save(typeEntity);

            typeEntity  =    setProductsAndReturnEntity(categoryIds, typeEntity);
            typeRepository.save(typeEntity);
    }





}
