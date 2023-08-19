package cri.vaycompany.linkserve.services;

import cri.vaycompany.linkserve.dto.ServiceOfferDTO;
import cri.vaycompany.linkserve.exceprions.ProviderMismatchException;
import cri.vaycompany.linkserve.exceprions.ServiceOfferIdMismatchException;
import cri.vaycompany.linkserve.exceprions.ServiceOfferNotFoundException;
import cri.vaycompany.linkserve.models.Provider;
import cri.vaycompany.linkserve.models.ServiceOffer;
import cri.vaycompany.linkserve.repositories.ServiceOfferRepo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceOfferService {

    private final ServiceOfferRepo serviceOfferRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ServiceOfferService(ServiceOfferRepo serviceOfferRepository, ModelMapper modelMapper) {
        this.serviceOfferRepository = serviceOfferRepository;
        this.modelMapper = modelMapper;
    }

    public ServiceOfferDTO createServiceOffer(ServiceOfferDTO serviceOfferDTO) {
        ServiceOffer serviceOffer = modelMapper.map(serviceOfferDTO, ServiceOffer.class);
        ServiceOffer savedServiceOffer = serviceOfferRepository.save(serviceOffer);
        return modelMapper.map(savedServiceOffer, ServiceOfferDTO.class);
    }

    public Optional<ServiceOfferDTO> getServiceOfferById(Long id) {
        return serviceOfferRepository.findById(id)
                .map(serviceOffer -> modelMapper.map(serviceOffer, ServiceOfferDTO.class));
    }

    public ServiceOfferDTO updateServiceOffer(Long id, ServiceOfferDTO serviceOfferDTO) {
        if (!id.equals(serviceOfferDTO.getId())) {
            throw new ServiceOfferIdMismatchException("Id in the path and in the request body must be the same");
        }

        ServiceOffer existingServiceOffer = serviceOfferRepository.findById(id)
                .orElseThrow(() -> new ServiceOfferNotFoundException("ServiceOffer with id " + id + " not found"));

        Provider existingProvider = existingServiceOffer.getProvider();
        Provider providerInDTO = modelMapper.map(serviceOfferDTO.getProvider(), Provider.class);

        if (!existingProvider.getId().equals(providerInDTO.getId())) {
            throw new ProviderMismatchException("Provider in the existing service offer and in the request body must be the same");
        }

        modelMapper.map(serviceOfferDTO, existingServiceOffer);
        ServiceOffer updatedServiceOffer = serviceOfferRepository.save(existingServiceOffer);
        return modelMapper.map(updatedServiceOffer, ServiceOfferDTO.class);
    }
    public void deleteServiceOffer(Long id) {
        if (!serviceOfferRepository.existsById(id)) {
            throw new ServiceOfferNotFoundException("ServiceOffer with id " + id + " not found");
        }
        serviceOfferRepository.deleteById(id);
    }
}
