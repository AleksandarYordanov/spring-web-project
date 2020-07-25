package project.spring.project.admin.type.service;


import org.springframework.stereotype.Service;
import project.spring.project.admin.connectionEntities.departmentCategory.model.DepartmentCategoryDTO;
import project.spring.project.admin.connectionEntities.departmentCategory.model.DepartmentCategoryPK;
import project.spring.project.admin.connectionEntities.typeProduct.model.TypeProductDTO;
import project.spring.project.admin.connectionEntities.typeProduct.model.TypeProductPK;
import project.spring.project.admin.connectionEntities.typeProduct.service.TypeProductService;
import project.spring.project.admin.product.model.ProductDTO;
import project.spring.project.admin.product.service.ProductService;
import project.spring.project.admin.type.model.TypeDTO;
import project.spring.project.admin.type.model.TypeEntity;
import project.spring.project.admin.type.model.TypeMapper;
import project.spring.project.admin.type.repository.TypeRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;
    private final ProductService productService;
    private final TypeProductService typeProductService;

    public TypeServiceImpl(TypeRepository typeRepository, ProductService productService, TypeProductService typeProductService) {
        this.typeRepository = typeRepository;
        this.productService = productService;
        this.typeProductService = typeProductService;
    }


    @Override
    public List<TypeDTO> getAll() {
        return typeRepository.
                findAll().
                stream().
                map(TypeMapper.INSTANCE::mapTypeEntityToDto).
                collect(Collectors.toList());
    }

    @Override
    public void create(TypeDTO typeDTO) {

        TypeEntity typeEntity= TypeMapper.INSTANCE.mapTypeDtoToEntity(typeDTO);
       typeRepository.save(typeEntity);
    }

    @Override
    public void create(TypeDTO type, List<Long> productIds) {



        TypeEntity typeEntity= TypeMapper.INSTANCE.mapTypeDtoToEntity(type);
        typeEntity = typeRepository.save(typeEntity);
        TypeDTO typeDTO = TypeMapper.INSTANCE.mapTypeEntityToDto(typeEntity);
        typeDTO =  setProductsAndReturnDTO(productIds, typeDTO);
    }

    private TypeDTO setProductsAndReturnDTO(List<Long> productsIds, TypeDTO typeDTO) {
        if (typeDTO.getProducts() == null){
          return   setProductsEntityNoProducts(productsIds,typeDTO);
        }else {
          return   setProductsEntityWithProducts(productsIds,typeDTO);
        }

    }

    private TypeDTO setProductsEntityWithProducts(List<Long> productsIds, TypeDTO typeDTO) {
        List<ProductDTO> currentProducts = typeDTO.getProducts();
        List<Long> currentProductsIds = new ArrayList<>();
        currentProducts.forEach(p -> currentProductsIds.add(p.getId()));
        //ids to be removed;
         currentProductsIds.removeAll(productsIds);

         currentProductsIds.forEach(id ->{
             TypeProductPK typeProductPK = new TypeProductPK();
             typeProductPK.setProductId(id);
             typeProductPK.setTypeId(typeDTO.getId());
             typeProductService.deleteById(typeProductPK);
         });

       return   setProductsEntityNoProducts(productsIds,typeDTO);
    }

    private TypeDTO setProductsEntityNoProducts(List<Long> productsIds, TypeDTO typeDTO) {

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
        return getById(typeDTO.getId());
    }



    @Override
    public TypeDTO getById(Long id) {
        TypeEntity typeEntity = typeRepository.getOne(id);

        TypeDTO typeDTO = TypeMapper.INSTANCE.mapTypeEntityToDto(typeEntity);
        System.out.println(typeDTO);
        return typeDTO;
    }

    @Override
    public void deleteById(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    public void update(TypeDTO typeDTO, List<Long> categoryIds) {

         typeDTO =    setProductsAndReturnDTO(categoryIds, typeDTO);

        TypeEntity typeEntity= TypeMapper.INSTANCE.mapTypeDtoToEntity(typeDTO);

        typeRepository.save(typeEntity);
    }





}
