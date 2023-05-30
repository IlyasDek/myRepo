package cri.vaycompany.linkserve.services;


import cri.vaycompany.linkserve.models.ServiceProvider;
import cri.vaycompany.linkserve.repositories.ServiceProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceProviderService {

    private final ServiceProviderRepository serviceProviderRepository;

    public ServiceProvider addService(ServiceProvider serviceProvider) {
        // TODO: Add validation
        return serviceProviderRepository.save(serviceProvider);
    }

    // TODO: Implement other service-related methods.
}

