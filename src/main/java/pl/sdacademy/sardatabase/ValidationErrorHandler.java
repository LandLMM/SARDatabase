package pl.sdacademy.sardatabase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FieldValidationError>> handleValidationError(MethodArgumentNotValidException e) {
        List<FieldValidationError> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> new FieldValidationError(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
