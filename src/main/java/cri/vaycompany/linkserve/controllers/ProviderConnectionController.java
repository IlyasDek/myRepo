//package cri.vaycompany.linkserve.controllers;
//
//import cri.vaycompany.linkserve.dto.ProviderConnectionDTO;
//import cri.vaycompany.linkserve.services.ProviderConnectionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/providerconnections")
//public class ProviderConnectionController {
//
//    private final ProviderConnectionService providerConnectionService;
//
//    @Autowired
//    public ProviderConnectionController(ProviderConnectionService providerConnectionService) {
//        this.providerConnectionService = providerConnectionService;
//    }
//
//    @PostMapping
//    public ResponseEntity<ProviderConnectionDTO> createProviderConnection(@RequestBody ProviderConnectionDTO providerConnectionDTO) {
//        ProviderConnectionDTO createdProviderConnectionDTO = providerConnectionService.createProviderConnection(providerConnectionDTO);
//        return new ResponseEntity<>(createdProviderConnectionDTO, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}/confirm")
//    public ResponseEntity<ProviderConnectionDTO> confirmProviderConnection(@PathVariable Long id) {
//        ProviderConnectionDTO confirmedProviderConnectionDTO = providerConnectionService.confirmProviderConnection(id);
//        return new ResponseEntity<>(confirmedProviderConnectionDTO, HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}/decline")
//    public void declineProviderConnection(@PathVariable Long id) {
//        providerConnectionService.declineProviderConnection(id);
//    }
//
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ProviderConnectionDTO> getProviderConnectionById(@PathVariable Long id) {
//        Optional<ProviderConnectionDTO> providerConnectionDTOOptional = providerConnectionService.getProviderConnectionById(id);
//        return providerConnectionDTOOptional
//                .map(providerConnectionDTO -> new ResponseEntity<>(providerConnectionDTO, HttpStatus.OK))
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<ProviderConnectionDTO>> getAllProviderConnections() {
//        List<ProviderConnectionDTO> providerConnectionDTOS = providerConnectionService.getAllProviderConnections();
//        return new ResponseEntity<>(providerConnectionDTOS, HttpStatus.OK);
//    }
//
//    @GetMapping("/providers/{providerId}")
//    public ResponseEntity<List<ProviderConnectionDTO>> getProviderConnections(@PathVariable Long providerId) {
//        List<ProviderConnectionDTO> providerConnectionDTOS = providerConnectionService.getProviderConnections(providerId);
//        return new ResponseEntity<>(providerConnectionDTOS, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProviderConnection(@PathVariable Long id) {
//        providerConnectionService.deleteProviderConnection(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//}
