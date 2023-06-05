package cri.vaycompany.linkserve.services;

import cri.vaycompany.linkserve.models.ServiceOffer;
import cri.vaycompany.linkserve.repositories.ServiceOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceOfferService {

    private final ServiceOfferRepo serviceOfferRepository;

    @Autowired
    public ServiceOfferService(ServiceOfferRepo serviceOfferRepository) {
        this.serviceOfferRepository = serviceOfferRepository;
    }

    public ServiceOffer createServiceOffer(ServiceOffer serviceOffer) {
        return serviceOfferRepository.save(serviceOffer);
    }

    public Optional<ServiceOffer> getServiceOfferById(Long id) {
        return serviceOfferRepository.findById(id);
    }

    public ServiceOffer updateServiceOffer(ServiceOffer serviceOffer) {
        return serviceOfferRepository.save(serviceOffer);
    }

    public void deleteServiceOffer(Long id) {
        serviceOfferRepository.deleteById(id);
    }
}
