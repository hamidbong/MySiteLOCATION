<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page d'Agent de Location</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
        }
        .options {
            display: flex;
            justify-content: space-around;
            margin-top: 30px;
        }
        .option {
            text-align: center;
            padding: 20px;
            background-color: #e0e0e0;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .option:hover {
            background-color: #ccc;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Page d'Agent de Location</h1>
        <div class="options">
            <div class="option" onclick="navigateToCatalog()">Catalogue de Produits</div>
            <div class="option" onclick="navigateToReservations()">Gestion des Réservations</div>
            <div class="option" onclick="navigateToUsers()">Gestion des Utilisateurs</div>
        </div>
    </div>

    <script>
        function navigateToCatalog() {
            window.location.href = "catalogue.jsp";
        }

        function navigateToReservations() {
            window.location.href = "reservations.jsp";
        }

        function navigateToUsers() {
            window.location.href = "utilisateurs.jsp";
        }
    </script>
</body>
</html>
