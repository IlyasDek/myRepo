package cri.vaycompany.linkserve.dto;

import cri.vaycompany.linkserve.enums.PrivacySetting;
import cri.vaycompany.linkserve.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserPublicDTO {
    private Long id;
    private Role role;
    private String name;
    private String contactData;
    private String email;
    private String phoneNumber;
    private PrivacySetting connectionsPrivacy;
    private List<Long> initiatedConnectionsIds;
    private List<Long> receivedConnectionsIds;
    private PrivacySetting subscriptionsPrivacy;
    private List<Long> subscriptionIds;
    private List<Long> connectionsAsUserOneIds;
    private List<Long> connectionsAsUserTwoIds;
    private List<Long> subscriptionsIds;
}


