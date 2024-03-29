package ru.job4j.srp.setreport;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.srp.Employee;
import ru.job4j.srp.Report;
import ru.job4j.srp.Store;

import java.util.function.Predicate;

public class ReportToJSON implements Report {
    private Store store;
    private Gson gson;

    public ReportToJSON(Store store) {
        this.store = store;
        this.gson = new GsonBuilder().create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store.findBy(filter));
    }
}
