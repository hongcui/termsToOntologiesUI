package ui.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bioportal.OntologyMapper;
import bioportal.beans.ProvisionalTerm;
import bioportal.client.TermsToOntologiesClient;

import com.opensymphony.xwork2.ActionSupport;

public class ProvisionalTermSubmissionAction extends ActionSupport {

	private ProvisionalTerm provisionalTerm;
	private String action = "send";
	private List<String> ontologies = new ArrayList<String>();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public ProvisionalTermSubmissionAction() {
		ontologies = OntologyMapper.getInstance().getOntologies();
	}
	
	public String execute() {
		try {
			TermsToOntologiesClient termsToOntologiesClient = new TermsToOntologiesClient();
			termsToOntologiesClient.sendTerm(provisionalTerm);
		} catch (Exception e) {
			logger.error(e.getMessage());
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
	
	public List<String> getOntologies() {
		return ontologies;
	}

	public void setOntologies(List<String> ontologies) {
		this.ontologies = ontologies;
	}
}
