package project.spring.project.admin.department.service;


import org.springframework.stereotype.Service;
import project.spring.project.admin.department.model.DepartmentDTO;
import project.spring.project.admin.department.model.DepartmentEntity;
import project.spring.project.admin.department.model.DepartmentMapper;
import project.spring.project.admin.department.repository.DepartmentRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    @Override
    public List<DepartmentDTO> getAll() {
        return departmentRepository.
                findAll().
                stream().
                map(DepartmentMapper.INSTANCE::mapDepartmentEntityToDto).
                collect(Collectors.toList());
    }

    @Override
    public void create(DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity= DepartmentMapper.INSTANCE.mapDepartmentDtoToEntity(departmentDTO);
        departmentRepository.save(departmentEntity);

    }
}
