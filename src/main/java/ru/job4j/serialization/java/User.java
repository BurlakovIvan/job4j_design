package ru.job4j.serialization.java;

import java.util.Arrays;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    @XmlAttribute
    private boolean access;
    @XmlAttribute
    private String username;
    @XmlAttribute
    private int age;
    private Address address;
    @XmlElementWrapper(name = "conditions")
    @XmlElement(name = "condition")
    private String[] conditions;

    public User() {
    }

    public User(String name, boolean access, int age, Address address, String[] conditions) {
        this.username = name;
        this.access = access;
        this.age = age;
        this.address = address;
        this.conditions = conditions;
    }

    @Override
    public String toString() {
        return "User{"
                + "access=" + access
                + ", username='" + username + '\''
                + ", age=" + age
                + ", address=" + address
                + ", information=" + Arrays.toString(conditions)
                + '}';
    }
}
