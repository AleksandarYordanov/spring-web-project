package project.spring.project.admin.homePage.homeFeatured.service;


import org.springframework.stereotype.Service;
import project.spring.project.admin.homePage.homeFeatured.connectionEntity.model.HomeFeaturedProductDTO;
import project.spring.project.admin.homePage.homeFeatured.connectionEntity.model.HomeFeaturedProductEntity;
import project.spring.project.admin.homePage.homeFeatured.connectionEntity.model.HomeFeaturedProductPK;
import project.spring.project.admin.homePage.homeFeatured.connectionEntity.service.HomeFeaturedProductService;
import project.spring.project.admin.homePage.homeFeatured.model.HomeFeaturedChildDTO;
import project.spring.project.admin.homePage.homeFeatured.model.HomeFeaturedEntity;
import project.spring.project.admin.homePage.homeFeatured.model.HomeFeaturedMapper;
import project.spring.project.admin.homePage.homeFeatured.repository.HomeFeaturedRepository;
import project.spring.project.admin.product.model.ProductEntity;

import project.spring.project.admin.type.model.TypeEntity;
import project.spring.project.admin.utils.fileUpload.UploadFileRepository;
import project.spring.project.admin.utils.fileUpload.UploadFileService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HomeFeaturedServiceImpl implements HomeFeaturedService {
    private final HomeFeaturedRepository homeFeaturedRepository;
    private final HomeFeaturedProductService homeFeaturedProductService;
    private final UploadFileService uploadFileService;
    private final UploadFileRepository uploadFileRepository;


    public HomeFeaturedServiceImpl(HomeFeaturedRepository homeFeaturedRepository, HomeFeaturedProductService homeFeaturedProductService, UploadFileService uploadFileService, UploadFileRepository uploadFileRepository) {
        this.homeFeaturedRepository = homeFeaturedRepository;
        this.homeFeaturedProductService = homeFeaturedProductService;
        this.uploadFileService = uploadFileService;
        this.uploadFileRepository = uploadFileRepository;
    }


    private void setToBeMain(Long id){
        try{
            List<HomeFeaturedEntity> currentMain = homeFeaturedRepository.findAllByMain(true);
            currentMain.forEach( e -> {
                e.setMain(false);
                homeFeaturedRepository.save(e);
            });
        }catch (Exception e){
            System.out.println(e);
        }

        HomeFeaturedEntity entity = homeFeaturedRepository.getOne(id);
        entity.setMain(true);
        homeFeaturedRepository.save(entity);
    }


    private void managePosition(HomeFeaturedChildDTO homeFeaturedChildDTO) {
        if (homeFeaturedChildDTO.getPosition() == null){
            homeFeaturedChildDTO.setPosition( homeFeaturedRepository.findAll().size()+1);
        }else {
            updatePosition(homeFeaturedChildDTO.getPosition());
        }
    }

    private void updatePosition(Integer position) {
        List<HomeFeaturedEntity> all = homeFeaturedRepository.findAll();
        all.sort(Comparator.comparing(HomeFeaturedEntity::getPosition));
        for (int i = position-1; i < all.size(); i++) {
            HomeFeaturedEntity homeFeaturedEntity =   all.get(i);
            homeFeaturedEntity.setPosition(i+2);
            homeFeaturedRepository.save(homeFeaturedEntity);
        }
    }

    @Override
    public void deleteById(Long id) {
        homeFeaturedRepository.deleteById(id);
    }

    @Override
    public HomeFeaturedChildDTO getById(Long id) {
        HomeFeaturedEntity homeFeaturedEntity = homeFeaturedRepository.getOne(id);

        HomeFeaturedChildDTO homeFeaturedChildDTO = HomeFeaturedMapper.INSTANCE.mapEntityToChildDTO(homeFeaturedEntity);

        homeFeaturedChildDTO.getProducts().forEach(p->{
            p.setPhotos(uploadFileService.getAllPhotosForProduct(p.getId()));
        });

        return homeFeaturedChildDTO;
    }

    @Override
    public List<HomeFeaturedChildDTO> getAll() {
        return homeFeaturedRepository.
                findAll().
                stream().
                map(HomeFeaturedMapper.INSTANCE::mapEntityToChildDTO).
                collect(Collectors.toList());
    }

    @Override
    public List<HomeFeaturedChildDTO> getAllActiveSortedWithoutMain(boolean active) {
       List<HomeFeaturedChildDTO> homeFeaturedChildDTOList = homeFeaturedRepository.
                findAllByActiveAndMainFalseOrderByPosition(active).
                stream().
                map(HomeFeaturedMapper.INSTANCE::mapEntityToChildDTO)
                .collect(Collectors.toList());

        homeFeaturedChildDTOList.forEach(homeFeaturedChildDTO -> {
            homeFeaturedChildDTO.getProducts().forEach(p->{
                p.setPhotos(uploadFileService.getAllPhotosForProduct(p.getId()));
            });
        });

        return homeFeaturedChildDTOList;



    }

    @Override
    public HomeFeaturedChildDTO getMain() {
       HomeFeaturedEntity homeFeaturedEntity = homeFeaturedRepository.findAllByMain(true).get(0);
       HomeFeaturedChildDTO homeFeaturedChildDTO = HomeFeaturedMapper.INSTANCE.mapEntityToChildDTO(homeFeaturedEntity);

       homeFeaturedChildDTO.getProducts().forEach(p ->{
          p.setPhotos(uploadFileService.getAllPhotosForProduct(p.getId()));
       });

       return homeFeaturedChildDTO;
    }

    @Override
    public void create(HomeFeaturedChildDTO homeFeatured) {
        HomeFeaturedEntity homeFeaturedEntity = HomeFeaturedMapper.INSTANCE.mapChildDTOtoEntity(homeFeatured);
       homeFeaturedEntity = homeFeaturedRepository.save(homeFeaturedEntity);
        managePosition(homeFeatured);
        if (homeFeatured.isMain()){
            setToBeMain(homeFeaturedEntity.getId());
        }

    }

    @Override
    public void create(HomeFeaturedChildDTO homeFeatured, List<Long> productIds) {
        HomeFeaturedEntity homeFeaturedEntity = HomeFeaturedMapper.INSTANCE.mapChildDTOtoEntity(homeFeatured);
       homeFeaturedEntity = homeFeaturedRepository.save(homeFeaturedEntity);
        managePosition(homeFeatured);

        setProductsAndReturnEntity(productIds, homeFeaturedEntity);
        if (homeFeatured.isMain()){
            setToBeMain(homeFeaturedEntity.getId());
        }
    }

    private HomeFeaturedEntity setProductsAndReturnEntity(List<Long> productsIds, HomeFeaturedEntity homeFeatured) {
        if (homeFeatured.getProducts() == null){
            return   setProductsEntityNoProducts(productsIds,homeFeatured);
        }else {
            return   setProductsEntityWithProducts(productsIds,homeFeatured);
        }

    }

    private HomeFeaturedEntity setProductsEntityWithProducts(List<Long> productsIds, HomeFeaturedEntity homeFeatured) {
        List<ProductEntity> currentProducts = homeFeatured.getProducts();
        List<Long> currentProductsIds = new ArrayList<>();
        currentProducts.forEach(p -> currentProductsIds.add(p.getId()));
        //ids to be removed;
        currentProductsIds.removeAll(productsIds);

        currentProductsIds.forEach(id ->{
            HomeFeaturedProductPK homeFeaturedProductPK = new HomeFeaturedProductPK();
            homeFeaturedProductPK.setProductId(id);
            homeFeaturedProductPK.setTypeId(homeFeatured.getId());
            homeFeaturedProductService.deleteById(homeFeaturedProductPK);
        });

        return   setProductsEntityNoProducts(productsIds,homeFeatured);
    }

    private HomeFeaturedEntity setProductsEntityNoProducts(List<Long> productsIds, HomeFeaturedEntity homeFeaturedDTO) {

        HomeFeaturedProductDTO homeFeaturedProductDTO = new HomeFeaturedProductDTO();
        HomeFeaturedProductPK homeFeaturedProductPK = new HomeFeaturedProductPK();

        for (int i = 0; i < productsIds.size(); i++) {
            Long id = productsIds.get(i);
            homeFeaturedProductPK.setTypeId(homeFeaturedDTO.getId());
            homeFeaturedProductPK.setProductId(id);
            homeFeaturedProductDTO.setId(homeFeaturedProductPK);
            homeFeaturedProductDTO.setPosition(i);
            homeFeaturedProductService.create(homeFeaturedProductDTO);
        }
        return  homeFeaturedRepository.getOne(homeFeaturedDTO.getId());
    }

    @Override
    public void update(HomeFeaturedChildDTO homeFeaturedDTO, List<Long> productIds) {
       HomeFeaturedEntity homeFeaturedEntity = homeFeaturedRepository.getOne(homeFeaturedDTO.getId());

        homeFeaturedEntity.setName(homeFeaturedDTO.getName());
        homeFeaturedEntity.setActive(homeFeaturedDTO.isActive());
        if (homeFeaturedDTO.isMain()){
            setToBeMain(homeFeaturedEntity.getId());
        }else {
            homeFeaturedEntity.setMain(false);
        }
        homeFeaturedRepository.save(homeFeaturedEntity);
        managePosition(homeFeaturedDTO);
        homeFeaturedEntity  =    setProductsAndReturnEntity(productIds, homeFeaturedEntity);
        homeFeaturedRepository.save(homeFeaturedEntity);
    }
}
