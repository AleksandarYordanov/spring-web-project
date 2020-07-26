package project.spring.project.admin.connectionEntities.categoryType.service;

import org.springframework.stereotype.Service;
import project.spring.project.admin.connectionEntities.categoryType.model.CategoryTypeDTO;
import project.spring.project.admin.connectionEntities.categoryType.model.CategoryTypeEntity;
import project.spring.project.admin.connectionEntities.categoryType.model.CategoryTypeMapper;
import project.spring.project.admin.connectionEntities.categoryType.model.CategoryTypePK;
import project.spring.project.admin.connectionEntities.categoryType.repository.CategoryTypeRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryTypeServiceImpl implements CategoryTypeService {
    private final CategoryTypeRepo categoryTypeRepo;

    public CategoryTypeServiceImpl(CategoryTypeRepo categoryTypeRepo) {
        this.categoryTypeRepo = categoryTypeRepo;
    }


    @Override
    public List<CategoryTypeDTO> getAll() {
        return categoryTypeRepo.
                findAll().
                stream().
                map(CategoryTypeMapper.INSTANCE::mapDepartmentCategoryEntityToDto).
                collect(Collectors.toList());
    }

    @Override
    public void create(CategoryTypeDTO categoryTypeDTO) {
        CategoryTypeEntity categoryTypeEntity = CategoryTypeMapper.INSTANCE.mapDepartmentCategoryDtoToEntity(categoryTypeDTO);
        categoryTypeRepo.save(categoryTypeEntity);
    }

    @Override
    public void deleteById(CategoryTypePK categoryTypePK) {
        categoryTypeRepo.deleteById(categoryTypePK);
    }


    @Override
    public CategoryTypeDTO getById(CategoryTypePK id) {
        CategoryTypeEntity categoryTypeEntity = categoryTypeRepo.getOne(id);
        return CategoryTypeMapper.INSTANCE.mapDepartmentCategoryEntityToDto(categoryTypeEntity);


    }

    @Override
    public void update(CategoryTypeDTO categoryTypeDTO) {
        CategoryTypeEntity categoryTypeEntity = CategoryTypeMapper.INSTANCE.mapDepartmentCategoryDtoToEntity(categoryTypeDTO);
        categoryTypeRepo.save(categoryTypeEntity);
    }
}
