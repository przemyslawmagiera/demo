package pl.solsoft.helloboot.hello.persistence.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "car", indexes = {
        @Index(name = "car_id_idx", columnList = "car_id", unique = true),
        @Index(name = "car_plate_number_idx", columnList = "plate_number", unique = true)
})
public class Car implements Serializable {
    @Id
    @NotNull
    @Column(name = "car_id", unique = true)
    @GenericGenerator(
            name = "car_id_seq",
            strategy = "sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "car_id_seq",
                            value = "sequence"
                    )

            })
    @GeneratedValue(generator = "car_id_seq")
    private Long id;

    @NotBlank
    @Column(name = "plate_number", nullable = false, length = 255, unique = true)
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
