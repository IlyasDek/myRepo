package cri.vaycompany.linkserve.models;

import cri.vaycompany.linkserve.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@Entity
@Table(name = "providers")
public class Provider extends AbstractUser implements UserDetails {

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String serviceDescription;

    @Column(nullable = false)
    private double personalRating;

    @Column(nullable = false)
    private double groupRating;

    @Column(nullable = false)
    private double recommendationRating;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "providerOne")
    private Set<ProviderConnection> providerConnectionsAsProviderOne;

    @OneToMany(mappedBy = "providerTwo")
    private Set<ProviderConnection> providerConnectionsAsProviderTwo;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private Set<ServiceOffer> serviceOffers;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(super.getRole().name()));
    }

    @Override
    public String getUsername() {
        return super.getName();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}