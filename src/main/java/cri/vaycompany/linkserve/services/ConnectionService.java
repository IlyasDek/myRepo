package cri.vaycompany.linkserve.services;

import cri.vaycompany.linkserve.dto.ProviderConnectionDTO;
import cri.vaycompany.linkserve.dto.SubscriptionDTO;
import cri.vaycompany.linkserve.dto.UserConnectionDTO;
import cri.vaycompany.linkserve.exceprions.ConnectionNotFoundException;;
import cri.vaycompany.linkserve.models.ProviderConnection;
import cri.vaycompany.linkserve.models.Subscription;
import cri.vaycompany.linkserve.models.UserConnection;
import cri.vaycompany.linkserve.repositories.ProviderConnectionRepository;
import cri.vaycompany.linkserve.repositories.SubscriptionRepository;
import cri.vaycompany.linkserve.repositories.UserConnectionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConnectionService {
    private final UserConnectionRepository userConnectionRepository;
    private final ProviderConnectionRepository providerConnectionRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ConnectionService(UserConnectionRepository userConnectionRepository,
                             ProviderConnectionRepository providerConnectionRepository,
                             SubscriptionRepository subscriptionRepository,
                             ModelMapper modelMapper) {
        this.userConnectionRepository = userConnectionRepository;
        this.providerConnectionRepository = providerConnectionRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.modelMapper = modelMapper;
    }

    public UserConnectionDTO createUserConnection(UserConnectionDTO userConnectionDTO) {
        UserConnection userConnection = modelMapper.map(userConnectionDTO, UserConnection.class);
        userConnection.setConfirmed(false);
        UserConnection savedUserConnection = userConnectionRepository.save(userConnection);
        return modelMapper.map(savedUserConnection, UserConnectionDTO.class);
    }

    public UserConnectionDTO confirmUserConnection(Long id) {
        UserConnection userConnection = userConnectionRepository.findById(id)
                .orElseThrow(() -> new ConnectionNotFoundException("UserConnection with id " + id + " not found"));
        userConnection.setConfirmed(true);
        UserConnection confirmedUserConnection = userConnectionRepository.save(userConnection);
        return modelMapper.map(confirmedUserConnection, UserConnectionDTO.class);
    }

    public void deleteUserConnection(Long id) {
        userConnectionRepository.deleteById(id);
    }

    public List<UserConnectionDTO> getUserConfirmedConnections(Long userId){
        return userConnectionRepository.findByUserOneIdOrUserTwoId(userId, userId).stream()
                .filter(UserConnection::isConfirmed)
                .map(userConnection -> modelMapper.map(userConnection, UserConnectionDTO.class))
                .collect(Collectors.toList());
    }

    public List<UserConnectionDTO> getConnectionsOfUser(Long userId) {
        List<UserConnection> connections = userConnectionRepository.findByUserOneIdOrUserTwoId(userId, userId);
        return connections.stream()
                .map(connection -> modelMapper.map(connection, UserConnectionDTO.class))
                .collect(Collectors.toList());
    }

    public ProviderConnectionDTO createProviderConnection(ProviderConnectionDTO providerConnectionDTO) {
        ProviderConnection providerConnection = modelMapper.map(providerConnectionDTO, ProviderConnection.class);
        providerConnection.setDeleted(false);
        providerConnection.setConfirmed(false);
        ProviderConnection savedProviderConnection = providerConnectionRepository.save(providerConnection);
        return modelMapper.map(savedProviderConnection, ProviderConnectionDTO.class);
    }
}
