package project.spring.project.admin.department.service;

import project.spring.project.admin.department.model.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> getAll();
    void create(DepartmentDTO departmentDTO);
}
