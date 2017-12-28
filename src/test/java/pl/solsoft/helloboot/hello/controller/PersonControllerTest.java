package pl.solsoft.helloboot.hello.controller;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.solsoft.helloboot.hello.common.mapper.PersonMapper;
import pl.solsoft.helloboot.hello.common.dto.PersonDTO;
import pl.solsoft.helloboot.hello.common.dto.ValidationMessageDTO;
import pl.solsoft.helloboot.hello.controller.advice.PersonControllerAdvice;
import pl.solsoft.helloboot.hello.factory.TestObjectFactory;
import pl.solsoft.helloboot.hello.persistence.entity.Person;
import pl.solsoft.helloboot.hello.persistence.repository.PersonRepository;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class PersonControllerTest {
    public static final String WRONG_EMAIL_MESSAGE = "Email - not a well-formed email address";
    public static final String NOT_BLANK_MESSAGE = "NotBlank - may not be empty";
    public static final String NOT_NULL_MESSAGE = "NotNull - may not be null";
    private static final String BASE_URL = "/person";

    private MockMvc mockMvc;

    @Resource
    private Gson gson;

    @Spy
    @Resource
    private PersonMapper mapper;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonController personController;

    @Before
    public void setUp()
    {
        this.mockMvc = MockMvcBuilders.standaloneSetup(personController)
                .setControllerAdvice(new PersonControllerAdvice()).build();
    }

    @Test
    public void shouldCreatePersonAndReturnDtoAndCreatedResponse() throws Exception {
        //given
        final Person p = TestObjectFactory.nextPerson();
        final PersonDTO personDTO = mapper.mapToPersonDTO(p);

        //when
        when(personRepository.save(p)).thenReturn(p);
        final ResultActions resultActions = mockMvc.perform(post(BASE_URL + "/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(personDTO)));
        final PersonDTO responsePersonDTO = gson
                .fromJson(resultActions.andReturn().getResponse().getContentAsString(), PersonDTO.class);
        //then
        resultActions.andExpect(status().isCreated());
        assertThat(personDTO)
                .isEqualTo(responsePersonDTO);
    }

    @Test
    public void shouldGiveAnValidationErrorAndBadRequestWhenParamsAreMissing() throws Exception {
        //given
        final Person p = TestObjectFactory.nextPerson();
        p.setName(null);
        p.setEyeColor(null);
        final PersonDTO personDTO = mapper.mapToPersonDTO(p);

        //when
        when(personRepository.save(p)).thenReturn(p);
        ResultActions resultActions = mockMvc.perform(post(BASE_URL + "/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(personDTO)));
        final ValidationMessageDTO responseMessage = gson
                .fromJson(resultActions.andReturn().getResponse().getContentAsString(), ValidationMessageDTO.class);

        //then
        resultActions.andExpect(status().isBadRequest());
        assertThat(responseMessage.getMessages())
                .contains(NOT_NULL_MESSAGE)
                .contains(NOT_BLANK_MESSAGE);
    }

    @Test
    public void shouldGiveAnValidationErrorAndBadRequestWhenEmailIsWrong() throws Exception {
        //given
        final Person p = TestObjectFactory.nextPerson();
        p.setEmail("qwerty");
        final PersonDTO personDTO = mapper.mapToPersonDTO(p);

        //when
        when(personRepository.save(p)).thenReturn(p);
        final ResultActions resultActions = mockMvc.perform(post(BASE_URL + "/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(personDTO)));
        final ValidationMessageDTO responseMessage = gson
                .fromJson(resultActions.andReturn().getResponse().getContentAsString(), ValidationMessageDTO.class);

        //then
        resultActions.andExpect(status().isBadRequest());
        assertThat(responseMessage.getMessages())
                .contains(WRONG_EMAIL_MESSAGE);
    }
}