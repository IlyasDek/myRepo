package cri.vaycompany.linkserve.controllers;

import cri.vaycompany.linkserve.models.Connection;
import cri.vaycompany.linkserve.services.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/connections")
@RequiredArgsConstructor
public class ConnectionController {

    private final ConnectionService connectionService;

    @PostMapping("/add")
    public ResponseEntity<Connection> addConnection(@RequestBody Connection connection) {
        return ResponseEntity.ok(connectionService.addConnection(connection));
    }

    @PutMapping("/update")
    public ResponseEntity<Connection> updateConnection(@RequestBody Connection connection) {
        return ResponseEntity.ok(connectionService.updateConnection(connection));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteConnection(@PathVariable Long id) {
        connectionService.deleteConnection(id);
        return ResponseEntity.noContent().build();
    }

    // TODO: Implement other connection-related endpoints.
}
