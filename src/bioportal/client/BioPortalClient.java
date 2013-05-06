package bioportal.client;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;


import bioportal.beans.Filter;
import bioportal.beans.ProvisionalTerm;
import bioportal.beans.response.Entry;
import bioportal.beans.response.Relations;
import bioportal.beans.response.Success;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * BioPortalClient provides access to BioPortals REST Web Services 
 * e.g. at the moment the part of services available to provide provisional ontology terms
 * @author rodenhausen
 */
public class BioPortalClient {

	private String userId;
	private String apiKey;
	private String apiUrl;
	private Client client;
	private String superclassPrefix = "http://purl.obolibrary.org/obo/";
	
	/**
	 * @param apiUrl
	 * @param apiKey
	 */
	public BioPortalClient(String apiUrl, String userId, String apiKey) {
		this.apiUrl = apiUrl;
		this.userId = userId;
		this.apiKey = apiKey;
		ClientConfig clientConfig = new DefaultClientConfig();
		client = Client.create(clientConfig);
		client.addFilter(new LoggingFilter(System.out));
	}
	
	/**
	 * Get a single provisional term for the given provisional term id.
	 * @param termid
	 * @return Success
	 * @throws JAXBException 
	 * @throws Exception when returned XML cannot be parsed into schema, 
	 * e.g. because no success but error response was returned by web service
	 */
	public Success getProvisionalTerm(String termId) throws Exception {
		String url = this.apiUrl + "provisional";
	    WebResource webResource = client.resource(url);
	    MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
	    queryParams.add("termid", termId);
	    queryParams.add("apikey", this.apiKey);
	    return webResource.queryParams(queryParams).get(Success.class);
	}
	
	/**
	 * Get all available provisional terms using a paged interface.
	 * @throws Exception when returned XML cannot be parsed into schema, 
	 * e.g. because no success but error response was returned by web service
	 */
	public Success getProvisionalTerms() throws Exception {
		String url = this.apiUrl + "provisional";
	    WebResource webResource = client.resource(url);
	    MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
	    queryParams.add("apikey", this.apiKey);	    
	    return webResource.queryParams(queryParams).get(Success.class);
	}
	
	/**
	 * Get all available provisional terms using a paged interface.
	 * @param filter
	 * @return Success
	 * @return Success
	 * @throws Exception when returned XML cannot be parsed into schema, 
	 * e.g. because no success but error response was returned by web service
	 */
	public Success getProvisionalTerms(Filter filter) throws Exception {
		String url = this.apiUrl + "provisional";
	    WebResource webResource = client.resource(url);
	    MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
	    queryParams.add("apikey", this.apiKey);
	    
	    if(filter.hasCreatedEndDate())
	    	queryParams.add("createdenddate", filter.getCreatedEndDate());
	    if(filter.hasImplementedTermsOnly())
	    	queryParams.add("implementedtermsonly", filter.getImplementedTermsOnly());
	    if(filter.hasOntologyIds())
	    	queryParams.add("ontologyids", filter.getOntologyIds());
	    if(filter.hasPageNum())
	    	queryParams.add("pagenum", filter.getPageNum());
	    if(filter.hasPageSize())
	    	queryParams.add("pagesize", filter.getPageSize());
	    if(filter.hasSubmittedBy())
	    	queryParams.add("submittedby", filter.getSubmittedBy());
	    if(filter.hasUpdatedEndDate())
	    	queryParams.add("updatedenddate", filter.getUpdatedEndDate());
	    if(filter.hasUpdatedStartDate())
	    	queryParams.add("updatedstartdate", filter.getUpdatedStartDate());
	    
	    return webResource.queryParams(queryParams).get(Success.class);
	}
	
	/**
	 * Create a provisional term.
	 * @return Success
	 * @param provisionalTerm
	 * @throws Exception when returned XML cannot be parsed into schema, 
	 * e.g. because no success but error response was returned by web service
	 */
	public Success createProvisionalTerm(ProvisionalTerm provisionalTerm) throws Exception {
		String url = this.apiUrl + "provisional";
	    WebResource webResource = client.resource(url);
	    MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
	    queryParams.add("apikey", this.apiKey);
	    
	    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
	    formData.add("submittedby", this.userId);
	    if(provisionalTerm.hasTerm() && provisionalTerm.hasDefinition()) {
	    	formData.add("preferredname", provisionalTerm.getTerm());
	    	formData.add("definition", provisionalTerm.getDefinition());
	    } else {
	    	//required parameters not met
	    	return null;
	    }
	    if(provisionalTerm.hasOntologyIds())
	    	formData.add("ontologyids", provisionalTerm.getOntologyids());
	    if(provisionalTerm.hasSynonyms())
	    	formData.add("synonyms", provisionalTerm.getSynonyms());
	    if(provisionalTerm.hasSuperClass())
	    	formData.add("superclass", superclassPrefix + provisionalTerm.getSuperclass());
	    
	    return webResource.queryParams(queryParams).type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(Success.class, formData);
	}
	
	/**
	 * Update fields for a provisional term.
	 * @param termId
	 * @return Success
	 * @param provisionalTerm
	 * @throws Exception when returned XML cannot be parsed into schema, 
	 * e.g. because no success but error response was returned by web service
	 */
	public Success updateProvisionalTerm(String termId, ProvisionalTerm provisionalTerm) throws Exception {	    
		String url = this.apiUrl + "provisional";
	    WebResource webResource = client.resource(url);
	    
	    MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
	    queryParams.add("apikey", this.apiKey);
	    queryParams.add("termid", termId);
	    queryParams.add("submittedby", this.userId);
	    if(provisionalTerm.hasTerm())
	    	queryParams.add("preferredname", provisionalTerm.getTerm());
	    if(provisionalTerm.hasDefinition()) 
	    	queryParams.add("definition", provisionalTerm.getDefinition());
	    if(provisionalTerm.hasOntologyIds())
	    	queryParams.add("ontologyids", provisionalTerm.getOntologyids());
	    if(provisionalTerm.hasPermanentId())
	    	queryParams.add("permanentid", provisionalTerm.getPermanentid());
	    if(provisionalTerm.hasSuperClass())
	    	queryParams.add("superclass", superclassPrefix + provisionalTerm.getSuperclass());
	    if(provisionalTerm.hasSynonyms())
	    	queryParams.add("synonyms", provisionalTerm.getSynonyms());
	    
	    return webResource.queryParams(queryParams).put(Success.class);
	}
	
	/**
	 * Delete a provisional term.
	 * @return Success
	 * @param termId
	 * @throws Exception when returned XML cannot be parsed into schema, 
	 * e.g. because no success but error response was returned by web service
	 */
	public Success deleteProvisionalTerm(String termId) throws Exception {
		String url = this.apiUrl + "provisional";
	    WebResource webResource = client.resource(url);
	    MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
	    queryParams.add("termid", termId);
	    queryParams.add("apikey", this.apiKey);
	    return webResource.queryParams(queryParams).delete(Success.class);
	}
	
	public static void main(String[] args) throws Exception {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties(); 
		properties.load(loader.getResourceAsStream("config.properties"));
		String url = properties.getProperty("bioportalUrl");
		String userId = properties.getProperty("bioportalUserId");
		String apiKey = properties.getProperty("bioportalApiKey");
		BioPortalClient bioPortalClient = new BioPortalClient(url, userId, apiKey);	
		
		String[] ids = new String[] { 
				"http://purl.bioontology.org/ontology/provisional/50cf20bc-6ce5-4992-aa08-8fbfa3ad5925",
				"http://purl.bioontology.org/ontology/provisional/7117c00c-88d9-457f-a39d-cf93b18564c8",
				"http://purl.bioontology.org/ontology/provisional/36776c5f-51fe-403c-9f76-1b440185a6c2",
				"http://purl.bioontology.org/ontology/provisional/61f9feab-8e50-408b-a48a-64a9dd0376df",
				"http://purl.bioontology.org/ontology/provisional/a4fc6aec-fe2e-4cd1-8e37-7d2f7dcbb842",
				"http://purl.bioontology.org/ontology/provisional/ebe43aeb-b79a-43d6-8581-ee770a80393b",
				"http://purl.bioontology.org/ontology/provisional/524e317b-bf03-4b4d-823f-172be7263d18",
				"http://purl.bioontology.org/ontology/provisional/7de65602-fb2d-4201-bfe7-1727c725b26a",
				"http://purl.bioontology.org/ontology/provisional/b9d70adf-d2d2-4144-b02d-7359b5409a26",
				"http://purl.bioontology.org/ontology/provisional/ea9b4ddf-eb2d-4f64-8bf1-a2f4324f8268",
				"http://purl.bioontology.org/ontology/provisional/51f6ccba-23f5-4220-b5c9-9d8462ac732f",
				"http://purl.bioontology.org/ontology/provisional/301d2180-1cf2-4f9f-b5e0-15318b5e741f",
				"http://purl.bioontology.org/ontology/provisional/f54beb20-30b9-48ea-a030-309125d2995b",
				"http://purl.bioontology.org/ontology/provisional/cb3d0fa2-2464-4884-83cc-b92b92f1745a",
				"http://purl.bioontology.org/ontology/provisional/4a8fa284-b3c8-4418-b7f7-634f58cb43b5",
				"http://purl.bioontology.org/ontology/provisional/a333d709-f724-48f2-b56a-fb4503d87beb",
				"http://purl.bioontology.org/ontology/provisional/d3267598-ca34-4d9b-a0bc-f0ae5fbbc328",
				"http://purl.bioontology.org/ontology/provisional/033daa88-e03e-4ed8-bf2c-fc33f81800eb",
				"http://purl.bioontology.org/ontology/provisional/cbb1e6db-7d2d-4bd8-93ae-4563cd3150e4"
			}; 
				
		for(String id : ids) {
			bioPortalClient.deleteProvisionalTerm(id);
		}
		
		
		//for(int i=0; i<56; i++) {
			Filter filter = new Filter();
			filter.setSubmittedBy(userId);
			//filter.setPageSize("279");
			//filter.setPageNum(String.valueOf(i));
			//filter.setImplementedTermsOnly("true");
			Success success = bioPortalClient.getProvisionalTerms(filter);
			
			//System.out.println("-----------------done-------------------");
			//System.out.println(success.getData().getList());
			//System.out.println(success.getData().getClassBean());
		//}
		
		/*System.out.println("Provide a new term: ");
		ProvisionalTerm provisionalTerm = new ProvisionalTerm();
		provisionalTerm.setPreferredname("test name");
		provisionalTerm.setDefinition("this is a test definition");
		Success createSuccess = bioPortalClient.createProvisionalTerm(provisionalTerm);
		
		System.out.println("All the terms provided by this userId: ");
		Filter filter = new Filter();
		filter.setSubmittedBy(userId);
		Success getSuccess = bioPortalClient.getProvisionalTerms(filter);
		
		List<Object> fullIdOrIdOrLabel = createSuccess.getData().getClassBean().getFullIdOrIdOrLabel();
		for(Object object : fullIdOrIdOrLabel) {
			if(object instanceof JAXBElement) {
				JAXBElement<String> possibleIdElement = (JAXBElement<String>)object;
				if(possibleIdElement.getName().toString().equals("id")) {
					ProvisionalTerm updatedProvisionalTerm = new ProvisionalTerm();
					updatedProvisionalTerm.setPreferredname("test name 2");
					System.out.println("update the provided term with a new preferredname");
					bioPortalClient.updateProvisionalTerm(possibleIdElement.getValue(), updatedProvisionalTerm);
					System.out.println("view the updated term:");
					Success getSuccess2 = bioPortalClient.getProvisionalTerm(possibleIdElement.getValue());
					
					System.out.println("delete provided term");
					bioPortalClient.deleteProvisionalTerm(possibleIdElement.getValue());
				}
			}
		}
		
		System.out.println("All the terms provided by this userId. The provided term should have disappeared.");
		Success getSuccess3 = bioPortalClient.getProvisionalTerms(filter); */
	}
}
