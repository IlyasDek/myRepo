//package cri.vaycompany.linkserve.controllers;
//
//import cri.vaycompany.linkserve.dto.ConnectionDTO;
//import cri.vaycompany.linkserve.services.ConnectionService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class ConnectionControllerTest {
//
//    @InjectMocks
//    private ConnectionController connectionController;
//
//    @Mock
//    private ConnectionService connectionService;
//
//    private ConnectionDTO connectionDTO;
//
//    @BeforeEach
//    public void setup() {
//        connectionDTO = new ConnectionDTO();
//        connectionDTO.setId(1L);
//        connectionDTO.setTrustLevel(1);
//    }
//
//    @Test
//    public void testCreateConnection() {
//        when(connectionService.createConnection(any(ConnectionDTO.class))).thenReturn(connectionDTO);
//        ConnectionDTO result = connectionController.createConnection(connectionDTO);
//        assertEquals(connectionDTO, result);
//    }
//
//    @Test
//    public void testConfirmConnection() {
//        when(connectionService.confirmConnection(any(Long.class))).thenReturn(connectionDTO);
//        ConnectionDTO result = connectionController.confirmConnection(1L);
//        assertEquals(connectionDTO, result);
//    }
//
//    @Test
//    public void testHideConnection() {
//        when(connectionService.hideConnection(any(Long.class))).thenReturn(connectionDTO);
//        ConnectionDTO result = connectionController.hideConnection(1L);
//        assertEquals(connectionDTO, result);
//    }
//
//    @Test
//    public void testDeclineConnection() {
//        // Этот метод возвращает void, поэтому мы просто проверяем, что он не вызывает исключений
//        connectionController.declineConnection(1L);
//    }
//
//    @Test
//    public void testGetAllConnections() {
//        List<ConnectionDTO> connections = new ArrayList<>();
//        connections.add(connectionDTO);
//        when(connectionService.getAllConnections()).thenReturn(connections);
//        List<ConnectionDTO> results = connectionController.getAllConnections();
//        assertEquals(connections, results);
//    }
//
//    @Test
//    public void testGetUserConnections() {
//        List<ConnectionDTO> connections = new ArrayList<>();
//        connections.add(connectionDTO);
//        when(connectionService.getUserConnections(any(Long.class))).thenReturn(connections);
//        List<ConnectionDTO> results = connectionController.getUserConnections(1L);
//        assertEquals(connections, results);
//    }
//
//    @Test
//    public void testGetConnectionById() {
//        when(connectionService.getConnectionById(any(Long.class))).thenReturn(Optional.of(connectionDTO));
//        ResponseEntity<ConnectionDTO> result = connectionController.getConnectionById(1L);
//        assertEquals(connectionDTO, result.getBody());
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//    }
//
//    @Test
//    public void testUpdateConnection() {
//        when(connectionService.getConnectionById(any(Long.class))).thenReturn(Optional.of(connectionDTO));
//        when(connectionService.updateConnection(any(Long.class), any(ConnectionDTO.class))).thenReturn(connectionDTO);
//        ResponseEntity<ConnectionDTO> result = connectionController.updateConnection(1L, connectionDTO);
//        assertEquals(connectionDTO, result.getBody());
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//    }
//}
