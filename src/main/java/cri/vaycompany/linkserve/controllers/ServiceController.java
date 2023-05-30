package cri.vaycompany.linkserve.controllers;

import cri.vaycompany.linkserve.models.ServiceProvider;
import cri.vaycompany.linkserve.services.ServiceProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceProviderService serviceService;

    @PostMapping("/add")
    public ResponseEntity<ServiceProvider> addService(@RequestBody ServiceProvider serviceProvider) {
        return ResponseEntity.ok(serviceService.addService(serviceProvider));
    }

    // TODO: Implement other service-related endpoints.
}