package beans;

public class TermDescription {

	private String term;
	private String definition;
	private String source;
	private String superclass;
	private String ontologyIds;
	private String synonyms;
	
	public TermDescription() {
		
	}
	
	public TermDescription(String term, String definition, String source,
			String superclass, String ontologyIds, String synonyms) {
		super();
		this.term = term;
		this.definition = definition;
		this.source = source;
		this.superclass = superclass;
		this.ontologyIds = ontologyIds;
		this.synonyms = synonyms;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSuperclass() {
		return superclass;
	}

	public void setSuperclass(String superclass) {
		this.superclass = superclass;
	}

	public String getOntologyIds() {
		return ontologyIds;
	}

	public void setOntologyIds(String ontologyIds) {
		this.ontologyIds = ontologyIds;
	}

	public String getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(String synonyms) {
		this.synonyms = synonyms;
	}
}


