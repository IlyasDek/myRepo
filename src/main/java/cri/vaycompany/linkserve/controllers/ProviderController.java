package cri.vaycompany.linkserve.controllers;
import cri.vaycompany.linkserve.dto.ProviderConnectionDTO;
import cri.vaycompany.linkserve.dto.ProviderPrivateDTO;
import cri.vaycompany.linkserve.dto.ProviderPublicDTO;
import cri.vaycompany.linkserve.dto.SubscriptionDTO;
import cri.vaycompany.linkserve.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<ProviderPublicDTO> createProvider(@RequestBody ProviderPrivateDTO providerPrivateDTO) {
        ProviderPublicDTO createdProviderDTO = providerService.createProvider(providerPrivateDTO);
        return new ResponseEntity<>(createdProviderDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderPublicDTO> getProviderById(@PathVariable Long id) {
        Optional<ProviderPublicDTO> providerPublicDTO = providerService.getProviderById(id);
        return providerPublicDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProviderPublicDTO> updateProvider(@PathVariable Long id, @RequestBody ProviderPrivateDTO providerPrivateDTO) {
        return providerService.updateProvider(id, providerPrivateDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvider(@PathVariable Long id) {
        providerService.deleteProvider(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{providerOneId}/connections/{providerTwoId}")
    public ResponseEntity<ProviderConnectionDTO> createProviderConnection(@PathVariable Long providerOneId, @PathVariable Long providerTwoId) {
        ProviderConnectionDTO createdProviderConnection = providerService.createProviderConnection(providerOneId, providerTwoId);
        return new ResponseEntity<>(createdProviderConnection, HttpStatus.CREATED);
    }

    @PostMapping("/{providerId}/subscriptions/{userId}")
    public ResponseEntity<SubscriptionDTO> createSubscription(@PathVariable Long providerId, @PathVariable Long userId) {
        SubscriptionDTO createdSubscriptionDTO = providerService.createSubscription(providerId, userId);
        return new ResponseEntity<>(createdSubscriptionDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{providerId}/subscriptions")
    public ResponseEntity<List<SubscriptionDTO>> getProviderSubscriptions(@PathVariable Long providerId) {
        List<SubscriptionDTO> providerSubscriptions = providerService.getProviderSubscriptions(providerId);
        return new ResponseEntity<>(providerSubscriptions, HttpStatus.OK);
    }

    @DeleteMapping("/subscriptions/{subscriptionId}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long subscriptionId) {
        providerService.deleteSubscription(subscriptionId);
        return ResponseEntity.noContent().build();
    }

}
