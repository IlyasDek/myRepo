package cri.vaycompany.linkserve.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SharedClientDTO {

    private Long id;
    private ProviderPublicDTO senderProvider;
    private ProviderPublicDTO receiverProvider;
    private UserPublicDTO client;
    private boolean isConfirmed;
}