package ru.job4j.serialization.java;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
public class Address {

    @XmlAttribute
    private String city;
    @XmlAttribute
    private String street;

    public Address() {
    }

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }
}
