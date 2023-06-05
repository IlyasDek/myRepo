package cri.vaycompany.linkserve.controllers;

import cri.vaycompany.linkserve.models.Provider;
import cri.vaycompany.linkserve.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    private final ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping
    public Provider createProvider(@RequestBody Provider provider) {
        return providerService.createProvider(provider);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Provider> getProviderById(@PathVariable Long id) {
        Optional<Provider> provider = providerService.getProviderById(id);
        return provider.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Provider> updateProvider(@PathVariable Long id, @RequestBody Provider provider) {
        return providerService.getProviderById(id)
                .map(existingProvider -> {
                    existingProvider.setName(provider.getName());
                    existingProvider.setEmail(provider.getEmail());
                    existingProvider.setPhoneNumber(provider.getPhoneNumber());
                    existingProvider.setPassword(provider.getPassword());
                    existingProvider.setContactData(provider.getContactData());
                    return ResponseEntity.ok(providerService.updateProvider(existingProvider));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvider(@PathVariable Long id) {
        providerService.getProviderById(id)
                .ifPresent(provider -> providerService.deleteProvider(id));
        return ResponseEntity.noContent().build();
    }
}