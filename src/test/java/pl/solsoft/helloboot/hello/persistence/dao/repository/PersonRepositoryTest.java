package pl.solsoft.helloboot.hello.persistence.dao.repository;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.solsoft.helloboot.hello.persistence.entity.Person;
import pl.solsoft.helloboot.hello.persistence.repository.PersonRepository;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.solsoft.helloboot.hello.factory.TestObjectFactory.nextPerson;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase
@Transactional
public class PersonRepositoryTest {

    @Resource
    private PersonRepository personRepository;

    @Test
    public void shouldReturnTrueWhenEmailDuplicated()
    {
        //given
        final Person person1 = nextPerson("test1@test.pl");
        final Person person2 = nextPerson("test1@test.pl");

        //when
        personRepository.save(person1);
        final boolean response = personRepository.existsByEmail(person2.getEmail());

        //then
        assertThat(response).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenEmailNotDuplicated()
    {
        //given
        final Person person1 = nextPerson("test1@test.pl");
        final Person person2 = nextPerson("test2@test.pl");

        //when
        personRepository.save(person1);
        final boolean response = personRepository.existsByEmail(person2.getEmail());

        //then
        assertThat(response).isFalse();
    }
}
