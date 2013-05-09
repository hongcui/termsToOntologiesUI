package ui.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ui.db.IUnadoptedTermDAO;
import ui.db.UnadoptedTermDAO;
import bioportal.OntologyMapper;
import bioportal.beans.ProvisionalTerm;
import bioportal.db.ProvisionalTermDAO;

import com.opensymphony.xwork2.ActionSupport;

public class ProvisionalTermAction extends ActionSupport implements SessionAware {

	private ProvisionalTerm provisionalTerm;
	private String localId;
	private String action;
	private List<String> ontologies = new ArrayList<String>();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Map<String, Object> sessionMap;
	private List<ProvisionalTerm> structureProvisionalTerms;
	private List<ProvisionalTerm> characterProvisionalTerms;
	private List<ProvisionalTerm> structureAwaitingAdoptionProvisionalTerms;
	private List<ProvisionalTerm> characterAwaitingAdoptionProvisionalTerms;
	private List<ProvisionalTerm> structureAdoptedProvisionalTerms;
	private List<ProvisionalTerm> characterAdoptedProvisionalTerms;
	
	public ProvisionalTermAction() throws ClassNotFoundException, SQLException, IOException {
		ontologies = OntologyMapper.getInstance().getOntologies();
		structureProvisionalTerms = UnadoptedTermDAO.getInstance().getUnadoptedStructureTerms();
		characterProvisionalTerms = UnadoptedTermDAO.getInstance().getUnadoptedCharacterTerms();
		structureAwaitingAdoptionProvisionalTerms = ProvisionalTermDAO.getInstance().getAllStructureAwaitingAdoption();
		characterAwaitingAdoptionProvisionalTerms = ProvisionalTermDAO.getInstance().getAllCharacterAwaitingAdoption();
		structureAdoptedProvisionalTerms = ProvisionalTermDAO.getInstance().getAdoptedStructureTerms();
		characterAdoptedProvisionalTerms = ProvisionalTermDAO.getInstance().getAdoptedCharacterTerms();
	}

	public String execute() {
		try {
			switch(action) {
			case "send":
				IUnadoptedTermDAO unadoptedTermDAO = UnadoptedTermDAO.getInstance();
				provisionalTerm = unadoptedTermDAO.getUnadoptedTerm(localId);				
				return SUCCESS;
			case "update":
				provisionalTerm = ProvisionalTermDAO.getInstance().getAwaitingAdoption(localId);
				return SUCCESS;
			case "adopted":
				provisionalTerm = ProvisionalTermDAO.getInstance().getAdopted(localId);
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

	public ProvisionalTerm getProvisionalTerm() {
		return provisionalTerm;
	}

	public void setProvisionalTerm(ProvisionalTerm provisionalTerm) {
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
