package ru.job4j.question;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        return new Info(count(previous, current, false),
                count(current, previous, true),
                count(current, previous, false));
    }

    private static int count(Set<User> firstSet,
                             Set<User> secondSet, boolean conditionWithListID) {
        var cListId = listID(firstSet, c -> !secondSet.contains(c));
        var conditionCount = condition(firstSet, cListId, conditionWithListID);
        return countStream(secondSet, conditionCount);
    }

    private static Predicate<User> condition(Set<User> set,
                                             List<Integer> listID, boolean conditionWithListID) {
        Predicate<User> notInSet = c -> !set.contains(c);
        return notInSet.and(conditionWithListID ? c -> listID.contains(c.getId())
                : c -> !listID.contains(c.getId()));
    }

    private static int countStream(Set<User> set, Predicate<User> condition) {
        return (int) set
                .stream()
                .filter(condition)
                .count();
    }

    private static List<Integer> listID(Set<User> set, Predicate<User> condition) {
        return set
                .stream()
                .filter(condition)
                .map(User::getId)
                .collect(Collectors.toList());
    }
}