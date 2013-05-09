package ui.beans;

public class User {

	private String name;
	private String password;
	private String bioPortalUserId;
	private String bioPortalAPIKey;
	
	public User() { }
	
	public User(String name, String password, String bioPortalUserId,
			String bioPortalAPIKey) {
		super();
		this.name = name;
		this.password = password;
		this.bioPortalUserId = bioPortalUserId;
		this.bioPortalAPIKey = bioPortalAPIKey;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getBioPortalUserId() {
		return bioPortalUserId;
	}

	public String getBioPortalAPIKey() {
		return bioPortalAPIKey;
	}

	public void setBioPortalAPIKey(String bioPortalAPIKey) {
		this.bioPortalAPIKey = bioPortalAPIKey;
	}

	public void setBioPortalUserId(String bioPortalUserId) {
		this.bioPortalUserId = bioPortalUserId;
	}
}
