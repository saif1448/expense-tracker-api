package com.practiceproj.expensetracker.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.practiceproj.expensetracker.models.Income;
import com.practiceproj.expensetracker.models.Invoice;
import com.practiceproj.expensetracker.models.User;

@Service
public class ExpenseTrackerService {

    private List<User> userList = new ArrayList<User>();

    public User addNewUser(User user) {
        userList.add(user);
        return user;
    }

    public List<User> getAllUsers() {
        return this.userList;
    }

    public User getUser(int id) {
        User matchedUser = new User();
        for (var user : this.userList) {
            if (user.getId() == id) {
                matchedUser = user;
                break;
            }
        }
        return matchedUser;
    }

    public Invoice addExpense(Invoice invoice, int id) {
        User user = getUser(id);
        user.addExepense(invoice);
        return invoice;
    }

    public Income addIncome(Income income, int id) {
        User user = getUser(id);
        user.addIncome(income);
        return income;
    }

    public List<Invoice> fetchAllExpenses(int id) {
        User user = getUser(id);
        return user.getExpenseList();
    }

    public List<Income> fetchAllIncomes(int id) {
        User user = getUser(id);
        return user.getIncomeList();
    }

    public List<Invoice> fetchMonthlyExpenses(int id, String month) {
        List<Invoice> montlyExpenses = new ArrayList<>();
        List<Invoice> allExpenses = fetchAllExpenses(id);
        for (var expense : allExpenses) {
            if (expense.getMonth().equals(month)) {
                montlyExpenses.add(expense);
            }
        }
        return montlyExpenses;
    }

    public List<Income> fetchMonthlyIncomes(int id, String month) {
        List<Income> montlyIncomes = new ArrayList<>();
        List<Income> allIncomes = fetchAllIncomes(id);
        for (var income : allIncomes) {
            if (income.getMonth().equals(month)) {
                montlyIncomes.add(income);
            }
        }
        return montlyIncomes;
    }

    public Map<String, Double> fetchMonthlyDetails(int id, String month) {
        double totalIncome = 0;
        double totalExpense = 0;
        double netBalance = 0;

        for (var income : fetchMonthlyIncomes(id, month)) {
            totalIncome += income.getAmount();
        }

        for (var expense : fetchMonthlyExpenses(id, month)) {
            totalExpense += expense.getAmount();
        }

        netBalance = totalIncome - totalExpense;

        Map<String, Double> monthlyDetails = new HashMap<String, Double>();

        monthlyDetails.put("total_income", totalIncome);
        monthlyDetails.put("total_expense", totalExpense);
        monthlyDetails.put("net_balance", netBalance);

        return monthlyDetails;

    }

    public Map<String, Map<String, Double>> fetchYearlyDetails(int id) {
        List<String> monthList = new ArrayList<String>(
                List.of("jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"));

        Map<String, Map<String, Double>> yearlyDetails = new HashMap<>();

        for (var month : monthList) {
            Map<String, Double> monthlyDetails = new HashMap<>();
            monthlyDetails = fetchMonthlyDetails(id, month);

            yearlyDetails.put(month, monthlyDetails);
        }

        return yearlyDetails;

    }

}
