package DAO;

import java.util.List;

import model.Etudiant;

public interface EtudiantDao {
    void insertEtudiant(Etudiant etudiant);
    Etudiant findEtudiant(int id);
    List<Etudiant> findAll();
    void updateEtudiant(Etudiant etudiant);
    void deleteEtudiant(int id);
}