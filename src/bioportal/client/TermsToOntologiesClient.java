package bioportal.client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bioportal.db.ProvisionalTermDAO;

import bioportal.beans.Filter;
import bioportal.beans.ProvisionalTerm;
import bioportal.beans.response.Entry;
import bioportal.beans.response.Relations;
import bioportal.beans.response.Success;

public class TermsToOntologiesClient {

	private BioPortalClient bioPortalClient;
	private static TermsToOntologiesClient instance;
	
	public static TermsToOntologiesClient getInstance() throws IOException {
		if(instance == null)
			instance = new TermsToOntologiesClient();
		return instance;
	}
	
	private TermsToOntologiesClient() throws IOException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties(); 
		properties.load(loader.getResourceAsStream("config.properties"));
		String url = properties.getProperty("bioportalUrl");
		String userId = properties.getProperty("bioportalUserId");
		String apiKey = properties.getProperty("bioportalApiKey");
		bioPortalClient = new BioPortalClient(url, userId, apiKey);	
	}
	
	/**
	 * @param provisionalTerm
	 * @return temporary id given to the provided provisionalTerm
	 * @throws SQLException 
	 * @throws JAXBException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public String sendTerm(ProvisionalTerm provisionalTerm) throws SQLException, JAXBException, ClassNotFoundException, IOException, IllegalArgumentException {
		Success success = bioPortalClient.createProvisionalTerm(provisionalTerm);
		String temporaryId = getIdFromSuccessfulCreate(success, "id");
		provisionalTerm.setTemporaryid(temporaryId);
		ProvisionalTermDAO.getInstance().addAwaitingAdoption(provisionalTerm);
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
		List<ProvisionalTerm> allProvisionalTermsAwaitingAdoption = ProvisionalTermDAO.getInstance().getAllAwaitingAdoption();
		for(ProvisionalTerm provisionalTerm : allProvisionalTermsAwaitingAdoption) {
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
				ProvisionalTermDAO.getInstance().deleteAwaitingAdoption(provisionalTerm);
				ProvisionalTermDAO.getInstance().storeAdopted(provisionalTerm);
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

	public void updateTerm(ProvisionalTerm provisionalTerm) throws JAXBException, SQLException, ClassNotFoundException, IOException {
		bioPortalClient.updateProvisionalTerm(provisionalTerm.getTemporaryid(), provisionalTerm);
		ProvisionalTermDAO.getInstance().updateAwaitingAdoption(provisionalTerm);
	}

	public void deleteTerm(ProvisionalTerm provisionalTerm) throws JAXBException, SQLException, ClassNotFoundException, IOException {
		bioPortalClient.deleteProvisionalTerm(provisionalTerm.getTemporaryid());
		ProvisionalTermDAO.getInstance().deleteAwaitingAdoption(provisionalTerm);
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
			
			TermsToOntologiesClient termsToOntologiesClient = new TermsToOntologiesClient();
			String id = termsToOntologiesClient.getIdFromSuccessfulCreate(success, "id");
			bioPortalClient.deleteProvisionalTerm(id);
		}
	}
}
