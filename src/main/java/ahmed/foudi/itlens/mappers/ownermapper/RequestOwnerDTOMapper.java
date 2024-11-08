package ahmed.foudi.itlens.mappers.ownermapper;

import ahmed.foudi.itlens.dto.ownerdto.OwnerRequestDto;
import ahmed.foudi.itlens.dto.ownerdto.OwnerResponseDto;
import ahmed.foudi.itlens.entities.Owner;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RequestOwnerDTOMapper {
    Owner toEntity(OwnerRequestDto dto);

    OwnerResponseDto toResponseDto(Owner entity);

}
