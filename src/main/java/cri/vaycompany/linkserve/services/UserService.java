package cri.vaycompany.linkserve.services;

import cri.vaycompany.linkserve.dto.SubscriptionDTO;
import cri.vaycompany.linkserve.dto.UserConnectionDTO;
import cri.vaycompany.linkserve.dto.UserPrivateDTO;
import cri.vaycompany.linkserve.dto.UserPublicDTO;
import cri.vaycompany.linkserve.exceprions.UserNotFoundException;
import cri.vaycompany.linkserve.models.User;
import cri.vaycompany.linkserve.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ConnectionService connectionService;
    private final SubscriptionService subscriptionService;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper,
                       ConnectionService connectionService, SubscriptionService subscriptionService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.subscriptionService = subscriptionService;
        this.connectionService = connectionService;
    }

    public List<UserPublicDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToPublicDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserPublicDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::convertToPublicDTO);
    }

    public UserPublicDTO createUser(UserPrivateDTO userDTO) {
        User user = convertToEntity(userDTO);
        User createdUser = userRepository.save(user);
        return convertToPublicDTO(createdUser);
    }

    public UserPublicDTO updateUser(UserPrivateDTO userDTO) {
        User existingUser = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new UserNotFoundException("User with id " + userDTO.getId() + " not found"));

        User user = convertToEntity(userDTO);
        User updatedUser = userRepository.save(user);
        return convertToPublicDTO(updatedUser);
    }

    public SubscriptionDTO subscribeUserToProvider(Long userId, Long providerId) {
        return subscriptionService.createSubscription(userId, providerId);
    }

    public List<SubscriptionDTO> getUserSubscriptions(Long userId) {
        return subscriptionService.getUserSubscriptions(userId);
    }

    public void unsubscribeUserFromProvider(Long subscriptionId) {
        subscriptionService.deleteSubscription(subscriptionId);
    }

    public List<UserConnectionDTO> getUserConnections(Long userId) {
        return connectionService.getUserConfirmedConnections(userId);
    }

    public UserConnectionDTO createConnection(UserConnectionDTO userConnectionDTO) {
        return connectionService.createUserConnection(userConnectionDTO);
    }

    public UserConnectionDTO updateConnection(Long connectionId) {
        return connectionService.confirmUserConnection(connectionId);
    }

    public void deleteConnection(Long connectionId) {
        connectionService.deleteUserConnection(connectionId);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    private User convertToEntity(UserPrivateDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private UserPublicDTO convertToPublicDTO(User user) {
        return modelMapper.map(user, UserPublicDTO.class);
    }
}
