package pl.solsoft.helloboot.hello.persistence.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import pl.solsoft.helloboot.hello.enumeration.EyeColor;
import pl.solsoft.helloboot.hello.enumeration.Sex;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;

@Entity
@Table(name = "person", indexes = {
        @Index(name = "person_number_children_idx", columnList = "number_of_children", unique = false)
})
public class Person implements Serializable {
    public static final String FIELD_ID = "id";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_EYE_COLOR = "eyeColor";
    public static final String FIELD_SEX = "sex";
    public static final String FIELD_NUMBER_OF_CHILDREN = "numberOfChildren";
    @Id
    @Column(name = "person_id", unique = true)
    @GenericGenerator(
            name = "person_id_seq",
            strategy = "sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "person_id_seq",
                            value = "sequence"
                    )

            })
    @GeneratedValue(generator = "person_id_seq")
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @NotBlank
    @Email
    @Size(max = 254)
    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "eye_color", nullable = false, length = 5)
    private EyeColor eyeColor;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false, length = 1)
    private Sex sex;

    @NotNull
    @Min(0)
    @Column(name = "number_of_children", nullable = false)
    private int numberOfChildren = 0;

    @OneToMany(targetEntity = Car.class, mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Car> cars = new ArrayList<>();

    @ManyToMany(mappedBy = "people", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Address> addresses = new ArrayList<>();

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(final EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(final Sex sex) {
        this.sex = sex;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(final Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public List<Car> getCars() {
        return unmodifiableList(cars);
    }

    public void addCar(final Car car) {
        cars.add(car);
        car.setPerson(this);
    }

    public void removeCar(final Car car) {
        car.setPerson(null);
        cars.remove(car);
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(final Address address) {
        addresses.add(address);
        address.people.add(this);
    }

    public void removeAddress(final Address address) {
        addresses.remove(address);
        address.people.remove(this);
    }

    public void remove() {
        List<Address> toBeRemoved = new ArrayList<>(addresses);
        toBeRemoved.forEach(this::removeAddress);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
