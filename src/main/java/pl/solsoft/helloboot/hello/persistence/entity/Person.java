package pl.solsoft.helloboot.hello.persistence.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import pl.solsoft.helloboot.hello.enumeration.EyeColor;
import pl.solsoft.helloboot.hello.enumeration.Sex;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "person", indexes = {
        @Index(name = "person_index", columnList = "name,email,eye_color,sex,number_of_children", unique = false)
})
public class Person implements Serializable {
    @Id
    @Column(name = "person_id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @NotBlank
    @Email
    @Size(max = 255)
    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @NotEmpty
    @Size(max = 255)
    @Enumerated(EnumType.STRING)
    @Column(name = "eye_color", nullable = false, length = 255)
    private EyeColor eyeColor;

    @NotEmpty
    @Size(max = 255)
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false, length = 255)
    private Sex sex;

    @NotNull
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
