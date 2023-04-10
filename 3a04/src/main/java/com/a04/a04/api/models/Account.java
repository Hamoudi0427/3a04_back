package com.a04.a04.api.models;

public class Account {
    public String email, password, firstName, lastName, address, city, province, gender;
    public int age, ratingAmount;
    public float rating;
    public Account(String email, String password, int age, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
