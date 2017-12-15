package pl.solsoft.helloboot.hello.persistence.dao;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import pl.solsoft.helloboot.hello.enumeration.EyeColor;
import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.persistence.entity.Person;

import javax.annotation.Resource;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class PersonDaoTest {

    @Resource
    private PersonDao personDao;

    @Test
    public void shouldCreateAndFindPersonByEmail() {
        //given
        Person person = new Person();
        person.setEmail("test@test.pl");
        person.setEyeColor(EyeColor.BLUE);
        person.setSex(Sex.F);
        person.setName("Jan");
        person.setNumberOfChildren(5);

        //when
        personDao.save(person);

        //then
        assertThat(personDao.findPersonByEmail(person.getEmail()).getEmail()).isEqualTo("test@test.pl");
    }


    protected static Person returnPersonWithEmail(String email) {
        Person person = new Person();
        Random random = new Random();
        if (random.nextBoolean())
            person.setSex(Sex.F);
        else
            person.setSex(Sex.M);
        person.setNumberOfChildren(random.nextInt(10));
        person.setName(RandomStringUtils.randomAlphabetic());

        return person;
    }
}