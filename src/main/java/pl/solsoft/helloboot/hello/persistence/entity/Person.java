package pl.solsoft.helloboot.hello.persistence.entity;

import pl.solsoft.helloboot.hello.enumeration.EyeColor;
import pl.solsoft.helloboot.hello.enumeration.Sex;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
public class Person implements Serializable {
    @Id
    @Column(name = "person_id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "Id cannot be null")
    private Long id;

    @Pattern(regexp = "[a-zA-Z]+")
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @Pattern(regexp = "[a-zA-Z]+")
    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, max = 255, message = "Last name must be between 2 and 255 characters")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull(message = "Eye color cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "eye_color", nullable = false)
    private EyeColor eyeColor;

    @NotNull(message = "Sex cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;

    @NotNull(message = "Number of children cannot be null")
    @Column(name = "number_of_children", nullable = false)
    private Integer numberOfChildren = 0;

    @OneToMany(targetEntity = Car.class, mappedBy = "person", cascade = CascadeType.ALL)
    private List<Car> cars;

    @ManyToMany
    @JoinTable(
            name = "person_address",
            joinColumns = {@JoinColumn(name = "person_id")},
            inverseJoinColumns = {@JoinColumn(name = "address_id")}
    )
    private List<Address> addresses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
