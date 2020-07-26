package project.spring.project.admin.connectionEntities.categoryType.service;

import project.spring.project.admin.connectionEntities.categoryType.model.CategoryTypeDTO;
import project.spring.project.admin.connectionEntities.categoryType.model.CategoryTypePK;

import java.util.List;

public interface CategoryTypeService {
    List<CategoryTypeDTO> getAll();
    void create(CategoryTypeDTO categoryTypeDTO);

    void deleteById(CategoryTypePK categoryTypePK);

    CategoryTypeDTO getById(CategoryTypePK id);

    void update(CategoryTypeDTO categoryTypeDTO);
}
