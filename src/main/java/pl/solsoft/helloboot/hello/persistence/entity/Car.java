package pl.solsoft.helloboot.hello.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "car", indexes = {
        @Index(name = "car_index", columnList = "name,email,eye_color,sex,number_of_children", unique = false)
})
public class Car implements Serializable {
    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotNull(message = "Plate number cannot be null")
    @Column(name = "plate_number", nullable = false, length = 255)
    private String plateNumber;

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "person_id")
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
