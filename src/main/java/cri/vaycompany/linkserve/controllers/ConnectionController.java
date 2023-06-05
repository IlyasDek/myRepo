package cri.vaycompany.linkserve.controllers;

import cri.vaycompany.linkserve.models.Connection;
import cri.vaycompany.linkserve.services.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/connections")
public class ConnectionController {

    private final ConnectionService connectionService;

    @Autowired
    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @PostMapping
    public Connection createConnection(@RequestBody Connection connection) {
        return connectionService.createConnection(connection);
    }

    @GetMapping
    public List<Connection> getAllConnections() {
        return connectionService.getAllConnections();
    }

    @GetMapping("/{userId}")
    public List<Connection> getUserConnections(@PathVariable Long userId) {
        return connectionService.getUserConnections(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Connection> getConnectionById(@PathVariable Long id) {
        return connectionService.getConnectionById(id)
                .map(connection -> ResponseEntity.ok(connection))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Connection> updateConnection(@PathVariable Long id, @RequestBody Connection connection) {
        if (!id.equals(connection.getId())) {
            throw new IllegalArgumentException("Id in the path and in the request body must be the same");
        }
        return connectionService.getConnectionById(id)
                .map(existingConnection -> {
                    existingConnection.setTrustLevel(connection.getTrustLevel());
                    return ResponseEntity.ok(connectionService.updateConnection(existingConnection));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteConnection(@PathVariable Long id) {
        connectionService.deleteConnection(id);
    }
}
