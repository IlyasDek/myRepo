package cri.vaycompany.linkserve.exceprions;

public class ServiceOfferNotFoundException extends RuntimeException {
    public ServiceOfferNotFoundException(String message) {
        super(message);
    }
}
