package com.example.listener.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionEvent {
    private LocalDate date;
    private double amount;
    private String description;

    private Result result;

    public TransactionEvent(LocalDate date, double amount, String description, Result result) {
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.result = result;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Result getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "BankTransaction [amount=" + amount + ", date=" + date + ", description=" + description + "]";
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionEvent that = (TransactionEvent) o;
        return Double.compare(that.amount, amount) == 0 &&
                date.equals(that.date) &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount, description, result);
    }

}
