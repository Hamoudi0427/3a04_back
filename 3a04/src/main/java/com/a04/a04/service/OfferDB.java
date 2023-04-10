package com.a04.a04.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.a04.a04.api.models.Offer;

public class OfferDB {
    private String dbUrl = "jdbc:mysql://db4free.net:3306/final_proj_3a";
    private String dbUsername = "group7", dbPassword = "apple112233";
    private Connection connection;
    public OfferDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<Offer> getAllOffers() {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM offers";
            ResultSet rs = statement.executeQuery(query);
            ArrayList<Offer> offers = new ArrayList<Offer>();
            while (rs.next()) {
                Offer temp = new Offer(rs.getInt("Offer_ID"), rs.getInt("Offering_taxi"), rs.getInt("Open_seats"),
                        rs.getString("Offerer"), rs.getFloat("Offerer_rating"), rs.getString("Offer_time"),
                        rs.getString("Offer_destination"), rs.getString("Start_location"));
                offers.add(temp);
            }
            return offers;
        } catch (Exception e) {
            System.out.println(e);
            return null;
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