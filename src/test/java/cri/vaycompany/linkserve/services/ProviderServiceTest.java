//package cri.vaycompany.linkserve.services;
//
//import cri.vaycompany.linkserve.dto.ProviderPrivateDTO;
//import cri.vaycompany.linkserve.dto.ProviderPublicDTO;
//import cri.vaycompany.linkserve.models.Provider;
//import cri.vaycompany.linkserve.repositories.ProviderRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class ProviderServiceTest {
//
//    @Mock
//    private ProviderRepository providerRepository;
//
//    private ProviderService providerService;
//    private ProviderPrivateDTO providerPrivateDTO;
//    private ProviderPublicDTO providerPublicDTO;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        providerService = new ProviderService(providerRepository, new ModelMapper());
//
//        providerPrivateDTO = new ProviderPrivateDTO();
//        providerPrivateDTO.setName("Test Provider");
//        providerPrivateDTO.setId(1L);
//
//        providerPublicDTO = new ProviderPublicDTO();
//        providerPublicDTO.setName("Test Provider");
//        providerPublicDTO.setId(1L);
//
//        Provider provider = new Provider();
//        provider.setName("Test Provider");
//        provider.setId(1L);
//
//        when(providerRepository.save(any(Provider.class))).thenReturn(provider);
//        when(providerRepository.findById(anyLong())).thenReturn(Optional.of(provider));
//    }
//
//    @Test
//    public void testCreateProvider() {
//        ProviderPublicDTO createdProviderPublicDTO = providerService.createProvider(providerPrivateDTO);
//        assertNotNull(createdProviderPublicDTO);
//    }
//
//    @Test
//    public void testGetProviderById() {
//        Optional<ProviderPublicDTO> foundProviderPublicDTO = providerService.getProviderById(1L);
//        assertTrue(foundProviderPublicDTO.isPresent());
//        assertEquals(providerPublicDTO.getName(), foundProviderPublicDTO.get().getName());
//    }
//
//    @Test
//    public void testUpdateProvider() {
//        providerPrivateDTO.setName("Updated Provider");
//        Optional<ProviderPublicDTO> updatedProviderPublicDTO = providerService.updateProvider(1L, providerPrivateDTO);
//        assertTrue(updatedProviderPublicDTO.isPresent());
//        assertEquals("Updated Provider", updatedProviderPublicDTO.get().getName());
//    }
//
//    @Test
//    public void testDeleteProvider() {
//        doNothing().when(providerRepository).deleteById(anyLong());
//        providerService.deleteProvider(1L);
//        verify(providerRepository, times(1)).deleteById(1L);
//    }
//}
