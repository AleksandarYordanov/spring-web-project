package project.spring.project.admin.department.service;

import project.spring.project.admin.department.model.DepartmentChildDTO;
import project.spring.project.admin.department.model.DepartmentDTO;
import project.spring.project.admin.department.model.DepartmentSelfDTO;

import java.util.List;

public interface DepartmentService {
    List<DepartmentChildDTO> getAll();

    List<DepartmentDTO> getAllWitchCategoryChildDTO();

    void create(DepartmentSelfDTO departmentSelfDTO);

    void create(DepartmentSelfDTO departmentSelfDTO, List<Long> categoryIds);

    DepartmentChildDTO getById(Long id);

    void deleteById(Long id);

    void update(DepartmentSelfDTO departmentSelfDTO, List<Long> categoryIds);
}
