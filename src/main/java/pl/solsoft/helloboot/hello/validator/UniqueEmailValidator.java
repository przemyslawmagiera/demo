package pl.solsoft.helloboot.hello.validator;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.util.ObjectUtils;
import pl.solsoft.helloboot.hello.persistence.repository.PersonRepository;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

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
