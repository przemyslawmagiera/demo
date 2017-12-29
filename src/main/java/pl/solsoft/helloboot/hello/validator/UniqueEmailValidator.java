package pl.solsoft.helloboot.hello.validator;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.util.ObjectUtils;
import pl.solsoft.helloboot.hello.persistence.repository.PersonRepository;

import javax.annotation.Resource;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmailValidator.UniqueEmail, String> {

    @Constraint(validatedBy = UniqueEmailValidator.class)
    @Target( ElementType.FIELD )
    @Retention(value = RetentionPolicy.RUNTIME)
    public @interface UniqueEmail {
        String message() default "Duplicate email";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }


    @Resource
    private PersonRepository personRepository;

    @Override
    public void initialize(final UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return BooleanUtils.isFalse(personRepository.existsByEmail(value));
    }
}
