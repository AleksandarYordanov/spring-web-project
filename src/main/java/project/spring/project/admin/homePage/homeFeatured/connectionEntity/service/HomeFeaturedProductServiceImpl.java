package project.spring.project.admin.homePage.homeFeatured.connectionEntity.service;

import org.springframework.stereotype.Service;
import project.spring.project.admin.homePage.homeFeatured.connectionEntity.model.HomeFeaturedProductDTO;
import project.spring.project.admin.homePage.homeFeatured.connectionEntity.model.HomeFeaturedProductEntity;
import project.spring.project.admin.homePage.homeFeatured.connectionEntity.model.HomeFeaturedProductMapper;
import project.spring.project.admin.homePage.homeFeatured.connectionEntity.model.HomeFeaturedProductPK;
import project.spring.project.admin.homePage.homeFeatured.connectionEntity.repository.HomeFeaturedProductRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeFeaturedProductServiceImpl implements HomeFeaturedProductService {
    private final HomeFeaturedProductRepo homeFeaturedProductRepo;

    public HomeFeaturedProductServiceImpl(HomeFeaturedProductRepo homeFeaturedProductRepo) {
        this.homeFeaturedProductRepo = homeFeaturedProductRepo;
    }


    @Override
    public List<HomeFeaturedProductDTO> getAll() {
        return homeFeaturedProductRepo.
                findAll().
                stream().
                map(HomeFeaturedProductMapper.INSTANCE::mapTypeProductEntityToDto).
                collect(Collectors.toList());
    }

    @Override
    public void create(HomeFeaturedProductDTO homeFeaturedProductDto) {
        HomeFeaturedProductEntity departmentCategoryEntity = HomeFeaturedProductMapper.INSTANCE.mapTypeProductDtoToEntity(homeFeaturedProductDto);
        homeFeaturedProductRepo.save(departmentCategoryEntity);
    }

    @Override
    public void deleteById(HomeFeaturedProductPK departmentCategoryPK) {
        homeFeaturedProductRepo.deleteById(departmentCategoryPK);
    }


    @Override
    public HomeFeaturedProductDTO getById(HomeFeaturedProductPK id) {
        HomeFeaturedProductEntity departmentCategoryEntity = homeFeaturedProductRepo.getOne(id);
        return HomeFeaturedProductMapper.INSTANCE.mapTypeProductEntityToDto(departmentCategoryEntity);


    }

    @Override
    public void update(HomeFeaturedProductDTO homeFeaturedProductDto) {
        HomeFeaturedProductEntity departmentCategoryEntity = HomeFeaturedProductMapper.INSTANCE.mapTypeProductDtoToEntity(homeFeaturedProductDto);
        homeFeaturedProductRepo.save(departmentCategoryEntity);
    }
}
