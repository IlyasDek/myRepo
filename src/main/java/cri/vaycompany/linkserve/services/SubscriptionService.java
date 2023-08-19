package cri.vaycompany.linkserve.services;

import cri.vaycompany.linkserve.dto.SubscriptionDTO;
import cri.vaycompany.linkserve.exceprions.SubscriptionNotFoundException;
import cri.vaycompany.linkserve.exceprions.UserNotFoundException;
import cri.vaycompany.linkserve.models.Provider;
import cri.vaycompany.linkserve.models.Subscription;
import cri.vaycompany.linkserve.models.User;
import cri.vaycompany.linkserve.repositories.ProviderRepository;
import cri.vaycompany.linkserve.repositories.SubscriptionRepository;
import cri.vaycompany.linkserve.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.ProviderNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final ProviderRepository providerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository,
                               UserRepository userRepository,
                               ProviderRepository providerRepository,
                               ModelMapper modelMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.providerRepository = providerRepository;
        this.modelMapper = modelMapper;
    }

    public SubscriptionDTO createSubscription(Long userId, Long providerId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));
        Provider provider = providerRepository.findById(providerId) // использование новой зависимости
                .orElseThrow(() -> new ProviderNotFoundException("Provider with id " + providerId + " not found"));

        // Check if subscription already exists
        Subscription existingSubscription = subscriptionRepository.findByUserAndProvider(user, provider);
        if (existingSubscription != null) {
            throw new IllegalArgumentException("Subscription already exists");
        }

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setProvider(provider);

        Subscription createdSubscription = subscriptionRepository.save(subscription);

        return convertToDTO(createdSubscription);
    }

    public List<SubscriptionDTO> getUserSubscriptions(Long userId) {
        return subscriptionRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SubscriptionDTO> getProviderSubscriptions(Long providerId) {
        List<Subscription> subscriptions = subscriptionRepository.findByProviderId(providerId);
        return subscriptions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteSubscription(Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription with id " + subscriptionId + " not found"));
        subscriptionRepository.delete(subscription);
    }

    private SubscriptionDTO convertToDTO(Subscription subscription) {
        return modelMapper.map(subscription, SubscriptionDTO.class);
    }

    private Subscription convertToEntity(SubscriptionDTO subscriptionDTO) {
        return modelMapper.map(subscriptionDTO, Subscription.class);
    }
}

