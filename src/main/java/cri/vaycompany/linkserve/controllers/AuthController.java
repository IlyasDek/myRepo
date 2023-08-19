//package cri.vaycompany.linkserve.controllers;
//
//import cri.vaycompany.linkserve.security.jwt.JwtUtils;
//import cri.vaycompany.linkserve.security.services.UserDetailsImpl;
//import cri.vaycompany.linkserve.services.AuthenticationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    AuthenticationService authenticationService;
//
//    @Autowired
//    JwtUtils jwtUtils;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//
//        Authentication authentication = authenticationService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
//        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail()));
//    }
//}
