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
import bioportal.db.ProvisionalTermDAO;

import com.opensymphony.xwork2.ActionSupport;

public class MenuAction extends ActionSupport implements SessionAware {

	private String action;
	private Map<String, String> termAdoptions;
	private int numberOfTermAdoptions;
	private ProvisionalTerm provisionalTerm;
	private List<String> ontologies = new ArrayList<String>();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Map<String, Object> sessionMap;
	private List<ProvisionalTerm> structureProvisionalTerms;
	private List<ProvisionalTerm> characterProvisionalTerms;
	private List<ProvisionalTerm> structureAwaitingAdoptionProvisionalTerms;
	private List<ProvisionalTerm> characterAwaitingAdoptionProvisionalTerms;
	private List<ProvisionalTerm> structureAdoptedProvisionalTerms;
	private List<ProvisionalTerm> characterAdoptedProvisionalTerms;
	
	public MenuAction() throws ClassNotFoundException, SQLException, IOException {
		ontologies = OntologyMapper.getInstance().getOntologies();
		structureProvisionalTerms = UnadoptedTermDAO.getInstance().getUnadoptedStructureTerms();
		characterProvisionalTerms = UnadoptedTermDAO.getInstance().getUnadoptedCharacterTerms();
		structureAwaitingAdoptionProvisionalTerms = ProvisionalTermDAO.getInstance().getAllStructureAwaitingAdoption();
		characterAwaitingAdoptionProvisionalTerms = ProvisionalTermDAO.getInstance().getAllCharacterAwaitingAdoption();
		structureAdoptedProvisionalTerms = ProvisionalTermDAO.getInstance().getAdoptedStructureTerms();
		characterAdoptedProvisionalTerms = ProvisionalTermDAO.getInstance().getAdoptedCharacterTerms();
	}
	
	public String execute() {		
		switch(action) {
		case "send":
			try {
				IUnadoptedTermDAO unadoptedTermDAO = UnadoptedTermDAO.getInstance();
				provisionalTerm = unadoptedTermDAO.getFirstUnadoptedTerm();			
			} catch (SQLException e) {
				logger.error(e.getMessage());
				addActionError(getText("error.db"));
				return ERROR;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				addActionError(getText("error.properties"));
				return ERROR;
			} catch (IOException e) {
				logger.error(e.getMessage());
				addActionError(getText("error.properties"));
				return ERROR;
			}
			return action;
		case "update":
			try {
				provisionalTerm = ProvisionalTermDAO.getInstance().getFirstAwaitingTerm();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				addActionError(getText("error.db"));
				return ERROR;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				addActionError(getText("error.properties"));
				return ERROR;
			} catch (IOException e) {
				logger.error(e.getMessage());
				addActionError(getText("error.properties"));
				return ERROR;
			}
			return action;
		case "adopted":
			try {
				provisionalTerm = ProvisionalTermDAO.getInstance().getFirstAdoptedTerm();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				addActionError(getText("error.db"));
				return ERROR;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				addActionError(getText("error.properties"));
				return ERROR;
			} catch (IOException e) {
				logger.error(e.getMessage());
				addActionError(getText("error.properties"));
				return ERROR;
			}
			return action;
		case "check":
			try {
				TermsToOntologiesClient termsToOntologiesClient = new TermsToOntologiesClient(
						(String)sessionMap.get(SessionVariables.BIOPORTAL_USER_ID.toString()), 
						(String)sessionMap.get(SessionVariables.BIOPORTAL_API_KEY.toString()));
				termAdoptions = termsToOntologiesClient.checkTermAdoptions();
				numberOfTermAdoptions = termAdoptions.size();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				addActionError(getText("error.db"));
				return ERROR;
			} catch (JAXBException e) {
				logger.error(e.getMessage());
				addActionError(getText("error.webservice"));
				return ERROR;
			} catch (IOException e) {
				logger.error(e.getMessage());
				addActionError(getText("error.properties"));
				return ERROR;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				addActionError(getText("error.properties"));
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

	public List<ProvisionalTerm> getStructureAwaitingAdoptionProvisionalTerms() {
		return structureAwaitingAdoptionProvisionalTerms;
	}

	public void setStructureAwaitingAdoptionProvisionalTerms(
			List<ProvisionalTerm> structureAwaitingAdoptionProvisionalTerms) {
		this.structureAwaitingAdoptionProvisionalTerms = structureAwaitingAdoptionProvisionalTerms;
	}

	public List<ProvisionalTerm> getCharacterAwaitingAdoptionProvisionalTerms() {
		return characterAwaitingAdoptionProvisionalTerms;
	}

	public void setCharacterAwaitingAdoptionProvisionalTerms(
			List<ProvisionalTerm> characterAwaitingAdoptionProvisionalTerms) {
		this.characterAwaitingAdoptionProvisionalTerms = characterAwaitingAdoptionProvisionalTerms;
	}
	
	public List<ProvisionalTerm> getStructureAdoptedProvisionalTerms() {
		return structureAdoptedProvisionalTerms;
	}

	public void setStructureAdoptedProvisionalTerms(
			List<ProvisionalTerm> structureAdoptedProvisionalTerms) {
		this.structureAdoptedProvisionalTerms = structureAdoptedProvisionalTerms;
	}

	public List<ProvisionalTerm> getCharacterAdoptedProvisionalTerms() {
		return characterAdoptedProvisionalTerms;
	}

	public void setCharacterAdoptedProvisionalTerms(
			List<ProvisionalTerm> characterAdoptedProvisionalTerms) {
		this.characterAdoptedProvisionalTerms = characterAdoptedProvisionalTerms;
	}
	
	
}
