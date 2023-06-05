package cri.vaycompany.linkserve.controllers;

import cri.vaycompany.linkserve.models.Provider;
import cri.vaycompany.linkserve.services.ProviderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProviderControllerTest {

    @InjectMocks
    ProviderController providerController;

    @Mock
    ProviderService providerService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProvider() {
        Provider provider = new Provider();
        when(providerService.createProvider(any(Provider.class))).thenReturn(provider);
        Provider result = providerController.createProvider(new Provider());
        assertEquals(provider, result);
        verify(providerService, times(1)).createProvider(any(Provider.class));
    }

    @Test
    public void testGetProviderById() {
        Provider provider = new Provider();
        when(providerService.getProviderById(any(Long.class))).thenReturn(Optional.of(provider));
        ResponseEntity<Provider> result = providerController.getProviderById(1L);
        assertEquals(provider, result.getBody());
        verify(providerService, times(1)).getProviderById(any(Long.class));
    }

    @Test
    public void testUpdateProvider() {
        Provider provider = new Provider();
        when(providerService.getProviderById(any(Long.class))).thenReturn(Optional.of(provider));
        when(providerService.updateProvider(any(Provider.class))).thenReturn(provider);
        ResponseEntity<Provider> result = providerController.updateProvider(1L, new Provider());
        assertEquals(provider, result.getBody());
        verify(providerService, times(1)).updateProvider(any(Provider.class));
    }

    @Test
    public void testDeleteProvider() {
        Provider provider = new Provider();
        when(providerService.getProviderById(any(Long.class))).thenReturn(Optional.of(provider));
        doNothing().when(providerService).deleteProvider(any(Long.class));
        providerController.deleteProvider(1L);
        verify(providerService, times(1)).deleteProvider(any(Long.class));
    }
}
