package cri.vaycompany.linkserve.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import cri.vaycompany.linkserve.auth.AuthenticationRequest;
import cri.vaycompany.linkserve.auth.RegisterRequest;
import cri.vaycompany.linkserve.config.security.services.JwtService;
import cri.vaycompany.linkserve.config.security.token.Token;
import cri.vaycompany.linkserve.config.security.token.TokenRepository;
import cri.vaycompany.linkserve.config.security.token.TokenType;
import cri.vaycompany.linkserve.dto.ProviderPrivateDTO;
import cri.vaycompany.linkserve.dto.UserPrivateDTO;
import cri.vaycompany.linkserve.enums.Role;
import cri.vaycompany.linkserve.models.Provider;
import cri.vaycompany.linkserve.models.User;
import cri.vaycompany.linkserve.repositories.ProviderRepository;
import cri.vaycompany.linkserve.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ProviderRepository providerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final ModelMapper modelMapper;

//    public AuthenticationResponse registerUser(RegisterRequest request) {
//        var userDTO = UserPrivateDTO.builder()
//                .name(request.getName())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.USER)
//                .build();
//        var user =  modelMapper.map(userDTO, User.class);
//        var savedUser = userRepository.save(user);
//        var jwtToken = jwtService.generateToken(savedUser);
//        var refreshToken = jwtService.generateRefreshToken(savedUser);
//
//        saveUserToken(savedUser, jwtToken);
//
//        return AuthenticationResponse.builder()
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
//                .build();
//    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var userDetails = this.getUserDetailsByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(userDetails);
        var refreshToken = jwtService.generateRefreshToken(userDetails);
        revokeAllUserTokens(userDetails);
        saveUserToken(userDetails, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse registerProvider(RegisterRequest request) {
        var providerDto  = ProviderPrivateDTO.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.PROVIDER)
                .build();

        var provider = modelMapper.map(providerDto, Provider.class);
        var savedProvider = providerRepository.save(provider);
        var jwtToken = jwtService.generateToken(savedProvider);
        var refreshToken = jwtService.generateRefreshToken(savedProvider);

        saveUserToken(savedProvider, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var userDetails = getUserDetails(request.getEmail(), request.getPassword());
        var jwtToken = jwtService.generateToken(userDetails);
        var refreshToken = jwtService.generateRefreshToken(userDetails);
        saveUserToken(userDetails, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private UserDetails getUserDetails(String email, String password) {
        try {
            var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            return (UserDetails) authentication.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect email or password");
        }
    }

    private void saveUserToken(UserDetails user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(UserDetails user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var userDetails = this.getUserDetailsByEmail(userEmail);
            if (jwtService.isTokenValid(refreshToken, userDetails)) {
                var accessToken = jwtService.generateToken(userDetails);
                revokeAllUserTokens(userDetails);
                saveUserToken(userDetails, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    private UserDetails getUserDetailsByEmail(String email) {
        var user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            return user;
        }
        var provider = providerRepository.findByEmail(email).orElse(null);
        if (provider != null) {
            return provider;
        }
        throw new UsernameNotFoundException("No user found with email: " + email);
    }

}


