package ui.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ui.beans.User;
import ui.db.UserDAO;

import com.opensymphony.xwork2.ActionSupport;


public class LoginAction extends ActionSupport implements SessionAware {
	
	private String username;
	private String password;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Map<String, Object> sessionMap;

	public String execute() {
		try {
			User loginUser = new User();
			loginUser.setPassword(this.password);
			loginUser.setName(this.username);
			if(UserDAO.getInstance().checkIfExists(loginUser)) {
				User user = UserDAO.getInstance().getUser(this.username);
				sessionMap.put(SessionVariables.LOGGED_IN_USER.toString(), this.username);
				sessionMap.put(SessionVariables.BIOPORTAL_USER_ID.toString(), user.getBioPortalUserId());
				sessionMap.put(SessionVariables.BIOPORTAL_API_KEY.toString(), user.getBioPortalAPIKey());
				return SUCCESS;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			addActionError(getText("error.db"));	
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
			addActionError(getText("error.properties"));	
		} catch (IOException e) {
			logger.error(e.getMessage());
			addActionError(getText("error.properties"));	
		}
		addActionError(getText("error.login"));		
		return ERROR;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
}


