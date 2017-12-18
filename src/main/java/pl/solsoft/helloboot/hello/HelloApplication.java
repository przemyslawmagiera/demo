package pl.solsoft.helloboot.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class HelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
        System.out.println(getMessage());
    }

    private static String getMessage() {
        return "hello world";
    }

}
