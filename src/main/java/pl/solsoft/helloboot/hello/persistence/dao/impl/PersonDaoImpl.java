package pl.solsoft.helloboot.hello.persistence.dao.impl;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.persistence.dao.PersonDao;
import pl.solsoft.helloboot.hello.persistence.entity.Person;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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
        criteria.select(root).where(builder.equal(root.get(Person.FIELD_SEX), sex));
        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public Person findPersonByEmail(final String email) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        Root<Person> root = criteria.from(Person.class);
        criteria.select(root).where(builder.equal(root.get(Person.FIELD_EMAIL), email));

        List<Person> personList = entityManager.createQuery(criteria).getResultList();

        if (!CollectionUtils.isEmpty(personList)) {
            return personList.get(0);
        } else {
            return null;
        }
    }

}
