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

import ui.beans.OTOProvisionalTerm;
import ui.business.TermsToOntologiesClient;
import ui.db.OTOProvisionalTermDAO;

import bioportal.beans.ProvisionalTerm;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.jersey.api.client.UniformInterfaceException;

public class ProvisionalTermUpdateAction extends ActionSupport implements SessionAware {

	private OTOProvisionalTerm provisionalTerm;
	private String action = "update";
	private List<String> ontologies = new ArrayList<String>();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Map<String, Object> sessionMap;
	private List<OTOProvisionalTerm> structureAwaitingAdoptionProvisionalTerms;
	private List<OTOProvisionalTerm> characterAwaitingAdoptionProvisionalTerms;
	
	public ProvisionalTermUpdateAction() throws ClassNotFoundException, SQLException, IOException {
		ontologies = OntologyMapper.getInstance().getOntologies();
	}
	
	public String execute() {
		try {
			TermsToOntologiesClient termsToOntologiesClient = new TermsToOntologiesClient(
					(String)sessionMap.get(SessionVariables.BIOPORTAL_USER_ID.toString()), 
					(String)sessionMap.get(SessionVariables.BIOPORTAL_API_KEY.toString()));
			termsToOntologiesClient.updateTerm(provisionalTerm);
			addActionMessage(getText("success.update"));
			structureAwaitingAdoptionProvisionalTerms = OTOProvisionalTermDAO.getInstance().getAllStructureAwaitingAdoption();
			characterAwaitingAdoptionProvisionalTerms = OTOProvisionalTermDAO.getInstance().getAllCharacterAwaitingAdoption();
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

	public OTOProvisionalTerm getProvisionalTerm() {
		return provisionalTerm;
	}

	public void setProvisionalTerm(OTOProvisionalTerm provisionalTerm) {
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

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<OTOProvisionalTerm> getStructureAwaitingAdoptionProvisionalTerms() {
		return structureAwaitingAdoptionProvisionalTerms;
	}

	public void setStructureAwaitingAdoptionProvisionalTerms(
			List<OTOProvisionalTerm> structureAwaitingAdoptionProvisionalTerms) {
		this.structureAwaitingAdoptionProvisionalTerms = structureAwaitingAdoptionProvisionalTerms;
	}

	public List<OTOProvisionalTerm> getCharacterAwaitingAdoptionProvisionalTerms() {
		return characterAwaitingAdoptionProvisionalTerms;
	}

	public void setCharacterAwaitingAdoptionProvisionalTerms(
			List<OTOProvisionalTerm> characterAwaitingAdoptionProvisionalTerms) {
		this.characterAwaitingAdoptionProvisionalTerms = characterAwaitingAdoptionProvisionalTerms;
	}
	
	
}
