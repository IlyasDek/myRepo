package cri.vaycompany.linkserve.dto;

import cri.vaycompany.linkserve.models.Provider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProviderConnectionDTO {
    private Long id;
    private Provider providerOne;
    private Provider providerTwo;
    private boolean isDeleted;
    private boolean isConfirmed;
}
