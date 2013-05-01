package db;

import beans.User;

public class AddAdminUser {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		User admin = new User("admin", "admin123");
		UserDAO.getInstance().addUser(admin);
	}
}
