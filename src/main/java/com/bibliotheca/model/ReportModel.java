package com.bibliotheca.model;

public class ReportModel {
    private int Report_ID;
    private String Report_Type;
    private String Report_Description;
    private String Report_Date;
    private int User_ID;
    
    // Fields for User Report JSP
    private int borrowedBooks;
    private double totalPayments;
    
    // Fields for Admin Report JSP
    private int totalBooks;
    private int totalUsers;
    private double monthlyRevenue;
    private int currentlyBorrowed;
    private int usersWithFines;

    public ReportModel() {
    }

    public ReportModel(int report_ID, String report_Type, String report_Description, String report_Date, int user_ID) {
        Report_ID = report_ID;
        Report_Type = report_Type;
        Report_Description = report_Description;
        Report_Date = report_Date;
        User_ID = user_ID;
    }

    public int getReport_ID() {
        return Report_ID;
    }

    public void setReport_ID(int report_ID) {
        Report_ID = report_ID;
    }

    public String getReport_Type() {
        return Report_Type;
    }

    public void setReport_Type(String report_Type) {
        Report_Type = report_Type;
    }

    public String getReport_Description() {
        return Report_Description;
    }

    public void setReport_Description(String report_Description) {
        Report_Description = report_Description;
    }

    public String getReport_Date() {
        return Report_Date;
    }

    public void setReport_Date(String report_Date) {
        Report_Date = report_Date;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }
    
    // Getters and setters for User Report fields
    public int getBorrowedBooks() {
        return borrowedBooks;
    }
    
    public void setBorrowedBooks(int borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
    
    public double getTotalPayments() {
        return totalPayments;
    }
    
    public void setTotalPayments(double totalPayments) {
        this.totalPayments = totalPayments;
    }
    
    // Getters and setters for Admin Report fields
    public int getTotalBooks() {
        return totalBooks;
    }
    
    public void setTotalBooks(int totalBooks) {
        this.totalBooks = totalBooks;
    }
    
    public int getTotalUsers() {
        return totalUsers;
    }
    
    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }
    
    public double getMonthlyRevenue() {
        return monthlyRevenue;
    }
    
    public void setMonthlyRevenue(double monthlyRevenue) {
        this.monthlyRevenue = monthlyRevenue;
    }
    
    public int getCurrentlyBorrowed() {
        return currentlyBorrowed;
    }
    
    public void setCurrentlyBorrowed(int currentlyBorrowed) {
        this.currentlyBorrowed = currentlyBorrowed;
    }
    
    public int getUsersWithFines() {
        return usersWithFines;
    }
    
    public void setUsersWithFines(int usersWithFines) {
        this.usersWithFines = usersWithFines;
    }
}