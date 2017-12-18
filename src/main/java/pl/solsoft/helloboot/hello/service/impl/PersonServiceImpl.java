package pl.solsoft.helloboot.hello.service.impl;

import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.persistence.dao.PersonDao;
import pl.solsoft.helloboot.hello.persistence.entity.Person;
import pl.solsoft.helloboot.hello.service.PersonService;

import javax.annotation.Resource;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    @Resource
    private PersonDao personDao;

    @Override
    public int countChildrenBySex(final Sex sex) {
        List<Person> personList = personDao.findAllByGender(sex);

        return personList.stream().mapToInt(Person::getNumberOfChildren).sum();
    }
}
