//package cri.vaycompany.linkserve.repositories;
//
//import cri.vaycompany.linkserve.enums.Status;
//import cri.vaycompany.linkserve.models.Provider;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//public class ProviderRepositoryTest {
//
//    @Autowired
//    private ProviderRepository providerRepository;
//
//    private Provider provider;
//
//    @BeforeEach
//    public void setUp() {
//        provider = new Provider();
//        provider.setName("Test Provider");
//        provider.setContactData("test@example.com");
//        provider.setPassword("testPassword");
//        provider.setEmail("test@example.com");
//        provider.setPhoneNumber("1234567890");
//        provider.setLocation("Test Location");
//        provider.setServiceDescription("Test Description");
//        provider.setPersonalRating(4.83);
//        provider.setGroupRating(4.50);
//        provider.setRecommendationRating(4.65);
//        provider.setStatus(Status.ACTIVE);
//    }
//
//    @Test
//    public void itShouldSaveProvider() {
//        Provider savedProvider = providerRepository.save(provider);
//        assertNotNull(savedProvider);
//        assertEquals(savedProvider, provider);
//    }
//
//    @Test
//    public void itShouldFindProviderById() {
//        Provider savedProvider = providerRepository.save(provider);
//        Optional<Provider> foundProvider = providerRepository.findById(savedProvider.getId());
//        assertTrue(foundProvider.isPresent());
//        assertEquals(savedProvider.getId(), foundProvider.get().getId());
//    }
//
//    @Test
//    public void itShouldUpdateProvider() {
//        Provider savedProvider = providerRepository.save(provider);
//        savedProvider.setName("Updated Provider");
//        Provider updatedProvider = providerRepository.save(savedProvider);
//        assertEquals(savedProvider, updatedProvider);
//    }
//
//    @Test
//    public void itShouldNotDeleteProviderButMarkAsDeleted() {
//        Provider savedProvider = providerRepository.save(provider);
//        providerRepository.deleteById(savedProvider.getId());
//        Optional<Provider> foundProvider = providerRepository.findById(savedProvider.getId());
//        assertTrue(foundProvider.isPresent());
//        assertEquals(Status.DELETED, foundProvider.get().getStatus());
//    }
//}
