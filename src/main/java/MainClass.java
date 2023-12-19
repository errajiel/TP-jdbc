import java.util.List;

import DAO.EtudiantDao;
import model.Etudiant;
import DAOimplementation.*;

public class MainClass {

	public static void main(String[] args) {
        EtudiantDao etudiantDao = new EtudiantDaoImpl();

        // Fetch all etudiants from the database
        List<Etudiant> etudiants = etudiantDao.findAll();

        // Display the etudiants
        for (Etudiant etudiant : etudiants) {
            System.out.println("ID: " + etudiant.getIdEtudiant() + ", Nom: " + etudiant.getNom() 
                + ", Prénom: " + etudiant.getPrenom() + ", Date de Naissance: " 
                + etudiant.getDateDeNaissance() + ", ID Département: " + etudiant.getIdDepartement());
        }
    }

}
