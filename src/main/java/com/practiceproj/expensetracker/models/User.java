package com.practiceproj.expensetracker.models;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String name;
    private int id;
    private List<Invoice> expenseList;
    private List<Income> incomeList;

    public User() {
        expenseList = new ArrayList<>();
        incomeList = new ArrayList<>();
    }

    public void addExepense(Invoice invoice) {
        this.expenseList.add(invoice);
    }

    public void addIncome(Income income) {
        this.incomeList.add(income);
    }

}
