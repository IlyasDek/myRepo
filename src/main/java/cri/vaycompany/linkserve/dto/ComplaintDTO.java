package cri.vaycompany.linkserve.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ComplaintDTO {
    private Long id;
    private UserPublicDTO complainant;
    private UserPublicDTO target;
    private ServiceOfferDTO serviceOffer;
    private String complaintText;
    private String description;
}