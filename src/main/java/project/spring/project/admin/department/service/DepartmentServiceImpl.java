package project.spring.project.admin.department.service;


import org.springframework.stereotype.Service;
import project.spring.project.admin.connectionEntities.departmentCategory.model.DepartmentCategoryDTO;
import project.spring.project.admin.connectionEntities.departmentCategory.model.DepartmentCategoryPK;
import project.spring.project.admin.connectionEntities.departmentCategory.service.DepartmentCategoryService;
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
    private final DepartmentCategoryService departmentCategoryService;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentCategoryService departmentCategoryService) {
        this.departmentRepository = departmentRepository;
              this.departmentCategoryService = departmentCategoryService;
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

    @Override
    public void create(DepartmentDTO department, List<Long> categoryIds) {

        if (department.getPosition() == null){
        department.setPosition( getAll().size()+1);
        }else {
            updatePosition(department.getPosition());
        }
        DepartmentEntity departmentEntity= DepartmentMapper.INSTANCE.mapDepartmentDtoToEntity(department);
        departmentEntity =  departmentRepository.save(departmentEntity);
        DepartmentDTO departmentDTO = DepartmentMapper.INSTANCE.mapDepartmentEntityToDto(departmentEntity);
        setCategories(categoryIds, departmentDTO);
    }

    private void setCategories(List<Long> categoryIds, DepartmentDTO departmentDTO) {

        DepartmentCategoryDTO departmentCategoryDTO = new DepartmentCategoryDTO();
        DepartmentCategoryPK departmentCategoryPK = new DepartmentCategoryPK();

        for (int i = 0; i < categoryIds.size(); i++) {
            Long id = categoryIds.get(i);
            departmentCategoryPK.setDepartmentId(departmentDTO.getId());
            departmentCategoryPK.setCategoryId(id);
            departmentCategoryDTO.setId(departmentCategoryPK);
            departmentCategoryDTO.setPosition(i);
            departmentCategoryService.create(departmentCategoryDTO);
        }
    }

//    private void removeAllCategoriesThatAreNotUsed(List<Long> categoryIds, DepartmentDTO departmentDTO){
//        if (departmentDTO.getCategories() == null){
//            return;
//        }
//        List<CategoryDTO> categoryDTOS = departmentDTO.getCategories();
//        List<Long> categoriesToBeRemoved = new ArrayList<>();
//        categoryDTOS.forEach(c->categoriesToBeRemoved.add(c.getId()));
//        categoriesToBeRemoved.removeAll(categoryIds);
//        categoriesToBeRemoved.forEach(cId ->{
//            DepartmentCategoryPK departmentCategoryPK = new DepartmentCategoryPK();
//            departmentCategoryPK.setCategoryId(cId);
//            departmentCategoryPK.setDepartmentId(departmentDTO.getId());
//            departmentCategoryService.deleteById(departmentCategoryPK);
//        });
//
//    }

    @Override
    public DepartmentDTO getById(Long id) {
        DepartmentEntity departmentEntity = departmentRepository.getOne(id);
        System.out.println();
        System.out.println("aaaaa");
        DepartmentDTO departmentDTO = DepartmentMapper.INSTANCE.mapDepartmentEntityToDto(departmentEntity);
        System.out.println(departmentDTO);
        return departmentDTO;
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public void update(DepartmentDTO department, List<Long> categoryIds) {
        if (department.getPosition() == null){
            department.setPosition( getAll().size()+1);
        }else {
            updatePosition(department.getPosition());
        }
        DepartmentEntity departmentEntity= DepartmentMapper.INSTANCE.mapDepartmentDtoToEntity(department);
         departmentRepository.save(departmentEntity);
        setCategories(categoryIds, department);

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
