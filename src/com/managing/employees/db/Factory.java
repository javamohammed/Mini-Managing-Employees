package com.managing.employees.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Factory {
	private String url;
	private String username;
    private String password;
    
    public Factory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    public static Factory getInstance() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }
        Factory instance = new Factory(
                "jdbc:mysql://localhost:3306/managing_employees", "root", "");
        return instance;
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public UserInterface getUserDao() {
        return new UserImplement(this);
    }
    public EmployeeInterface getEmployeeDao() {
        return new EmployeeImplement(this);
    }
}
