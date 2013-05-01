package ui.db;

import java.util.ArrayList;
import java.util.List;

import bioportal.beans.ProvisionalTerm;

public class UnadoptedTermDAO {

	private static UnadoptedTermDAO instance;

	private UnadoptedTermDAO() throws Exception { }
	
	public static UnadoptedTermDAO getInstance() throws Exception {
		if(instance == null)
			instance = new UnadoptedTermDAO();
		return instance;
	}
	
	public List<ProvisionalTerm> getUnadoptedStructureTerms() {
		List<ProvisionalTerm> result = new ArrayList<ProvisionalTerm>();
		result.add(new ProvisionalTerm("structureA", "def1", "", "", "", "", "", "", ""));
		result.add(new ProvisionalTerm("structureB", "def2", "", "", "", "", "", "", ""));
		result.add(new ProvisionalTerm("structureC", "def3", "", "", "", "", "", "", ""));
		result.add(new ProvisionalTerm("structureD", "def4", "", "", "", "", "", "", ""));
		result.add(new ProvisionalTerm("structureE", "def5", "", "", "", "", "", "", ""));
		result.add(new ProvisionalTerm("structureF", "def6", "", "", "", "", "", "", ""));
		
		return result;
	}
	
	public List<ProvisionalTerm> getUnadoptedCharacterTerms() {
		List<ProvisionalTerm> result = new ArrayList<ProvisionalTerm>();
		result.add(new ProvisionalTerm("characterA", "def1", "", "", "", "", "", "", ""));
		result.add(new ProvisionalTerm("characterB", "def2", "", "", "", "", "", "", ""));
		result.add(new ProvisionalTerm("characterC", "def3", "", "", "", "", "", "", ""));
		result.add(new ProvisionalTerm("characterD", "def4", "", "", "", "", "", "", ""));
		result.add(new ProvisionalTerm("characterE", "def5", "", "", "", "", "", "", ""));
		result.add(new ProvisionalTerm("characterG", "def6", "", "", "", "", "", "", ""));
		return result;
	}
}
