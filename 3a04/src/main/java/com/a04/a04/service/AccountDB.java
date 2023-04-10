package com.a04.a04.service;

import java.sql.*;

import com.a04.a04.api.models.Account;


public class AccountDB {
    private String dbUrl = "jdbc:mysql://db4free.net:3306/final_proj_3a";
    private String dbUsername = "group7", dbPassword = "apple112233";
    private Connection connection;
    public AccountDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public boolean login(Account account) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM accounts WHERE Email = '" + account.email + "' AND Password = '" + account.password + "';";
            ResultSet resultSet = statement.executeQuery(query);
            int found = 0;
            while (resultSet.next()) found++;
            return found == 1;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean signUp(Account account) {
        try {
            Statement statement = connection.createStatement();
            String query = "insert into accounts "
                    + "(Username, Password, Email, Firstname, Lastname, Address, City, Province, Gender, Age, Rating_amount, Rating) "
                    + "values ('" + account.email + "','" + account.password + "','" + account.email + "','" + account.firstName + "','" + account.lastName + "','" + "N/A" + "','" + "N/A" + "','" + "N/A" + "','" + "N/A" + "','" + account.age + "','" + 0 + "','" + 0 + "');";
            statement.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean doesUserExist(String email) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM accounts WHERE Email = '" + email + "';";
            ResultSet resultSet = statement.executeQuery(query);
            int found = 0;
            while (resultSet.next()) found++;
            return found >= 1;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteAccount(String email) {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM accounts WHERE Email = '" + email + "';";
            statement.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


// to do: interface for all services/db's
// to do: put url of db in env variables
// add enryption