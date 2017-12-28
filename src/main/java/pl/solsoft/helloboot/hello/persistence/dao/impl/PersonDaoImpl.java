package pl.solsoft.helloboot.hello.persistence.dao.impl;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.solsoft.helloboot.hello.enumeration.EyeColor;
import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.persistence.dao.PersonDao;
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
public class PersonDaoImpl extends AbstractDao<Person> implements PersonDao {

    //Criteria query używamy kiedy mamy zapytania dynamiczne, mocno parametryzowane, kiedy potrzebujemy jakiegoś
    //ifa, czy ionnych programistycznych rzeczy. JPQL używamy do większości prostych kwerend
    @Override
    public List<Person> findAllByGender(final Sex sex) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        Root<Person> root = criteria.from(Person.class);
        criteria.select(root).where(builder.equal(root.get(Person_.sex), sex));
        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public Person findPersonByEmail(final String email) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        Root<Person> root = criteria.from(Person.class);
        criteria.select(root).where(builder.equal(root.get(Person_.email), email));

        try {
            return entityManager.createQuery(criteria).getSingleResult();

        } catch (NoResultException nre) {
            return null;
        }
    }


    public List<Person> findFiltered (final Sex sex, final EyeColor eyeColor, final Integer numberOfChildren) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        Root<Person> root = criteria.from(Person.class);
        List<Predicate> predicates = new ArrayList<>();

        Optional<Sex> optionalSex = Optional.ofNullable(sex);
        optionalSex.ifPresent(sexOp -> predicates.add(builder.equal(root.get(Person_.sex), sexOp)));

        Optional<EyeColor> optionalEyeColor = Optional.ofNullable(eyeColor);
        optionalEyeColor.ifPresent(eyeColorOp -> predicates.add(builder.equal(root.get(Person_.eyeColor), eyeColorOp)));

        Optional<Integer> optionalNumberOfChildren = Optional.ofNullable(numberOfChildren);
        optionalNumberOfChildren.ifPresent(numOp -> predicates.add(builder.equal(root.get(Person_.numberOfChildren), numOp)));

        criteria.select(root).where(builder.and(predicates.toArray(new Predicate[] {})));

        return entityManager.createQuery(criteria).getResultList();
    }

}
