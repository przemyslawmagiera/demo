package pl.solsoft.helloboot.hello.persistence.dao;

public interface DaoI<T> {
    void save(final T object);

    void delete(final T object);
}
