<?php
include_once '../service/EtudiantService.php';
$es = new EtudiantService();
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Handle both form-data/x-www-form-urlencoded and JSON
    $data = $_POST;
    if (empty($data)) {
        $json = file_get_contents('php://input');
        $data = json_decode($json, true);
    }
    // DEBUG: Show received data (Temporary)
    // header('X-Debug-Data: ' . json_encode($data));
    // header('X-Debug-POST: ' . json_encode($_POST));
    if (!empty($data)) {
        $nom = isset($data['nom']) ? $data['nom'] : null;
        $prenom = isset($data['prenom']) ? $data['prenom'] : null;
        $ville = isset($data['ville']) ? $data['ville'] : null;
        $sexe = isset($data['sexe']) ? $data['sexe'] : null;
        if ($nom && $prenom) {
            $es->create(new Etudiant(0, $nom, $prenom, $ville, $sexe));
        }
    }
    header('Content-Type: application/json');
    echo json_encode($es->findAllApi());
}
?>