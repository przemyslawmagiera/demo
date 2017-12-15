package pl.solsoft.helloboot.hello;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.solsoft.helloboot.hello.persistence.dao.PersonDao;
import pl.solsoft.helloboot.hello.persistence.entity.Person;

import javax.annotation.Resource;


@SpringBootApplication
public class HelloApplication
{

    @Resource
    private PersonDao personDao;

    public static void main(String[] args)
    {
        SpringApplication.run(HelloApplication.class, args);
        System.out.println(getMessage());
    }

    private static String getMessage()
    {
        return "hello world";
    }

}
