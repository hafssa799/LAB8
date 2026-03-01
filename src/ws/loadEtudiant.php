<?php
// Affichage des erreurs pour debug
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

// Inclure le service EtudiantService et la classe Etudiant
include_once '../../service/EtudiantService.php';
include_once '../../classes/Etudiant.php';

// Créer une instance du service
$es = new EtudiantService();

// Retourner tous les étudiants en JSON
header('Content-Type: application/json');
echo json_encode($es->findAllApi());
?>