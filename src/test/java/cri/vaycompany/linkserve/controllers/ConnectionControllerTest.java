package cri.vaycompany.linkserve.controllers;

import cri.vaycompany.linkserve.models.Connection;
import cri.vaycompany.linkserve.services.ConnectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConnectionControllerTest {

    @InjectMocks
    private ConnectionController connectionController;

    @Mock
    private ConnectionService connectionService;

    private Connection connection;

    @BeforeEach
    public void setup() {
        connection = new Connection();
        connection.setId(1L);
        connection.setTrustLevel(1);
    }

    @Test
    public void testCreateConnection() {
        when(connectionService.createConnection(any(Connection.class))).thenReturn(connection);
        Connection result = connectionController.createConnection(connection);
        assertEquals(connection, result);
    }

    @Test
    public void testGetAllConnections() {
        List<Connection> connections = new ArrayList<>();
        connections.add(connection);
        when(connectionService.getAllConnections()).thenReturn(connections);
        List<Connection> results = connectionController.getAllConnections();
        assertEquals(connections, results);
    }

    @Test
    public void testGetUserConnections() {
        List<Connection> connections = new ArrayList<>();
        connections.add(connection);
        when(connectionService.getUserConnections(any(Long.class))).thenReturn(connections);
        List<Connection> results = connectionController.getUserConnections(1L);
        assertEquals(connections, results);
    }

    @Test
    public void testGetConnectionById() {
        when(connectionService.getConnectionById(any(Long.class))).thenReturn(Optional.of(connection));
        ResponseEntity<Connection> result = connectionController.getConnectionById(1L);
        assertEquals(connection, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testUpdateConnection() {
        when(connectionService.getConnectionById(any(Long.class))).thenReturn(Optional.of(connection));
        when(connectionService.updateConnection(any(Connection.class))).thenReturn(connection);
        ResponseEntity<Connection> result = connectionController.updateConnection(1L, connection);
        assertEquals(connection, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
