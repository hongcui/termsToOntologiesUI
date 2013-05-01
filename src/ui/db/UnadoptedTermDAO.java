package ui.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.AbstractDAO;

import bioportal.beans.ProvisionalTerm;

public class UnadoptedTermDAO extends AbstractDAO {

	private static UnadoptedTermDAO instance;

	private UnadoptedTermDAO() throws Exception { }
	
	public static UnadoptedTermDAO getInstance() throws Exception {
		if(instance == null)
			instance = new UnadoptedTermDAO();
		return instance;
	}
	
	public List<ProvisionalTerm> getUnadoptedStructureTerms() throws SQLException {
		List<ProvisionalTerm> result = new ArrayList<ProvisionalTerm>();
		this.openConnection();
		
		String sql = "SELECT * FROM dummynewterms WHERE category = 'structure'";
		PreparedStatement preparedStatement = this.executeSQL(sql);
		ResultSet resultSet = preparedStatement.getResultSet();
		while(resultSet.next()) {
			result.add(new ProvisionalTerm(
						resultSet.getString("localId"),
						resultSet.getString("term"),
						"",
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
		
		String sql = "SELECT * FROM dummynewterms WHERE category = 'character'";
		PreparedStatement preparedStatement = this.executeSQL(sql);
		ResultSet resultSet = preparedStatement.getResultSet();
		while(resultSet.next()) {
			result.add(new ProvisionalTerm(
						resultSet.getString("localId"),
						resultSet.getString("term"),
						"",
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
		System.out.println(sql);
		PreparedStatement preparedStatement = this.executeSQL(sql);
		ResultSet resultSet = preparedStatement.getResultSet();
		resultSet.next();
		result = new ProvisionalTerm(
				resultSet.getString("localId"),
				resultSet.getString("term"),
				"",
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
}
