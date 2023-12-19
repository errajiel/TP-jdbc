package DAOimplementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.EtudiantDao;
import model.Etudiant;

public class EtudiantDaoImpl implements EtudiantDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/School";
    private String jdbcUsername = "root";
    private String jdbcPassword = "111";

    private static final String INSERT_ETUDIANTS_SQL = "INSERT INTO Étudiants" +
        "  (ID_Etudiant, Nom, Prénom, Date_de_naissance, ID_Departement) VALUES " +
        " (?, ?, ?, ?, ?);";

    private static final String SELECT_ETUDIANT_BY_ID = "select ID_Etudiant, Nom, Prénom, Date_de_naissance, ID_Departement from Étudiants where ID_Etudiant =?";
    private static final String SELECT_ALL_ETUDIANTS = "select * from Étudiants";
    private static final String DELETE_ETUDIANTS_SQL = "delete from Étudiants where ID_Etudiant = ?;";
    private static final String UPDATE_ETUDIANTS_SQL = "update Étudiants set Nom = ?, Prénom= ?, Date_de_naissance =?, ID_Departement =? where ID_Etudiant = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertEtudiant(Etudiant etudiant) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ETUDIANTS_SQL)) {
            preparedStatement.setInt(1, etudiant.getIdEtudiant());
            preparedStatement.setString(2, etudiant.getNom());
            preparedStatement.setString(3, etudiant.getPrenom());
            preparedStatement.setDate(4, new java.sql.Date(etudiant.getDateDeNaissance().getTime()));
            preparedStatement.setInt(5, etudiant.getIdDepartement());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Etudiant findEtudiant(int id) {
        Etudiant etudiant = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ETUDIANT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prénom");
                Date dateDeNaissance = rs.getDate("Date_de_naissance");
                int idDepartement = rs.getInt("ID_Departement");
                etudiant = new Etudiant(id, nom, prenom, dateDeNaissance, idDepartement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiant;
    }

    @Override
    public List<Etudiant> findAll() {
        List<Etudiant> etudiants = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ETUDIANTS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID_Etudiant");
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prénom");
                Date dateDeNaissance = rs.getDate("Date_de_naissance");
                int idDepartement = rs.getInt("ID_Departement");
                etudiants.add(new Etudiant(id, nom, prenom, dateDeNaissance, idDepartement));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiants;
    }

    @Override
    public void updateEtudiant(Etudiant etudiant) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ETUDIANTS_SQL)) {
            statement.setString(1, etudiant.getNom());
            statement.setString(2, etudiant.getPrenom());
            statement.setDate(3, new java.sql.Date(etudiant.getDateDeNaissance().getTime()));
            statement.setInt(4, etudiant.getIdDepartement());
            statement.setInt(5, etudiant.getIdEtudiant());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEtudiant(int id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ETUDIANTS_SQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
