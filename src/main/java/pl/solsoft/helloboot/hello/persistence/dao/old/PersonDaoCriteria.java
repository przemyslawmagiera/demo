package pl.solsoft.helloboot.hello.persistence.dao.old;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.solsoft.helloboot.hello.enumeration.EyeColor;
import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.persistence.dao.PersonDao;
import pl.solsoft.helloboot.hello.persistence.dao.old.AbstractDao;
import pl.solsoft.helloboot.hello.persistence.entity.Person;
import pl.solsoft.helloboot.hello.persistence.entity.Person_;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class PersonDaoCriteria extends AbstractDao<Person> implements PersonDao {

    //Criteria query używamy kiedy mamy zapytania dynamiczne, mocno parametryzowane, kiedy potrzebujemy jakiegoś
    //ifa, czy ionnych programistycznych rzeczy. JPQL używamy do większości prostych kwerend
    @Override
    public List<Person> findAllByGender(final Sex sex) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        final Root<Person> root = criteria.from(Person.class);
        criteria.select(root).where(builder.equal(root.get(Person_.sex), sex));
        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public Person findPersonByEmail(final String email) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        final Root<Person> root = criteria.from(Person.class);
        criteria.select(root).where(builder.equal(root.get(Person_.email), email));

        try {
            return entityManager.createQuery(criteria).getSingleResult();

        } catch (NoResultException nre) {
            return null;
        }
    }

}