package pl.solsoft.helloboot.hello.persistence.dao;

import pl.solsoft.helloboot.hello.persistence.entity.Person;

public interface DaoI<T> {
    void save(final T object);

    void delete(final T object);
}
