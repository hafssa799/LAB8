 
# LAB 8 – Consommer un Web Service PHP 8 depuis une application Android avec Volley

# Partie 1 : Création de la base de données MySQL :

- Objectif

L'objectif de cette étape est de créer une base de données MySQL et une table Etudiant pour stocker des informations de test, afin de préparer le web service PHP pour l’application Android.
  
# Étapes réalisées

1. Démarrage de XAMPP

   - Apache et MySQL activés via le panneau de contrôle XAMPP.

2. Accès à phpMyAdmin

   - Ouverture de l’URL : http://localhost/phpmyadmin

3. Création de la base de données school1.

Création de la table Etudiant avec les colonnes id, nom, prenom, ville, sexe.

![](https://github.com/user-attachments/assets/73a7983f-4e6b-4596-8b89-1280a3fec460)

![](https://github.com/user-attachments/assets/6f2892c5-afd1-4930-bf15-531125f3ddab)

![](https://github.com/user-attachments/assets/5e7e7eff-f84f-4d78-a37b-1562e322e013)

# Résultat attendu

- La base school1 contient la table Etudiant avec les données de test.

# Partie 2 : Développement du Web Service PHP 8

- Objectif

Créer un Web Service PHP 8 permettant :

- D’ajouter un étudiant dans la base MySQL (POST).

- De récupérer la liste des étudiants (GET).

# Étapes réalisées

1. Structure du projet

<img width="161" height="311" alt="image" src="https://github.com/user-attachments/assets/18b91efb-cc3d-4b87-acab-61fd5ebe5333" />

2. Classe de connexion PDO (connexion/Connexion.php)

-  à la base MySQL school1 avec PDO et gestion des erreurs.

3. Classe Etudiant (classes/Etudiant.php)

- Contient les attributs id, nom, prenom, ville, sexe.

- Getters et setters pour accéder aux données.
 
4. Interface IDao (dao/IDao.php)

- Définit les méthodes standards create, delete, update, findAll, findById.

5. Service EtudiantService (service/EtudiantService.php)

- Implémente IDao.

- Permet de créer un étudiant et de récupérer tous les étudiants au format tableau associatif (findAllApi).

6. VWeb Services PHP (ws/)

- createEtudiant.php : ajoute un étudiant via requête POST et renvoie la liste des étudiants en JSON.

- loadEtudiant.php : récupère tous les étudiants via requête GET et renvoie le JSON.

7. Tests avec Advanced REST Client (ARC)

# Ajouter un étudiant :

URL : http://localhost/projet/ws/createEtudiant.php

Méthode : POST

Body : x-www-form-urlencoded avec nom, prenom, ville, sexe

Résultat : JSON avec tous les étudiants, nouveau inclus.

# Récupérer la liste :

URL : http://localhost/projet/ws/loadEtudiant.php

Méthode : GET

# Résultat : JSON contenant tous les étudiants.


phpMyAdmin : vérification de l’insertion dans la table Etudiant
