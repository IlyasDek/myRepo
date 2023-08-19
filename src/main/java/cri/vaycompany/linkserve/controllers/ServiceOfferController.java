package cri.vaycompany.linkserve.controllers;

import cri.vaycompany.linkserve.dto.ServiceOfferDTO;
import cri.vaycompany.linkserve.services.ServiceOfferService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/service-offers")
public class ServiceOfferController {

    private final ServiceOfferService serviceOfferService;

    @Autowired
    public ServiceOfferController(ServiceOfferService serviceOfferService) {
        this.serviceOfferService = serviceOfferService;
    }

    @PostMapping
    public ResponseEntity<ServiceOfferDTO> createServiceOffer(@RequestBody ServiceOfferDTO serviceOfferDTO) {
        ServiceOfferDTO createdServiceOffer = serviceOfferService.createServiceOffer(serviceOfferDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdServiceOffer.getId()).toUri();
        return ResponseEntity.created(location).body(createdServiceOffer);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ServiceOfferDTO> getServiceOfferById(@PathVariable Long id) {
        return serviceOfferService.getServiceOfferById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOfferDTO> updateServiceOffer(@PathVariable Long id, @RequestBody ServiceOfferDTO serviceOfferDTO) {
        try {
            ServiceOfferDTO updatedServiceOfferDTO = serviceOfferService.updateServiceOffer(id, serviceOfferDTO);
            return ResponseEntity.ok(updatedServiceOfferDTO);
        } catch (IllegalArgumentException | EntityNotFoundException ex) {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceOffer(@PathVariable Long id) {
        serviceOfferService.deleteServiceOffer(id);
        return ResponseEntity.noContent().build();
    }
}
