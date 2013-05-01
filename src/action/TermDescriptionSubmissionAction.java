package action;

import beans.TermDescription;

import com.opensymphony.xwork2.ActionSupport;

public class TermDescriptionSubmissionAction extends ActionSupport {

	//private String term;
	//private String definition;
	private TermDescription termDescription;
	
	public String execute() {
		//here i would call up on the bioportal service	
		return SUCCESS;
	}

	public TermDescription getTermDescription() {
		return termDescription;
	}

	public void setTermDescription(TermDescription termDescription) {
		this.termDescription = termDescription;
	}

	/*
	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}
	*/
	
	

}
