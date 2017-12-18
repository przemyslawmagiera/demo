package pl.solsoft.helloboot.hello.persistence.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.persistence.entity.Person;

import java.util.List;

public interface PersonDao extends DaoI<Person> {

    List<Person> findAllByGender(final Sex sex);

    Person findPersonByEmail(final String email);
}