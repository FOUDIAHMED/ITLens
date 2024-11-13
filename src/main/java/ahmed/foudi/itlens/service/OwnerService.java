package ahmed.foudi.itlens.service;


import ahmed.foudi.itlens.dao.OwnerDAO;
import ahmed.foudi.itlens.dto.ownerdto.OwnerRequestDto;
import ahmed.foudi.itlens.dto.ownerdto.OwnerResponseDto;
import ahmed.foudi.itlens.entities.Owner;
import ahmed.foudi.itlens.mappers.RequestOwnerDTOMapper;
import ahmed.foudi.itlens.utils.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService implements ServiceInterface<Long, OwnerRequestDto, OwnerResponseDto> {

    private final OwnerDAO ownerDAO;
    private final RequestOwnerDTOMapper requestOwnerDTOMapper;
    @Override
    public List<OwnerResponseDto> findAll() {
        return ownerDAO.findAll().stream().map(requestOwnerDTOMapper::toResponseDto).toList();
    }

    @Override
    public OwnerResponseDto findById(Long id) {
        Owner owner = ownerDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Owner with id " + id + " not found"));
        return requestOwnerDTOMapper.toResponseDto(owner);
    }

    @Override
    public OwnerResponseDto create(OwnerRequestDto dto) {
       Owner owner= ownerDAO.save(requestOwnerDTOMapper.toEntity(dto));
        return requestOwnerDTOMapper.toResponseDto(owner);
    }

    @Override
    public OwnerResponseDto update(Long id, OwnerRequestDto dto) {
        Owner owner = ownerDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("owner not found"));

        owner.setName(dto.getName());
        ownerDAO.save(owner);
        return requestOwnerDTOMapper.toResponseDto(owner);
    }

    @Override
    public void delete(Long id) {
        if(!ownerDAO.existsById(id)) {
            throw new EntityNotFoundException("Owner with id " + id + " not found");
        }
        ownerDAO.deleteById(id);
    }
}
