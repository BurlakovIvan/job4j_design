package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class UserSerialization {

    public static void main(String[] args) throws Exception {
        final User user = new User("Ivan", true, 30,
                new Address("Moscow", "Lenina"),
                new String[] {"condition 1", "condition 2"});

        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        var userInJSON = gson.toJson(user);
        System.out.println("JSON");
        System.out.println(userInJSON);
        final User userFromJSON = gson.fromJson(userInJSON, User.class);
        System.out.println(userFromJSON);

        System.out.println("XML");
        /*
        Преобразование XML
         */
        JAXBContext context = JAXBContext.newInstance(User.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(user, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            User result = (User) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }

}
