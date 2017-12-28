package pl.solsoft.helloboot.hello.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.solsoft.helloboot.hello.common.mapper.PersonMapper;
import pl.solsoft.helloboot.hello.common.dto.PersonDTO;
import pl.solsoft.helloboot.hello.persistence.entity.Person;
import pl.solsoft.helloboot.hello.persistence.repository.PersonRepository;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/person")
public class PersonController
{
    @Resource
    private PersonRepository personRepository;

    @Resource
    private PersonMapper mapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create")
    public PersonDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        Person person = mapper.mapToPerson(personDTO);
        Person createdPerson = personRepository.save(person);
        PersonDTO createdPersonDTO = mapper.mapToPersonDTO(createdPerson);
        return createdPersonDTO;
    }
}
