package main.java.dao;

import main.java.models.Membre;
import main.java.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class MembreDAO {

    private Connection connection;

    // Méthode pour obtenir une connexion à la base de données
    public MembreDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public String verifierMembre(int id) {
        String sql = "SELECT nom FROM Membre WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nom"); // Retourne le nom du membre
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retourne null si l'ID n'existe pas ou en cas d'erreur
    }


    // Ajouter un membre
    public int ajouterMembre(Membre membre) {
        String query = "INSERT INTO Membre (nom, prenom, email, adhesiondate) VALUES (?, ?, ?, ?) RETURNING id";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, membre.getNom());
            stmt.setString(2, membre.getPrenom());
            stmt.setString(3, membre.getEmail());
            stmt.setDate(4, Date.valueOf(membre.getAdhesionDate())); // Conversion de LocalDate en java.sql.Date
            ResultSet rs = stmt.executeQuery();

            // Récupérer l'ID généré
            if (rs.next()) {
                return rs.getInt("id"); // Retourne l'ID s
            } else {
                throw new SQLException("Aucun ID généré lors de l'ajout du membre.");
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retourner -1 en cas d'erreur
    }

    // Supprimer un membre
    public void supprimerMembre(int id) {
        String query = "DELETE FROM Membre WHERE id = ?";
        try {
            Connection conn = connection; PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Rechercher un membre par son nom
    public List<Membre> rechercherMembreParNom(String nom) {
        String query = "SELECT * FROM Membre WHERE nom LIKE ?";
        List<Membre> membres = new ArrayList<>();
        try {
            Connection conn = connection; PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String prenom = rs.getString("prenom");
                String vraiNom = rs.getString("nom");
                String email = rs.getString("email");
                LocalDate adhesionDate = rs.getDate("adhesionDate").toLocalDate(); // Conversion de java.sql.Date en LocalDate
                Membre membre = new Membre(id, vraiNom, prenom, email, adhesionDate);
                membres.add(membre);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return membres;
    }
}
