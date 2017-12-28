package pl.solsoft.helloboot.hello.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.solsoft.helloboot.hello.common.to.ValidationMessageTo;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class PersonControllerAdvice {
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationMessageTo validationException(MethodArgumentNotValidException ex) {
        ValidationMessageTo message = new ValidationMessageTo();
        List<String> errorMessages = new ArrayList<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> errorMessages.add(error.getCode() + " - " + error.getDefaultMessage()));
        message.setMessages(errorMessages);
        return message;
    }
}
