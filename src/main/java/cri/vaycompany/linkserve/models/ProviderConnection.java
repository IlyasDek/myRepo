package cri.vaycompany.linkserve.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "provider_connections")
public class ProviderConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "provider_one_id")
    private Provider providerOne;

    @ManyToOne
    @JoinColumn(name = "provider_two_id")
    private Provider providerTwo;

    private boolean isDeleted;
    private boolean isConfirmed;
}

