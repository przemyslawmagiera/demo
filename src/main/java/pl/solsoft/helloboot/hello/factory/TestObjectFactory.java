package pl.solsoft.helloboot.hello.factory;

import org.apache.commons.lang3.RandomStringUtils;
import pl.solsoft.helloboot.hello.enumeration.EyeColor;
import pl.solsoft.helloboot.hello.enumeration.Sex;
import pl.solsoft.helloboot.hello.persistence.entity.Person;

import java.util.Random;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class TestObjectFactory {
    public static Person nextPerson(final String email) {
        Person person = new Person();
        Random random = new Random();
        if (random.nextBoolean()) {
            person.setSex(Sex.F);
        } else {
            person.setSex(Sex.M);
        }
        person.setNumberOfChildren(random.nextInt(10));
        person.setName(randomAlphabetic(random.nextInt(10) + 5));
        if (random.nextBoolean()) {
            person.setEyeColor(EyeColor.BLUE);
        } else {
            person.setEyeColor(EyeColor.BROWN);
        }
        person.setEmail(email);
        return person;
    }

    public static Person nextPerson(final Sex sex) {
        Person person = new Person();
        Random random = new Random();
        person.setNumberOfChildren(random.nextInt(10));
        person.setName(randomAlphabetic(random.nextInt(10) + 5));
        if (random.nextBoolean()) {
            person.setEyeColor(EyeColor.BLUE);
        } else {
            person.setEyeColor(EyeColor.BROWN);
        }
        person.setEmail(randomAlphabetic(random.nextInt(10) + 5) + "@test.pl");
        person.setSex(sex);
        return person;
    }

    public static Person nextPerson() {
        Person person = new Person();
        Random random = new Random();
        person.setNumberOfChildren(random.nextInt(10));
        person.setName(randomAlphabetic(random.nextInt(10) + 5));
        if (random.nextBoolean()) {
            person.setEyeColor(EyeColor.BLUE);
        } else {
            person.setEyeColor(EyeColor.BROWN);
        }
        person.setEmail(randomAlphabetic(random.nextInt(10) + 5) + "@test.pl");
        if (random.nextBoolean()) {
            person.setSex(Sex.M);
        } else {
            person.setSex(Sex.F);
        }
        return person;
    }

    public static Person nextPerson(final Sex sex, final int numberOfChildren) {
        Person person = new Person();
        Random random = new Random();
        person.setNumberOfChildren(numberOfChildren);
        person.setName(randomAlphabetic(random.nextInt(10) + 5));
        if (random.nextBoolean()) {
            person.setEyeColor(EyeColor.BLUE);
        }else {
            person.setEyeColor(EyeColor.BROWN);
        }
        person.setEmail(randomAlphabetic(random.nextInt(10) + 5) + "@test.pl");
        person.setSex(sex);
        return person;
    }

    public static Person nextPerson(final Sex sex, final int numberOfChildren, final EyeColor eyeColor) {
        Person person = new Person();
        Random random = new Random();
        person.setNumberOfChildren(numberOfChildren);
        person.setName(randomAlphabetic(random.nextInt(10) + 5));
        person.setEyeColor(eyeColor);
        person.setEmail(randomAlphabetic(random.nextInt(10) + 5) + "@test.pl");
        person.setSex(sex);
        return person;
    }
}
