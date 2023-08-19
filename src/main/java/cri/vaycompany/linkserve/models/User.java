package cri.vaycompany.linkserve.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@Entity
@Table(name = "users")
public class User extends AbstractUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "userOne")
    private Set<UserConnection> connectionsInitiatedByThisUser;

    @OneToMany(mappedBy = "userTwo")
    private Set<UserConnection> connectionsReceivedByThisUser;

    @OneToMany(mappedBy = "user")
    private List<Subscription> subscriptions;

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