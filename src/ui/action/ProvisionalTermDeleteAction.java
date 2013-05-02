package ui.action;

import bioportal.beans.ProvisionalTerm;
import bioportal.client.TermsToOntologiesClient;

import com.opensymphony.xwork2.ActionSupport;

public class ProvisionalTermDeleteAction extends ActionSupport {

	private ProvisionalTerm provisionalTerm;
	
	public String execute() {
		try {
			TermsToOntologiesClient termsToOntologiesClient = new TermsToOntologiesClient();
			termsToOntologiesClient.deleteTerm(provisionalTerm);
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
}
