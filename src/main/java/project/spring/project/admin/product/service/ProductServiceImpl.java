package project.spring.project.admin.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.spring.project.admin.product.model.ProductChildDTO;
import project.spring.project.admin.product.model.ProductDTO;
import project.spring.project.admin.product.model.ProductEntity;
import project.spring.project.admin.product.model.ProductMapper;
import project.spring.project.admin.product.repository.ProductRepository;
import project.spring.project.admin.utils.fileUpload.UploadFileDTO;
import project.spring.project.admin.utils.fileUpload.UploadFileService;
import project.spring.project.admin.utils.googleCloudStorage.GoogleCloudStorageServiceImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UploadFileService uploadFileService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UploadFileService uploadFileService) {
        this.productRepository = productRepository;
        this.uploadFileService = uploadFileService;
    }


    @Override
    public List<ProductChildDTO> getAll() {

        List<ProductChildDTO> productChildDTOList = productRepository.
                findAll().
                stream().
                map(ProductMapper.INSTANCE::mapProductEntityToChildDto).
                collect(Collectors.toList());

        productChildDTOList.forEach(p -> {
          p.setPhotos( uploadFileService.getAllPhotosForProduct(p.getId()));
        });
        return productChildDTOList;
    }

    @Override
    public void create(ProductDTO productDTO) {
        ProductEntity productEntity = ProductMapper.INSTANCE.mapProductDtoToEntity(productDTO);
        productRepository.save(productEntity);
    }

    @Override
    public Long createAndReturnId(ProductDTO productDTO) {
        ProductEntity productEntity = ProductMapper.INSTANCE.mapProductDtoToEntity(productDTO);
       productEntity = productRepository.save(productEntity);
       return productEntity.getId();
    }

    @Override
    public ProductChildDTO getById(Long id) {
      Optional<ProductEntity> productEntity = productRepository.findById(id);
        ProductChildDTO  productChildDTO = new ProductChildDTO();
      if (productEntity.isPresent()){
          productChildDTO =  ProductMapper.INSTANCE.mapProductEntityToChildDto(productEntity.get());
      }else {
          throw new IllegalArgumentException("no such entity with id: " + id);
      }
        productChildDTO.setPhotos( uploadFileService.getAllPhotosForProduct(productChildDTO.getId()));
      return productChildDTO;
    }

    @Override
    public void addPhotoToId(UploadFileDTO photo, Long productId) {
      uploadFileService.saveFileForProductId(photo,productId);
    }

    @Override
    public void createProductWithImage(ProductDTO product, List<String> myParams) {
        myParams.removeAll(Arrays.asList("",null));
        Long id = createAndReturnId(product);
        for (int i = 0; i < myParams.size(); i++) {
            String p = myParams.get(i);
            String location = getDestinationLocation(id,p.substring(p.length()/2,p.length()/2+8));
            String locationWeb = "";
            try {
                locationWeb = saveWeb(location,p);
            } catch (IOException e) {
                e.printStackTrace();
            }
            UploadFileDTO uploadFileDTO = new UploadFileDTO();
            uploadFileDTO.setLocation(locationWeb);
            uploadFileDTO.setPosition(i);
            uploadFileService.saveFileForProductId(uploadFileDTO,id);
        }
    }

    private String getDestinationLocation(Long productId, String name) {
        return "products/"+productId+"/"+name;
    }

    private String saveWeb(String location, String base64string) throws IOException {

        String base64Image = base64string.split(",")[1];

        byte[] imageBytes = Base64.getDecoder()
                .decode(base64Image.getBytes(StandardCharsets.UTF_8));

        GoogleCloudStorageServiceImpl googleCloudStorageService = new GoogleCloudStorageServiceImpl();
        return (googleCloudStorageService.saveToWeb(imageBytes, location));

    }

    @Override
    public void updateProductWithImage(ProductDTO productDTO, List<String> myParams) {
        myParams.removeAll(Arrays.asList("",null));
        ProductEntity productEntity = productRepository.getOne(productDTO.getId());

     productEntity.setActive(productDTO.isActive());
     productEntity.setDescription(productDTO.getDescription());
     productEntity.setName(productDTO.getName());
     productEntity.setPrice(productDTO.getPrice());
     productEntity.setShortDescription(productDTO.getShortDescription());
     productEntity.setStock(productDTO.getStock());
     productEntity.setUnlimited(productDTO.isUnlimited());

        manageUploadFile(myParams, productEntity.getId());
    }

    private void manageUploadFile(List<String> myParams, Long id) {
        String p = myParams.get(0);
        try {
            if(productRepository.existsById(Long.parseLong(p))){
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

        uploadFileService.saveFileForProductId(uploadFileDTO,id);
    }
}
