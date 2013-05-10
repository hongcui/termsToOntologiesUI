package ui.db;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ui.beans.OTOProvisionalTerm;


public class OTOProvisionalTermDAO extends AbstractDAO {

	private static OTOProvisionalTermDAO instance;
	
	private OTOProvisionalTermDAO() throws SQLException, ClassNotFoundException, IOException {
		this.openConnection();
		createTableIfNecessary("awaitingadoption");
		createTableIfNecessary("adopted");
		this.closeConnection();
	}
	
	public static OTOProvisionalTermDAO getInstance() throws SQLException, ClassNotFoundException, IOException {
		if(instance == null)
			instance = new OTOProvisionalTermDAO();
		return instance;
	}

	public void addAwaitingAdoption(OTOProvisionalTerm OTOProvisionalTerm) throws SQLException {
		this.openConnection();
		this.storeOTOProvisionalTerm("awaitingadoption", OTOProvisionalTerm);		
		this.closeConnection();
	}

	public List<OTOProvisionalTerm> getAllAwaitingAdoption() throws SQLException {
		this.openConnection();
		List<OTOProvisionalTerm> result =  this.getAll("awaitingAdoption", "");
		this.closeConnection();
		return result;
	}
	
	public List<OTOProvisionalTerm> getAllStructureAwaitingAdoption() throws SQLException {
		this.openConnection();
		List<OTOProvisionalTerm> result =  this.getAll("awaitingAdoption", "WHERE termType='structure'");
		this.closeConnection();
		return result;
	}
	
	public List<OTOProvisionalTerm> getAdoptedStructureTerms() throws SQLException {
		this.openConnection();
		List<OTOProvisionalTerm> result =  this.getAll("adopted", "WHERE termType='structure'");
		this.closeConnection();
		return result;
	}
	
	public List<OTOProvisionalTerm> getAdoptedCharacterTerms() throws SQLException {
		this.openConnection();
		List<OTOProvisionalTerm> result =  this.getAll("adopted", "WHERE termType='character'");
		this.closeConnection();
		return result;
	}
	
	public List<OTOProvisionalTerm> getAllCharacterAwaitingAdoption() throws SQLException {
		this.openConnection();
		List<OTOProvisionalTerm> result =  this.getAll("awaitingAdoption", "WHERE termType='character'");
		this.closeConnection();
		return result;
	}

	public void storeAdopted(OTOProvisionalTerm OTOProvisionalTerm) throws SQLException {
		this.openConnection();
		
		storeOTOProvisionalTerm("adopted", OTOProvisionalTerm);
		this.closeConnection();
	}
	
	public void deleteAwaitingAdoption(OTOProvisionalTerm OTOProvisionalTerm) throws SQLException {
		this.openConnection();
		this.deleteTerm("awaitingadoption", OTOProvisionalTerm);
		this.closeConnection();
	}
	
	public void deleteTerm(String table, OTOProvisionalTerm OTOProvisionalTerm) throws SQLException {
		this.executeSQL("DELETE FROM bioportal_" + table + " WHERE temporaryId='" + OTOProvisionalTerm.getTemporaryid() + "'");
	}
	
	private void storeOTOProvisionalTerm(String tableName, OTOProvisionalTerm OTOProvisionalTerm) throws SQLException {
		PreparedStatement preparedStatement = this.prepareStatement("INSERT INTO bioportal_" + tableName + " (`localId`, `temporaryId`, `permanentId`, `superClass`, " +
				"`submittedby`, `definition`, `ontologyids`, `preferredname`, `synonyms`, `source`, `termType`, `termCategory`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		preparedStatement.setString(1, OTOProvisionalTerm.getLocalId());
		preparedStatement.setString(2, OTOProvisionalTerm.getTemporaryid());
		preparedStatement.setString(3, OTOProvisionalTerm.getPermanentid());
		preparedStatement.setString(4, OTOProvisionalTerm.getSuperclass());
		preparedStatement.setString(5, OTOProvisionalTerm.getSubmittedby());
		preparedStatement.setString(6, OTOProvisionalTerm.getDefinition());
		preparedStatement.setString(7, OTOProvisionalTerm.getOntologyids());
		preparedStatement.setString(8, OTOProvisionalTerm.getTerm());
		preparedStatement.setString(9, OTOProvisionalTerm.getSynonyms());
		preparedStatement.setString(10, OTOProvisionalTerm.getSource());
		preparedStatement.setString(11, OTOProvisionalTerm.getTermType());
		preparedStatement.setString(12, OTOProvisionalTerm.getTermCategory());
		preparedStatement.executeUpdate();
	}
	
	private void createTableIfNecessary(String tableName) throws SQLException {
		this.executeSQL("CREATE TABLE IF NOT EXISTS bioportal_" + tableName + "(" +
				" `localId` varchar(100) NOT NULL, " +
				" `temporaryId` varchar(100) NOT NULL, " +
				"  `permanentId` varchar(100) DEFAULT NULL, " +
				" `superClass` varchar(100) DEFAULT NULL, " +
				"  `submittedBy` varchar(100) DEFAULT NULL, " +
				"  `definition` text, " +
				"  `ontologyIds` varchar(100) DEFAULT NULL, " +
				"  `preferredName` varchar(100) DEFAULT NULL, " +
				"  `synonyms` varchar(100) DEFAULT NULL, " +
				"  `source` varchar(100) DEFAULT NULL, " +
				"  `termType` varchar(100) DEFAULT NULL, " +
				"  `termCategory` varchar(100) DEFAULT NULL, " +
				"  PRIMARY KEY (`localId`))");
	}
	
	public List<OTOProvisionalTerm> getAll(String tableName, String where) throws SQLException {
		List<OTOProvisionalTerm> result = new ArrayList<OTOProvisionalTerm>();
		PreparedStatement preparedStatement = this.executeSQL("SELECT * FROM bioportal_" + tableName
				+ " " + where);
		ResultSet resultSet = preparedStatement.getResultSet();
		while(resultSet.next()) {
			result.add(new OTOProvisionalTerm(
					resultSet.getString("localId"),
					resultSet.getString("preferredName"), 
					resultSet.getString("termType"),
					resultSet.getString("termCategory"),
					resultSet.getString("definition"), 
					resultSet.getString("superClass"),
					resultSet.getString("synonyms"),
					resultSet.getString("ontologyIds"), 
					resultSet.getString("submittedBy"), 
					resultSet.getString("temporaryId"), 
					resultSet.getString("permanentId"),
					resultSet.getString("source")
					));
		}
		return result;
	}

	
	public static void main(String[] args) {
		OTOProvisionalTerm OTOProvisionalTerm = new OTOProvisionalTerm();
		OTOProvisionalTerm.setTemporaryid("tempId");
		OTOProvisionalTerm.setDefinition("def");
		OTOProvisionalTerm.setTerm("name");
		OTOProvisionalTerm.setSubmittedby("submittedby");
		try {
			OTOProvisionalTermDAO.getInstance().addAwaitingAdoption(OTOProvisionalTerm);
			List<OTOProvisionalTerm> all = OTOProvisionalTermDAO.getInstance().getAllAwaitingAdoption();
			OTOProvisionalTermDAO.getInstance().storeAdopted(OTOProvisionalTerm);
			OTOProvisionalTermDAO.getInstance().deleteAwaitingAdoption(OTOProvisionalTerm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public OTOProvisionalTerm getFirstAwaitingTerm() throws SQLException {
		this.openConnection();
		OTOProvisionalTerm result = getFirst("awaitingadoption");
		this.closeConnection();
		return result;
	}

	public OTOProvisionalTerm getFirstAdoptedTerm() throws SQLException {
		this.openConnection();
		OTOProvisionalTerm result = getFirst("adopted");
		this.closeConnection();
		return result;
	}
	
	public OTOProvisionalTerm getFirst(String tableName) throws SQLException {
		PreparedStatement preparedStatement = this.executeSQL("SELECT * FROM bioportal_" + tableName + " " +
				"ORDER BY localId");
		ResultSet resultSet = preparedStatement.getResultSet();
		if(!resultSet.next())
			return null;
		return new OTOProvisionalTerm(
					resultSet.getString("localId"),
					resultSet.getString("preferredName"), 
					resultSet.getString("termType"),
					resultSet.getString("termCategory"),
					resultSet.getString("definition"), 
					resultSet.getString("superClass"),
					resultSet.getString("synonyms"),
					resultSet.getString("ontologyIds"), 
					resultSet.getString("submittedBy"), 
					resultSet.getString("temporaryId"), 
					resultSet.getString("permanentId"),
					resultSet.getString("source")
					);
	}

	public void updateAwaitingAdoption(OTOProvisionalTerm OTOProvisionalTerm) throws SQLException {
		this.openConnection();		
		PreparedStatement preparedStatement = this.prepareStatement("UPDATE bioportal_awaitingadoption SET " +
				"preferredName = ?, definition = ?, superClass = ?, synonyms = ?, ontologyIds = ?, source = ? WHERE localId = ?");
		preparedStatement.setString(1, OTOProvisionalTerm.getTerm());
		preparedStatement.setString(2, OTOProvisionalTerm.getDefinition());
		preparedStatement.setString(3, OTOProvisionalTerm.getSuperclass());
		preparedStatement.setString(4, OTOProvisionalTerm.getSynonyms());
		preparedStatement.setString(5, OTOProvisionalTerm.getOntologyids());
		preparedStatement.setString(6, OTOProvisionalTerm.getSource());
		preparedStatement.setString(7, OTOProvisionalTerm.getLocalId());
		preparedStatement.executeUpdate();
		
		this.closeConnection();
	}

	public OTOProvisionalTerm getAdopted(String localId) throws SQLException {
		this.openConnection();
		OTOProvisionalTerm result = getTerm("adopted", localId);
		this.closeConnection();
		return result;
	}

	public OTOProvisionalTerm getAwaitingAdoption(String localId) throws SQLException {
		this.openConnection();
		OTOProvisionalTerm result = getTerm("awaitingadoption", localId);
		this.closeConnection();
		return result;
	}
	
	public OTOProvisionalTerm getTerm(String tableName, String localId) throws SQLException {
		PreparedStatement preparedStatement = this.executeSQL("SELECT * FROM bioportal_" + tableName + " " +
				"WHERE localId = " + localId);
		ResultSet resultSet = preparedStatement.getResultSet();
		resultSet.next();
		return new OTOProvisionalTerm(
					resultSet.getString("localId"),
					resultSet.getString("preferredName"), 
					resultSet.getString("termType"),
					resultSet.getString("termCategory"),
					resultSet.getString("definition"), 
					resultSet.getString("superClass"),
					resultSet.getString("synonyms"),
					resultSet.getString("ontologyIds"), 
					resultSet.getString("submittedBy"), 
					resultSet.getString("temporaryId"), 
					resultSet.getString("permanentId"),
					resultSet.getString("source")
					);
	}
}
