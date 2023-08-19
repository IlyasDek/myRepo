package cri.vaycompany.linkserve.services;

import cri.vaycompany.linkserve.dto.*;
import cri.vaycompany.linkserve.enums.Status;
import cri.vaycompany.linkserve.models.*;
import cri.vaycompany.linkserve.repositories.ProviderRepository;
import cri.vaycompany.linkserve.repositories.SubscriptionRepository;
import cri.vaycompany.linkserve.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.ProviderNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProviderService {
    private final UserRepository userRepository;
    private final ProviderRepository providerRepository;
//    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionService subscriptionService;
    private final ModelMapper modelMapper;
    private final ConnectionService connectionService;

    @Autowired
    public ProviderService(UserRepository userRepository,
                           ProviderRepository providerRepository,
                           SubscriptionService subscriptionService,
                           ModelMapper modelMapper,
                           ConnectionService connectionService) {
        this.userRepository = userRepository;
        this.providerRepository = providerRepository;
        this.subscriptionService = subscriptionService;
        this.modelMapper = modelMapper;
        this.connectionService = connectionService;
    }

    public ProviderPublicDTO createProvider(ProviderPrivateDTO providerPrivateDTO) {
        Provider provider = modelMapper.map(providerPrivateDTO, Provider.class);
        Provider createdProvider = providerRepository.save(provider);
        return modelMapper.map(createdProvider, ProviderPublicDTO.class);
    }

    public Optional<ProviderPublicDTO> getProviderById(Long id) {
        Optional<Provider> provider = providerRepository.findById(id);
        return provider.map(providerEntity -> modelMapper.map(providerEntity, ProviderPublicDTO.class));
    }

    public Optional<ProviderPublicDTO> updateProvider(Long id, ProviderPrivateDTO providerPrivateDTO) {
        if (!id.equals(providerPrivateDTO.getId())) {
            throw new IllegalArgumentException("ID in the path and in the request body must be the same");
        }
        Optional<Provider> existingProvider = providerRepository.findById(providerPrivateDTO.getId());

        if (existingProvider.isPresent()) {
            Provider providerToUpdate = existingProvider.get();

            providerToUpdate.setName(providerPrivateDTO.getName());
            providerToUpdate.setContactData(providerPrivateDTO.getContactData());
            providerToUpdate.setEmail(providerPrivateDTO.getEmail());
            providerToUpdate.setPassword(providerPrivateDTO.getPassword());
            providerToUpdate.setPhoneNumber(providerPrivateDTO.getPhoneNumber());
            providerToUpdate.setLocation(providerPrivateDTO.getLocation());
            providerToUpdate.setServiceDescription(providerPrivateDTO.getServiceDescription());
            providerToUpdate.setPersonalRating(providerPrivateDTO.getPersonalRating());
            providerToUpdate.setGroupRating(providerPrivateDTO.getGroupRating());
            providerToUpdate.setRecommendationRating(providerPrivateDTO.getRecommendationRating());
            providerToUpdate.setStatus(providerPrivateDTO.getStatus());

            if (providerPrivateDTO.getServiceOffers() != null) {
                Set<ServiceOffer> updatedServices = providerPrivateDTO.getServiceOffers().stream()
                        .map(serviceOfferDTO -> modelMapper.map(serviceOfferDTO, ServiceOffer.class))
                        .collect(Collectors.toSet());
                providerToUpdate.setServiceOffers(updatedServices);
            }

            Provider updatedProvider = providerRepository.save(providerToUpdate);
            return Optional.of(modelMapper.map(updatedProvider, ProviderPublicDTO.class));
        } else {
            return Optional.empty();
        }
    }

    public ProviderConnectionDTO createProviderConnection(Long providerOneId, Long providerTwoId) {
        Provider providerOne = providerRepository.findById(providerOneId)
                .orElseThrow(() -> new ProviderNotFoundException("Provider with id " + providerOneId + " not found"));
        Provider providerTwo = providerRepository.findById(providerTwoId)
                .orElseThrow(() -> new ProviderNotFoundException("Provider with id " + providerTwoId + " not found"));

        ProviderConnectionDTO providerConnectionDTO = new ProviderConnectionDTO();
        providerConnectionDTO.setProviderOne(providerOne);
        providerConnectionDTO.setProviderTwo(providerTwo);

        return connectionService.createProviderConnection(providerConnectionDTO);
    }

    public SubscriptionDTO createSubscription(Long providerId, Long userId) {
        return subscriptionService.createSubscription(userId, providerId);
    }

    public List<SubscriptionDTO> getProviderSubscriptions(Long providerId) {
        return subscriptionService.getProviderSubscriptions(providerId);
    }

    public void deleteSubscription(Long subscriptionId) {
        subscriptionService.deleteSubscription(subscriptionId);
    }

    public void deleteProvider(Long id) {
        Optional<Provider> existingProvider = providerRepository.findById(id);
        if (existingProvider.isPresent()) {
            Provider providerToDelete = existingProvider.get();
            providerToDelete.setStatus(Status.DELETED);
            providerRepository.save(providerToDelete);
        } else {
            throw new IllegalArgumentException("No provider with the provided ID found");
        }
    }
    public Provider findProviderById(Long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new ProviderNotFoundException("Provider with id " + id + " not found"));
    }
}
