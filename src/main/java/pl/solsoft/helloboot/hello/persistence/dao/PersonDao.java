package pl.solsoft.helloboot.hello.persistence.dao;

import pl.solsoft.helloboot.hello.enumeration.EyeColor;
import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.persistence.entity.Person;

import java.util.List;

public interface PersonDao extends DaoI<Person> {

    List<Person> findAllByGender(final Sex sex);

    Person findPersonByEmail(final String email);
}
