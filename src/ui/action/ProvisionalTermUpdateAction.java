package ui.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bioportal.OntologyMapper;
import bioportal.beans.ProvisionalTerm;
import bioportal.client.TermsToOntologiesClient;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.jersey.api.client.UniformInterfaceException;

public class ProvisionalTermUpdateAction extends ActionSupport {

	private ProvisionalTerm provisionalTerm;
	private String action = "update";
	private List<String> ontologies = new ArrayList<String>();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public ProvisionalTermUpdateAction() {
		ontologies = OntologyMapper.getInstance().getOntologies();
	}
	
	public String execute() {
		try {
			TermsToOntologiesClient termsToOntologiesClient = TermsToOntologiesClient.getInstance();
			termsToOntologiesClient.updateTerm(provisionalTerm);
			addActionMessage(getText("success.update"));
		} catch (SQLException e) {
			addActionError(getText("error.db"));
			logger.error(e.getMessage());
			return ERROR;
		} catch (JAXBException e) {
			addActionError(getText("error.webservice"));
			logger.error(e.getMessage());
			return ERROR;
		} catch (UniformInterfaceException e) {
			addActionError(getText("error.webservice"));
			logger.error(e.getMessage());
			return ERROR;
		} catch (IOException e) {
			addActionError(getText("error.properties"));
			logger.error(e.getMessage());
			return ERROR;
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
			addActionError(getText("error.properties"));
			return ERROR;
		} catch (Exception e) {
			logger.error(e.getMessage());
			addActionError("Error, check the logs for more information");
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
		return action ;
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
