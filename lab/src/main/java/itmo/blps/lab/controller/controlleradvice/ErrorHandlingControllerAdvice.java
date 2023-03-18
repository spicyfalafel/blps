package itmo.blps.lab.controller.controlleradvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import itmo.blps.lab.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.regex.Pattern;

@ControllerAdvice
class ErrorHandlingControllerAdvice {

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
    ValidationErrorResponse error = new ValidationErrorResponse();
    for (ConstraintViolation violation : e.getConstraintViolations()) {
      error.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
    }
    return error;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    ValidationErrorResponse error = new ValidationErrorResponse();
    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      error.getViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
    }
    return error;
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  ValidationErrorResponse onHttpMessageNotReadableException(HttpMessageNotReadableException e){
    ValidationErrorResponse error = new ValidationErrorResponse();
    String message = e.getMessage();
    assert message != null;
    String field = Pattern.compile("\"(.*?)\"")
            .matcher(message).results().map(mr -> mr.group(1)).findFirst().get();
    error.getViolations().add(new Violation(field, e.getMessage()));
    return error;
  }

  @ResponseBody
  @ExceptionHandler(MyException.class)
  protected ResponseEntity<?> handleMyException(MyException ex, WebRequest request) {
    if (ex.getMessage() !=null) return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    else return new ResponseEntity<>(ex.getStatus());
  }
}