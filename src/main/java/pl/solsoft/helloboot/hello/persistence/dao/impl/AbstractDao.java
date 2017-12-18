package pl.solsoft.helloboot.hello.persistence.dao.impl;

import pl.solsoft.helloboot.hello.persistence.dao.DaoI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDao<T> implements DaoI<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    public void save(final T object) {
        entityManager.persist(object);
    }

    public void delete(final T object) {
        entityManager.remove(object);
    }

}
