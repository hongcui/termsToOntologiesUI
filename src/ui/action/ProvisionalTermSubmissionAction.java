package ui.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ui.db.IUnadoptedTermDAO;
import ui.db.UnadoptedTermDAO;
import bioportal.OntologyMapper;
import bioportal.beans.ProvisionalTerm;
import bioportal.client.TermsToOntologiesClient;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.jersey.api.client.UniformInterfaceException;

public class ProvisionalTermSubmissionAction extends ActionSupport implements SessionAware {

	private ProvisionalTerm provisionalTerm;
	private String action = "send";
	private List<String> ontologies = new ArrayList<String>();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Map<String, Object> sessionMap;
	private List<ProvisionalTerm> structureProvisionalTerms;
	private List<ProvisionalTerm> characterProvisionalTerms;
	
	public ProvisionalTermSubmissionAction() throws ClassNotFoundException, SQLException, IOException {
		ontologies = OntologyMapper.getInstance().getOntologies();
	}
	
	public String execute() {
		try {
			TermsToOntologiesClient termsToOntologiesClient = new TermsToOntologiesClient(
					(String)sessionMap.get(SessionVariables.BIOPORTAL_USER_ID.toString()), 
					(String)sessionMap.get(SessionVariables.BIOPORTAL_API_KEY.toString()));
			termsToOntologiesClient.sendTerm(provisionalTerm);
			IUnadoptedTermDAO unadoptedTermDAO = UnadoptedTermDAO.getInstance();
			unadoptedTermDAO.markSent(provisionalTerm.getLocalId());
			addActionMessage(getText("success.send"));
			provisionalTerm = null;
			structureProvisionalTerms = UnadoptedTermDAO.getInstance().getUnadoptedStructureTerms();
			characterProvisionalTerms = UnadoptedTermDAO.getInstance().getUnadoptedCharacterTerms();
		} catch (IllegalArgumentException e) {
			addActionError(getText("error.parameters"));
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
		} catch (SQLException e) {
			addActionError(getText("error.db"));
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

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<ProvisionalTerm> getStructureProvisionalTerms() {
		return structureProvisionalTerms;
	}

	public void setStructureProvisionalTerms(
			List<ProvisionalTerm> structureProvisionalTerms) {
		this.structureProvisionalTerms = structureProvisionalTerms;
	}

	public List<ProvisionalTerm> getCharacterProvisionalTerms() {
		return characterProvisionalTerms;
	}

	public void setCharacterProvisionalTerms(
			List<ProvisionalTerm> characterProvisionalTerms) {
		this.characterProvisionalTerms = characterProvisionalTerms;
	}
	
	
}
