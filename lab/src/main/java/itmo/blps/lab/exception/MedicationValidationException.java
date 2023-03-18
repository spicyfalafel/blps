package itmo.blps.lab.exception;

public class MedicationValidationException extends MyException {
    public MedicationValidationException() {
        super("medication is not valid");
    }

    public MedicationValidationException(String message) {
        super(message);
    }
}
