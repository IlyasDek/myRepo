//package cri.vaycompany.linkserve.controllers;
//
//import cri.vaycompany.linkserve.dto.ProviderPrivateDTO;
//import cri.vaycompany.linkserve.dto.ProviderPublicDTO;
//import cri.vaycompany.linkserve.services.ProviderService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//public class ProviderControllerTest {
//
//    @InjectMocks
//    ProviderController providerController;
//
//    @Mock
//    ProviderService providerService;
//
//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testCreateProvider() {
//        ProviderPrivateDTO providerPrivateDTO = new ProviderPrivateDTO();
//        ProviderPublicDTO providerPublicDTO = new ProviderPublicDTO();
//        when(providerService.createProvider(any(ProviderPrivateDTO.class))).thenReturn(providerPublicDTO);
//        ResponseEntity<ProviderPublicDTO> response = providerController.createProvider(providerPrivateDTO);
//        assertNotNull(response.getBody());
//        assertEquals(providerPublicDTO, response.getBody());
//        verify(providerService, times(1)).createProvider(any(ProviderPrivateDTO.class));
//    }
//
//    @Test
//    public void testGetProviderById() {
//        ProviderPublicDTO providerPublicDTO = new ProviderPublicDTO();
//        when(providerService.getProviderById(any(Long.class))).thenReturn(Optional.of(providerPublicDTO));
//        ResponseEntity<ProviderPublicDTO> response = providerController.getProviderById(1L);
//        assertNotNull(response.getBody());
//        assertEquals(providerPublicDTO, response.getBody());
//        verify(providerService, times(1)).getProviderById(any(Long.class));
//    }
//
//    @Test
//    public void testUpdateProvider() {
//        ProviderPrivateDTO providerPrivateDTO = new ProviderPrivateDTO();
//        providerPrivateDTO.setId(1L);
//        ProviderPublicDTO providerPublicDTO = new ProviderPublicDTO();
//        when(providerService.updateProvider(anyLong(), any(ProviderPrivateDTO.class))).thenReturn(Optional.of(providerPublicDTO));
//        ResponseEntity<ProviderPublicDTO> response = providerController.updateProvider(1L, providerPrivateDTO);
//        assertNotNull(response.getBody());
//        assertEquals(providerPublicDTO, response.getBody());
//        verify(providerService, times(1)).updateProvider(anyLong(), any(ProviderPrivateDTO.class));
//    }
//
//    @Test
//    public void testDeleteProvider() {
//        doNothing().when(providerService).deleteProvider(any(Long.class));
//        providerController.deleteProvider(1L);
//        verify(providerService, times(1)).deleteProvider(1L);
//    }
//}
