package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class UserSerialization {

    public static void main(String[] args) {
        final User user = new User("Ivan", true, 30,
                new Address("Moscow", "Lenina"),
                new String[] {"condition 1", "condition 2"});

        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        var userInJSON = gson.toJson(user);
        System.out.println(userInJSON);
        final User userFromJSON = gson.fromJson(userInJSON, User.class);
        System.out.println(userFromJSON);
    }

}
