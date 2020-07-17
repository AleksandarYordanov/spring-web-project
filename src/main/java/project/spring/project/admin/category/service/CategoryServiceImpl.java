package project.spring.project.admin.category.service;

import org.springframework.stereotype.Service;
import project.spring.project.admin.category.model.CategoryDTO;
import project.spring.project.admin.category.model.CategoryEntity;
import project.spring.project.admin.category.model.CategoryMapper;
import project.spring.project.admin.category.repository.CategoryRepository;
import project.spring.project.admin.department.model.DepartmentMapper;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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
        CategoryEntity categoryEntity = CategoryMapper.INSTANCE.mapDepartmentDtoToEntity(categoryDTO);
        categoryRepository.save(categoryEntity);
    }
}
