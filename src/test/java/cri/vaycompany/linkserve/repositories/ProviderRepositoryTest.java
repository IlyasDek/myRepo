package cri.vaycompany.linkserve.repositories;

import cri.vaycompany.linkserve.models.Provider;
import cri.vaycompany.linkserve.repositories.ProviderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProviderRepositoryTest {

    @Autowired
    private ProviderRepository providerRepository;

    private Provider provider;

    @BeforeEach
    public void setUp() {
        provider = new Provider();
        // Здесь вы можете установить любые свойства для вашего провайдера, которые требуются для теста
        // Например:;
        provider.setName("Test Provider");
    }

    @Test
    public void itShouldSaveProvider() {
        Provider savedProvider = providerRepository.save(provider);
        assertNotNull(savedProvider);
    }

    @Test
    public void itShouldFindProviderById() {
        Provider savedProvider = providerRepository.save(provider);
        Optional<Provider> foundProvider = providerRepository.findById(savedProvider.getId());
        assertTrue(foundProvider.isPresent());
        assertEquals(savedProvider.getId(), foundProvider.get().getId());
    }

    @Test
    public void itShouldUpdateProvider() {
        Provider savedProvider = providerRepository.save(provider);
        // Измените здесь любые свойства провайдера для проверки обновления
        // Например:
        savedProvider.setName("Updated Provider");
        Provider updatedProvider = providerRepository.save(savedProvider);
        assertEquals(savedProvider, updatedProvider);
    }

    @Test
    public void itShouldDeleteProvider() {
        Provider savedProvider = providerRepository.save(provider);
        providerRepository.deleteById(savedProvider.getId());
        Optional<Provider> foundProvider = providerRepository.findById(savedProvider.getId());
        assertTrue(foundProvider.isEmpty());
    }
}
