package pl.solsoft.helloboot.hello.persistence.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;
import pl.solsoft.helloboot.hello.enumeration.EyeColor;
import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.persistence.dao.impl.PersonDaoImpl;
import pl.solsoft.helloboot.hello.persistence.entity.Person;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonDaoTest {

    @Resource
    private PersonDao personDao;

    @Test
    public void shouldAddPerson()
    {
        Person person = new Person();
        person.setEmail("test@test.pl");
        person.setEyeColor(EyeColor.BLUE);
        person.setSex(Sex.F);
        person.setName("Jan");
        person.setNumberOfChildren(5);

//        personDao.save(person);
//        assertEquals(personDao.findPersonByEmail(person.getEmail()).getEmail(), person.getEmail());
    }

}