package ui.action;

import bioportal.beans.ProvisionalTerm;

import com.opensymphony.xwork2.ActionSupport;

public class ProvisionalTermAction extends ActionSupport {

	private ProvisionalTerm provisionalTerm;
	private String term;
	
	public String execute() {
		//here i would load the extra info from the database as far it is available already e.g. source;
		provisionalTerm = new ProvisionalTerm();
		provisionalTerm.setTerm(term);
		provisionalTerm.setDefinition("the definition that i retrieved for term");
		System.out.println("termaction " + term);
		return SUCCESS;
	}

	public ProvisionalTerm getProvisionalTerm() {
		return provisionalTerm;
	}

	public void setProvisionalTerm(ProvisionalTerm provisionalTerm) {
		this.provisionalTerm = provisionalTerm;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}	
}
