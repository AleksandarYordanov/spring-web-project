package project.spring.project.admin.utils.fileUpload;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import project.spring.project.admin.product.model.ProductDTO;
import project.spring.project.admin.product.model.ProductEntity;


@Mapper
public interface UploadFileMapper {


    UploadFileMapper INSTANCE = Mappers.getMapper(UploadFileMapper.class);

    ProductEntity mapProductDtoToEntity(ProductDTO dto);

    ProductDTO mapProductEntityToDto(ProductEntity entity);

    UploadFileEntity mapUploadFileDtoToEntity(UploadFileDTO dto);

    UploadFileDTO mapUploadFileEntityToDto(UploadFileEntity entity);

    UploadFileSelfDTO mapUploadFileEntityToSelfDTO(UploadFileEntity entity);

}
