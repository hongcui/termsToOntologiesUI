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
import ui.db.IUnadoptedTermDAO;
import ui.db.OTOProvisionalTermDAO;
import ui.db.UnadoptedTermDAO;

import com.opensymphony.xwork2.ActionSupport;

public class MenuAction extends ActionSupport implements SessionAware {

	private String action;
	private Map<String, String> termAdoptions;
	private int numberOfTermAdoptions;
	private OTOProvisionalTerm provisionalTerm;
	private List<String> ontologies = new ArrayList<String>();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Map<String, Object> sessionMap;
	private List<OTOProvisionalTerm> structureProvisionalTerms;
	private List<OTOProvisionalTerm> characterProvisionalTerms;
	private List<OTOProvisionalTerm> structureAwaitingAdoptionProvisionalTerms;
	private List<OTOProvisionalTerm> characterAwaitingAdoptionProvisionalTerms;
	private List<OTOProvisionalTerm> structureAdoptedProvisionalTerms;
	private List<OTOProvisionalTerm> characterAdoptedProvisionalTerms;
	
	public MenuAction() throws ClassNotFoundException, SQLException, IOException {
		ontologies = OntologyMapper.getInstance().getOntologies();
		structureProvisionalTerms = UnadoptedTermDAO.getInstance().getUnadoptedStructureTerms();
		characterProvisionalTerms = UnadoptedTermDAO.getInstance().getUnadoptedCharacterTerms();
		structureAwaitingAdoptionProvisionalTerms = OTOProvisionalTermDAO.getInstance().getAllStructureAwaitingAdoption();
		characterAwaitingAdoptionProvisionalTerms = OTOProvisionalTermDAO.getInstance().getAllCharacterAwaitingAdoption();
		structureAdoptedProvisionalTerms = OTOProvisionalTermDAO.getInstance().getAdoptedStructureTerms();
		characterAdoptedProvisionalTerms = OTOProvisionalTermDAO.getInstance().getAdoptedCharacterTerms();
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
				provisionalTerm = OTOProvisionalTermDAO.getInstance().getFirstAwaitingTerm();
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
				provisionalTerm = OTOProvisionalTermDAO.getInstance().getFirstAdoptedTerm();
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

	public OTOProvisionalTerm getProvisionalTerm() {
		return provisionalTerm;
	}

	public void setProvisionalTerm(OTOProvisionalTerm provisionalTerm) {
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

	public List<OTOProvisionalTerm> getStructureProvisionalTerms() {
		return structureProvisionalTerms;
	}

	public void setStructureProvisionalTerms(
			List<OTOProvisionalTerm> structureProvisionalTerms) {
		this.structureProvisionalTerms = structureProvisionalTerms;
	}

	public List<OTOProvisionalTerm> getCharacterProvisionalTerms() {
		return characterProvisionalTerms;
	}

	public void setCharacterProvisionalTerms(
			List<OTOProvisionalTerm> characterProvisionalTerms) {
		this.characterProvisionalTerms = characterProvisionalTerms;
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
	
	public List<OTOProvisionalTerm> getStructureAdoptedProvisionalTerms() {
		return structureAdoptedProvisionalTerms;
	}

	public void setStructureAdoptedProvisionalTerms(
			List<OTOProvisionalTerm> structureAdoptedProvisionalTerms) {
		this.structureAdoptedProvisionalTerms = structureAdoptedProvisionalTerms;
	}

	public List<OTOProvisionalTerm> getCharacterAdoptedProvisionalTerms() {
		return characterAdoptedProvisionalTerms;
	}

	public void setCharacterAdoptedProvisionalTerms(
			List<OTOProvisionalTerm> characterAdoptedProvisionalTerms) {
		this.characterAdoptedProvisionalTerms = characterAdoptedProvisionalTerms;
	}
	
	
}
