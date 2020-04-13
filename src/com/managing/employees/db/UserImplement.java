package com.managing.employees.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.managing.employees.beans.User;
import com.managing.employees.utils.Useful;

public class UserImplement implements UserInterface{
	private Factory factory;

	public UserImplement(Factory factory) {
		this.factory = factory;
	}
 @Override
	public void AddUser(User user) {
	 Connection connexion = null;
     PreparedStatement preparedStatement = null;
     try {
         connexion = factory.getConnection();
         preparedStatement = connexion.prepareStatement("insert into users(name,email,password) values(?,?,?)");
         preparedStatement.setString(1, user.getName());
         preparedStatement.setString(2, user.getEmail());
         preparedStatement.setString(3, user.getPassword());
         preparedStatement.executeUpdate();
     } catch (SQLException e) {
         e.printStackTrace();
     }
}
	 @Override
	public boolean userExist(String email) {
		 boolean exist = false;
		 Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultat = null;
		 try {
	         connexion = factory.getConnection();
	         preparedStatement = connexion.prepareStatement("SELECT email FROM users WHERE email=?");
	         preparedStatement.setString(1, email);
	         resultat = preparedStatement.executeQuery();
	         while (resultat.next()) {
	        	 exist = true;
	            }
	     } catch (SQLException e) {
	         e.printStackTrace();
	     }
		return exist;
	}
	 @Override
	public String login(String email, String password) {
		 String name= "";
		 Connection connexion = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet resultat = null; 
		 try {
	         connexion = factory.getConnection();
	         preparedStatement = connexion.prepareStatement("SELECT name FROM users WHERE email=? and password=?");
	         preparedStatement.setString(1, email);
	         preparedStatement.setString(2, Useful.MD5(password));
	         resultat = preparedStatement.executeQuery();
	         while (resultat.next()) {
	        	 name =  resultat.getString("name");
	            }
	     } catch (SQLException e) {
	         e.printStackTrace();
	     }
		return name;
	}
}
