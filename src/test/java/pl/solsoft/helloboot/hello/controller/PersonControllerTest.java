package pl.solsoft.helloboot.hello.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;
import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.factory.TestObjectFactory;
import pl.solsoft.helloboot.hello.persistence.entity.Person;
import pl.solsoft.helloboot.hello.persistence.entity.Person_;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PersonControllerTest
{
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private static final String BASE_HTTP = "http://localhost:";

    private static final String BASE_URL = "/person";

    @Test
    public void shouldCreatePerson() {
        //given
        Person p = TestObjectFactory.nextPerson(Sex.M);

        //when
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_HTTP + port + BASE_URL + "/create")
                .queryParam(Person_.name.getName(), p.getName())
                .queryParam(Person_.email.getName(), p.getEmail() + "@test.com")
                .queryParam(Person_.eyeColor.getName(), p.getEyeColor())
                .queryParam(Person_.sex.getName(), p.getSex())
                .queryParam(Person_.numberOfChildren.getName(), p.getNumberOfChildren());
        List<String> response = restTemplate.postForObject(builder.toUriString(),null, List.class);

        //then
        assertThat(response)
                .isEmpty();
    }

    @Test
    public void whenParamsAreMissingShouldGiveAnError() {
        //given
        Person p = TestObjectFactory.nextPerson(Sex.M);

        //when
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_HTTP + port + BASE_URL + "/create")
                .queryParam(Person_.email.getName(), p.getEmail() + "@test.com")
                .queryParam(Person_.eyeColor.getName(), p.getEyeColor())
                .queryParam(Person_.sex.getName(), p.getSex())
                .queryParam(Person_.numberOfChildren.getName(), p.getNumberOfChildren());
        String response = restTemplate.postForObject(builder.toUriString(),null, String.class);

        //then
        assertThat(response)
                .hasSize(2);
    }


}