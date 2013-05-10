package ui.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ui.beans.OTOProvisionalTerm;
import ui.db.IUnadoptedTermDAO;
import ui.db.OTOProvisionalTermDAO;
import ui.db.UnadoptedTermDAO;

import com.opensymphony.xwork2.ActionSupport;

public class ProvisionalTermAction extends ActionSupport implements SessionAware {

	private OTOProvisionalTerm provisionalTerm;
	private String localId;
	private String action;
	private List<String> ontologies = new ArrayList<String>();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Map<String, Object> sessionMap;
	private List<OTOProvisionalTerm> structureProvisionalTerms;
	private List<OTOProvisionalTerm> characterProvisionalTerms;
	private List<OTOProvisionalTerm> structureAwaitingAdoptionProvisionalTerms;
	private List<OTOProvisionalTerm> characterAwaitingAdoptionProvisionalTerms;
	private List<OTOProvisionalTerm> structureAdoptedProvisionalTerms;
	private List<OTOProvisionalTerm> characterAdoptedProvisionalTerms;
	
	public ProvisionalTermAction() throws ClassNotFoundException, SQLException, IOException {
		ontologies = OntologyMapper.getInstance().getOntologies();
		structureProvisionalTerms = UnadoptedTermDAO.getInstance().getUnadoptedStructureTerms();
		characterProvisionalTerms = UnadoptedTermDAO.getInstance().getUnadoptedCharacterTerms();
		structureAwaitingAdoptionProvisionalTerms = OTOProvisionalTermDAO.getInstance().getAllStructureAwaitingAdoption();
		characterAwaitingAdoptionProvisionalTerms = OTOProvisionalTermDAO.getInstance().getAllCharacterAwaitingAdoption();
		structureAdoptedProvisionalTerms = OTOProvisionalTermDAO.getInstance().getAdoptedStructureTerms();
		characterAdoptedProvisionalTerms = OTOProvisionalTermDAO.getInstance().getAdoptedCharacterTerms();
	}

	public String execute() {
		try {
			switch(action) {
			case "send":
				IUnadoptedTermDAO unadoptedTermDAO = UnadoptedTermDAO.getInstance();
				provisionalTerm = unadoptedTermDAO.getUnadoptedTerm(localId);				
				return SUCCESS;
			case "update":
				provisionalTerm = OTOProvisionalTermDAO.getInstance().getAwaitingAdoption(localId);
				return SUCCESS;
			case "adopted":
				provisionalTerm = OTOProvisionalTermDAO.getInstance().getAdopted(localId);
				return SUCCESS;
			default:
				addActionError("Unknown action");
				return ERROR;
			}
		} catch (SQLException e) {
			addActionError(getText("error.db"));
			logger.error(e.getMessage());
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
	}

	public OTOProvisionalTerm getProvisionalTerm() {
		return provisionalTerm;
	}

	public void setProvisionalTerm(OTOProvisionalTerm provisionalTerm) {
		this.provisionalTerm = provisionalTerm;
	}

	public String getLocalId() {
		return localId;
	}

	public void setLocalId(String localId) {
		this.localId = localId;
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
