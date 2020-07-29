package project.spring.project.admin.homePage.homeFeatured.connectionEntity.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HomeFeaturedProductMapper {
    HomeFeaturedProductMapper INSTANCE = Mappers.getMapper(HomeFeaturedProductMapper.class);

    HomeFeaturedProductEntity mapTypeProductDtoToEntity(HomeFeaturedProductDTO dto);

    HomeFeaturedProductDTO mapTypeProductEntityToDto(HomeFeaturedProductEntity entity);
}
