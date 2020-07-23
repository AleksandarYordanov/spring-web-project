package project.spring.project.admin.department.service;


import org.springframework.stereotype.Service;
import project.spring.project.admin.category.model.CategoryEntity;
import project.spring.project.admin.category.repository.CategoryRepository;
import project.spring.project.admin.connectionEntities.departmentCategory.DepartmentCategoryDTO;
import project.spring.project.admin.connectionEntities.departmentCategory.DepartmentCategoryEntity;
import project.spring.project.admin.connectionEntities.departmentCategory.DepartmentCategoryPK;
import project.spring.project.admin.connectionEntities.departmentCategory.DepartmentCategoryRepo;
import project.spring.project.admin.department.model.DepartmentDTO;
import project.spring.project.admin.department.model.DepartmentEntity;
import project.spring.project.admin.department.model.DepartmentMapper;
import project.spring.project.admin.department.repository.DepartmentRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;
    private final CategoryRepository categoryRepository;
    private final DepartmentCategoryRepo departmentCategoryRepo;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, CategoryRepository categoryRepository, DepartmentCategoryRepo departmentCategoryRepo) {
        this.departmentRepository = departmentRepository;
        this.categoryRepository = categoryRepository;
        this.departmentCategoryRepo = departmentCategoryRepo;
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
       CategoryEntity categoryEntity = categoryRepository.findAll().get(0);

        DepartmentEntity departmentEntity= DepartmentMapper.INSTANCE.mapDepartmentDtoToEntity(departmentDTO);


        departmentEntity =  departmentRepository.save(departmentEntity);



        DepartmentCategoryEntity departmentCategoryEntity = new DepartmentCategoryEntity();

        DepartmentCategoryPK departmentCategoryPK = new DepartmentCategoryPK();
        departmentCategoryPK.setDepartmentId(departmentEntity.getId());
        departmentCategoryPK.setCategoryId(categoryEntity.getId());

        departmentCategoryEntity.setId(departmentCategoryPK);
        departmentCategoryEntity.setPosition(2);
        departmentCategoryRepo.save(departmentCategoryEntity);



    }

    @Override
    public void create(DepartmentDTO department, List<Long> categoryIds) {

        if (department.getPosition() == null){
        department.setPosition( getAll().size()+1);
        }else {
            updatePosition(department.getPosition());
        }
        DepartmentEntity departmentEntity= DepartmentMapper.INSTANCE.mapDepartmentDtoToEntity(department);
        departmentEntity =  departmentRepository.save(departmentEntity);
        DepartmentCategoryEntity departmentCategoryEntity = new DepartmentCategoryEntity();
        DepartmentCategoryPK departmentCategoryPK = new DepartmentCategoryPK();
        DepartmentEntity finalDepartmentEntity = departmentEntity;
        for (int i = 0; i < categoryIds.size(); i++) {
            Long id = categoryIds.get(i);
            departmentCategoryPK.setDepartmentId(finalDepartmentEntity.getId());
            departmentCategoryPK.setCategoryId(id);

            departmentCategoryEntity.setId(departmentCategoryPK);
            departmentCategoryEntity.setPosition(i);

            departmentCategoryRepo.save(departmentCategoryEntity);
        }
    }


    private void updatePosition(int position){
        List<DepartmentDTO> allDTO = getAll();
       allDTO.sort(Comparator.comparing(DepartmentDTO::getPosition));
        for (int i = position-1; i < allDTO.size(); i++) {
            DepartmentDTO departmentDTO =   allDTO.get(i);
            departmentDTO.setPosition(i+2);
            DepartmentEntity departmentEntity= DepartmentMapper.INSTANCE.mapDepartmentDtoToEntity(departmentDTO);
            departmentRepository.save(departmentEntity);
        }
    }


}
