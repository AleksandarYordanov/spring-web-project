package project.spring.project.admin.homePage.topLefDeals.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.spring.project.admin.utils.fileUpload.UploadFileEntity;
import project.spring.project.admin.utils.fileUpload.UploadFileSelfDTO;

@Mapper
public interface TopLeftDealMapper {

    TopLeftDealMapper INSTANCE = Mappers.getMapper(TopLeftDealMapper.class);

    TopLeftDealEntity mapChildDTOtoEntity(TopLeftDealChildDTO dto);
    TopLeftDealChildDTO mapEntityToChildDTO(TopLeftDealEntity entity);

    UploadFileEntity mapUploadFileSelfDTOToEntity(UploadFileSelfDTO dto);
    UploadFileSelfDTO mapUploadFileEntityToSelfDTO(UploadFileEntity entity);
}
