package etudiants;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Etudiant {

	public static String ADMIS_PROP = "admis";
	public static String PRENOM_PROP = "prenom";
	public static String NOM_PROP = "nom";

	private String prenom;
	private String nom;
	private boolean admis;

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public Etudiant() { 
		nom="Baron";
		prenom="Jonathan";
	}

	public Etudiant(String _prenom, String _nom){ 
		prenom = _prenom;
		nom = _nom;
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return prenom + " " + nom;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener){
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void addPropertyChangeListener(String propertyName,PropertyChangeListener listener){
		propertyChangeSupport.addPropertyChangeListener(propertyName,listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener){
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		propertyChangeSupport.firePropertyChange(PRENOM_PROP, this.prenom, this.prenom = prenom);
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		propertyChangeSupport.firePropertyChange(NOM_PROP, this.nom, this.nom = nom);
	}

	public boolean isAdmis() {
		return admis;
	}
	public void setAdmis(boolean isAdmis) {
		propertyChangeSupport.firePropertyChange(ADMIS_PROP, this.admis, this.admis = isAdmis);
	}
}
