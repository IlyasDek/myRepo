package cri.vaycompany.linkserve.dto;

import cri.vaycompany.linkserve.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserConnectionDTO {
    private Long id;
    private User userOne;
    private User userTwo;
    private boolean isDeleted;
    private boolean isConfirmed;
}
