package edu.purdue.vieck.budgetapp;

/**
 * Created by vieck on 7/14/15.
 */
public class BudgetElement {
    private String category, note;
    private boolean type;
    private int day, month, year;
    private float amount;

    public BudgetElement() {

    }

    public BudgetElement(float amount, String category, boolean type, int day, int month, int year, String note){
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.day = day;
        this.month = month;
        this.year = year;
        this.note = note;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
