package cri.vaycompany.linkserve.dto;

import cri.vaycompany.linkserve.enums.Role;
import cri.vaycompany.linkserve.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProviderPrivateDTO {
    private Long id;
    private Role role;
    private String name;
    private String contactData;
    private String email;
    private String password;
    private String phoneNumber;
    private String location;
    private String serviceDescription;
    private double personalRating;
    private double groupRating;
    private double recommendationRating;
    private Status status;
    private Set<ServiceOfferDTO> serviceOffers;
    private List<Long> providerConnectionsAsProviderOneIds;
    private List<Long> providerConnectionsAsProviderTwoIds;
}

