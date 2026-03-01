 
# LAB 8 – Consommer un Web Service PHP 8 depuis une application Android avec Volley

Dans ce laboratoire, nous avons réalisé une application Android capable de consommer un Web Service développé en PHP 8.
L’objectif principal est de comprendre le fonctionnement des services web REST et la communication entre une application mobile et un serveur distant.

Le Web Service PHP expose des données au format JSON, tandis que l’application Android utilise la bibliothèque Volley pour envoyer des requêtes HTTP (GET, POST, PUT, DELETE) et récupérer les réponses.

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

![](https://github.com/user-attachments/assets/18b91efb-cc3d-4b87-acab-61fd5ebe5333)

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

![](https://github.com/user-attachments/assets/f3099df7-2f8a-42a7-beab-d7236390c982)

# Récupérer la liste :

URL : http://localhost/projet/ws/loadEtudiant.php

Méthode : GET

![](https://github.com/user-attachments/assets/8cc1934d-81b9-471e-b620-d2153a8fa466)

Résultat : JSON contenant tous les étudiants avec l'ajout de nouveau etudiant .

- le nouvel enregistrement a bien été ajouté dans phpMyadmin 

![](https://github.com/user-attachments/assets/a6d2eb76-d112-4148-a663-03d66d1a7f24)

# Partie 3 : Application Android (Volley + Gson)

- Objectif

Développer une application Android permettant d’ajouter un étudiant via un service web PHP en utilisant :

- Volley pour les requêtes HTTP

- Gson pour la conversion JSON → Objet Java

 # Étape 1 — Création du projet Android

![](https://github.com/user-attachments/assets/328c3f8c-2505-452f-9e45-e2f6dd36cf14)

 # Étape 2 — Permission Internet

- Ajout de la permission Internet dans le fichier AndroidManifest.xml.
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Projetws"
        android:networkSecurityConfig="@xml/network_security_config"
        android:usesCleartextTraffic="true">
        <activity
            android:name=".AddEtudiant"
            android:exported="false" />
        <activity
            android:name=".ListEtudiant"
            android:exported="false" />
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>

# Étape 3 — Intégration de Volley et Gson

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.projetws"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.projetws"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("com.android.volley:volley:1.2.1")
    implementation("com.google.code.gson:gson:2.10.1")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

# Étape 4 — Création de l’activité AddEtudiant

![](https://github.com/user-attachments/assets/98f3811b-6e2f-494f-acff-55e63821098f)
Les résultats sont affichés dans Logcat
![](https://github.com/user-attachments/assets/7872ec52-14bc-4686-8cac-e7a76d4e517b)

#  Test et vérification

-Insertion d’un étudiant réussie
![](https://github.com/user-attachments/assets/f38b7949-6948-464b-a144-743c01360524)







