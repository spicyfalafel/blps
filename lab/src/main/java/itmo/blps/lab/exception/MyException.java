package itmo.blps.lab.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class MyException extends RuntimeException{
    private HttpStatus status;

    public MyException(){super(); this.status = HttpStatus.BAD_REQUEST;}
    public MyException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }
    public MyException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
