package cri.vaycompany.linkserve.controllers;

import cri.vaycompany.linkserve.models.ServiceOffer;
import cri.vaycompany.linkserve.services.ServiceOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ServiceOffer> createServiceOffer(@RequestBody ServiceOffer serviceOffer) {
        return ResponseEntity.ok(serviceOfferService.createServiceOffer(serviceOffer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffer> getServiceOfferById(@PathVariable Long id) {
        return serviceOfferService.getServiceOfferById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOffer> updateServiceOffer(@PathVariable Long id, @RequestBody ServiceOffer serviceOffer) {
        if (!id.equals(serviceOffer.getId())) {
            throw new IllegalArgumentException("Id in the path and in the request body must be the same");
        }
        return serviceOfferService.getServiceOfferById(id)
                .map(existingServiceOffer -> {
                    return ResponseEntity.ok(serviceOfferService.updateServiceOffer(serviceOffer));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceOffer(@PathVariable Long id) {
        serviceOfferService.deleteServiceOffer(id);
        return ResponseEntity.noContent().build();
    }
}
