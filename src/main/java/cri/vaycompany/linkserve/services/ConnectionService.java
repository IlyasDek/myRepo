package cri.vaycompany.linkserve.services;

import cri.vaycompany.linkserve.models.Connection;
import cri.vaycompany.linkserve.repositories.ConnectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConnectionService {

    private final ConnectionRepository connectionRepository;

    public Connection addConnection(Connection connection) {
        // TODO: Add validation
        return connectionRepository.save(connection);
    }

    public Connection updateConnection(Connection connection) {
        // TODO: Add validation
        return connectionRepository.save(connection);
    }

    public void deleteConnection(Long id) {
        // TODO: Add validation
        connectionRepository.deleteById(id);
    }

    // TODO: Implement other connection-related methods.
}