package cri.vaycompany.linkserve.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserPrivateDTO {
    private Long id;
    private String name;
    private String contactData;
    private String role;
    private String password;
    private String email;
    private String phoneNumber;
    private List<Long> connectionsAsUserOneIds;
    private List<Long> connectionsAsUserTwoIds;
    private List<Long> subscriptionsIds;
}


