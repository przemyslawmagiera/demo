package pl.solsoft.helloboot.hello.persistence.dao.impl;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.persistence.dao.PersonDao;
import pl.solsoft.helloboot.hello.persistence.entity.Person;
import pl.solsoft.helloboot.hello.persistence.entity.Person_;

import javax.persistence.NoResultException;
import java.util.List;

public class PersonDaoJPQL extends AbstractDao<Person> implements PersonDao {

    @Override
    public List<Person> findAllByGender(final Sex sex) {
        String query = "SELECT p FROM Person p WHERE p.sex = :sex";

        @SuppressWarnings("unchecked")
        List<Person> personList = entityManager.createQuery(query).setParameter(Person_.sex.getName(), sex).getResultList();

        return personList;
    }

    @Override
    public Person findPersonByEmail(final String email) {
        String query = "SELECT p FROM Person p WHERE p.email = :email";

        try {
            return (Person) entityManager.createQuery(query).setParameter(Person_.email.getName(), email).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
