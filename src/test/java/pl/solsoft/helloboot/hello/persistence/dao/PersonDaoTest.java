package pl.solsoft.helloboot.hello.persistence.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.persistence.entity.Person;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.solsoft.helloboot.hello.factory.TestObjectFactory.nextPerson;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class PersonDaoTest {

    @Resource
    private PersonDao personDao;

    @Test
    public void shouldCreateAndNotFindPersonByEmail() {
        //given
        Person person = nextPerson("test1@test.pl");
        personDao.save(person);

        //when
        Person p = personDao.findPersonByEmail("test@test.pl");
        //then
        assertThat(p)
                .isNull();
    }

    @Test
    public void shouldCreateAndFindPersonByEmail() {
        //given
        Person person = nextPerson("test@test.pl");
        personDao.save(person);

        //when
        Person p = personDao.findPersonByEmail(person.getEmail());
        //then
        assertThat(p)
                .isEqualTo(person);
    }

    @Test
    public void shouldCreateAndFindAllPeopleByGender() {
        //given
        Person person1 = nextPerson(Sex.F);
        Person person2 = nextPerson(Sex.M);
        Person person3 = nextPerson(Sex.F);
        personDao.save(person1);
        personDao.save(person2);
        personDao.save(person3);

        //when
        List<Person> personList = personDao.findAllByGender(Sex.F);

        //then
        assertThat(personList).hasSize(2);
    }

    @Test
    public void whenNoResultsShouldReturnEmptyList() {
        //given
        List<Person> personList = new ArrayList<>();
        IntStream.range(0, 15).forEach(integer -> {
            personList.add(nextPerson(Sex.M));
        });
        personList.forEach(person -> personDao.save(person));

        //when
        List<Person> personListResult = personDao.findAllByGender(Sex.F);

        //then
        assertThat(personListResult).isEmpty();
    }


}