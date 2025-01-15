package main.java;

import main.java.dao.*;
import main.java.models.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MembreDAO membreDAO = new MembreDAO();
        LivreDAO livreDAO = new LivreDAO();
        EmpruntDAO empruntDAO = new EmpruntDAO();

        int idUtilisateurActuel = -1; // Stocke l'ID de l'utilisateur connecté

        // Connexion ou inscription de l'utilisateur
        while (idUtilisateurActuel == -1) {
            System.out.println("\n--- Bienvenue sur le programmme de gestion de bibliothèque ! ---");
            System.out.println("1. Se connecter");
            System.out.println("2. S'inscrire comme membre");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le saut de ligne

            switch (choix) {
                case 1:
                    // Connexion
                    System.out.print("Entrez votre ID : ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consommer le saut de ligne
                    String nomMembre = membreDAO.verifierMembre(id);
                    if (nomMembre != null) {
                        idUtilisateurActuel = id;
                        System.out.println("Connexion réussie ! Bonjour, " + nomMembre + " !");
                    } else {
                        System.out.println("ID invalide. Veuillez réessayer.");
                    }
                    break;


                case 2:
                    // Inscription
                    System.out.print("Entrez votre nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrez votre prénom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Entrez votre email : ");
                    String email = scanner.nextLine();
                    Membre membre = new Membre(nom, prenom, email);
                    int nouvelId = membreDAO.ajouterMembre(membre);
                    if (nouvelId != -1) {
                        idUtilisateurActuel = nouvelId;
                        System.out.println("Inscription réussie ! Votre ID est : " + nouvelId);
                    } else {
                        System.out.println("Erreur lors de l'inscription.");
                    }
                    break;
                case 3:
                    System.out.println("Au revoir !");
                    return;

                default:
                    System.out.println("Option invalide !");
                    break;
            }
        }

        // Menu principal après connexion ou inscription
        while (true) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Gestion des membres");
            System.out.println("2. Gestion des livres");
            System.out.println("3. Gestion des emprunts");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le saut de ligne

            switch (choix) {
                case 1:
                    // Menu de gestion des membres
                    gererMembres(scanner, membreDAO);
                    break;

                case 2:
                    // Menu de gestion des livres
                    gererLivres(scanner, livreDAO);
                    break;

                case 3:
                    // Menu de gestion des emprunts
                    gererEmprunts(scanner, empruntDAO);
                    break;

                case 4:
                    System.out.println("Au revoir !");
                    return;

                default:
                    System.out.println("Option invalide !");
            }
        }
    }

    // Méthode pour gérer les membres
    private static void gererMembres(Scanner scanner, MembreDAO membreDAO) {
        while (true) {
            System.out.println("\n--- Menu de Gestion des Membres ---");
            System.out.println("1. Ajouter un membre");
            System.out.println("2. Rechercher un membre");
            System.out.println("3. Supprimer un membre");
            System.out.println("4. Retour au menu principal");
            System.out.print("Choisissez une option : ");
            int choixMembre = scanner.nextInt();
            scanner.nextLine(); // Consommer le saut de ligne

            switch (choixMembre) {
                case 1:
                    // Ajouter un membre
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Email : ");
                    String email = scanner.nextLine();
                    Membre membre = new Membre(nom, prenom, email);
                    int id = membreDAO.ajouterMembre(membre);
                    break;

                case 2:
                    // Rechercher un membre
                    System.out.print("Entrez le nom du membre à rechercher : ");
                    String rechercheNom = scanner.nextLine();
                    List<Membre> membres = membreDAO.rechercherMembreParNom(rechercheNom);
                    if (membres.isEmpty()) {
                        System.out.println("Aucun membre trouvé.");
                    } else {
                        for (Membre m : membres) {
                            System.out.println(m);
                        }
                    }
                    break;

                case 3:
                    // Supprimer un membre
                    System.out.print("Entrez l'ID du membre à supprimer : ");
                    int idMembre = scanner.nextInt();
                    membreDAO.supprimerMembre(idMembre);
                    break;

                case 4:
                    // Retour au menu principal
                    return;

                default:
                    System.out.println("Option invalide !");
            }
        }
    }

    // Méthode pour gérer les livres
    private static void gererLivres(Scanner scanner, LivreDAO livreDAO) {
        while (true) {
            System.out.println("\n--- Menu de Gestion des Livres ---");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Rechercher un livre par titre");
            System.out.println("3. Afficher tous les livres");
            System.out.println("4. Retour au menu principal");
            System.out.print("Choisissez une option : ");
            int choixLivre = scanner.nextInt();
            scanner.nextLine(); // Consommer le saut de ligne

            switch (choixLivre) {
                case 1:
                    // Ajouter un livre
                    System.out.print("Titre : ");
                    String titre = scanner.nextLine();
                    System.out.print("Auteur : ");
                    String auteur = scanner.nextLine();
                    System.out.print("Catégorie : ");
                    String categorie = scanner.nextLine();
                    System.out.print("Nombre d'exemplaires : ");
                    int nombreExemplaires = scanner.nextInt();

                    Livre livre = new Livre(titre, auteur, categorie, nombreExemplaires);
                    livreDAO.ajouterLivre(livre);
                    break;

                case 2:
                    // Rechercher un livre
                    System.out.print("Entrez le titre à rechercher : ");
                    String titreRecherche = scanner.nextLine();
                    List<Livre> livres = livreDAO.rechercherLivreParTitre(titreRecherche);
                    if (livres.isEmpty()) {
                        System.out.println("Aucun livre trouvé.");
                    } else {
                        for (Livre l : livres) {
                            System.out.println(l);
                        }
                    }
                    break;

                case 3:
                    // Afficher tous les livres
                    List<Livre> tousLesLivres = livreDAO.afficherTousLesLivres();
                    for (Livre l : tousLesLivres) {
                        System.out.println(l);
                    }
                    break;

                case 4:
                    // Retour au menu principal
                    return;

                default:
                    System.out.println("Option invalide !");
            }
        }
    }

    // Méthodes pour gérer les emprunts
    private static void gererEmprunts(Scanner scanner, EmpruntDAO empruntDAO) {
        while (true) {
            System.out.println("\n--- Menu de Gestion des Emprunts ---");
            System.out.println("1. Ajouter un emprunt");
            System.out.println("2. Retourner un livre");
            System.out.println("3. Rechercher les emprunts d'un membre");
            System.out.println("4. Afficher tous les emprunts");
            System.out.println("5. Retour au menu principal");
            System.out.print("Choisissez une option : ");
            int choixEmprunt = scanner.nextInt();
            scanner.nextLine(); // Consommer le saut de ligne

            switch (choixEmprunt) {
                case 1:
                    System.out.print("ID du membre : ");
                    int idMembre = scanner.nextInt();
                    System.out.print("ID du livre : ");
                    int idLivre = scanner.nextInt();
                    scanner.nextLine(); // Consommer le saut de ligne
                    Emprunt emprunt = new Emprunt(idMembre, idLivre);
                    empruntDAO.ajouterEmprunt(emprunt);
                    break;

                case 2:
                    System.out.print("ID de l'emprunt : ");
                    int idEmprunt = scanner.nextInt();
                    scanner.nextLine(); // Consommer le saut de ligne
                    LocalDate dateRetourEffective = LocalDate.now();
                    empruntDAO.retournerLivre(idEmprunt, dateRetourEffective);
                    break;

                case 3:
                    System.out.print("ID du membre : ");
                    int membreId = scanner.nextInt();
                    List<Emprunt> emprunts = empruntDAO.rechercherEmpruntsParMembre(membreId);
                    if (emprunts.isEmpty()) {
                        System.out.println("Aucun emprunt trouvé.");
                    } else {
                        for (Emprunt e : emprunts) {
                            System.out.println(e);
                        }
                    }
                    break;

                case 4:
                    List<Emprunt> tousLesEmprunts = empruntDAO.afficherTousLesEmprunts();
                    for (Emprunt e : tousLesEmprunts) {
                        System.out.println(e);
                    }
                    break;

                case 5:
                    // Retour au menu principal
                    return;

                default:
                    System.out.println("Option invalide !");
            }
        }
    }
}
