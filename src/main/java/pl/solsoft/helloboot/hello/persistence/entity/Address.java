package pl.solsoft.helloboot.hello.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
public class Address implements Serializable {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Pattern(regexp = "[a-zA-Z0-9]+")
    @Column(name = "street", nullable = false)
    private String street;

    @Pattern(regexp = "\\d+.?")
    @Column(name = "number", nullable = false)
    @Size(min = 2, max = 255, message = "Number must be between 10 and 255 characters")
    private String number;

    @Pattern(regexp = "[a-zA-Z0-9]+")
    @Column(name = "flat_number", nullable = true)
    private String flatNumber;

    @Pattern(regexp = "\\d{2}-?\\d{3}")
    @Column(name = "code", nullable = false)
    private String code;

    @Pattern(regexp = "[a-zA-Z0-9]+")
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
