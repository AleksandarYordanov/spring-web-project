package project.spring.project.admin.homePage.promoBanners.service;

import org.springframework.stereotype.Service;
import project.spring.project.admin.homePage.promoBanners.model.PromoBannerChildDTO;
import project.spring.project.admin.homePage.promoBanners.model.PromoBannerEntity;
import project.spring.project.admin.homePage.promoBanners.model.PromoBannerMapper;
import project.spring.project.admin.homePage.promoBanners.repository.PromoBannerRepository;
import project.spring.project.admin.utils.fileUpload.UploadFileDTO;
import project.spring.project.admin.utils.fileUpload.UploadFileRepository;
import project.spring.project.admin.utils.fileUpload.UploadFileService;
import project.spring.project.admin.utils.googleCloudStorage.GoogleCloudStorageServiceImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromoBannerServiceImpl implements PromoBannerService {
    private final PromoBannerRepository promoBannerRepository;
    private final UploadFileService uploadFileService;
    private final UploadFileRepository uploadFileRepository;

    public PromoBannerServiceImpl(PromoBannerRepository promoBannerRepository, UploadFileService uploadFileService, UploadFileRepository uploadFileRepository) {
        this.promoBannerRepository = promoBannerRepository;
        this.uploadFileService = uploadFileService;
        this.uploadFileRepository = uploadFileRepository;
    }

    @Override
    public void createTopLeftDealWithImage(PromoBannerChildDTO promoBannerChildDTO, List<String> myParams) {
        myParams.removeAll(Arrays.asList("",null));
        managePosition(promoBannerChildDTO);
        Long id = createAndReturnId(promoBannerChildDTO);
        manageUploadFile(myParams, id);

    }

    @Override
    public void updateTopLefDealWithImage(PromoBannerChildDTO promoBannerChildDTO, List<String> myParams) {
        myParams.removeAll(Arrays.asList("",null));
        PromoBannerEntity promoBannerEntity = promoBannerRepository.getOne(promoBannerChildDTO.getId());

        if (!promoBannerEntity.getPosition().equals(promoBannerChildDTO.getPosition())) {
            managePosition(promoBannerChildDTO);
            promoBannerEntity.setPosition(promoBannerChildDTO.getPosition());
        }



        promoBannerEntity.setActive(promoBannerChildDTO.isActive());
        promoBannerEntity.setDescription(promoBannerChildDTO.getDescription());
        promoBannerEntity.setLink(promoBannerChildDTO.getLink());
        promoBannerEntity.setBackground(promoBannerChildDTO.getBackground());
        promoBannerEntity.setDate(promoBannerChildDTO.getDate());
        promoBannerRepository.save(promoBannerEntity);

        manageUploadFile(myParams, promoBannerEntity.getId());
    }

    @Override
    public void deleteById(Long id) {
        promoBannerRepository.deleteById(id);
    }

    private void manageUploadFile(List<String> myParams, Long id) {
        String p = myParams.get(0);
        try {
            if(uploadFileRepository.existsById(Long.parseLong(p))){
                return;
            }
        }catch (Exception e){
            System.out.println(e);
        }

        String location = getDestinationLocation(id,p.substring(p.length()/2,p.length()/2+8));
        String locationWeb = "";
        try {
            locationWeb = saveWeb(location,p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        UploadFileDTO uploadFileDTO = new UploadFileDTO();
        uploadFileDTO.setLocation(locationWeb);
        uploadFileDTO.setPosition(0);
       Long idOfPic  =  uploadFileService.saveFileAndReturnId(uploadFileDTO);
       updateSliderPicture(id, idOfPic);
    }

    private void updateSliderPicture(Long idOfTopSlider, Long idOfUploadFile) {
       PromoBannerEntity promoBannerEntity = promoBannerRepository.getOne(idOfTopSlider);
       promoBannerEntity.setPhoto(uploadFileRepository.getOne(idOfUploadFile));
       promoBannerRepository.save(promoBannerEntity);
    }

    private void managePosition(PromoBannerChildDTO promoBannerChildDTO) {
        if (promoBannerChildDTO.getPosition() == null){
            promoBannerChildDTO.setPosition( promoBannerRepository.findAll().size()+1);
        }else {
            updatePosition(promoBannerChildDTO.getPosition());
        }
    }

    private void updatePosition(Integer position) {
        List<PromoBannerEntity> all = promoBannerRepository.findAll();
        all.sort(Comparator.comparing(PromoBannerEntity::getPosition));
        for (int i = position-1; i < all.size(); i++) {
            PromoBannerEntity promoBannerEntity =   all.get(i);
            promoBannerEntity.setPosition(i+2);
            promoBannerRepository.save(promoBannerEntity);
        }
    }

    private Long createAndReturnId(PromoBannerChildDTO promoBannerChildDTO) {
        PromoBannerEntity promoBannerEntity = PromoBannerMapper.INSTANCE.mapChildDTOtoEntity(promoBannerChildDTO);
        promoBannerEntity = promoBannerRepository.save(promoBannerEntity);
        return promoBannerEntity.getId();
    }

    @Override
    public PromoBannerChildDTO getById(Long id) {
        PromoBannerEntity promoBannerEntity = promoBannerRepository.getOne(id);
        return PromoBannerMapper.INSTANCE.mapEntityToChildDTO(promoBannerEntity);
    }

    @Override
    public List<PromoBannerChildDTO> getAll() {
       return promoBannerRepository.
                findAll().
                stream().
                map(PromoBannerMapper.INSTANCE::mapEntityToChildDTO).
                collect(Collectors.toList());
    }

    @Override
    public List<PromoBannerChildDTO> getAllActiveSorted(boolean active) {
        return promoBannerRepository.
                findAllByActiveOrderByPosition(active).
                stream().
                map(PromoBannerMapper.INSTANCE::mapEntityToChildDTO).
                collect(Collectors.toList());
    }

    private String getDestinationLocation(Long productId, String name) {
        return "homePage/promoBanners/"+productId+"/"+name;
    }

    private String saveWeb(String location, String base64string) throws IOException {

        String base64Image = base64string.split(",")[1];

        byte[] imageBytes = Base64.getDecoder()
                .decode(base64Image.getBytes(StandardCharsets.UTF_8));

        GoogleCloudStorageServiceImpl googleCloudStorageService = new GoogleCloudStorageServiceImpl();
        return (googleCloudStorageService.saveToWeb(imageBytes, location));



    }
}
