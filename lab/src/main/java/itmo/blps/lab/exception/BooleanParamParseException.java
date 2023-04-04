package itmo.blps.lab.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "boolean param is not true or false")
public class BooleanParamParseException extends MyException {

    public BooleanParamParseException() {
        super();
    }

    public BooleanParamParseException(String message) {
        super(message);
    }
}
