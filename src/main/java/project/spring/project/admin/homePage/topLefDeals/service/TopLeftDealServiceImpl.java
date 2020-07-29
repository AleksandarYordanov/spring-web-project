package project.spring.project.admin.homePage.topLefDeals.service;

import org.springframework.stereotype.Service;
import project.spring.project.admin.homePage.topLefDeals.model.TopLeftDealChildDTO;
import project.spring.project.admin.homePage.topLefDeals.model.TopLeftDealEntity;
import project.spring.project.admin.homePage.topLefDeals.model.TopLeftDealMapper;
import project.spring.project.admin.homePage.topLefDeals.repository.TopLeftDealRepository;
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
public class TopLeftDealServiceImpl implements TopLeftDealService {
    private final TopLeftDealRepository topLeftDealRepository;
    private final UploadFileService uploadFileService;
    private final UploadFileRepository uploadFileRepository;

    public TopLeftDealServiceImpl(TopLeftDealRepository topLeftDealRepository, UploadFileService uploadFileService, UploadFileRepository uploadFileRepository) {
        this.topLeftDealRepository = topLeftDealRepository;
        this.uploadFileService = uploadFileService;
        this.uploadFileRepository = uploadFileRepository;
    }

    @Override
    public void createTopLeftDealWithImage(TopLeftDealChildDTO topLeftDealChildDTO, List<String> myParams) {
        myParams.removeAll(Arrays.asList("",null));
        managePosition(topLeftDealChildDTO);
        Long id = createAndReturnId(topLeftDealChildDTO);
        manageUploadFile(myParams, id);

    }

    @Override
    public void updateTopLefDealWithImage(TopLeftDealChildDTO topLeftDealChildDTO, List<String> myParams) {
        myParams.removeAll(Arrays.asList("",null));
        TopLeftDealEntity topLeftDealEntity = topLeftDealRepository.getOne(topLeftDealChildDTO.getId());

        if (!topLeftDealEntity.getPosition().equals(topLeftDealChildDTO.getPosition())) {
            managePosition(topLeftDealChildDTO);
            topLeftDealEntity.setPosition(topLeftDealChildDTO.getPosition());
        }



        topLeftDealEntity.setActive(topLeftDealChildDTO.isActive());
        topLeftDealEntity.setDescription(topLeftDealChildDTO.getDescription());
        topLeftDealEntity.setLink(topLeftDealChildDTO.getLink());
        topLeftDealEntity.setBackground(topLeftDealChildDTO.getBackground());
        topLeftDealRepository.save(topLeftDealEntity);

        manageUploadFile(myParams, topLeftDealEntity.getId());
    }

    @Override
    public void deleteById(Long id) {
        topLeftDealRepository.deleteById(id);
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
       TopLeftDealEntity topLeftDealEntity = topLeftDealRepository.getOne(idOfTopSlider);
       topLeftDealEntity.setPhoto(uploadFileRepository.getOne(idOfUploadFile));
       topLeftDealRepository.save(topLeftDealEntity);
    }

    private void managePosition(TopLeftDealChildDTO topLeftDealChildDTO) {
        if (topLeftDealChildDTO.getPosition() == null){
            topLeftDealChildDTO.setPosition( topLeftDealRepository.findAll().size()+1);
        }else {
            updatePosition(topLeftDealChildDTO.getPosition());
        }
    }

    private void updatePosition(Integer position) {
        List<TopLeftDealEntity> all = topLeftDealRepository.findAll();
        all.sort(Comparator.comparing(TopLeftDealEntity::getPosition));
        for (int i = position-1; i < all.size(); i++) {
            TopLeftDealEntity topLeftDealEntity =   all.get(i);
            topLeftDealEntity.setPosition(i+2);
            topLeftDealRepository.save(topLeftDealEntity);
        }
    }

    private Long createAndReturnId(TopLeftDealChildDTO topLeftDealChildDTO) {
        TopLeftDealEntity topLeftDealEntity = TopLeftDealMapper.INSTANCE.mapChildDTOtoEntity(topLeftDealChildDTO);
        topLeftDealEntity = topLeftDealRepository.save(topLeftDealEntity);
        return topLeftDealEntity.getId();
    }

    @Override
    public TopLeftDealChildDTO getById(Long id) {
        TopLeftDealEntity topLeftDealEntity = topLeftDealRepository.getOne(id);
        return TopLeftDealMapper.INSTANCE.mapEntityToChildDTO(topLeftDealEntity);
    }

    @Override
    public List<TopLeftDealChildDTO> getAll() {
       return topLeftDealRepository.
                findAll().
                stream().
                map(TopLeftDealMapper.INSTANCE::mapEntityToChildDTO).
                collect(Collectors.toList());
    }

    @Override
    public List<TopLeftDealChildDTO> getAllActiveSorted(boolean active) {
        return topLeftDealRepository.
                findAllByActiveOrderByPosition(active).
                stream().
                map(TopLeftDealMapper.INSTANCE::mapEntityToChildDTO).
                collect(Collectors.toList());
    }

    private String getDestinationLocation(Long productId, String name) {
        return "homePage/topLeftDeal/"+productId+"/"+name;
    }

    private String saveWeb(String location, String base64string) throws IOException {

        String base64Image = base64string.split(",")[1];

        byte[] imageBytes = Base64.getDecoder()
                .decode(base64Image.getBytes(StandardCharsets.UTF_8));

        GoogleCloudStorageServiceImpl googleCloudStorageService = new GoogleCloudStorageServiceImpl();
        return (googleCloudStorageService.saveToWeb(imageBytes, location));



    }
}
