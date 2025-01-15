package main.java.dao;

import main.java.models.Emprunt;
import main.java.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class EmpruntDAO {

    private Connection connection;

    public EmpruntDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Ajouter un emprunt
    public void ajouterEmprunt(Emprunt emprunt) {
        String sql = "INSERT INTO Emprunt (idMembre, idLivre, dateEmprunt, dateRetourPrevue) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, emprunt.getMembreId());
            stmt.setInt(2, emprunt.getLivreId());
            stmt.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
            stmt.setDate(4, Date.valueOf(emprunt.getDateRetourPrevue()));
            stmt.executeUpdate();
            System.out.println("Emprunt ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mettre à jour la date de retour d'un emprunt
    public void retournerLivre(int idEmprunt, LocalDate dateRetourEffective) {
        String sqlUpdate = "UPDATE Emprunt SET dateRetourEffective = ?, penalite = ? WHERE id = ?";
        String sqlSelect = "SELECT dateRetourPrevue FROM Emprunt WHERE id = ?";
        double montantParJour = 5.0; // Exemple de montant par jour de retard

        try (PreparedStatement selectStmt = connection.prepareStatement(sqlSelect);
             PreparedStatement updateStmt = connection.prepareStatement(sqlUpdate)) {

            // Récupérer la date de retour prévue
            selectStmt.setInt(1, idEmprunt);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                LocalDate dateRetourPrevue = rs.getDate("dateRetourPrevue").toLocalDate();

                // Calculer les pénalités
                double penalite = 0.0;
                if (dateRetourEffective.isAfter(dateRetourPrevue)) {
                    long joursDeRetard = java.time.temporal.ChronoUnit.DAYS.between(dateRetourPrevue, dateRetourEffective);
                    penalite = joursDeRetard * montantParJour;
                }

                // Mettre à jour la base de données avec la date de retour effective et la pénalité
                updateStmt.setDate(1, Date.valueOf(dateRetourEffective));
                updateStmt.setDouble(2, penalite);
                updateStmt.setInt(3, idEmprunt);
                updateStmt.executeUpdate();

                // Afficher le résultat à l'utilisateur
                if (penalite > 0) {
                    System.out.println("Livre retourné avec succès ! Vous avez une pénalité de " + penalite + " à payer.");
                } else {
                    System.out.println("Livre retourné avec succès ! Aucune pénalité à payer.");
                }
            } else {
                System.out.println("Aucun emprunt trouvé avec cet ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Rechercher les emprunts actifs par membre
    public List<Emprunt> rechercherEmpruntsParMembre(int idMembre) {
        String sql = "SELECT * FROM Emprunt WHERE idMembre = ? AND dateRetourEffective IS NULL";
        List<Emprunt> emprunts = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idMembre);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                emprunts.add(new Emprunt(
                        rs.getInt("idEmprunt"),
                        rs.getInt("MembreId"),
                        rs.getInt("LivreId"),
                        rs.getDate("dateEmprunt").toLocalDate(),
                        rs.getDate("dateRetourPrevue").toLocalDate(),
                        rs.getDate("dateRetourEffective") != null ? rs.getDate("dateRetourEffective").toLocalDate() : null,
                rs.getDouble("penalite")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprunts;
    }

    public List<Emprunt> afficherTousLesEmprunts() {
        List<Emprunt> emprunts = new ArrayList<>();
        String sql = "SELECT * FROM Emprunt";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Emprunt emprunt = new Emprunt(
                rs.getInt("idEmprunt"), rs.getInt("membreId"), rs.getInt("livreId"), rs.getDate("dateEmprunt").toLocalDate(), rs.getDate("dateRetourPrevue").toLocalDate(), rs.getDate("dateRetourEffective") != null ?
                rs.getDate("dateRetourEffective").toLocalDate() : null, rs.getDouble("penalite")
                );
                emprunts.add(emprunt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprunts;
    }

}
