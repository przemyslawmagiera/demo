package pl.solsoft.helloboot.hello.persistence.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
public class Address
{
    @Id
    @Column(name = "ADDRESS_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "STREET")
    private String street;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "CODE")
    private String code;

    @Column(name = "CITY")
    private String city;

    @ManyToMany(mappedBy = "addresses")
    private List<Person> people;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public List<Person> getPeople()
    {
        return people;
    }

    public void setPeople(List<Person> people)
    {
        this.people = people;
    }
}
