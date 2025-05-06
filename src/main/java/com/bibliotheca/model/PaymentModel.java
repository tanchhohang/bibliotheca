package com.bibliotheca.model;

public class PaymentModel {
	private int Payment_ID;
	private String Payment_Type;
	private int Payment_Amount;
	private String Payment_Date;
	private int User_ID;
	
	public PaymentModel() {
		
	}
	
	public PaymentModel(int payment_ID, String payment_Type, int payment_Amount, String payment_Date, int user_ID) {
		super();
		Payment_ID = payment_ID;
		Payment_Type = payment_Type;
		Payment_Amount = payment_Amount;
		Payment_Date = payment_Date;
		User_ID = user_ID;
	}

	public int getPayment_ID() {
		return Payment_ID;
	}

	public void setPayment_ID(int payment_ID) {
		Payment_ID = payment_ID;
	}

	public String getPayment_Type() {
		return Payment_Type;
	}

	public void setPayment_Type(String payment_Type) {
		Payment_Type = payment_Type;
	}

	public int getPayment_Amount() {
		return Payment_Amount;
	}

	public void setPayment_Amount(int payment_Amount) {
		Payment_Amount = payment_Amount;
	}

	public String getPayment_Date() {
		return Payment_Date;
	}

	public void setPayment_Date(String payment_Date) {
		Payment_Date = payment_Date;
	}

	public int getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(int user_ID) {
		User_ID = user_ID;
	}
	
	
	
}
	