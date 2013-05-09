package bioportal.db;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.AbstractDAO;

import bioportal.beans.ProvisionalTerm;

public class ProvisionalTermDAO extends AbstractDAO {

	private static ProvisionalTermDAO instance;
	
	private ProvisionalTermDAO() throws SQLException, ClassNotFoundException, IOException {
		this.openConnection();
		createTableIfNecessary("awaitingadoption");
		createTableIfNecessary("adopted");
		this.closeConnection();
	}
	
	public static ProvisionalTermDAO getInstance() throws SQLException, ClassNotFoundException, IOException {
		if(instance == null)
			instance = new ProvisionalTermDAO();
		return instance;
	}

	public void addAwaitingAdoption(ProvisionalTerm provisionalTerm) throws SQLException {
		this.openConnection();
		this.storeProvisionalTerm("awaitingadoption", provisionalTerm);		
		this.closeConnection();
	}

	public List<ProvisionalTerm> getAllAwaitingAdoption() throws SQLException {
		this.openConnection();
		List<ProvisionalTerm> result =  this.getAll("awaitingAdoption", "");
		this.closeConnection();
		return result;
	}
	
	public List<ProvisionalTerm> getAllStructureAwaitingAdoption() throws SQLException {
		this.openConnection();
		List<ProvisionalTerm> result =  this.getAll("awaitingAdoption", "WHERE termType='structure'");
		this.closeConnection();
		return result;
	}
	
	public List<ProvisionalTerm> getAdoptedStructureTerms() throws SQLException {
		this.openConnection();
		List<ProvisionalTerm> result =  this.getAll("adopted", "WHERE termType='structure'");
		this.closeConnection();
		return result;
	}
	
	public List<ProvisionalTerm> getAdoptedCharacterTerms() throws SQLException {
		this.openConnection();
		List<ProvisionalTerm> result =  this.getAll("adopted", "WHERE termType='character'");
		this.closeConnection();
		return result;
	}
	
	public List<ProvisionalTerm> getAllCharacterAwaitingAdoption() throws SQLException {
		this.openConnection();
		List<ProvisionalTerm> result =  this.getAll("awaitingAdoption", "WHERE termType='character'");
		this.closeConnection();
		return result;
	}

	public void storeAdopted(ProvisionalTerm provisionalTerm) throws SQLException {
		this.openConnection();
		
		storeProvisionalTerm("adopted", provisionalTerm);
		this.closeConnection();
	}
	
	public void deleteAwaitingAdoption(ProvisionalTerm provisionalTerm) throws SQLException {
		this.openConnection();
		this.deleteTerm("awaitingadoption", provisionalTerm);
		this.closeConnection();
	}
	
	public void deleteTerm(String table, ProvisionalTerm provisionalTerm) throws SQLException {
		this.executeSQL("DELETE FROM bioportal_" + table + " WHERE temporaryId='" + provisionalTerm.getTemporaryid() + "'");
	}
	
	private void storeProvisionalTerm(String tableName, ProvisionalTerm provisionalTerm) throws SQLException {
		PreparedStatement preparedStatement = this.prepareStatement("INSERT INTO bioportal_" + tableName + " (`localId`, `temporaryId`, `permanentId`, `superClass`, " +
				"`submittedby`, `definition`, `ontologyids`, `preferredname`, `synonyms`, `source`, `termType`, `termCategory`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		preparedStatement.setString(1, provisionalTerm.getLocalId());
		preparedStatement.setString(2, provisionalTerm.getTemporaryid());
		preparedStatement.setString(3, provisionalTerm.getPermanentid());
		preparedStatement.setString(4, provisionalTerm.getSuperclass());
		preparedStatement.setString(5, provisionalTerm.getSubmittedby());
		preparedStatement.setString(6, provisionalTerm.getDefinition());
		preparedStatement.setString(7, provisionalTerm.getOntologyids());
		preparedStatement.setString(8, provisionalTerm.getTerm());
		preparedStatement.setString(9, provisionalTerm.getSynonyms());
		preparedStatement.setString(10, provisionalTerm.getSource());
		preparedStatement.setString(11, provisionalTerm.getTermType());
		preparedStatement.setString(12, provisionalTerm.getTermCategory());
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
	
	public List<ProvisionalTerm> getAll(String tableName, String where) throws SQLException {
		List<ProvisionalTerm> result = new ArrayList<ProvisionalTerm>();
		PreparedStatement preparedStatement = this.executeSQL("SELECT * FROM bioportal_" + tableName
				+ " " + where);
		ResultSet resultSet = preparedStatement.getResultSet();
		while(resultSet.next()) {
			result.add(new ProvisionalTerm(
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
		ProvisionalTerm provisionalTerm = new ProvisionalTerm();
		provisionalTerm.setTemporaryid("tempId");
		provisionalTerm.setDefinition("def");
		provisionalTerm.setTerm("name");
		provisionalTerm.setSubmittedby("submittedby");
		try {
			ProvisionalTermDAO.getInstance().addAwaitingAdoption(provisionalTerm);
			List<ProvisionalTerm> all = ProvisionalTermDAO.getInstance().getAllAwaitingAdoption();
			ProvisionalTermDAO.getInstance().storeAdopted(provisionalTerm);
			ProvisionalTermDAO.getInstance().deleteAwaitingAdoption(provisionalTerm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ProvisionalTerm getFirstAwaitingTerm() throws SQLException {
		this.openConnection();
		ProvisionalTerm result = getFirst("awaitingadoption");
		this.closeConnection();
		return result;
	}

	public ProvisionalTerm getFirstAdoptedTerm() throws SQLException {
		this.openConnection();
		ProvisionalTerm result = getFirst("adopted");
		this.closeConnection();
		return result;
	}
	
	public ProvisionalTerm getFirst(String tableName) throws SQLException {
		PreparedStatement preparedStatement = this.executeSQL("SELECT * FROM bioportal_" + tableName + " " +
				"ORDER BY localId");
		ResultSet resultSet = preparedStatement.getResultSet();
		if(!resultSet.next())
			return null;
		return new ProvisionalTerm(
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

	public void updateAwaitingAdoption(ProvisionalTerm provisionalTerm) throws SQLException {
		this.openConnection();		
		PreparedStatement preparedStatement = this.prepareStatement("UPDATE bioportal_awaitingadoption SET " +
				"preferredName = ?, definition = ?, superClass = ?, synonyms = ?, ontologyIds = ?, source = ? WHERE localId = ?");
		preparedStatement.setString(1, provisionalTerm.getTerm());
		preparedStatement.setString(2, provisionalTerm.getDefinition());
		preparedStatement.setString(3, provisionalTerm.getSuperclass());
		preparedStatement.setString(4, provisionalTerm.getSynonyms());
		preparedStatement.setString(5, provisionalTerm.getOntologyids());
		preparedStatement.setString(6, provisionalTerm.getSource());
		preparedStatement.setString(7, provisionalTerm.getLocalId());
		preparedStatement.executeUpdate();
		
		this.closeConnection();
	}

	public ProvisionalTerm getAdopted(String localId) throws SQLException {
		this.openConnection();
		ProvisionalTerm result = getTerm("adopted", localId);
		this.closeConnection();
		return result;
	}

	public ProvisionalTerm getAwaitingAdoption(String localId) throws SQLException {
		this.openConnection();
		ProvisionalTerm result = getTerm("awaitingadoption", localId);
		this.closeConnection();
		return result;
	}
	
	public ProvisionalTerm getTerm(String tableName, String localId) throws SQLException {
		PreparedStatement preparedStatement = this.executeSQL("SELECT * FROM bioportal_" + tableName + " " +
				"WHERE localId = " + localId);
		ResultSet resultSet = preparedStatement.getResultSet();
		resultSet.next();
		return new ProvisionalTerm(
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
