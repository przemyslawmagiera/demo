package pl.solsoft.helloboot.hello.persistence.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.solsoft.helloboot.hello.enumeration.EyeColor;
import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.persistence.dao.impl.PersonDaoImpl;
import pl.solsoft.helloboot.hello.persistence.entity.Person;
import pl.solsoft.helloboot.hello.persistence.repository.PersonRepository;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.solsoft.helloboot.hello.factory.TestObjectFactory.nextPerson;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase
@Transactional
public class PersonDaoTest {

    @Resource
    private PersonDao personDao;

    @Test
    public void shouldReturnNullWhenNoSingleResult() {
        //given
        final Person person = nextPerson("test1@test.pl");
        personDao.save(person);

        //when
        final Person p = personDao.findPersonByEmail("test@test.pl");
        //then
        assertThat(p)
                .isNull();
    }

    @Test
    public void shouldThrowAnExceptionWhenEmailIsDuplicated() {
        //given
        final Person person1 = nextPerson("test1@test.pl");
        final Person person2 = nextPerson("test1@test.pl");

        //when
        personDao.save(person1);
        personDao.save(person2);

        //then
    }

    @Test
    public void shouldFindPersonByEmail() {
        //given
        final Person person = nextPerson("test@test.pl");
        personDao.save(person);

        //when
        final Person p = personDao.findPersonByEmail(person.getEmail());
        //then
        assertThat(p)
                .isEqualTo(person);
    }

    @Test
    public void shouldFindAllPeopleByGender() {
        //given
        final Person person1 = nextPerson(Sex.F);
        final Person person2 = nextPerson(Sex.M);
        final Person person3 = nextPerson(Sex.F);
        personDao.save(person1);
        personDao.save(person2);
        personDao.save(person3);

        //when
        final List<Person> personList = personDao.findAllByGender(Sex.F);

        //then
        assertThat(personList).hasSize(2);
    }

    @Test
    public void shouldReturnEmptyListWhenNoResults() {
        //given
        final List<Person> personList = new ArrayList<>();
        IntStream.range(0, 15).forEach(integer -> {
            personList.add(nextPerson(Sex.M));
        });
        personList.forEach(person -> personDao.save(person));

        //when
        final List<Person> personListResult = personDao.findAllByGender(Sex.F);

        //then
        assertThat(personListResult).isEmpty();
    }

    @Test
    public void shouldFilterBySexAndEyeColor() {
        //given
        final Person person1 = nextPerson(Sex.F);
        final Person person2 = nextPerson(Sex.M);
        final Person person3 = nextPerson(Sex.F);
        person1.setEyeColor(EyeColor.BLUE);
        person2.setEyeColor(EyeColor.BLUE);
        person3.setEyeColor(EyeColor.BLUE);
        personDao.save(person1);
        personDao.save(person2);
        personDao.save(person3);
        final List<Person> expected = Arrays.asList(person1,person3);

        //when
        final List<Person> personListResult = ((PersonDaoImpl)personDao).findFiltered(Sex.F, EyeColor.BLUE, null);

        assertThat(personListResult)
                .hasSameElementsAs(expected);
    }

    @Test
    public void shouldFilterByNumberOfChildrenEyeColorAndSex() {
        //given
        final Person person1 = nextPerson(Sex.F,2, EyeColor.BLUE);
        final Person person2 = nextPerson(Sex.M,2, EyeColor.BLUE);
        final Person person3 = nextPerson(Sex.F,0, EyeColor.BLUE);
        personDao.save(person1);
        personDao.save(person2);
        personDao.save(person3);
        final List<Person> expected = Collections.singletonList(person1);

        //when
        final List<Person> personListResult = ((PersonDaoImpl)personDao).findFiltered(Sex.F, EyeColor.BLUE, 2);

        assertThat(personListResult)
                .hasSameElementsAs(expected);
    }

    @Test
    public void shouldFilterByNothing() {
        //given
        final Person person1 = nextPerson();
        final Person person2 = nextPerson();
        final Person person3 = nextPerson();
        personDao.save(person1);
        personDao.save(person2);
        personDao.save(person3);
        final List<Person> expected = Arrays.asList(person1, person2, person3);

        //when
        final List<Person> personListResult = ((PersonDaoImpl)personDao).findFiltered(null, null, null);

        assertThat(personListResult)
                .hasSameElementsAs(expected);
    }
}