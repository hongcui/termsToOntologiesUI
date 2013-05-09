package ui.db;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.AbstractDAO;

import bioportal.beans.ProvisionalTerm;

public class UnadoptedTermDAO extends AbstractDAO implements IUnadoptedTermDAO {

	private static UnadoptedTermDAO instance;

	private UnadoptedTermDAO() throws ClassNotFoundException, IOException { }
	
	public static UnadoptedTermDAO getInstance() throws ClassNotFoundException, IOException {
		if(instance == null)
			instance = new UnadoptedTermDAO();
		return instance;
	}
	
	public List<ProvisionalTerm> getUnadoptedStructureTerms() throws SQLException {
		List<ProvisionalTerm> result = new ArrayList<ProvisionalTerm>();
		this.openConnection();
		
		String sql = "SELECT * FROM dummynewterms WHERE sentMarker = 0 AND termType = 'structure'";
		PreparedStatement preparedStatement = this.executeSQL(sql);
		ResultSet resultSet = preparedStatement.getResultSet();
		while(resultSet.next()) {
			result.add(new ProvisionalTerm(
						resultSet.getString("localId"),
						resultSet.getString("term"),
						resultSet.getString("termType"),
						resultSet.getString("termCategory"),
						"",
						"",
						"", 
						"",
						"",
						"", 
						"", 
						resultSet.getString("source")
					));
		}
		
		this.closeConnection();		
		return result;
	}
	
	public List<ProvisionalTerm> getUnadoptedCharacterTerms() throws SQLException {
		List<ProvisionalTerm> result = new ArrayList<ProvisionalTerm>();
		this.openConnection();
		
		String sql = "SELECT * FROM dummynewterms WHERE sentMarker = 0 AND termType = 'character'";
		PreparedStatement preparedStatement = this.executeSQL(sql);
		ResultSet resultSet = preparedStatement.getResultSet();
		while(resultSet.next()) {
			result.add(new ProvisionalTerm(
						resultSet.getString("localId"),
						resultSet.getString("term"),
						resultSet.getString("termType"),
						resultSet.getString("termCategory"),
						"",
						"",
						"", 
						"",
						"",
						"", 
						"", 
						resultSet.getString("source")
					));
		}
		
		this.closeConnection();
		return result;
	}

	public ProvisionalTerm getUnadoptedTerm(String localId) throws SQLException {
		ProvisionalTerm result = null;
		this.openConnection();
		String sql = "SELECT * FROM dummynewterms WHERE localId = " + localId;
		PreparedStatement preparedStatement = this.executeSQL(sql);
		ResultSet resultSet = preparedStatement.getResultSet();
		resultSet.next();
		result = new ProvisionalTerm(
				resultSet.getString("localId"),
				resultSet.getString("term"),
				resultSet.getString("termType"),
				resultSet.getString("termCategory"),
				"",
				"",
				"", 
				"",
				"",
				"", 
				"", 
				resultSet.getString("source")
			);
		this.closeConnection();
		return result;
	}

	public void createDummyData() throws SQLException {
		createTable();
		insertDummyData();
	}
	
	

	private void insertDummyData() throws SQLException {
		this.openConnection();
		String sql = "INSERT INTO `dummynewterms` (`localId`, `term`, `termType`, `termCategory`, `source`, `sentMarker`) VALUES" +
				"(0, 'blue', 'character', 'coloration', 'the leafs are blue and long', 0), (1, 'stem', 'structure', '', 'stems wider than long', 0);";
		this.executeSQL(sql);
		this.closeConnection();
	}

	private void createTable() throws SQLException {
		this.openConnection();
		String sql = "CREATE TABLE IF NOT EXISTS `dummynewterms` (" +
				" `localId` int(11) NOT NULL," +
				" `term` varchar(100) NOT NULL," +
				" `termType` varchar(100) NOT NULL," +
				" `termCategory` varchar(100) NOT NULL," +
				" `source` varchar(100) NOT NULL," +
				" `sentMarker` tinyint(1) NOT NULL," + 
				"  PRIMARY KEY (`localId`))";
		this.executeSQL(sql);
		this.closeConnection();
	}

	public ProvisionalTerm getFirstUnadoptedTerm() throws SQLException {
		ProvisionalTerm result = null;
		this.openConnection();
		String sql = "SELECT * FROM dummynewterms WHERE sentMarker = 0 ORDER BY localId";
		PreparedStatement preparedStatement = this.executeSQL(sql);
		ResultSet resultSet = preparedStatement.getResultSet();
		resultSet.next();
		result = new ProvisionalTerm(
				resultSet.getString("localId"),
				resultSet.getString("term"),
				resultSet.getString("termType"),
				resultSet.getString("termCategory"),
				"",
				"",
				"", 
				"",
				"",
				"", 
				"", 
				resultSet.getString("source")
			);
		this.closeConnection();
		return result;
	}

	@Override
	public void markNotSent(String localId) throws SQLException {
		this.openConnection();
		String sql = "UPDATE dummynewterms SET sentMarker = 0 WHERE localId = " + localId;
		PreparedStatement preparedStatement = this.executeSQL(sql);
		ResultSet resultSet = preparedStatement.getResultSet();
		this.closeConnection();
	}

	@Override
	public void markSent(String localId) throws SQLException {
		this.openConnection();
		String sql = "UPDATE dummynewterms SET sentMarker = 1 WHERE localId = " + localId;
		PreparedStatement preparedStatement = this.executeSQL(sql);
		ResultSet resultSet = preparedStatement.getResultSet();
		this.closeConnection();
	}
}
