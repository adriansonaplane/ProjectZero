package dev.ledesma.entities;

import java.util.Objects;

public class Expense {

    private int id;
    private int amount;
    private long date;
    private String category;
    private String description;
    private ExpenseStatus status;
    private int employeeId;

    public Expense(){}

    public Expense(int id, int amount, long date, String category, String description, ExpenseStatus status, int employeeId) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.description = description;
        this.status = status;
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExpenseStatus getStatus() {
        return status;
    }

    public void setStatus(ExpenseStatus status) {
        this.status = status;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", employeeId=" + employeeId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return id == expense.id && amount == expense.amount && date == expense.date && employeeId == expense.employeeId && Objects.equals(category, expense.category) && Objects.equals(description, expense.description) && status == expense.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, date, category, description, status, employeeId);
    }
}
