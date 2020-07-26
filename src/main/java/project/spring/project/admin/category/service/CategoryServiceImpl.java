package project.spring.project.admin.category.service;

import org.springframework.stereotype.Service;
import project.spring.project.admin.category.model.*;
import project.spring.project.admin.category.repository.CategoryRepository;

import project.spring.project.admin.connectionEntities.categoryType.model.CategoryTypeDTO;
import project.spring.project.admin.connectionEntities.categoryType.model.CategoryTypePK;
import project.spring.project.admin.connectionEntities.categoryType.service.CategoryTypeService;

import project.spring.project.admin.type.model.TypeDTO;
import project.spring.project.admin.type.model.TypeEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryTypeService categoryTypeService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryTypeService categoryTypeService) {
        this.categoryRepository = categoryRepository;
        this.categoryTypeService = categoryTypeService;
    }

    @Override
    public List<CategoryChildDTO> getAll() {
        return categoryRepository.
                findAll().
                stream().
                map(CategoryMapper.INSTANCE::mapCategoryEntityToChildDto).
                collect(Collectors.toList());
    }

    @Override
    public void create(CategorySelfDTO categorySelfDTO) {

        CategoryEntity categoryEntity= CategoryMapper.INSTANCE.mapCategorySelfDtoToEntity(categorySelfDTO);
        categoryRepository.save(categoryEntity);
    }

    @Override
    public void create(CategorySelfDTO categorySelfDTO, List<Long> typesIds) {
        CategoryEntity categoryEntity= CategoryMapper.INSTANCE.mapCategorySelfDtoToEntity(categorySelfDTO);
        categoryEntity = categoryRepository.save(categoryEntity);

        setTypesAndReturnEntity(typesIds, categoryEntity);
    }

    @Override
    public CategoryChildDTO getById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.getOne(id);
        CategoryChildDTO categoryChildDTO = CategoryMapper.INSTANCE.mapCategoryEntityToChildDto(categoryEntity);

        return categoryChildDTO;
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void update(CategorySelfDTO categorySelfDTO, List<Long> typesIds) {
        CategoryEntity categoryEntity = categoryRepository.getOne(categorySelfDTO.getId());
        categoryEntity.setName(categorySelfDTO.getName());
        categoryEntity.setActive(categorySelfDTO.isActive());
        categoryRepository.save(categoryEntity);
       setTypesAndReturnEntity(typesIds, categoryEntity);


    }


    private CategoryEntity setTypesAndReturnEntity(List<Long> productsIds, CategoryEntity categoryEntity) {
        if (categoryEntity.getTypes() == null){
            return   setTypesEntityNoTypes(productsIds,categoryEntity);
        }else {
            return   setTypesEntityWithTypes(productsIds,categoryEntity);
        }

    }

    private CategoryEntity setTypesEntityWithTypes(List<Long> typesIds, CategoryEntity categoryEntity) {
        List<TypeEntity> currentTypes = categoryEntity.getTypes();
        List<Long> currentTypesIds = new ArrayList<>();
        currentTypes.forEach(p -> currentTypesIds.add(p.getId()));
        //ids to be removed;
        currentTypesIds.removeAll(typesIds);

        currentTypesIds.forEach(id ->{
            CategoryTypePK categoryTypePK = new CategoryTypePK();
            categoryTypePK.setTypeId(id);
            categoryTypePK.setCategoryId(categoryEntity.getId());
            categoryTypeService.deleteById(categoryTypePK);
        });

        return   setTypesEntityNoTypes(typesIds,categoryEntity);
    }

    private CategoryEntity setTypesEntityNoTypes(List<Long> typesIds, CategoryEntity categoryEntity) {

        CategoryTypeDTO categoryTypeDTO = new CategoryTypeDTO();
        CategoryTypePK categoryTypePK = new CategoryTypePK();

        for (int i = 0; i < typesIds.size(); i++) {
            Long id = typesIds.get(i);
            categoryTypePK.setCategoryId(categoryEntity.getId());
            categoryTypePK.setTypeId(id);
            categoryTypeDTO.setId(categoryTypePK);
            categoryTypeDTO.setPosition(i);
            categoryTypeService.create(categoryTypeDTO);
        }
        return categoryRepository.getOne(categoryEntity.getId());
    }

}
