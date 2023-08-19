//package cri.vaycompany.linkserve.controllers;
//
//import cri.vaycompany.linkserve.dto.UserConnectionDTO;
//import cri.vaycompany.linkserve.services.UserConnectionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/userconnections")
//public class UserConnectionController {
//
//    private final UserConnectionService userConnectionService;
//
//    @Autowired
//    public UserConnectionController(UserConnectionService userConnectionService) {
//        this.userConnectionService = userConnectionService;
//    }
//
//    @PostMapping
//    public ResponseEntity<UserConnectionDTO> createUserConnection(@RequestBody UserConnectionDTO userConnectionDTO) {
//        UserConnectionDTO savedUserConnection = userConnectionService.createUserConnection(userConnectionDTO);
//        return new ResponseEntity<>(savedUserConnection, HttpStatus.CREATED);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<UserConnectionDTO>> getAllUserConnections() {
//        List<UserConnectionDTO> userConnectionDTOS = userConnectionService.getAllUserConnections();
//        return new ResponseEntity<>(userConnectionDTOS, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserConnectionDTO> getUserConnectionById(@PathVariable Long id) {
//        Optional<UserConnectionDTO> userConnectionDTO = userConnectionService.getUserConnectionById(id);
//        if (userConnectionDTO.isPresent()) {
//            return new ResponseEntity<>(userConnectionDTO.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//
//    @PutMapping("/{id}/confirm")
//    public ResponseEntity<UserConnectionDTO> confirmUserConnection(@PathVariable Long id) {
//        UserConnectionDTO userConnectionDTO = userConnectionService.confirmUserConnection(id);
//        return new ResponseEntity<>(userConnectionDTO, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}/decline")
//    public ResponseEntity<Void> declineUserConnection(@PathVariable Long id) {
//        userConnectionService.declineUserConnection(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//}
//
