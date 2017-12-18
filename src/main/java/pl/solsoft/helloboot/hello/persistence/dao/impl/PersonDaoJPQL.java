package pl.solsoft.helloboot.hello.persistence.dao.impl;

import org.springframework.util.CollectionUtils;
import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.persistence.dao.PersonDao;
import pl.solsoft.helloboot.hello.persistence.entity.Person;

import java.util.Collections;
import java.util.List;

public class PersonDaoJPQL extends AbstractDao<Person> implements PersonDao{

    @Override
    public List<Person> findAllByGender(final Sex sex) {
        String query = "SELECT p FROM Person p WHERE p.sex = :sex";

        @SuppressWarnings("unchecked")
        List<Person> personList = entityManager.createQuery(query).setParameter(Person.FIELD_SEX, sex).getResultList();

        return personList;
    }

    @Override
    public Person findPersonByEmail(final String email) {
        String query = "SELECT p FROM Person p WHERE p.email = :email";

        @SuppressWarnings("unchecked")
        List<Person> personList = entityManager.createQuery(query).setParameter(Person.FIELD_EMAIL, email).getResultList();

        if(!CollectionUtils.isEmpty(personList))
            return personList.get(0);
        else
            return null;
    }
}
