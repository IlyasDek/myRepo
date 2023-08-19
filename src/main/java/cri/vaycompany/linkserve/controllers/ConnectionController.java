//package cri.vaycompany.linkserve.controllers;
//
//import cri.vaycompany.linkserve.dto.ConnectionDTO;
//import cri.vaycompany.linkserve.services.ConnectionService;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/connections")
//public class ConnectionController {
//
//    private final ConnectionService connectionService;
//    private ModelMapper modelMapper;
//
//    @Autowired
//    public ConnectionController(ConnectionService connectionService, ModelMapper modelMapper) {
//        this.connectionService = connectionService;
//        this.modelMapper = modelMapper;
//    }
//
//    @PostMapping
//    public ConnectionDTO createConnection(@RequestBody ConnectionDTO connectionDto) {
//        return connectionService.createConnection(connectionDto);
//    }
//
//    // новый эндпоинт для подтверждения связи
//    @PutMapping("/{id}/confirm")
//    public ConnectionDTO confirmConnection(@PathVariable Long id) {
//        return connectionService.confirmConnection(id);
//    }
//
//    // новый эндпоинт для отклонения связи
//    @DeleteMapping("/{id}/decline")
//    public void declineConnection(@PathVariable Long id) {
//        connectionService.declineConnection(id);
//    }
//
//    // новый эндпоинт для скрытия связи
//    @PutMapping("/{id}/hide")
//    public ConnectionDTO hideConnection(@PathVariable Long id) {
//        return connectionService.hideConnection(id);
//    }
//
//    @GetMapping
//    public List<ConnectionDTO> getAllConnections() {
//        return connectionService.getAllConnections();
//    }
//
//    @GetMapping("/user/{userId}")
//    public List<ConnectionDTO> getUserConnections(@PathVariable Long userId) {
//        return connectionService.getUserConnections(userId);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ConnectionDTO> getConnectionById(@PathVariable Long id) {
//        return connectionService.getConnectionById(id)
//                .map(connection -> ResponseEntity.ok(connection))
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ConnectionDTO> updateConnection(@PathVariable Long id, @RequestBody ConnectionDTO connectionDTO) {
//        if (!id.equals(connectionDTO.getId())) {
//            throw new IllegalArgumentException("Id in the path and in the request body must be the same");
//        }
//        return connectionService.getConnectionById(id)
//                .map(existingConnection -> {
//                    existingConnection.setTrustLevel(connectionDTO.getTrustLevel());
//                    return ResponseEntity.ok(connectionService.updateConnection(id, connectionDTO));
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteConnection(@PathVariable Long id) {
//        connectionService.deleteConnection(id);
//    }
//}
