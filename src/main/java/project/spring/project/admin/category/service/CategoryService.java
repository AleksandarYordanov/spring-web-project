package project.spring.project.admin.category.service;

import project.spring.project.admin.category.model.CategoryDTO;
import project.spring.project.admin.category.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAll();
    void create(CategoryDTO categoryDTO);

    void create(CategoryDTO category, List<Long> typesIds);

    CategoryDTO getById(Long id);

    void deleteById(Long id);

    void update(CategoryDTO category, List<Long> typesIds);
}
