package etudiants;

import java.util.ArrayList;
import java.util.List;

public enum ModelProvider {

	INSTANCE;
	
	private List<Etudiant> etudiants;
	
	private ModelProvider(){
		etudiants = new ArrayList<Etudiant>();
		etudiants.add(new Etudiant("Peter","Parker"));
		etudiants.add(new Etudiant("Clark","Kent"));
		etudiants.add(new Etudiant("Bruce","Wayne"));
		etudiants.add(new Etudiant("Tony","Stark"));
	}
	
	public List<Etudiant> getEtudiants(){
		return etudiants;
	}
}
