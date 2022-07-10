package ru.job4j.serialization.java;

import org.json.JSONPropertyIgnore;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
public class Address {

    private User head;
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


    public User getHead() {
        return head;
    }

    @JSONPropertyIgnore
    public void setHead(User head) {
        this.head = head;
    }
}
