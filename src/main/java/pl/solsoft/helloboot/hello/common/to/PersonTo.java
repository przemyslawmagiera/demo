package pl.solsoft.helloboot.hello.common.to;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import pl.solsoft.helloboot.hello.enumeration.EyeColor;
import pl.solsoft.helloboot.hello.enumeration.Sex;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
public class PersonTo {

    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private EyeColor eyeColor;

    @NotNull
    private Sex gender;

    @NotNull
    private int kids;
}
