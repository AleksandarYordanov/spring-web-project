package project.spring.project.admin.category.service;

import org.springframework.stereotype.Service;
import project.spring.project.admin.category.model.CategoryDTO;
import project.spring.project.admin.category.model.CategoryEntity;
import project.spring.project.admin.category.model.CategoryMapper;
import project.spring.project.admin.category.repository.CategoryRepository;

import project.spring.project.admin.connectionEntities.categoryType.model.CategoryTypeDTO;
import project.spring.project.admin.connectionEntities.categoryType.model.CategoryTypePK;
import project.spring.project.admin.connectionEntities.categoryType.service.CategoryTypeService;
import project.spring.project.admin.connectionEntities.typeProduct.model.TypeProductPK;

import project.spring.project.admin.department.model.DepartmentDTO;
import project.spring.project.admin.department.model.DepartmentEntity;
import project.spring.project.admin.department.model.DepartmentMapper;
import project.spring.project.admin.type.model.TypeDTO;
import project.spring.project.admin.type.model.TypeEntity;
import project.spring.project.admin.type.model.TypeMapper;

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
    public List<CategoryDTO> getAll() {
        return categoryRepository.
                findAll().
                stream().
                map(CategoryMapper.INSTANCE::mapCategoryEntityToDto).
                collect(Collectors.toList());
    }

    @Override
    public void create(CategoryDTO categoryDTO) {

        CategoryEntity categoryEntity= CategoryMapper.INSTANCE.mapCategoryDtoToEntity(categoryDTO);
        categoryRepository.save(categoryEntity);
    }

    @Override
    public void create(CategoryDTO category, List<Long> typesIds) {
        CategoryEntity categoryEntity= CategoryMapper.INSTANCE.mapCategoryDtoToEntity(category);
        categoryEntity = categoryRepository.save(categoryEntity);
        CategoryDTO categoryDTO = CategoryMapper.INSTANCE.mapCategoryEntityToDto(categoryEntity);
        categoryDTO =  setTypesAndReturnDTO(typesIds, categoryDTO);
    }

    @Override
    public CategoryDTO getById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.getOne(id);
        CategoryDTO categoryDTO = CategoryMapper.INSTANCE.mapCategoryEntityToDto(categoryEntity);

        return categoryDTO;
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void update(CategoryDTO category, List<Long> typesIds) {
        category =    setTypesAndReturnDTO(typesIds, category);
        CategoryEntity categoryEntity= CategoryMapper.INSTANCE.mapCategoryDtoToEntity(category);
        categoryRepository.save(categoryEntity);
    }


    private CategoryDTO setTypesAndReturnDTO(List<Long> productsIds, CategoryDTO categoryDTO) {
        if (categoryDTO.getTypes() == null){
            return   setTypesEntityNoTypes(productsIds,categoryDTO);
        }else {
            return   setTypesEntityWithTypes(productsIds,categoryDTO);
        }

    }

    private CategoryDTO setTypesEntityWithTypes(List<Long> typesIds, CategoryDTO categoryDTO) {
        List<TypeDTO> currentTypes = categoryDTO.getTypes();
        List<Long> currentTypesIds = new ArrayList<>();
        currentTypes.forEach(p -> currentTypesIds.add(p.getId()));
        //ids to be removed;
        currentTypesIds.removeAll(typesIds);

        currentTypesIds.forEach(id ->{
            CategoryTypePK categoryTypePK = new CategoryTypePK();
            categoryTypePK.setTypeId(id);
            categoryTypePK.setCategoryId(categoryDTO.getId());
            categoryTypeService.deleteById(categoryTypePK);
        });

        return   setTypesEntityNoTypes(typesIds,categoryDTO);
    }

    private CategoryDTO setTypesEntityNoTypes(List<Long> typesIds, CategoryDTO categoryDTO) {

        CategoryTypeDTO categoryTypeDTO = new CategoryTypeDTO();
        CategoryTypePK categoryTypePK = new CategoryTypePK();

        for (int i = 0; i < typesIds.size(); i++) {
            Long id = typesIds.get(i);
            categoryTypePK.setCategoryId(categoryDTO.getId());
            categoryTypePK.setTypeId(id);
            categoryTypeDTO.setId(categoryTypePK);
            categoryTypeDTO.setPosition(i);
            categoryTypeService.create(categoryTypeDTO);
        }
        return getById(categoryDTO.getId());
    }

}
