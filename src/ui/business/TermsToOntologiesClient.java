package ui.business;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import ui.beans.OTOProvisionalTerm;
import ui.db.OTOProvisionalTermDAO;

import bioportal.beans.Filter;
import bioportal.beans.ProvisionalTerm;
import bioportal.beans.response.Entry;
import bioportal.beans.response.Relations;
import bioportal.beans.response.Success;
import bioportal.client.BioPortalClient;

public class TermsToOntologiesClient {

	private BioPortalClient bioPortalClient;
	private String bioportalUserId;
	/*private static TermsToOntologiesClient instance;
	
	public static TermsToOntologiesClient getInstance() throws IOException {
		if(instance == null)
			instance = new TermsToOntologiesClient();
		return instance;
	}*/
	
	public TermsToOntologiesClient(String bioportalUserId, String bioportalAPIKey) throws IOException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties(); 
		properties.load(loader.getResourceAsStream("config.properties"));
		String url = properties.getProperty("bioportalUrl");
		//String userId = properties.getProperty("bioportalUserId");
		//String apiKey = properties.getProperty("bioportalApiKey");
		this.bioportalUserId = bioportalUserId;
		bioPortalClient = new BioPortalClient(url, bioportalUserId, bioportalAPIKey);	
	}
	
	/**
	 * @param provisionalTerm
	 * @return temporary id given to the provided provisionalTerm
	 * @throws SQLException 
	 * @throws JAXBException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public String sendTerm(OTOProvisionalTerm provisionalTerm) throws SQLException, JAXBException, ClassNotFoundException, IOException, IllegalArgumentException {
		Success success = bioPortalClient.createProvisionalTerm(provisionalTerm);
		String temporaryId = getIdFromSuccessfulCreate(success, "id");
		provisionalTerm.setTemporaryid(temporaryId);
		provisionalTerm.setSubmittedby(bioportalUserId);
		OTOProvisionalTermDAO.getInstance().addAwaitingAdoption(provisionalTerm);
		return temporaryId;
	}
	
	/**
	 * @return Map<Temporary ID, Permanent ID> of newly discovered adoptions 
	 * @throws SQLException 
	 * @throws JAXBException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public Map<String, String> checkTermAdoptions() throws SQLException, JAXBException, ClassNotFoundException, IOException  {
		Map<String, String> result = new HashMap<String, String>();
		List<OTOProvisionalTerm> allProvisionalTermsAwaitingAdoption = OTOProvisionalTermDAO.getInstance().getAllAwaitingAdoption();
		for(OTOProvisionalTerm provisionalTerm : allProvisionalTermsAwaitingAdoption) {
			String permanentId = null;
			Success success = bioPortalClient.getProvisionalTerm(provisionalTerm.getTemporaryid());
			List<Object> fullIdOrIdOrLabels = success.getData().getClassBean().getFullIdOrIdOrLabel();
			for(Object fullIdOrIdOrLabel : fullIdOrIdOrLabels) {
				if(fullIdOrIdOrLabel instanceof Relations) {
					Relations relations = (Relations)fullIdOrIdOrLabel;
					List<Entry> entries = relations.getEntry();
					for(Entry entry : entries) {
						List<Object> objects = entry.getStringOrList();
						if(objects.size() >= 2 && objects.get(0).equals("provisionalPermanentId")) {
							permanentId = (String)objects.get(1);
						}
					}
				}
			}
			
			if(permanentId == null)
				continue;
			else {
				provisionalTerm.setPermanentid(permanentId);
				OTOProvisionalTermDAO.getInstance().deleteAwaitingAdoption(provisionalTerm);
				OTOProvisionalTermDAO.getInstance().storeAdopted(provisionalTerm);
				result.put(provisionalTerm.getTemporaryid(), permanentId);
			}
		}
		return result;
	}
	
	private String getIdFromSuccessfulCreate(Success createSuccess, String idName) {
		List<Object> fullIdOrIdOrLabel = createSuccess.getData().getClassBean().getFullIdOrIdOrLabel();
		for(Object object : fullIdOrIdOrLabel) {
			if(object instanceof JAXBElement) {
				JAXBElement<String> possibleIdElement = (JAXBElement<String>)object;
				if(possibleIdElement.getName().toString().equals(idName)) {
					return possibleIdElement.getValue();
				}
			}
		}
		return null;
	}

	public void updateTerm(OTOProvisionalTerm provisionalTerm) throws JAXBException, SQLException, ClassNotFoundException, IOException {
		bioPortalClient.updateProvisionalTerm(provisionalTerm.getTemporaryid(), provisionalTerm);
		OTOProvisionalTermDAO.getInstance().updateAwaitingAdoption(provisionalTerm);
	}

	public void deleteTerm(OTOProvisionalTerm provisionalTerm) throws JAXBException, SQLException, ClassNotFoundException, IOException {
		bioPortalClient.deleteProvisionalTerm(provisionalTerm.getTemporaryid());
		OTOProvisionalTermDAO.getInstance().deleteAwaitingAdoption(provisionalTerm);
	}
	
	public static void main(String[] args) throws IOException, JAXBException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties(); 
		properties.load(loader.getResourceAsStream("config.properties"));
		String url = properties.getProperty("bioportalUrl");
		String userId = properties.getProperty("bioportalUserId");
		String apiKey = properties.getProperty("bioportalApiKey");
		BioPortalClient bioPortalClient = new BioPortalClient(url, userId, apiKey);	
		
		for(int i=0; i<12; i++) {
			Filter filter = new Filter();
			filter.setPageSize("1");
			filter.setPageNum(String.valueOf(i));
			filter.setSubmittedBy(userId);
			Success success = bioPortalClient.getProvisionalTerms(filter);
			
			TermsToOntologiesClient termsToOntologiesClient = new TermsToOntologiesClient(userId, apiKey);
			String id = termsToOntologiesClient.getIdFromSuccessfulCreate(success, "id");
			bioPortalClient.deleteProvisionalTerm(id);
		}
	}
}
