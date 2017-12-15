package pl.solsoft.helloboot.hello.persistence.dao.impl;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.persistence.dao.PersonDao;
import pl.solsoft.helloboot.hello.persistence.entity.Person;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class PersonDaoImpl implements PersonDao{

//    @PersistenceContext
//    private EntityManager entityManager;

//    @Override
//    public void save(Person person) {
//        entityManager.persist(person);
//    }
//
//    @Override
//    public void delete(Person person) {
//        entityManager.remove(person);
//    }
//
//    @Override
//    public List<Person> findAllByGender(Sex sex) {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
//        Root<Person> root = criteria.from(Person.class);
//        criteria.select(root).where(builder.equal(root.get(Person.FIELD_SEX), sex));
//        return entityManager.createQuery(criteria).getResultList();
//    }
//
//    @Override
//    public Person findPersonByEmail(String email) {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
//        Root<Person> root = criteria.from(Person.class);
//        criteria.select(root).where(builder.equal(root.get(Person.FIELD_EMAIL), email));
//        return entityManager.createQuery(criteria).getSingleResult();
//    }

}
