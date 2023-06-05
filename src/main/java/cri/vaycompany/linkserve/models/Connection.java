package cri.vaycompany.linkserve.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "connections")
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long providerId;
    private int trustLevel;

    public Connection() {
    }

    public Connection(Long id, Long userId, Long providerId, int trustLevel) {
        this.id = id;
        this.userId = userId;
        this.providerId = providerId;
        this.trustLevel = trustLevel;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProviderId() {
        return providerId;
    }

    public int getTrustLevel() {
        return trustLevel;
    }

    public void setTrustLevel(int trustLevel) {
        this.trustLevel = trustLevel;
    }
}
