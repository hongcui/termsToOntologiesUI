package ui.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import ui.beans.User;
import ui.db.UnadoptedTermDAO;
import ui.db.UserDAO;

import bioportal.beans.ProvisionalTerm;
import bioportal.client.TermsToOntologiesClient;
import bioportal.db.ProvisionalTermDAO;

import com.opensymphony.xwork2.ActionSupport;

public class MenuAction extends ActionSupport {

	private String action;
	private Map<String, String> termAdoptions;
	private int numberOfTermAdoptions;
	private ProvisionalTerm provisionalTerm;
	
	public String execute() {
		switch(action) {
		case "send":
			try {
				provisionalTerm = UnadoptedTermDAO.getInstance().getFirstUnadoptedTerm();
			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			}
			return action;
		case "update":
			try {
				provisionalTerm = ProvisionalTermDAO.getInstance().getFirstAwaitingTerm();
			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			}
			return action;
		case "adopted":
			try {
				provisionalTerm = ProvisionalTermDAO.getInstance().getFirstAdoptedTerm();
			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			}
			return action;
		case "check":
			try {
				TermsToOntologiesClient termsToOntologiesClient = new TermsToOntologiesClient();
				termAdoptions = termsToOntologiesClient.checkTermAdoptions();
				numberOfTermAdoptions = termAdoptions.size();
			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			}
			return action;
		default:
			return ERROR;
		}
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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

	public ProvisionalTerm getProvisionalTerm() {
		return provisionalTerm;
	}

	public void setProvisionalTerm(ProvisionalTerm provisionalTerm) {
		this.provisionalTerm = provisionalTerm;
	}
	
	
}
