package cri.vaycompany.linkserve.services;

import cri.vaycompany.linkserve.models.Provider;
import cri.vaycompany.linkserve.models.ServiceOffer;
import cri.vaycompany.linkserve.repositories.ServiceOfferRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ServiceOfferServiceTest {

    @InjectMocks
    private ServiceOfferService service;

    @Mock
    private ServiceOfferRepo repository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createServiceOfferTest() {
        ServiceOffer serviceOffer = new ServiceOffer();
        when(repository.save(any())).thenReturn(serviceOffer);
        assertEquals(serviceOffer, service.createServiceOffer(serviceOffer));
    }

    @Test
    public void getServiceOfferByIdTest() {
        ServiceOffer serviceOffer = new ServiceOffer();
        when(repository.findById(any())).thenReturn(Optional.of(serviceOffer));
        assertEquals(Optional.of(serviceOffer), service.getServiceOfferById(1L));
    }

    @Test
    public void updateServiceOfferTest() {
        ServiceOffer serviceOffer = new ServiceOffer();
        when(repository.save(any())).thenReturn(serviceOffer);
        assertEquals(serviceOffer, service.updateServiceOffer(serviceOffer));
    }

    @Test
    public void deleteServiceOfferTest() {
        ServiceOffer serviceOffer = new ServiceOffer();
        Provider provider = new Provider();

        provider.setName("Vasya");
        provider.setPassword("12312");
        provider.setPhoneNumber("4848484");
        provider.setEmail("dsadds@asdads.dsd");
        provider.setContactData("asdasdasd asdasd");

        serviceOffer.setProvider(provider);

        // Мокаем вызов repository.save(), чтобы он возвращал serviceOffer с провайдером
        when(repository.save(any())).thenReturn(serviceOffer);
        ServiceOffer savedServiceOffer = service.createServiceOffer(serviceOffer);

        // Мокаем вызов repository.deleteById()
        doNothing().when(repository).deleteById(any());
        service.deleteServiceOffer(savedServiceOffer.getId());

        // Мокаем вызов repository.findById(), чтобы он возвращал пустой Optional после удаления
        when(repository.findById(any())).thenReturn(Optional.empty());
        Optional<ServiceOffer> foundServiceOffer = service.getServiceOfferById(savedServiceOffer.getId());

        assertFalse(foundServiceOffer.isPresent());
    }
}
