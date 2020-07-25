package project.spring.project.admin.connectionEntities.departmentCategory.service;

import org.springframework.stereotype.Service;
import project.spring.project.admin.connectionEntities.departmentCategory.model.DepartmentCategoryDTO;
import project.spring.project.admin.connectionEntities.departmentCategory.model.DepartmentCategoryEntity;
import project.spring.project.admin.connectionEntities.departmentCategory.model.DepartmentCategoryMapper;
import project.spring.project.admin.connectionEntities.departmentCategory.model.DepartmentCategoryPK;
import project.spring.project.admin.connectionEntities.departmentCategory.repository.DepartmentCategoryRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentCategoryServiceImpl implements DepartmentCategoryService {
    private final DepartmentCategoryRepo departmentCategoryRepo;

    public DepartmentCategoryServiceImpl(DepartmentCategoryRepo departmentCategoryRepo) {
        this.departmentCategoryRepo = departmentCategoryRepo;
    }


    @Override
    public List<DepartmentCategoryDTO> getAll() {
        return departmentCategoryRepo.
                findAll().
                stream().
                map(DepartmentCategoryMapper.INSTANCE::mapDepartmentCategoryEntityToDto).
                collect(Collectors.toList());
    }

    @Override
    public void create(DepartmentCategoryDTO departmentCategoryDTO) {
        DepartmentCategoryEntity departmentCategoryEntity = DepartmentCategoryMapper.INSTANCE.mapDepartmentCategoryDtoToEntity(departmentCategoryDTO);
        departmentCategoryRepo.save(departmentCategoryEntity);
    }

    @Override
    public void deleteById(DepartmentCategoryPK departmentCategoryPK) {
        departmentCategoryRepo.deleteById(departmentCategoryPK);
    }


    @Override
    public DepartmentCategoryDTO getById(DepartmentCategoryPK id) {
        DepartmentCategoryEntity departmentCategoryEntity = departmentCategoryRepo.getOne(id);
        return DepartmentCategoryMapper.INSTANCE.mapDepartmentCategoryEntityToDto(departmentCategoryEntity);


    }

    @Override
    public void update(DepartmentCategoryDTO departmentCategoryDTO) {
        DepartmentCategoryEntity departmentCategoryEntity = DepartmentCategoryMapper.INSTANCE.mapDepartmentCategoryDtoToEntity(departmentCategoryDTO);
        departmentCategoryRepo.save(departmentCategoryEntity);
    }
}
