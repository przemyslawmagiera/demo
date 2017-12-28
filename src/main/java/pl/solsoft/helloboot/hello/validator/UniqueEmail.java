package pl.solsoft.helloboot.hello.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Target( ElementType.FIELD )
@Retention(value = RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "Duplicate email";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
