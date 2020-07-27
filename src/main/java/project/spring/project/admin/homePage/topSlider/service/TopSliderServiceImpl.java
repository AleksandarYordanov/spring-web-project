package project.spring.project.admin.homePage.topSlider.service;

import org.springframework.stereotype.Service;
import project.spring.project.admin.homePage.topSlider.model.TopSliderChildDTO;
import project.spring.project.admin.homePage.topSlider.model.TopSliderEntity;
import project.spring.project.admin.homePage.topSlider.model.TopSliderMapper;
import project.spring.project.admin.homePage.topSlider.repository.TopSliderRepository;
import project.spring.project.admin.utils.fileUpload.UploadFileDTO;
import project.spring.project.admin.utils.fileUpload.UploadFileEntity;
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
public class TopSliderServiceImpl implements TopSliderService {
    private final TopSliderRepository topSliderRepository;
    private final UploadFileService uploadFileService;
    private final UploadFileRepository uploadFileRepository;

    public TopSliderServiceImpl(TopSliderRepository topSliderRepository, UploadFileService uploadFileService, UploadFileRepository uploadFileRepository) {
        this.topSliderRepository = topSliderRepository;
        this.uploadFileService = uploadFileService;
        this.uploadFileRepository = uploadFileRepository;
    }

    @Override
    public void createTopSliderWithImage(TopSliderChildDTO topSliderChildDTO, List<String> myParams) {
        myParams.removeAll(Arrays.asList("",null));
        managePosition(topSliderChildDTO);
        Long id = createAndReturnId(topSliderChildDTO);
        manageUploadFile(myParams, id);

    }

    @Override
    public void updateTopSliderWithImage(TopSliderChildDTO topSliderChildDTO, List<String> myParams) {
        myParams.removeAll(Arrays.asList("",null));
        TopSliderEntity topSliderEntity = topSliderRepository.getOne(topSliderChildDTO.getId());

        if (!topSliderEntity.getPosition().equals(topSliderChildDTO.getPosition())) {
            managePosition(topSliderChildDTO);
            topSliderEntity.setPosition(topSliderChildDTO.getPosition());
        }



        topSliderEntity.setActive(topSliderChildDTO.isActive());
        topSliderEntity.setDescription(topSliderChildDTO.getDescription());
        topSliderEntity.setLink(topSliderChildDTO.getLink());
        topSliderRepository.save(topSliderEntity);

        manageUploadFile(myParams, topSliderEntity.getId());
    }

    @Override
    public void deleteById(Long id) {
        topSliderRepository.deleteById(id);
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
       TopSliderEntity topSliderEntity = topSliderRepository.getOne(idOfTopSlider);
       topSliderEntity.setPhoto(uploadFileRepository.getOne(idOfUploadFile));
       topSliderRepository.save(topSliderEntity);
    }

    private void managePosition(TopSliderChildDTO topSliderChildDTO) {
        if (topSliderChildDTO.getPosition() == null){
            topSliderChildDTO.setPosition( topSliderRepository.findAll().size()+1);
        }else {
            updatePosition(topSliderChildDTO.getPosition());
        }
    }

    private void updatePosition(Integer position) {
        List<TopSliderEntity> all = topSliderRepository.findAll();
        all.sort(Comparator.comparing(TopSliderEntity::getPosition));
        for (int i = position-1; i < all.size(); i++) {
            TopSliderEntity topSliderEntity =   all.get(i);
            topSliderEntity.setPosition(i+2);
            topSliderRepository.save(topSliderEntity);
        }
    }

    private Long createAndReturnId(TopSliderChildDTO topSliderChildDTO) {
        TopSliderEntity topSliderEntity = TopSliderMapper.INSTANCE.mapChildDTOtoEntity(topSliderChildDTO);
        topSliderEntity = topSliderRepository.save(topSliderEntity);
        return topSliderEntity.getId();
    }

    @Override
    public TopSliderChildDTO getById(Long id) {
        TopSliderEntity topSliderEntity = topSliderRepository.getOne(id);
        return TopSliderMapper.INSTANCE.mapEntityToChildDTO(topSliderEntity);
    }

    @Override
    public List<TopSliderChildDTO> getAll() {
       return topSliderRepository.
                findAll().
                stream().
                map(TopSliderMapper.INSTANCE::mapEntityToChildDTO).
                collect(Collectors.toList());
    }

    @Override
    public List<TopSliderChildDTO> getAllActiveSorted(boolean active) {
        return topSliderRepository.
                findAllByActiveOrderByPosition(active).
                stream().
                map(TopSliderMapper.INSTANCE::mapEntityToChildDTO).
                collect(Collectors.toList());
    }

    private String getDestinationLocation(Long productId, String name) {
        return "homePage/topSlider/"+productId+"/"+name;
    }

    private String saveWeb(String location, String base64string) throws IOException {

        String base64Image = base64string.split(",")[1];

        byte[] imageBytes = Base64.getDecoder()
                .decode(base64Image.getBytes(StandardCharsets.UTF_8));

        GoogleCloudStorageServiceImpl googleCloudStorageService = new GoogleCloudStorageServiceImpl();
        return (googleCloudStorageService.saveToWeb(imageBytes, location));



    }
}
