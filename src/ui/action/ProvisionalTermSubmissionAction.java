package ui.action;

import bioportal.beans.ProvisionalTerm;
import bioportal.client.TermsToOntologiesClient;

import com.opensymphony.xwork2.ActionSupport;

public class ProvisionalTermSubmissionAction extends ActionSupport {

	private ProvisionalTerm provisionalTerm;
	private String action = "send";
	
	public String execute() {
		System.out.println("this is the provisional term is execute upon");
		System.out.println(provisionalTerm.toString());
		try {
			TermsToOntologiesClient termsToOntologiesClient = new TermsToOntologiesClient();
			termsToOntologiesClient.sendTerm(provisionalTerm);
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
		System.out.println("called set");
		this.provisionalTerm = provisionalTerm;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	
}
