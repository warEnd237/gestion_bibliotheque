# Gestion d'une Bibliothèque

Ce projet est une application de gestion de bibliothèque développée en Java avec PostgreSQL comme base de données. Elle permet de suivre les livres, les membres et les emprunts tout en offrant une interface utilisateur simple et intuitive.

## Fonctionnalités Principales

### 1. Gestion des Livres

- Ajouter, modifier et supprimer des livres dans la bibliothèque.
- Rechercher un livre par titre, auteur ou catégorie.
- Afficher tous les livres disponibles.

### 2. Gestion des Membres

- Inscrire un nouveau membre.
- Supprimer un membre existant.
- Rechercher un membre par nom.
- Afficher un message de bienvenue personnalisé lors de la connexion d'un membre.

### 3. Gestion des Emprunts

- Enregistrer un emprunt de livre.
- Gérer les retours de livres.
- Calculer les pénalités en cas de retard (exemple : 100 F CFA par jour de retard).

### 4. Système de Recherche

- Rechercher des livres par :
  - Titre
  - Auteur
  - Catégorie

## Structure du Projet

### Modèle UML

Le projet est conçu en suivant les principes de la Programmation Orientée Objet (POO) et un diagramme UML des classes est inclus pour illustrer la structure du système. Les principales entités incluent :

- **Livre** : représente les livres de la bibliothèque.
- **Membre** : représente les membres inscrits.
- **Emprunt** : représente les emprunts de livres par les membres.
- **Personne** : classe parent pour les entités partagent des comportements communs.

### Technologies Utilisées

- **Langage** : Java
- **Base de données** : PostgreSQL
- **Connexion JDBC** : Utilisation de l'API JDBC pour interagir avec la base de données.
- **Framework UML** : PlantUML (pour la conception du diagramme).

## Prérequis

### 1. Installation

- **Java JDK 11 ou supérieur**
- **PostgreSQL**
- **Driver JDBC PostgreSQL** (téléchargeable depuis [le site officiel](https://jdbc.postgresql.org/))

### 2. Configuration de la Base de Données

- Créez une base de données PostgreSQL nommée `bibliotheque`.
- Exécutez les commandes SQL pour créer les tables requises :
  ```sql
  CREATE TABLE Livre (
      id SERIAL PRIMARY KEY,
      titre VARCHAR(255),
      auteur VARCHAR(255),
      categorie VARCHAR(100),
      nombreExemplaires INT
  );

  CREATE TABLE Membre (
      id SERIAL PRIMARY KEY,
      nom VARCHAR(100),
      prenom VARCHAR(100),
      email VARCHAR(255),
      adhesion_date DATE
  );

  CREATE TABLE Emprunt (
      idEmprunt SERIAL PRIMARY KEY,
      membreId INT REFERENCES Membre(id),
      livreId INT REFERENCES Livre(id),
      dateEmprunt DATE,
      dateRetourPrevue DATE,
      dateRetourEffective DATE
  );
  ```

## Lancement de l'Application

1. Clonez ce repository :
   ```bash
   git clone git@github.com:warEnd237/gestion_bibliotheque.git
   ```
2. Importez le projet dans un IDE Java (IntelliJ IDEA, Eclipse, ou autre).
3. Ajoutez le driver JDBC PostgreSQL au classpath du projet.
4. Configurez la connexion à la base de données dans la classe `DatabaseConnection` :
   ```java
   public class DatabaseConnection {
       private static final String URL = "jdbc:postgresql://localhost:5432/bibliotheque";
       private static final String USER = "votre_utilisateur";
       private static final String PASSWORD = "votre_mot_de_passe";

       public static Connection getConnection() throws SQLException {
           return DriverManager.getConnection(URL, USER, PASSWORD);
       }
   }
   ```
5. Exécutez la classe `Main` pour lancer l'application.

## Fonctionnalités Futures

- Ajout d'une interface graphique (JavaFX ou Swing).
- Gestion avancée des statistiques de la bibliothèque.
- Envoi de rappels par e-mail pour les retards.

## Contributeurs

- Kamgaing Bobda Joseph Warren

## Licence

Ce projet est distribué sous la licence MIT. Consultez le fichier LICENSE pour plus de détails.

