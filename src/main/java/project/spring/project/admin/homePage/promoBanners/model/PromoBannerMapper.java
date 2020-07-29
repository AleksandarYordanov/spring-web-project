package project.spring.project.admin.homePage.promoBanners.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.spring.project.admin.utils.fileUpload.UploadFileEntity;
import project.spring.project.admin.utils.fileUpload.UploadFileSelfDTO;

@Mapper
public interface PromoBannerMapper {

    PromoBannerMapper INSTANCE = Mappers.getMapper(PromoBannerMapper.class);

    PromoBannerEntity mapChildDTOtoEntity(PromoBannerChildDTO dto);
    PromoBannerChildDTO mapEntityToChildDTO(PromoBannerEntity entity);

    UploadFileEntity mapUploadFileSelfDTOToEntity(UploadFileSelfDTO dto);
    UploadFileSelfDTO mapUploadFileEntityToSelfDTO(UploadFileEntity entity);
}
