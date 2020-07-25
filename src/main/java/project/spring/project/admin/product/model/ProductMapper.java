package project.spring.project.admin.product.model;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.spring.project.admin.utils.fileUpload.UploadFileDTO;
import project.spring.project.admin.utils.fileUpload.UploadFileEntity;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

//    List<UploadFileEntity> mapUploadFileDtoListToEntity(List<UploadFileDTO> photos);
//
//    List<UploadFileDTO> mapUploadFileEntityListToDto(List<UploadFileEntity> photos);
//
//    UploadFileEntity mapUploadFileDtoToEntity(UploadFileDTO photo);
//
//    UploadFileDTO mapUploadFileEntityToDto(UploadFileEntity photo);

    ProductEntity mapProductDtoToEntity(ProductDTO dto);

    ProductDTO mapProductEntityToDto(ProductEntity entity);

}
