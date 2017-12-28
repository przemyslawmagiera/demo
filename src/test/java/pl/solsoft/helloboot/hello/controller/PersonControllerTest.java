package pl.solsoft.helloboot.hello.controller;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import pl.solsoft.helloboot.hello.enumeration.EyeColor;
import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.factory.TestObjectFactory;
import pl.solsoft.helloboot.hello.persistence.entity.Person;
import pl.solsoft.helloboot.hello.persistence.repository.PersonRepository;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class PersonControllerTest {
    public static final String WRONG_EMAIL_MESSAGE = "Email - not a well-formed email address";
    public static final String NOT_BLANK_MESSAGE = "NotBlank - may not be empty";
    public static final String NOT_NULL_MESSAGE = "NotNull - may not be null";
    public static final String NOT_UNIQUE_EMAIL = "UniqueEmail - Duplicate email";
    private static final String BASE_URL = "/person";
    private final Person testPerson = new Person();

    @Resource
    private MockMvc mockMvc;

    @Resource
    private Gson gson;

    @MockBean
    private PersonRepository personRepository;

    @Before
    public void setUp() {
        testPerson.setEmail("test@test.pl");
        testPerson.setEyeColor(EyeColor.BLUE);
        testPerson.setName("Test");
        testPerson.setNumberOfChildren(5);
        testPerson.setSex(Sex.F);
    }

    @Test
    public void shouldCreatePersonAndReturnDtoAndCreatedResponse() throws Exception {
        //given
        final Person p = testPerson;
        final String testPersonDtoJson = "{\"firstName\":\"Test\",\"email\":\"test@test.pl\",\"eyeColor\":\"BLUE\"" +
                ",\"gender\":\"F\",\"kids\":5}";

        //when
        when(personRepository.save(p)).thenReturn(p);
        final ResultActions resultActions = mockMvc.perform(post(BASE_URL + "/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testPersonDtoJson));
        final String responsePersonDTO = resultActions.andReturn().getResponse().getContentAsString();
        //then
        resultActions.andExpect(status().isCreated());
        assertThat(testPersonDtoJson)
                .isEqualTo(responsePersonDTO);
        verify(personRepository).save(any(Person.class));
    }

    @Test
    public void shouldGiveAnValidationErrorAndBadRequestWhenParamsAreMissing() throws Exception {
        //given
        final String testPersonDtoJson = "{\"email\":\"test@test.pl\"" +
                ",\"gender\":\"F\",\"kids\":5}";

        //when
        ResultActions resultActions = mockMvc.perform(post(BASE_URL + "/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testPersonDtoJson));
        final ValidationMessageDTO responseMessage = gson
                .fromJson(resultActions.andReturn().getResponse().getContentAsString(), ValidationMessageDTO.class);

        //then
        resultActions.andExpect(status().isBadRequest());
        assertThat(responseMessage.getMessages())
                .contains(NOT_NULL_MESSAGE)
                .contains(NOT_BLANK_MESSAGE);
        verify(personRepository, never()).save(any(Person.class));
    }

    @Test
    public void shouldGiveAnValidationErrorAndBadRequestWhenEmailIsWrong() throws Exception {
        //given
        final String testPersonDtoJson = "{\"firstName\":\"Test\",\"email\":\"testest.pl\",\"eyeColor\":\"BLUE\"" +
                ",\"gender\":\"F\",\"kids\":5}";

        //when
        final ResultActions resultActions = mockMvc.perform(post(BASE_URL + "/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testPersonDtoJson));
        final ValidationMessageDTO responseMessage = gson
                .fromJson(resultActions.andReturn().getResponse().getContentAsString(), ValidationMessageDTO.class);

        //then
        resultActions.andExpect(status().isBadRequest());
        assertThat(responseMessage.getMessages())
                .contains(WRONG_EMAIL_MESSAGE);
        verify(personRepository, never()).save(any(Person.class));
    }

    @Test
    public void shouldGiveAnValidationErrorAndBadRequestWhenEmailExists() throws Exception {
        //given
        final String testPersonDtoJson = "{\"firstName\":\"Test\",\"email\":\"test@test.pl\",\"eyeColor\":\"BLUE\"" +
                ",\"gender\":\"F\",\"kids\":5}";
        given(personRepository.existsByEmail("test@test.pl")).willReturn(true);

        //when
        final ResultActions resultActions = mockMvc.perform(post(BASE_URL + "/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testPersonDtoJson));
        final ValidationMessageDTO responseMessage = gson
                .fromJson(resultActions.andReturn().getResponse().getContentAsString(), ValidationMessageDTO.class);

        //then
        resultActions.andExpect(status().isBadRequest());
        assertThat(responseMessage.getMessages())
                .contains(NOT_UNIQUE_EMAIL);
        verify(personRepository).existsByEmail(any(String.class));
        verify(personRepository, never()).save(any(Person.class));
    }
}