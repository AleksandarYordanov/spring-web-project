package project.spring.project.admin.connectionEntities.departmentCategory.service;

import project.spring.project.admin.connectionEntities.departmentCategory.model.DepartmentCategoryDTO;
import project.spring.project.admin.connectionEntities.departmentCategory.model.DepartmentCategoryPK;

import java.util.List;

public interface DepartmentCategoryService {
    List<DepartmentCategoryDTO> getAll();
    void create(DepartmentCategoryDTO departmentCategoryDTO);

    void deleteById(DepartmentCategoryPK departmentCategoryPK);

    DepartmentCategoryDTO getById(DepartmentCategoryPK id);

    void update(DepartmentCategoryDTO departmentCategoryDTO);
}
