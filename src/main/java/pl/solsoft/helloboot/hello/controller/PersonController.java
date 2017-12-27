package pl.solsoft.helloboot.hello.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import pl.solsoft.helloboot.hello.enumeration.EyeColor;
import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.persistence.entity.Person;
import pl.solsoft.helloboot.hello.persistence.entity.Person_;
import pl.solsoft.helloboot.hello.persistence.repository.PersonRepository;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController
{
    @Resource
    private Validator validator;

    @Resource
    private PersonRepository personRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create")
    public List<String> createPerson(@RequestParam Map<String,String> requestParams) {
        Person p;
        try {
            p = Person.createFromRequestParams(requestParams);
        } catch(NumberFormatException nfe) {
            return new ArrayList<>();
        }

        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(p);
        if(CollectionUtils.isEmpty(constraintViolations))
        {
            personRepository.save(p);
            return constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        }
        else {
            return new ArrayList<>();
        }
    }
}
