package cri.vaycompany.linkserve.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ServiceOfferDTO {
    private Long id;
    private ProviderPublicDTO provider;
    private String serviceName;
    private String description;
    private String photos;
    private Double price;
    private String location;
}