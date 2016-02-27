package org.itu.demandforecaster;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Integer.getInteger;

/**
 * Created by Group 1.
 */

public class Transaction {
    private String category;
    private String subcategory;
    private int amount;
    private Date date;

    /**
     * Creates an instance of Transaction class using
     * data parsed from sample data file. Assumes all
     * parsed data are strings.
     *
     * @param cat Category name.
     * @param subcat Subcategory name.
     * @param amount Transaction amount.
     * @param date Transaction date.
     */
    public Transaction(String cat, String subcat, String amount, String date) {
        this.setCategory(cat);
        this.setAmount(amount);
        this.setSubcategory(subcat);
        this.setDate(date);
    }

    public Transaction(String cat, String subcat, int amount, Date date) {
        this.setCategory(cat);
        this.setAmount(amount);
        this.setSubcategory(subcat);
        this.setDate(date);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.date = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setAmount(String amount) {
        this.amount = getInteger(amount);
    }
}
