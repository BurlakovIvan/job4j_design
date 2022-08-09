package ru.job4j.srp.setreport;

import ru.job4j.srp.Employee;
import ru.job4j.srp.Report;
import ru.job4j.srp.Store;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportHR implements Report {

    private Store store;

    public ReportHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> workersSorted = new ArrayList<>(store.findBy(filter));
        workersSorted.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        for (Employee employee : workersSorted) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
