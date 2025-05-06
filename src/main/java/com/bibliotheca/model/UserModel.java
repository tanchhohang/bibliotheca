package com.bibliotheca.model;

public class UserModel {
	private String First_Name;
	private String Last_Name;
	private String DOB;
	private String Gender;
	private String Email;
	private String Membership;
	private String Address;
	private String Password;
	private String Role;
	private String Profile_pic;
	
	
	public UserModel() {}
	
	
	public UserModel(String email, String password, String role) {
		super();
		Email = email;
		Password = password;
		Role = role;
	}
	
	
	public UserModel(String first_Name, String last_Name, String dOB, String gender, String email,
			String membership, String address, String password, String role, String profile_pic) {
		super();
		First_Name = first_Name;
		Last_Name = last_Name;
		DOB = dOB;
		Gender = gender;
		Email = email;
		Membership = membership;
		Address = address;
		Password = password;
		Role = role;
		Profile_pic = profile_pic;
	}

	public String getFirst_Name() {
		return First_Name;
	}

	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}

	public String getLast_Name() {
		return Last_Name;
	}

	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getMembership() {
		return Membership;
	}

	public void setMembership(String membership) {
		Membership = membership;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public String getProfile_pic() {
		return Profile_pic;
	}

	public void setProfile_pic(String profile_pic) {
		Profile_pic = profile_pic;
	}
	
	
	
	
}