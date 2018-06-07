package org.sushisushi.sushiform.models;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private int postcode;
    private String phoneNumber;

    public User(String firstName, String lastName, String email, int postcode, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.postcode = postcode;
        this.phoneNumber = phoneNumber;
    }

    public User(){
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getPostcode() { return postcode; }
    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
}
