package ui.action;

import java.util.ArrayList;
import java.util.List;

import ui.db.UnadoptedTermDAO;
import bioportal.OntologyMapper;
import bioportal.beans.ProvisionalTerm;
import bioportal.db.ProvisionalTermDAO;

import com.opensymphony.xwork2.ActionSupport;

public class ProvisionalTermAction extends ActionSupport {

	private ProvisionalTerm provisionalTerm;
	private String localId;
	private String action;
	private List<String> ontologies = new ArrayList<String>();
	
	public ProvisionalTermAction() {
		ontologies = OntologyMapper.getInstance().getOntologies();
	}

	public String execute() {
		//here i would load the extra info from the database as far it is available already e.g. source;
		try {
			switch(action) {
			case "send":
				provisionalTerm = UnadoptedTermDAO.getInstance().getUnadoptedTerm(localId);
				return SUCCESS;
			case "update":
				provisionalTerm = ProvisionalTermDAO.getInstance().getAwaitingAdoption(localId);
				return SUCCESS;
			case "adopted":
				provisionalTerm = ProvisionalTermDAO.getInstance().getAdopted(localId);
				return SUCCESS;
			default:
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
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
}
