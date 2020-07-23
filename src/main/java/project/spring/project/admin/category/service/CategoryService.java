package project.spring.project.admin.category.service;

import project.spring.project.admin.category.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAll();
    void create(CategoryDTO categoryDTO);
    CategoryDTO getById(Long id);
}
