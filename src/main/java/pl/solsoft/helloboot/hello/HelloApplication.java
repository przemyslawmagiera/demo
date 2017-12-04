package pl.solsoft.helloboot.hello;

import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.stereotype.Controller;
import pl.solsoft.helloboot.hello.persistence.configuration.TraditionalPersistenceXmlConfiguration;


@SpringBootApplication
public class HelloApplication
{

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
