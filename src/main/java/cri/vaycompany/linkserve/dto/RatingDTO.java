package cri.vaycompany.linkserve.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RatingDTO {
    private Long id;
    private UserPublicDTO user;
    private ServiceOfferDTO serviceOffer;
    private double rating;
}