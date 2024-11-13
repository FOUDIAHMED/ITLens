package ahmed.foudi.itlens.service;

import ahmed.foudi.itlens.dao.OwnerDAO;
import ahmed.foudi.itlens.dto.ownerdto.OwnerRequestDto;
import ahmed.foudi.itlens.dto.ownerdto.OwnerResponseDto;
import ahmed.foudi.itlens.entities.Owner;
import ahmed.foudi.itlens.mappers.RequestOwnerDTOMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class OwnerServiceTest {

    @Mock
    private OwnerDAO ownerDAO;

    @Mock
    private RequestOwnerDTOMapper requestOwnerDTOMapper;

    @InjectMocks
    private OwnerService ownerService;

    private Owner owner1;
    private Owner owner2;
    private OwnerResponseDto ownerResponseDto1;
    private OwnerResponseDto ownerResponseDto2;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        owner1 = new Owner();
        owner1.setId(1L);
        owner1.setName("Owner One");

        owner2 = new Owner();
        owner2.setId(2L);
        owner2.setName("Owner Two");

        ownerResponseDto1 = new OwnerResponseDto();
        ownerResponseDto1.setId(1L);
        ownerResponseDto1.setName("Owner One");

        ownerResponseDto2 = new OwnerResponseDto();
        ownerResponseDto2.setId(2L);
        ownerResponseDto2.setName("Owner Two");
    }

    @Test
    public void testFindAll() {
        List<Owner> owners = Arrays.asList(owner1, owner2);
        List<OwnerResponseDto> ownerResponseDtos = Arrays.asList(ownerResponseDto1, ownerResponseDto2);

        when(ownerDAO.findAll()).thenReturn(owners);
        when(requestOwnerDTOMapper.toResponseDto(owner1)).thenReturn(ownerResponseDto1);
        when(requestOwnerDTOMapper.toResponseDto(owner2)).thenReturn(ownerResponseDto2);

        List<OwnerResponseDto> result = ownerService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Owner One", result.get(0).getName());
        assertEquals("Owner Two", result.get(1).getName());
    }

    @Test
    public void testFindById() {
        when(ownerDAO.findById(1L)).thenReturn(Optional.of(owner1));
        when(requestOwnerDTOMapper.toResponseDto(owner1)).thenReturn(ownerResponseDto1);

        OwnerResponseDto result = ownerService.findById(1L);

        assertNotNull(result);
        assertEquals("Owner One", result.getName());
    }

    @Test
    public void testFindByIdNotFound() {
        when(ownerDAO.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            ownerService.findById(999L);
        });
    }
}
