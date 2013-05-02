package ui.action;

import bioportal.beans.ProvisionalTerm;
import bioportal.client.TermsToOntologiesClient;
import bioportal.db.ProvisionalTermDAO;

import com.opensymphony.xwork2.ActionSupport;

public class ProvisionalTermDeleteAction extends ActionSupport {

	private ProvisionalTerm provisionalTerm;
	private String action = "update";
	
	public String execute() {
		try {
			System.out.println("delete ");
			System.out.println(provisionalTerm.toString());
			TermsToOntologiesClient termsToOntologiesClient = new TermsToOntologiesClient();
			termsToOntologiesClient.deleteTerm(provisionalTerm);
			provisionalTerm = null;
			if(ProvisionalTermDAO.getInstance().getFirstAwaitingTerm() == null)
				return "empty";
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
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
