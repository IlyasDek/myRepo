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
@Table(name = "shared_clients")
public class SharedClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_provider_id")
    private Provider senderProvider;

    @ManyToOne
    @JoinColumn(name = "receiver_provider_id")
    private Provider receiverProvider;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    private boolean isConfirmed;
    private boolean isDeleted;
}

