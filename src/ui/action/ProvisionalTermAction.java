package ui.action;

import ui.db.UnadoptedTermDAO;
import bioportal.beans.ProvisionalTerm;

import com.opensymphony.xwork2.ActionSupport;

public class ProvisionalTermAction extends ActionSupport {

	private ProvisionalTerm provisionalTerm;
	private String localId;
	private String action;
	
	public String execute() {
		//here i would load the extra info from the database as far it is available already e.g. source;
		try {
			provisionalTerm = UnadoptedTermDAO.getInstance().getUnadoptedTerm(localId);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	public ProvisionalTerm getProvisionalTerm() {
		return provisionalTerm;
	}

	public void setProvisionalTerm(ProvisionalTerm provisionalTerm) {
		this.provisionalTerm = provisionalTerm;
	}

	public String getLocalId() {
		return localId;
	}

	public void setLocalId(String localId) {
		this.localId = localId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}	
	
	
}
