package pl.solsoft.helloboot.hello.service.impl;

import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.persistence.entity.Person;
import pl.solsoft.helloboot.hello.persistence.repository.PersonRepository;
import pl.solsoft.helloboot.hello.service.PersonService;

import javax.annotation.Resource;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    @Resource
    private PersonRepository personDao;

    @Override
    public int countChildrenBySex(final Sex sex) {
        List<Person> personList = personDao.findAllBySex(sex);

        return personList.stream().mapToInt(Person::getNumberOfChildren).sum();
    }
}
