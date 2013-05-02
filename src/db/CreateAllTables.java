package db;

import ui.beans.User;
import ui.db.UnadoptedTermDAO;
import ui.db.UserDAO;

public class CreateAllTables {

	public static void main(String[] args) throws Exception {
		User admin = new User("admin", "admin123");
		UserDAO.getInstance().addUser(admin);
		
		UnadoptedTermDAO.getInstance().createDummyData();
	}
	
}
