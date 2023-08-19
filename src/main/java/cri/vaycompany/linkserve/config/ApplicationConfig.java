package cri.vaycompany.linkserve.config;

import cri.vaycompany.linkserve.models.Provider;
import cri.vaycompany.linkserve.models.User;
import cri.vaycompany.linkserve.repositories.ProviderRepository;
import cri.vaycompany.linkserve.repositories.UserRepository;
import cri.vaycompany.linkserve.config.security.AppUserDetails;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;
    private final ProviderRepository providerRepository;


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Optional<User> userOptional = userRepository.findByEmail(username);
            Optional<Provider> providerOptional = providerRepository.findByEmail(username);
            if (userOptional.isPresent()) {
                return new AppUserDetails(userOptional.get());
            } else if (providerOptional.isPresent()) {
                return new AppUserDetails(providerOptional.get());
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        };
    }




    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
