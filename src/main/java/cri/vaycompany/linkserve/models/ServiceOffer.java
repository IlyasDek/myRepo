package cri.vaycompany.linkserve.models;

import cri.vaycompany.linkserve.models.Provider;
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
@Table(name = "service_offers")
public class ServiceOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="provider_id", nullable=false)
    private Provider provider;

    private String serviceName;
    private String description;
    private String photos;
    private Double price;
    private String location;
}