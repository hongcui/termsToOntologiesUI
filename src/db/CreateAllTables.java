package db;

import java.util.Properties;

import ui.beans.User;
import ui.db.UnadoptedTermDAO;
import ui.db.UserDAO;

public class CreateAllTables {

	public static void main(String[] args) throws Exception {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties(); 
		properties.load(loader.getResourceAsStream("config.properties"));
		String bioportalUserId = properties.getProperty("bioportalUserId");
		String bioportalAPIKey = properties.getProperty("bioportalApiKey");
		User admin = new User("admin", "admin123", bioportalUserId, bioportalAPIKey);
		UserDAO.getInstance().addUser(admin);
		
		UnadoptedTermDAO.getInstance().createDummyData();
	}
	
}
