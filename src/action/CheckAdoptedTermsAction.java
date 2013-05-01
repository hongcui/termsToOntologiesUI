package action;

import java.util.List;

import beans.AdoptedTerm;

import com.opensymphony.xwork2.ActionSupport;

public class CheckAdoptedTermsAction extends ActionSupport {

	private List<AdoptedTerm> adoptedTerms;
	
	public String execute() {	
		//obtain adoptedterms here
		return SUCCESS;
	}

	public List<AdoptedTerm> getAdoptedTerms() {
		return adoptedTerms;
	}

	public void setAdoptedTerms(List<AdoptedTerm> adoptedTerms) {
		this.adoptedTerms = adoptedTerms;
	}
	
}
