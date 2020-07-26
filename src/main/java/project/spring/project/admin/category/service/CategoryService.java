package project.spring.project.admin.category.service;

import project.spring.project.admin.category.model.CategoryChildDTO;
import project.spring.project.admin.category.model.CategoryDTO;
import project.spring.project.admin.category.model.CategorySelfDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryChildDTO> getAll();


    void create(CategorySelfDTO categorySelfDTO);


    void create(CategorySelfDTO categorySelfDTO, List<Long> typesIds);

    CategoryChildDTO getById(Long id);

    void deleteById(Long id);



    void update(CategorySelfDTO categorySelfDTO, List<Long> typesIds);
}
