package pl.solsoft.helloboot.hello.persistence.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "car")
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id", "person"})
public class Car implements Serializable {

    @Id
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
    @Getter
    @Setter
    private Long id;

    @NotBlank
    @Column(name = "plate_number", nullable = false, length = 255, unique = true)
    @Getter
    @Setter
    private String plateNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    @Getter
    @Setter
    private Person person;
}
