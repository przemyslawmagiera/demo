package pl.solsoft.helloboot.hello.service;


import org.springframework.stereotype.Service;
import pl.solsoft.helloboot.hello.enumeration.Sex;

@Service
public interface PersonService {

    int countChildrenBySex(final Sex sex);
}
