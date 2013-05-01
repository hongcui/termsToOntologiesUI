package action;

import beans.TermDescription;

import com.opensymphony.xwork2.ActionSupport;

public class TermDescriptionAction extends ActionSupport {

	private TermDescription termDescription;
	private String term;
	
	public String execute() {
		//here i would load the extra info from the database as far it is available already e.g. source;
		termDescription = new TermDescription();
		termDescription.setTerm(term);
		termDescription.setDefinition("the definition that i retrieved for term");
		System.out.println("termaction " + term);
		return SUCCESS;
	}

	public TermDescription getTermDescription() {
		return termDescription;
	}

	public void setTermDescription(TermDescription termDescription) {
		this.termDescription = termDescription;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}	
}
