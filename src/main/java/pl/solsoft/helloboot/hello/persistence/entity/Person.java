package pl.solsoft.helloboot.hello.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Person
{
    @Id
    @Column(name = "PERSON_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EYE_COLOR")
    private String eyeColor;

    @Column(name = "SEX")
    private String sex;

    @Column(name = "NUMBER_OF_CHILDREN")
    private Integer numberOfChildren;

    @OneToMany(targetEntity = Car.class, mappedBy = "person", cascade = CascadeType.ALL) //cascades are not needed here
    private List<Car> cars;

    @ManyToMany
    @JoinTable(
            name = "Person_Address",
            joinColumns = { @JoinColumn(name = "PERSON_ID") },
            inverseJoinColumns = { @JoinColumn(name = "ADDRESS_ID") }
    )
    private List<Address> addresses;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEyeColor()
    {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor)
    {
        this.eyeColor = eyeColor;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public Integer getNumberOfChildren()
    {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Integer numberOfChildren)
    {
        this.numberOfChildren = numberOfChildren;
    }

    public List<Car> getCars()
    {
        return cars;
    }

    public void setCars(List<Car> cars)
    {
        this.cars = cars;
    }

    public List<Address> getAddresses()
    {
        return addresses;
    }

    public void setAddresses(List<Address> addresses)
    {
        this.addresses = addresses;
    }
}
