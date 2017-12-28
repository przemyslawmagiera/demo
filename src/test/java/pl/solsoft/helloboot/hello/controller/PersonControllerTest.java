package pl.solsoft.helloboot.hello.controller;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import pl.solsoft.helloboot.hello.common.mapper.PersonMapper;
import pl.solsoft.helloboot.hello.common.to.PersonTo;
import pl.solsoft.helloboot.hello.common.to.ValidationMessageTo;
import pl.solsoft.helloboot.hello.factory.TestObjectFactory;
import pl.solsoft.helloboot.hello.persistence.entity.Person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class PersonControllerTest {
    public static final String WRONG_EMAIL_MESSAGE = "Email - not a well-formed email address";
    public static final String NOT_BLANK_MESSAGE = "NotBlank - may not be empty";
    public static final String NOT_NULL_MESSAGE = "NotNull - may not be null";

    @Autowired
    private PersonMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_URL = "/person";

    @Test
    public void shouldCreatePerson() throws Exception {
        //given
        Person p = TestObjectFactory.nextPerson();
        PersonTo personTo = mapper.mapToPersonTo(p);
        PersonTo responsePersonTo;
        Gson gson = new Gson();

        //when
        ResultActions resultActions = mockMvc.perform(post(BASE_URL + "/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(personTo)));
        responsePersonTo = gson
                .fromJson(resultActions.andReturn().getResponse().getContentAsString(), PersonTo.class);

        //then
        resultActions.andExpect(status().isCreated());
        assertThat(personTo)
                .isEqualTo(responsePersonTo);
    }

    @Test
    public void whenParamsAreMissingShouldGiveAnValidationErrorAndBadRequest() throws Exception {
        //given
        Person p = TestObjectFactory.nextPerson();
        p.setName(null);
        p.setEyeColor(null);
        PersonTo personTo = mapper.mapToPersonTo(p);
        ValidationMessageTo responseMessage;
        Gson gson = new Gson();



        //when
        ResultActions resultActions = mockMvc.perform(post(BASE_URL + "/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(personTo)));
        responseMessage = gson
                .fromJson(resultActions.andReturn().getResponse().getContentAsString(), ValidationMessageTo.class);

        //then
        resultActions.andExpect(status().isBadRequest());
        assertThat(responseMessage.getMessages())
                .contains(NOT_NULL_MESSAGE)
                .contains(NOT_BLANK_MESSAGE);
    }

    @Test
    public void whenEmailIsWrongShouldGiveAnValidationErrorAndBadRequest() throws Exception {
        //given
        Person p = TestObjectFactory.nextPerson();
        p.setEmail("qwerty");
        final PersonTo personTo = mapper.mapToPersonTo(p);
        final ValidationMessageTo responseMessage;
        Gson gson = new Gson();

        //when
        ResultActions resultActions = mockMvc.perform(post(BASE_URL + "/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(personTo)));
        responseMessage = gson
                .fromJson(resultActions.andReturn().getResponse().getContentAsString(), ValidationMessageTo.class);

        //then
        resultActions.andExpect(status().isBadRequest());
        assertThat(responseMessage.getMessages())
                .contains(WRONG_EMAIL_MESSAGE);
    }
}