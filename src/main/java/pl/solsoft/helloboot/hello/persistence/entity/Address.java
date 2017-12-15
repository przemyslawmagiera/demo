package pl.solsoft.helloboot.hello.persistence.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "address", indexes = {
        @Index(name = "person_code_idx", columnList = "code", unique = false),
        @Index(name = "person_number_idx", columnList = "number", unique = false),
        @Index(name = "person_flat_number_idx", columnList = "flat_number", unique = false)
})
public class Address implements Serializable {
    public static final String FIELD_ID = "id";
    public static final String FIELD_STREET= "street";
    public static final String FIELD_NUMBER = "number";
    public static final String FIELD_FLAT_NUMBER = "flatNumber";
    public static final String FIELD_NUMBER_OF_CHILDREN = "numberOfChildren";
    public static final String FIELD_CODE = "code";
    public static final String FIELD_CITY = "city";
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
    private List<Person> people;

    public Address(){
    }

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
}
