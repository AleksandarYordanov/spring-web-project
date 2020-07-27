package project.spring.project.admin.department.service;


import org.springframework.stereotype.Service;
import project.spring.project.admin.category.model.CategoryEntity;
import project.spring.project.admin.connectionEntities.departmentCategory.model.DepartmentCategoryDTO;
import project.spring.project.admin.connectionEntities.departmentCategory.model.DepartmentCategoryPK;
import project.spring.project.admin.connectionEntities.departmentCategory.service.DepartmentCategoryService;
import project.spring.project.admin.department.model.*;
import project.spring.project.admin.department.repository.DepartmentRepository;

import java.util.ArrayList;
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
    public List<DepartmentChildDTO> getAll() {
        return departmentRepository.
                findAll().
                stream().
                map(DepartmentMapper.INSTANCE::mapDepartmentEntityToChildDto).
                collect(Collectors.toList());
    }

    @Override
    public List<DepartmentDTO> getAllWitchCategoryChildDTO() {
        return departmentRepository.
                findAll().
                stream().
                map(DepartmentMapper.INSTANCE::mapDepartmentEntityToDto).
                collect(Collectors.toList());
    }

    @Override
    public void create(DepartmentSelfDTO departmentSelfDTO) {

        DepartmentEntity departmentEntity= DepartmentMapper.INSTANCE.mapDepartmentSelfDtoToEntity(departmentSelfDTO);
       departmentRepository.save(departmentEntity);
    }

    @Override
    public void create(DepartmentSelfDTO departmentSelfDTO, List<Long> categoryIds) {

        if (departmentSelfDTO.getPosition() == null){
        departmentSelfDTO.setPosition( departmentRepository.findAll().size()+1);
        }else {
            updatePosition(departmentSelfDTO.getPosition());
        }
        DepartmentEntity departmentEntity= DepartmentMapper.INSTANCE.mapDepartmentSelfDtoToEntity(departmentSelfDTO);
        departmentEntity = departmentRepository.save(departmentEntity);

        setCategories(categoryIds, departmentEntity);
    }

    private DepartmentEntity setCategories(List<Long> categoryIds, DepartmentEntity departmentEntity) {

        if (departmentEntity.getCategories() == null){
            return   setCategoriesEntityNoCategories(categoryIds,departmentEntity);
        }else {
            return   setCategoriesEntityWithCategories(categoryIds,departmentEntity);
        }

    }

    private DepartmentEntity setCategoriesEntityWithCategories(List<Long> categoryIds, DepartmentEntity departmentEntity) {
        List<CategoryEntity> currentCategories = departmentEntity.getCategories();
        List<Long> currentCattegoriesIds = new ArrayList<>();
        currentCategories.forEach(p -> currentCattegoriesIds.add(p.getId()));
        //ids to be removed;
        currentCattegoriesIds.removeAll(categoryIds);

        currentCattegoriesIds.forEach(id ->{
            DepartmentCategoryPK departmentCategoryPK = new DepartmentCategoryPK();
            departmentCategoryPK.setCategoryId(id);
            departmentCategoryPK.setDepartmentId(departmentEntity.getId());
            departmentCategoryService.deleteById(departmentCategoryPK);
        });

        return   setCategoriesEntityNoCategories(categoryIds,departmentEntity);
    }

    private DepartmentEntity setCategoriesEntityNoCategories(List<Long> categoryIds, DepartmentEntity departmentEntity) {

        DepartmentCategoryDTO departmentCategoryDTO = new DepartmentCategoryDTO();
        DepartmentCategoryPK departmentCategoryPK = new DepartmentCategoryPK();

        for (int i = 0; i < categoryIds.size(); i++) {
            Long id = categoryIds.get(i);
            departmentCategoryPK.setDepartmentId(departmentEntity.getId());
            departmentCategoryPK.setCategoryId(id);
            departmentCategoryDTO.setId(departmentCategoryPK);
            departmentCategoryDTO.setPosition(i);
            departmentCategoryService.create(departmentCategoryDTO);
        }

        return departmentRepository.getOne(departmentEntity.getId());
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
//            CategoryTypePK departmentCategoryPK = new CategoryTypePK();
//            departmentCategoryPK.setCategoryId(cId);
//            departmentCategoryPK.setDepartmentId(departmentDTO.getId());
//            departmentCategoryService.deleteById(departmentCategoryPK);
//        });
//
//    }

    @Override
    public DepartmentChildDTO getById(Long id) {
        DepartmentEntity departmentEntity = departmentRepository.getOne(id);

        DepartmentChildDTO departmentChildDTO = DepartmentMapper.INSTANCE.mapDepartmentEntityToChildDto(departmentEntity);

        return departmentChildDTO;
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public void update(DepartmentSelfDTO departmentSelfDTO, List<Long> categoryIds) {
        if (departmentSelfDTO.getPosition() == null){
            departmentSelfDTO.setPosition( departmentRepository.findAll().size()+1);
        }else {
            updatePosition(departmentSelfDTO.getPosition());
        }
       DepartmentEntity departmentEntity = departmentRepository.getOne(departmentSelfDTO.getId());
        departmentEntity.setActive(departmentSelfDTO.isActive());
        departmentEntity.setIcon(departmentSelfDTO.getIcon());
        departmentEntity.setName(departmentSelfDTO.getName());
        departmentEntity.setPosition(departmentSelfDTO.getPosition());
         departmentRepository.save(departmentEntity);
        setCategories(categoryIds, departmentEntity);

    }


    private void updatePosition(int position){
        List<DepartmentEntity> all = departmentRepository.findAll();
       all.sort(Comparator.comparing(DepartmentEntity::getPosition));
        for (int i = position-1; i < all.size(); i++) {
            DepartmentEntity departmentEntity =   all.get(i);
            departmentEntity.setPosition(i+2);
            departmentRepository.save(departmentEntity);
        }
    }


}
