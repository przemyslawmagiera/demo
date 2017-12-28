package pl.solsoft.helloboot.hello.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.solsoft.helloboot.hello.common.dto.ValidationMessageDTO;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class PersonControllerAdvice {
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationMessageDTO validationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> errorMessages.add(error.getCode() + " - " + error.getDefaultMessage()));
        return new ValidationMessageDTO(errorMessages);
    }
}
