package db;

import java.util.ArrayList;
import java.util.List;

import beans.TermDescription;

public class UnadoptedTermDAO {

	private static UnadoptedTermDAO instance;

	private UnadoptedTermDAO() throws Exception { }
	
	public static UnadoptedTermDAO getInstance() throws Exception {
		if(instance == null)
			instance = new UnadoptedTermDAO();
		return instance;
	}
	
	public List<TermDescription> getUnadoptedStructureTerms() {
		List<TermDescription> result = new ArrayList<TermDescription>();
		result.add(new TermDescription("structureA", "def1", "", "", "", ""));
		result.add(new TermDescription("structureB", "def2", "", "", "", ""));
		result.add(new TermDescription("structureC", "def3", "", "", "", ""));
		result.add(new TermDescription("structureD", "def4", "", "", "", ""));
		result.add(new TermDescription("structureE", "def5", "", "", "", ""));
		result.add(new TermDescription("structureF", "def6", "", "", "", ""));
		
		return result;
	}
	
	public List<TermDescription> getUnadoptedCharacterTerms() {
		List<TermDescription> result = new ArrayList<TermDescription>();
		result.add(new TermDescription("characterA", "def1", "", "", "", ""));
		result.add(new TermDescription("characterB", "def2", "", "", "", ""));
		result.add(new TermDescription("characterC", "def3", "", "", "", ""));
		result.add(new TermDescription("characterD", "def4", "", "", "", ""));
		result.add(new TermDescription("characterE", "def5", "", "", "", ""));
		result.add(new TermDescription("characterG", "def6", "", "", "", ""));
		return result;
	}
}
