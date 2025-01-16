package main.java.dao;

import main.java.models.Livre;
import main.java.utils.DatabaseConnection;
import java.sql.*;
import java.util.*;

public class LivreDAO {

    private Connection connection;

    public LivreDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Ajouter un livre
    public void ajouterLivre(Livre livre) {
        String sql = "INSERT INTO Livre (titre, auteur, categorie, nombreExemplaires) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, livre.getTitre());
            stmt.setString(2, livre.getAuteur());
            stmt.setString(3, livre.getCategorie());
            stmt.setInt(4, livre.getNombreExemplaires());
            stmt.executeUpdate();
            System.out.println("Livre ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Rechercher un livre par titre
    public List<Livre> rechercherLivreParTitre(String titre) {
        String sql = "SELECT * FROM Livre WHERE titre ILIKE ?";
        List<Livre> livres = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, titre);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                livres.add(new Livre(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("categorie"),
                        rs.getInt("nombreExemplaires")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }

    // Afficher tous les livres
    public List<Livre> afficherTousLesLivres() {
        String sql = "SELECT * FROM Livre";
        List<Livre> livres = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                livres.add(new Livre(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("categorie"),
                        rs.getInt("nombreExemplaires")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }

    public boolean supprimerLivre(int idLivre) {
        String sqlCheckEmprunt = "SELECT COUNT(*) FROM Emprunt WHERE livreId = ?";
        String sqlDeleteLivre = "DELETE FROM Livre WHERE Id = ?";

        try {
            PreparedStatement checkStmt = connection.prepareStatement(sqlCheckEmprunt);
            PreparedStatement deleteStmt = connection.prepareStatement(sqlDeleteLivre);

            // Vérifie si le livre est utilisé dans un emprunt
            checkStmt.setInt(1, idLivre);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Le livre ne peut pas être supprimé car il est associé à un emprunt.");
                return false;
            }

            // Supprime le livre
            deleteStmt.setInt(1, idLivre);
            int rowsAffected = deleteStmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livre supprimé avec succès.");
                return true;
            } else {
                System.out.println("Aucun livre trouvé avec cet identifiant.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
