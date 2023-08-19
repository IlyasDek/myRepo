package cri.vaycompany.linkserve.dto;

import cri.vaycompany.linkserve.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CommentDTO {
    private Long id;
    private UserPublicDTO user;
    private ServiceOfferDTO serviceOffer;
    private RatingDTO rating;
    private CommentDTO parentComment;
    private List<CommentDTO> replies;
    private String text;
    private Status status;
}
