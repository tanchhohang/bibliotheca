package com.bibliotheca.model;

public class UserBookModel {
	private int User_ID;
	private int Book_ID;
	private String Borrowed_Date;
	private String Due_Date;
	private String Deturn_Date;
	private String Fine;
	
	
	public UserBookModel() {}
	
	public UserBookModel(int book_ID) {
		Book_ID = book_ID;
	}
	
	public UserBookModel(int user_ID, int book_ID, String borrowed_Date, String due_Date, String deturn_Date,
			String fine) {
		super();
		this.User_ID = user_ID;
		Book_ID = book_ID;
		Borrowed_Date = borrowed_Date;
		Due_Date = due_Date;
		Deturn_Date = deturn_Date;
		Fine = fine;
	}

	public int getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(int user_ID) {
		User_ID = user_ID;
	}

	public int getBook_ID() {
		return Book_ID;
	}

	public void setBook_ID(int book_ID) {
		Book_ID = book_ID;
	}

	public String getBorrowed_Date() {
		return Borrowed_Date;
	}

	public void setBorrowed_Date(String borrowed_Date) {
		Borrowed_Date = borrowed_Date;
	}

	public String getDue_Date() {
		return Due_Date;
	}

	public void setDue_Date(String due_Date) {
		Due_Date = due_Date;
	}

	public String getDeturn_Date() {
		return Deturn_Date;
	}

	public void setDeturn_Date(String deturn_Date) {
		Deturn_Date = deturn_Date;
	}

	public String getFine() {
		return Fine;
	}

	public void setFine(String fine) {
		Fine = fine;
	}
	
	

}
