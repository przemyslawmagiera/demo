package pl.solsoft.helloboot.hello.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.solsoft.helloboot.hello.common.to.PersonTo;
import pl.solsoft.helloboot.hello.persistence.entity.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Mapping(source = "name", target = "firstName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "eyeColor", target = "eyeColor")
    @Mapping(source = "sex", target = "gender")
    @Mapping(source = "numberOfChildren", target = "kids")
    PersonTo mapToPersonTo(Person person);

    @Mapping(source = "firstName", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "eyeColor", target = "eyeColor")
    @Mapping(source = "gender", target = "sex")
    @Mapping(source = "kids", target = "numberOfChildren")
    Person mapToPerson(PersonTo personTo);
}
