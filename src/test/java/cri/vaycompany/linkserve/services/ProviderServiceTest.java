package cri.vaycompany.linkserve.services;

import cri.vaycompany.linkserve.models.Provider;
import cri.vaycompany.linkserve.repositories.ProviderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProviderServiceTest {

    @Mock
    private ProviderRepository providerRepository;

    private ProviderService providerService;
    private Provider provider;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        providerService = new ProviderService(providerRepository);

        provider = new Provider();
        // Здесь вы можете установить любые свойства для вашего провайдера, которые требуются для теста
        // Например:
        provider.setName("Test Provider");
    }

    @Test
    public void itShouldCreateProvider() {
        when(providerRepository.save(any(Provider.class))).thenReturn(provider);
        Provider createdProvider = providerService.createProvider(provider);
        assertNotNull(createdProvider);
    }

    @Test
    public void itShouldGetProviderById() {
        when(providerRepository.findById(anyLong())).thenReturn(Optional.of(provider));
        Optional<Provider> foundProvider = providerService.getProviderById(1L);
        assertTrue(foundProvider.isPresent());
        assertEquals(provider, foundProvider.get());
    }

    @Test
    public void itShouldUpdateProvider() {
        when(providerRepository.save(any(Provider.class))).thenReturn(provider);
        Provider updatedProvider = providerService.updateProvider(provider);
        assertNotNull(updatedProvider);
    }

    @Test
    public void itShouldDeleteProvider() {
        doNothing().when(providerRepository).deleteById(anyLong());
        providerService.deleteProvider(1L);
        verify(providerRepository, times(1)).deleteById(1L);
    }
}

