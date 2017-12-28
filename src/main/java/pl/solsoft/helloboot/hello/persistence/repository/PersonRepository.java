package pl.solsoft.helloboot.hello.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.persistence.entity.Person;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryCustom
{
    Person findByEmail(String email);
    List<Person> findAllBySex(Sex sex);
    boolean existsByEmail(String email);
}
