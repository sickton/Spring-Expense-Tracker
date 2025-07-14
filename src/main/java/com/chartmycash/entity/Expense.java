package com.chartmycash.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private LocalDate expenseDate;

    @Column
    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    @Column
    private double amount;

    @Column
    private String description;

    @Column
    private String username;

    public Expense() {}

    public Expense(String title, LocalDate date, ExpenseCategory category, double amount, String description, String username)
    {
        if(title == null || title.isEmpty())
            throw new IllegalArgumentException("Title cannot be null or empty!");
        this.title = title;
        this.expenseDate = date;
        this.category = category;
        if(amount <= 0)
            throw new IllegalArgumentException("Amount cannot be negative!");
        this.amount = amount;
        if(description == null || description.isEmpty())
            throw new IllegalArgumentException("Description cannot be null or empty!");
        this.description = description;
        if(username == null || username.isEmpty())
            throw new IllegalArgumentException("Username cannot be null or empty!");
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }
}