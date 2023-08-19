//package cri.vaycompany.linkserve.services;
//
//import cri.vaycompany.linkserve.dto.ServiceOfferDTO;
//import cri.vaycompany.linkserve.exceprions.ServiceOfferIdMismatchException;
//import cri.vaycompany.linkserve.models.ServiceOffer;
//import cri.vaycompany.linkserve.repositories.ServiceOfferRepo;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.modelmapper.ModelMapper;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class ServiceOfferServiceTest {
//
//    @InjectMocks
//    private ServiceOfferService service;
//
//    @Mock
//    private ServiceOfferRepo repository;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testCreateServiceOffer() {
//        ServiceOfferDTO serviceOfferDTO = new ServiceOfferDTO();
//        ServiceOffer serviceOffer = new ServiceOffer();
//
//        when(modelMapper.map(any(ServiceOfferDTO.class), eq(ServiceOffer.class))).thenReturn(serviceOffer);
//        when(repository.save(any())).thenReturn(serviceOffer);
//        when(modelMapper.map(any(ServiceOffer.class), eq(ServiceOfferDTO.class))).thenReturn(serviceOfferDTO);
//
//        assertEquals(serviceOfferDTO, service.createServiceOffer(serviceOfferDTO));
//    }
//
//    @Test
//    public void testGetServiceOfferById() {
//        ServiceOfferDTO serviceOfferDTO = new ServiceOfferDTO();
//        ServiceOffer serviceOffer = new ServiceOffer();
//
//        when(repository.findById(any())).thenReturn(Optional.of(serviceOffer));
//        when(modelMapper.map(any(ServiceOffer.class), eq(ServiceOfferDTO.class))).thenReturn(serviceOfferDTO);
//
//        assertEquals(Optional.of(serviceOfferDTO), service.getServiceOfferById(1L));
//    }
//
//    @Test
//    public void testUpdateServiceOffer() {
//        ServiceOfferDTO serviceOfferDTO = new ServiceOfferDTO();
//        ServiceOffer serviceOffer = new ServiceOffer();
//        Long id = 1L;
//
//        serviceOfferDTO.setId(id);  // Добавяем ID в DTO
//        serviceOffer.setId(id); // Добавляем ID в сущность
//
//        when(repository.findById(anyLong())).thenReturn(Optional.of(serviceOffer));
//        when(modelMapper.map(any(ServiceOfferDTO.class), eq(ServiceOffer.class))).thenReturn(serviceOffer);
//        when(repository.save(any())).thenReturn(serviceOffer);
//        when(modelMapper.map(any(ServiceOffer.class), eq(ServiceOfferDTO.class))).thenReturn(serviceOfferDTO);
//
//        assertEquals(serviceOfferDTO, service.updateServiceOffer(id, serviceOfferDTO));
//    }
//
//    @Test
//    public void testUpdateServiceOfferWithIdMismatch() {
//        ServiceOfferDTO serviceOfferDTO = new ServiceOfferDTO();
//        Long id = 1L;
//
//        serviceOfferDTO.setId(id + 1);  // Добавляем другой ID в DTO
//
//        assertThrows(ServiceOfferIdMismatchException.class, () -> service.updateServiceOffer(id, serviceOfferDTO));
//    }
//
//    @Test
//    public void testDeleteServiceOffer() {
//        ServiceOfferDTO serviceOfferDTO = new ServiceOfferDTO();
//        ServiceOffer serviceOffer = new ServiceOffer();
//
//        when(modelMapper.map(any(ServiceOfferDTO.class), eq(ServiceOffer.class))).thenReturn(serviceOffer);
//        when(repository.save(any())).thenReturn(serviceOffer);
//        when(modelMapper.map(any(ServiceOffer.class), eq(ServiceOfferDTO.class))).thenReturn(serviceOfferDTO);
//
//        ServiceOfferDTO savedServiceOffer = service.createServiceOffer(serviceOfferDTO);
//
//        doNothing().when(repository).deleteById(any());
//        service.deleteServiceOffer(savedServiceOffer.getId());
//
//        when(repository.findById(any())).thenReturn(Optional.empty());
//        Optional<ServiceOfferDTO> foundServiceOffer = service.getServiceOfferById(savedServiceOffer.getId());
//
//        assertFalse(foundServiceOffer.isPresent());
//    }
//}
