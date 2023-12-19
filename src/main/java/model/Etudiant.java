package model;

import java.util.*;

public class Etudiant {
	private int idEtudiant;
	private String nom;
    private String prenom;
    private Date dateDeNaissance;
    private int idDepartement;
    
    public Etudiant(int idEtudiant, String nom, String prenom, Date dateDeNaissance2, int idDepartement) {
		super();
		this.idEtudiant = idEtudiant;
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance2;
		this.idDepartement = idDepartement;
	}
    
    
    public int getIdEtudiant() {
		return idEtudiant;
	}
	public void setIdEtudiant(int idEtudiant) {
		this.idEtudiant = idEtudiant;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}
	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}
	public int getIdDepartement() {
		return idDepartement;
	}
	public void setIdDepartement(int idDepartement) {
		this.idDepartement = idDepartement;
	}

}