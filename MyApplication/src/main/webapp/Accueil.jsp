<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil - Agence de Location</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header>
        <h1>Agence de Location</h1>
        <nav>
            <a href="#catalogue">Catalogue de Biens</a>
            <a href="#recherche">Recherche Avanc�e</a>
            <a href="#reservation">R�servation en Ligne</a>
            <a href="#gestion">Gestion des R�servations</a>
        </nav>
    </header>
    <div class="container">
        <section id="catalogue" class="section">
            <h2>Catalogue de Biens</h2>
            <div id="catalogue-biens">
                <!-- Contenu du catalogue de biens g�n�r� dynamiquement par JavaScript -->
            </div>
        </section>
        <section id="recherche" class="section">
            <h2>Recherche Avanc�e</h2>
            <form id="recherche-form">
                <!-- Formulaire de recherche avanc�e -->
            </form>
        </section>
    </div>
    <footer>
        <div class="container">
            <p>&copy; 2024 Agence de Location. Tous droits r�serv�s.</p>
        </div>
    </footer>

    <script src="script.js"></script>
</body>
</html>
