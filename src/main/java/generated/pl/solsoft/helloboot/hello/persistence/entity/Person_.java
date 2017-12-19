package pl.solsoft.helloboot.hello.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pl.solsoft.helloboot.hello.enumeration.EyeColor;
import pl.solsoft.helloboot.hello.enumeration.Sex;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Person.class)
public abstract class Person_ {

	public static volatile ListAttribute<Person, Car> cars;
	public static volatile ListAttribute<Person, Address> addresses;
	public static volatile SingularAttribute<Person, Integer> numberOfChildren;
	public static volatile SingularAttribute<Person, EyeColor> eyeColor;
	public static volatile SingularAttribute<Person, Sex> sex;
	public static volatile SingularAttribute<Person, String> name;
	public static volatile SingularAttribute<Person, Long> id;
	public static volatile SingularAttribute<Person, String> email;

}

