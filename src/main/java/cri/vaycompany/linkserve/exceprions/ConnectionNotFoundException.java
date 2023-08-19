package cri.vaycompany.linkserve.exceprions;

public class ConnectionNotFoundException extends RuntimeException {

    public ConnectionNotFoundException(String message) {
        super(message);
    }
}

