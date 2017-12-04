package pl.solsoft.helloboot.hello.persistence.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
public class Address implements Serializable {
    @Id
    @NotNull
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "street", nullable = false, length = 255)
    private String street;

    @NotBlank
    @Size(max = 255)
    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "flat_number", nullable = true)
    private String flatNumber;

    @NotNull(message = "Code cannot be null")
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull(message = "City cannot be null")
    @Column(name = "city", nullable = false)
    private String city;

    @ManyToMany(mappedBy = "addresses")
    private List<Person> people;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}
