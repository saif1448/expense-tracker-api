package com.practiceproj.expensetracker.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practiceproj.expensetracker.models.Income;
import com.practiceproj.expensetracker.models.Invoice;
import com.practiceproj.expensetracker.models.User;
import com.practiceproj.expensetracker.services.ExpenseTrackerService;

@RestController
public class ExpenseTrackerController {

    @Autowired
    ExpenseTrackerService expenseTrackerService;

    @PostMapping("/create-user")
    public User createUser(@RequestBody User user) {
        return expenseTrackerService.addNewUser(user);
    }

    @GetMapping("/show-users")
    public List<User> showAllUsers() {
        return expenseTrackerService.getAllUsers();
    }

    @GetMapping("/show-users/{id}")
    public User showSingleUser(@PathVariable int id) {
        System.out.println(id);
        return expenseTrackerService.getUser(id);
    }

    @PostMapping("/create-expense/{id}")
    public Invoice createExpense(@PathVariable int id, @RequestBody Invoice invoice) {

        return expenseTrackerService.addExpense(invoice, id);

    }

    @PostMapping("/create-income/{id}")
    public Income createIncome(@PathVariable int id, @RequestBody Income income) {
        return expenseTrackerService.addIncome(income, id);
    }

    @GetMapping("/get-all-expenses/{id}")
    public List<Invoice> getAllExpenses(@PathVariable int id) {
        return expenseTrackerService.fetchAllExpenses(id);
    }

    @GetMapping("/get-all-incomes/{id}")
    public List<Income> getAllIncomes(@PathVariable int id) {
        return expenseTrackerService.fetchAllIncomes(id);
    }

    @GetMapping("/get-monthly-expenses/{id}/{month}")
    public List<Invoice> getMonthlyExpenses(@PathVariable int id, @PathVariable String month) {
        return expenseTrackerService.fetchMonthlyExpenses(id, month);
    }

    @GetMapping("/get-monthly-incomes/{id}/{month}")
    public List<Income> getMonthlyIncomes(@PathVariable int id, @PathVariable String month) {
        return expenseTrackerService.fetchMonthlyIncomes(id, month);
    }

    @GetMapping("/get-monthly-details/{id}/{month}")
    public Map<String, Double> getMonthlyDetails(@PathVariable int id, @PathVariable String month) {
        return expenseTrackerService.fetchMonthlyDetails(id, month);
    }

    @GetMapping("/get-yearly-details/{id}")
    public Map<String, Map<String, Double>> getYearlyDetails(@PathVariable int id) {
        return expenseTrackerService.fetchYearlyDetails(id);
    }

}
