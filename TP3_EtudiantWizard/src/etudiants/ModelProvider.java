package etudiants;

import java.util.ArrayList;
import java.util.List;

public enum ModelProvider {

	INSTANCE;
	
	private List<Etudiant> etudiants;
	
	private ModelProvider(){
		etudiants = new ArrayList<Etudiant>();
		etudiants.add(new Etudiant("Peter","Parker",true));
		etudiants.add(new Etudiant("Clark","Kent",true));
		etudiants.add(new Etudiant("Bruce","Wayne",false));
		etudiants.add(new Etudiant("Tony","Stark",true));
	}
	
	public List<Etudiant> getEtudiants(){
		return etudiants;
	}
}
