package ui.db;

import java.sql.SQLException;
import java.util.List;

import bioportal.beans.ProvisionalTerm;

public interface IUnadoptedTermDAO {
	
	public List<ProvisionalTerm> getUnadoptedStructureTerms() throws SQLException; 
	
	public List<ProvisionalTerm> getUnadoptedCharacterTerms() throws SQLException;	
	
	public ProvisionalTerm getUnadoptedTerm(String localId) throws SQLException;

	public ProvisionalTerm getFirstUnadoptedTerm() throws SQLException;

	public void markNotSent(String localId) throws SQLException;

	public void markSent(String localId) throws SQLException;
	
}
