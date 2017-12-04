package pl.solsoft.helloboot.hello.persistence.entity;

import javax.persistence.*;

@Entity
public class Car
{
    @Id
    @Column(name = "CAR_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "PLATE_NUMBER")
    private String plateNumber;

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getPlateNumber()
    {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber)
    {
        this.plateNumber = plateNumber;
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }
}
