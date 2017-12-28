package pl.solsoft.helloboot.hello.persistence.repository;

import pl.solsoft.helloboot.hello.enumeration.EyeColor;
import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.persistence.entity.Person;

import java.util.List;

public interface PersonRepositoryCustom {
    List<Person> findFiltered(final Sex sex, final EyeColor eyeColor, final Integer numberOfChildren);
}
