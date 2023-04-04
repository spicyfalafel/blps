package itmo.blps.lab.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoEntityWithSuchIdException extends MyException {
    public NoEntityWithSuchIdException(String message) {
        super(message);
        this.setStatus(HttpStatus.NOT_FOUND);
    }
}
