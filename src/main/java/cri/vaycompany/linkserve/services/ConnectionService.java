package cri.vaycompany.linkserve.services;

import cri.vaycompany.linkserve.models.Connection;
import cri.vaycompany.linkserve.repositories.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConnectionService {

    private final ConnectionRepository connectionRepository;

    @Autowired
    public ConnectionService(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    public Connection createConnection(Connection connection) {
        return connectionRepository.save(connection);
    }

    public Optional<Connection> getConnectionById(Long id) {
        return connectionRepository.findById(id);
    }

    public List<Connection> getAllConnections(){
        return connectionRepository.findAll();
    }

    public List<Connection> getUserConnections(Long userId){
        return connectionRepository.findByUserId(userId);
    }

    public Connection updateConnection(Connection connection) {
        return connectionRepository.save(connection);
    }

    public void deleteConnection(Long id) {
        connectionRepository.deleteById(id);
    }
}
