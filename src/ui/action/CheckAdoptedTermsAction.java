package ui.action;

import java.util.Map;

import bioportal.client.TermsToOntologiesClient;

import com.opensymphony.xwork2.ActionSupport;

public class CheckAdoptedTermsAction extends ActionSupport {

	private Map<String, String> termAdoptions;
	private int numberOfTermAdoptions;

	public String execute() {	
		try {
			TermsToOntologiesClient termsToOntologiesClient = new TermsToOntologiesClient();
			termAdoptions = termsToOntologiesClient.checkTermAdoptions();
			numberOfTermAdoptions = termAdoptions.size();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	
		return SUCCESS;
	}

	public Map<String, String> getTermAdoptions() {
		return termAdoptions;
	}

	public void setTermAdoptions(Map<String, String> termAdoptions) {
		this.termAdoptions = termAdoptions;
	}

	public int getNumberOfTermAdoptions() {
		return numberOfTermAdoptions;
	}

	public void setNumberOfTermAdoptions(int numberOfTermAdoptions) {
		this.numberOfTermAdoptions = numberOfTermAdoptions;
	}
	
}
