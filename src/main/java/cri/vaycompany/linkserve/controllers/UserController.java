package cri.vaycompany.linkserve.controllers;

import cri.vaycompany.linkserve.dto.SubscriptionDTO;
import cri.vaycompany.linkserve.dto.UserConnectionDTO;
import cri.vaycompany.linkserve.dto.UserPrivateDTO;
import cri.vaycompany.linkserve.dto.UserPublicDTO;
import cri.vaycompany.linkserve.exceprions.InvalidRequestException;
import cri.vaycompany.linkserve.services.ConnectionService;
import cri.vaycompany.linkserve.services.SubscriptionService;
import cri.vaycompany.linkserve.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ConnectionService connectionService;
    private final SubscriptionService subscriptionService;

    @Autowired
    public UserController(UserService userService, ConnectionService connectionService, SubscriptionService subscriptionService) {
        this.userService = userService;
        this.connectionService = connectionService;
        this.subscriptionService = subscriptionService;
    }


    @GetMapping
    public ResponseEntity<List<UserPublicDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPublicDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserPublicDTO> createUser(@RequestBody UserPrivateDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserPublicDTO> updateUser(@PathVariable Long id, @RequestBody UserPrivateDTO userDTO) {
        if (!id.equals(userDTO.getId())) {
            throw new InvalidRequestException("Path id " + id + " and RequestBody id " + userDTO.getId() + " should be the same");
        }
        return ResponseEntity.ok(userService.updateUser(userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/connections")
    public ResponseEntity<UserConnectionDTO> createUserConnection(@RequestBody UserConnectionDTO userConnectionDTO) {
        return ResponseEntity.ok(connectionService.createUserConnection(userConnectionDTO));
    }

    @GetMapping("/{userId}/connections")
    public ResponseEntity<List<UserConnectionDTO>> getUserConnections(@PathVariable Long userId) {
        return ResponseEntity.ok(connectionService.getUserConfirmedConnections(userId));
    }

    @GetMapping("/{userId}/subscriptions")
    public ResponseEntity<List<SubscriptionDTO>> getUserSubscriptions(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionService.getUserSubscriptions(userId));
    }

}
