package ui.action;

import org.apache.struts2.ServletActionContext;

import ui.beans.User;
import ui.db.UserDAO;


import com.opensymphony.xwork2.ActionSupport;


public class LoginAction extends ActionSupport {
	
	private String username;
	private String password;
	private static final String USER_HANDLE = "loggedInUser";

	public String execute() {
		try {
			if(UserDAO.getInstance().checkIfExists(new User(this.username, this.password))) {
				ServletActionContext.getRequest().getSession().setAttribute(USER_HANDLE, this.username);
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
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
}


