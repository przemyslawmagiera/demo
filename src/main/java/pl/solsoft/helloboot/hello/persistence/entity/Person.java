package pl.solsoft.helloboot.hello.persistence.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import pl.solsoft.helloboot.hello.enumeration.EyeColor;
import pl.solsoft.helloboot.hello.enumeration.Sex;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person", indexes = {
        @Index(name = "person_id_idx", columnList = "car_id", unique = true),
        @Index(name = "person_email_idx", columnList = "email", unique = true),
        @Index(name = "person_number_children_idx", columnList = "number_of_children", unique = false)
})
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
    @Size(max = 255)
    @Column(name = "email", nullable = false, length = 255, unique = true)
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
    private List<Car> cars = new ArrayList<>();

    @ManyToMany(mappedBy = "people", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Address> addresses = new ArrayList<>();

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

    public void addCar(Car car) {
        cars.add(car);
        car.setPerson(this);
    }

    public void removeCar(Car car){
        car.setPerson(null);
        cars.remove(car);
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address){
        addresses.add(address);
        address.getPeople().add(this);
    }

    public void removeAddress(Address address){
        addresses.remove(address);
        address.getPeople().remove(this);
    }

    public void remove(){
        List<Address> toBeRemoved = new ArrayList<>(addresses);
        toBeRemoved.forEach(this::removeAddress);
    }
}
