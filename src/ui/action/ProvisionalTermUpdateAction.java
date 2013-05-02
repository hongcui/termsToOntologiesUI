package ui.action;

import bioportal.beans.ProvisionalTerm;
import bioportal.client.TermsToOntologiesClient;

import com.opensymphony.xwork2.ActionSupport;

public class ProvisionalTermUpdateAction extends ActionSupport {

	private ProvisionalTerm provisionalTerm;
	private String action = "update";
	
	public String execute() {
		try {
			TermsToOntologiesClient termsToOntologiesClient = new TermsToOntologiesClient();
			termsToOntologiesClient.updateTerm(provisionalTerm);
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
	
	public String getAction() {
		return action ;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
