package ui.beans;

import ui.action.OntologyMapper;
import bioportal.beans.ProvisionalTerm;

public class OTOProvisionalTerm extends ProvisionalTerm {

	private String localId;
	private String termType;
	private String termCategory;
	private String source;
	
	public OTOProvisionalTerm() { 
		super(); 
	}

	public OTOProvisionalTerm(String localId, String term, String termType, String termCategory, String definition, String superclass,
			String synonyms, String ontologyids, String submittedby,
			String temporaryid, String permanentid, String source) {
		super(term, definition, superclass, synonyms, ontologyids, submittedby, temporaryid, permanentid);
		this.localId = localId;
		this.termType = termType;
		this.termCategory = termCategory;
		this.source = source;
	}

	public boolean hasLocalId() {
		return this.localId != null && !this.localId.isEmpty();
	}
	
	public String getLocalId() {
		return localId;
	}

	public void setLocalId(String localId) {
		this.localId = localId;
	}
	
	public boolean hasTermType() {
		return this.termType != null && !this.termType.isEmpty();
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}
	
	public boolean hasTermCategory() {
		return this.termCategory != null && !this.termCategory.isEmpty();
	}

	public String getTermCategory() {
		return termCategory;
	}

	public void setTermCategory(String termCategory) {
		this.termCategory = termCategory;
	}
	
	public boolean hasSource() {
		return this.source != null && !this.source.isEmpty();
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public String getOntology() {
		return OntologyMapper.getInstance().getOntology(this.getOntologyids());
	}
	
	public void setOntology(String ontology) {
		this.setOntologyids(OntologyMapper.getInstance().getOntologyId(ontology));
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(super.toString());
		stringBuilder.append("localId: " + this.localId + "\n");
		stringBuilder.append("source: " + this.source + "\n");
		stringBuilder.append("termType: " + this.termType + "\n");
		stringBuilder.append("termCategory: " + this.termCategory + "\n");
		return stringBuilder.toString();
	}
}




	
