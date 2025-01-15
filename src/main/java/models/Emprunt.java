package main.java.models;

import java.time.LocalDate;

public class Emprunt {
    private int idEmprunt;
    private int membreId;
    private int livreId;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;
    private double penalite;

    public Emprunt(int idEmprunt, int membreId, int livreId, LocalDate dateEmprunt, LocalDate dateRetourPrevue, LocalDate dateRetourEffective, double penalite) {
        this.idEmprunt = idEmprunt;
        this.membreId = membreId;
        this.livreId = livreId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourEffective = dateRetourEffective;
        this.penalite = penalite;
    }

    public Emprunt(int membreId, int livreId) {
        this.membreId = membreId;
        this.livreId = livreId;
        this.dateEmprunt = LocalDate.now();
        this.dateRetourPrevue = dateEmprunt.plusDays(1);
        this.dateRetourEffective = null;
        this.penalite = 0.0;
    }

    public int getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(int idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public int getMembreId() {
        return membreId;
    }

    public void setMembreId(int membreId) {
        this.membreId = membreId;
    }

    public int getLivreId() {
        return livreId;
    }

    public void setLivreId(int livreId) {
        this.livreId = livreId;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public LocalDate getDateRetourEffective() {
        return dateRetourEffective;
    }

    public void setDateRetourEffective(LocalDate dateRetourEffective) {
        this.dateRetourEffective = dateRetourEffective;
    }


    public double getPenalite() {
        return penalite;
    }

    public void setPenalite(double penalite) {
        this.penalite = penalite;
    }

    // Méthode pour calculer les pénalités
    public double calculerPenalite(LocalDate currentDate, double montantParJour) {
        LocalDate dateReference = (dateRetourEffective != null) ? dateRetourEffective : currentDate;

        if (dateReference.isAfter(dateRetourPrevue)) {
            long joursDeRetard = java.time.temporal.ChronoUnit.DAYS.between(dateRetourPrevue, dateReference);
            return joursDeRetard * montantParJour;
        }

        return 0.0;
    }

    @Override
    public String toString() {
        return "Emprunt [ID=" + idEmprunt + ", MembreID=" + membreId +
                ", LivreID=" + livreId + ", Date Emprunt=" + dateEmprunt +
                ", Date Retour Prévue=" + dateRetourPrevue +
                ", Date Retour Effective=" + (dateRetourEffective != null ? dateRetourEffective : "Pas encore retourné") + ", penalite=" + penalite + "]";
    }
}

