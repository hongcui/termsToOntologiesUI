package ui.db;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ui.beans.User;


public class UserDAO extends AbstractDAO {

	private static UserDAO instance;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static UserDAO getInstance() throws SQLException, ClassNotFoundException, IOException {
		if(instance == null)
			instance = new UserDAO();
		return instance;
	}
	
	private UserDAO() throws SQLException, ClassNotFoundException, IOException { 
		this.openConnection();
		createTableIfNecessary();
		this.closeConnection();
	}
	
	public void addUser(User user) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
		user.setPassword(getEncryptedPassword(user.getPassword()));
		this.openConnection();
		String sql = "INSERT INTO ui_users (name, password, bioportalUserId, bioportalAPIKey) " +
				"VALUES ('" + user.getName() + "','" + user.getPassword() + "', '" + user.getBioPortalUserId() + "','" + user.getBioPortalAPIKey() +"')";
		PreparedStatement preparedStatement = this.executeSQL(sql);
		this.closeConnection();
	}
	
	private String getEncryptedPassword(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(text.getBytes("UTF-8"));
		byte[] digest = md.digest();
		return (new HexBinaryAdapter()).marshal(digest);
	}


	private void createTableIfNecessary() throws SQLException {
		this.executeSQL("CREATE TABLE IF NOT EXISTS `ui_users` (" +
				"`name` varchar(20) NOT NULL, " +
				"`password` char(64) NOT NULL, " +
				"`bioportalUserId` varchar(100) NOT NULL, " +
				"`bioportalAPIKey` varchar(100) NOT NULL, " +
				"PRIMARY KEY (`name`))");
	}
	
	public User getUser(String userName) throws SQLException {
		this.openConnection();
		String sql = "SELECT * FROM ui_users WHERE name = '" + userName + "'";
		PreparedStatement preparedStatement = this.executeSQL(sql);
		ResultSet resultSet = preparedStatement.getResultSet();
		resultSet.next();
		User user = new User(resultSet.getString("name"), resultSet.getString("password"), resultSet.getString("bioportalUserId"), 
				resultSet.getString("bioportalAPIKey"));
		this.closeConnection();
		return user;
	}

	public boolean checkIfExists(User user) {
		try {
			User dbUser = this.getUser(user.getName());
			return dbUser.getPassword().equals(this.getEncryptedPassword(user.getPassword()));
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		return false;
	}
	
}
