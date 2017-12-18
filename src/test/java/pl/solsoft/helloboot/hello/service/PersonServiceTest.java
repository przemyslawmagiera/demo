package pl.solsoft.helloboot.hello.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import pl.solsoft.helloboot.hello.enumeration.EyeColor;
import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.persistence.dao.PersonDao;
import pl.solsoft.helloboot.hello.persistence.entity.Person;
import pl.solsoft.helloboot.hello.service.impl.PersonServiceImpl;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static pl.solsoft.helloboot.hello.factory.TestObjectFactory.nextPerson;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService = new PersonServiceImpl();

    @Mock
    private PersonDao personDao;

    @Test
    public void shouldCountChildrenBySex()
    {
        //given
        List<Person> personList = new ArrayList<>();
        IntStream.range(0,15).forEach(integer -> personList.add(nextPerson(Sex.F, 5)));

        //when
        when(personDao.findAllByGender(Sex.F)).thenReturn(personList);

        //then
        assertThat(personService.countChildrenBySex(Sex.F)).isEqualTo(75);
    }

    @Test
    public void whenNoPeopleAtSexResultShouldBeZero()
    {
        //given

        //when
        when(personDao.findAllByGender(Sex.F)).thenReturn(new ArrayList<>());

        //then
        assertThat(personService.countChildrenBySex(Sex.F)).isEqualTo(0);
    }




}
