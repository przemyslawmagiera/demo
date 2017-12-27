package pl.solsoft.helloboot.hello.persistence.entity;

import lombok.*;
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
import java.util.Map;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;

@Entity
@Table(name = "person", indexes = {
        @Index(name = "person_number_children_idx", columnList = "number_of_children", unique = false)
})
@NoArgsConstructor
@EqualsAndHashCode(of = "email")
@Getter
@Setter
public class Person implements Serializable {
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
    @Setter(AccessLevel.NONE)
    private List<Car> cars = new ArrayList<>();

    @ManyToMany(mappedBy = "people", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @Setter(AccessLevel.NONE)
    private List<Address> addresses = new ArrayList<>();

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
        return unmodifiableList(addresses);
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

    public static Person createFromRequestParams(Map<String,String> requestParams) throws NumberFormatException {
        Person person = new Person();
        person.setName(requestParams.get(pl.solsoft.helloboot.hello.persistence.entity.Person_.name.getName()));
        person.setEyeColor(EyeColor.valueOf(requestParams.get(pl.solsoft.helloboot.hello.persistence.entity.Person_.eyeColor.getName())));
        person.setSex(Sex.valueOf(requestParams.get(pl.solsoft.helloboot.hello.persistence.entity.Person_.sex.getName())));
        person.setEmail(requestParams.get(pl.solsoft.helloboot.hello.persistence.entity.Person_.email.getName()));
        person.setNumberOfChildren(Integer.parseInt(requestParams.get(pl.solsoft.helloboot.hello.persistence.entity.Person_.numberOfChildren.getName())));
        return person;
    }
}
