package cri.vaycompany.linkserve.repositories;

import cri.vaycompany.linkserve.models.Provider;
import cri.vaycompany.linkserve.models.ServiceOffer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ServiceOfferRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ServiceOfferRepo serviceOfferRepo;

    @Test
    public void saveServiceOfferWithEntityManagerTest() {
        ServiceOffer serviceOffer = new ServiceOffer();
        ServiceOffer savedServiceOffer = entityManager.persistAndFlush(serviceOffer);
        assertEquals(serviceOffer, savedServiceOffer);
    }

    @Test
    public void saveServiceOfferWithRepoTest() {
        ServiceOffer serviceOffer = new ServiceOffer();
        ServiceOffer savedServiceOffer = serviceOfferRepo.save(serviceOffer);
        assertNotNull(savedServiceOffer.getId());
    }

    @Test
    public void getServiceOfferByIdTest() {
        ServiceOffer serviceOffer = new ServiceOffer();
        ServiceOffer savedServiceOffer = entityManager.persistAndFlush(serviceOffer);
        Optional<ServiceOffer> foundServiceOffer = serviceOfferRepo.findById(savedServiceOffer.getId());
        assertEquals(Optional.of(savedServiceOffer), foundServiceOffer);
    }

    @Test
    public void deleteServiceOfferTest() {
        Provider provider = new Provider();
        provider.setName("Vasya");
        provider.setPassword("12312");
        provider.setPhoneNumber("4848484");
        provider.setEmail("dsadds@asdads.dsd");
        provider.setContactData("asdasdasd asdasd");

        // Сохраняем provider перед использованием его в ServiceOffer
        provider = entityManager.persistAndFlush(provider);

        ServiceOffer serviceOffer = new ServiceOffer();
        serviceOffer.setProvider(provider);

        ServiceOffer savedServiceOffer = entityManager.persistAndFlush(serviceOffer);
        Optional<ServiceOffer> foundServiceOffer = serviceOfferRepo.findById(savedServiceOffer.getId());
        assertEquals(Optional.of(savedServiceOffer), foundServiceOffer);

        serviceOfferRepo.deleteById(savedServiceOffer.getId());
        foundServiceOffer = serviceOfferRepo.findById(savedServiceOffer.getId());
        assertFalse(foundServiceOffer.isPresent());
    }

}
