package project.spring.project.admin.homePage.topSlider.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.spring.project.admin.product.model.ProductMapper;
import project.spring.project.admin.utils.fileUpload.UploadFileEntity;
import project.spring.project.admin.utils.fileUpload.UploadFileSelfDTO;

@Mapper
public interface TopSliderMapper {

    TopSliderMapper INSTANCE = Mappers.getMapper(TopSliderMapper.class);

    TopSliderEntity mapChildDTOtoEntity(TopSliderChildDTO dto);
    TopSliderChildDTO mapEntityToChildDTO(TopSliderEntity entity);

    UploadFileEntity mapUploadFileSelfDTOToEntity(UploadFileSelfDTO dto);
    UploadFileSelfDTO mapUploadFileEntityToSelfDTO(UploadFileEntity entity);
}
