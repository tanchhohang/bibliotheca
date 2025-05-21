package com.bibliotheca.model;

public class UserModel {
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private String email;
    private String membership;
    private String address;
    private String password;
    private String role;
    private String profilePic;

    public UserModel() {}

    public UserModel(String email, String password, String role) {
        super();
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserModel(String firstName, String lastName, String dob, String gender,
                     String email, String membership, String address,
                     String password, String role, String profilePic) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.membership = membership;
        this.address = address;
        this.password = password;
        this.role = role;
        this.profilePic = profilePic;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}