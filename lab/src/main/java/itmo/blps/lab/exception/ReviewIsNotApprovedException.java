package itmo.blps.lab.exception;

public class ReviewIsNotApprovedException extends MyException {
    public ReviewIsNotApprovedException() {
        super("review is not approved");
    }

    public ReviewIsNotApprovedException(String message) {
        super(message);
    }
}
