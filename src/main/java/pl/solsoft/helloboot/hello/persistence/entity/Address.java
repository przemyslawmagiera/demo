package pl.solsoft.helloboot.hello.persistence.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "address", indexes = {
        @Index(name = "person_code_idx", columnList = "code", unique = false),
        @Index(name = "person_number_idx", columnList = "number", unique = false),
        @Index(name = "person_flat_number_idx", columnList = "flat_number", unique = false)
})
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Getter
@Setter
public class Address implements Serializable {

    @Id
    @NotNull
    @Column(name = "address_id")
    @GenericGenerator(
            name = "address_id_seq",
            strategy = "sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "address_id_seq",
                            value = "sequence"
                    )

            })
    @GeneratedValue(generator = "address_id_seq")
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "street", nullable = false, length = 255)
    private String street;

    @NotBlank
    @Size(max = 15)
    @Column(name = "number", nullable = false, length = 15)
    private String number;

    @Size(max = 15)
    @Column(name = "flat_number", nullable = true, length = 15)
    private String flatNumber;

    @NotBlank
    @Size(max = 255)
    @Column(name = "code", nullable = false, length = 255)
    private String code;

    @NotBlank
    @Size(max = 255)
    @Column(name = "city", nullable = false, length = 255)
    private String city;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "address_person",
            inverseJoinColumns = {@JoinColumn(name = "person_id", referencedColumnName = "person_id")},
            joinColumns = {@JoinColumn(name = "address_id", referencedColumnName = "address_id")}
    )
    @Setter(AccessLevel.NONE)
    List<Person> people = new ArrayList<>();

    public List<Person> getPeople() {
        return Collections.unmodifiableList(people);
    }
}
