package cri.vaycompany.linkserve.dto;

import cri.vaycompany.linkserve.models.Provider;
import cri.vaycompany.linkserve.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SubscriptionDTO {
    private Long id;
    private Provider provider;
    private User user;
    private boolean isDeleted;
}