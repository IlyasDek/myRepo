//package cri.vaycompany.linkserve.controllers;
//
//import cri.vaycompany.linkserve.dto.ProviderConnectionDTO;
//import cri.vaycompany.linkserve.services.ProviderConnectionService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class ProviderConnectionControllerTest {
//
//    @InjectMocks
//    private ProviderConnectionController providerConnectionController;
//
//    @Mock
//    private ProviderConnectionService providerConnectionService;
//
//    private ModelMapper modelMapper = new ModelMapper();
//    private ProviderConnectionDTO providerConnectionDTO;
//
//    @BeforeEach
//    public void setup() {
//        providerConnectionController = new ProviderConnectionController(providerConnectionService);
//        providerConnectionDTO = new ProviderConnectionDTO();
//        providerConnectionDTO.setId(1L);
//    }
//
//    @Test
//    public void testCreateProviderConnection() {
//        when(providerConnectionService.createProviderConnection(any(ProviderConnectionDTO.class))).thenReturn(providerConnectionDTO);
//        ProviderConnectionDTO result = providerConnectionController.createProviderConnection(providerConnectionDTO).getBody();
//        assertEquals(providerConnectionDTO, result);
//    }
//
//    @Test
//    public void testConfirmProviderConnection() {
//        when(providerConnectionService.confirmProviderConnection(anyLong())).thenReturn(providerConnectionDTO);
//        ProviderConnectionDTO result = providerConnectionController.confirmProviderConnection(providerConnectionDTO.getId()).getBody();
//        assertEquals(providerConnectionDTO, result);
//    }
//
//    @Test
//    public void testGetProviderConnectionById() {
//        when(providerConnectionService.getProviderConnectionById(anyLong())).thenReturn(Optional.of(providerConnectionDTO));
//        ProviderConnectionDTO result = providerConnectionController.getProviderConnectionById(providerConnectionDTO.getId()).getBody();
//        assertEquals(providerConnectionDTO, result);
//    }
//
//    @Test
//    public void testGetAllProviderConnections() {
//        List<ProviderConnectionDTO> providerConnections = new ArrayList<>();
//        providerConnections.add(providerConnectionDTO);
//        when(providerConnectionService.getAllProviderConnections()).thenReturn(providerConnections);
//        List<ProviderConnectionDTO> results = providerConnectionController.getAllProviderConnections().getBody();
//        assertEquals(providerConnections, results);
//    }
//
//    @Test
//    public void testGetProviderConnections() {
//        List<ProviderConnectionDTO> providerConnections = new ArrayList<>();
//        providerConnections.add(providerConnectionDTO);
//        when(providerConnectionService.getProviderConnections(anyLong())).thenReturn(providerConnections);
//        List<ProviderConnectionDTO> results = providerConnectionController.getProviderConnections(providerConnectionDTO.getId()).getBody();
//        assertEquals(providerConnections, results);
//    }
//
//    @Test
//    public void testDeleteProviderConnection() {
//        doNothing().when(providerConnectionService).deleteProviderConnection(anyLong());
//        providerConnectionController.deleteProviderConnection(providerConnectionDTO.getId());
//        verify(providerConnectionService, times(1)).deleteProviderConnection(providerConnectionDTO.getId());
//    }
//}
//
