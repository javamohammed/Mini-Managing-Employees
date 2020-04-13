package com.managing.employees.db;

import com.managing.employees.beans.User;

public interface UserInterface {
	public void AddUser(User user);
	public boolean userExist(String email);
	public String login(String email, String password);
}
