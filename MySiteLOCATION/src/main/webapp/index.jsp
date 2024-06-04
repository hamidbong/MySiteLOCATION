<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Model.Bien" %>
<%@ page import="java.util.List" %>
<%
	if(session.getAttribute("id_user")==null){
		System.out.println("le session.getAttribute: "+session.getAttribute("id_user"));
		response.sendRedirect("login.jsp");
	}
	else {
		System.out.println("le session.getAttribute: "+session.getAttribute("id_user"));
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
    <title>Agence de Location</title>
    <!-- Lien vers Font Awesome pour les icônes -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        /* Style pour la navbar */
        .navbar {
            /*background-color: #333;*/
            background-color: #007bff;
            overflow: hidden;
        }

        /* Style pour le logo */
        .logo {
            float: left;
            margin: 0;
            padding: 20px;
        }

        /* Style pour la barre de recherche */
        .search-bar {
            float: left;
            margin: 0;
            padding: 20px;
            font-size: 20px;
        }

        /* Style pour l'icône de profil utilisateur */
        .user-profile {
            float: right;
            margin: 0;
            padding: 20px;
        }

        /* Style pour l'icône du panier d'achat */
        .cart {
            float: right;
            margin: 0;
            padding: 20px;
        }
        .logout {
       	 	float: right;
            margin: 0;
            padding: 20px;
        	
        }

        /* Style pour les liens dans la navbar */
        .navbar a {
            color: white;
            text-decoration: none;
            font-size: 25px;
        }

       
        /*newwwwwwww*/
        .container {
            max-width: 1000px;
            block-size: 330px;
            margin: 60px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .container a {
        	text-decoration: none;
        	font-size: 25px;
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
            background-color: #ccc;*/
           
        }
        
        
        
        footer {
        background-color: #f8f8f8;
    	padding: 20px 0;
    	text-align: center;
    	margin-top: 20px; /* Ajoute un espace entre le contenu et le pied de page */
    	}

		footer p {
    	margin: 0;
    	font-size: 14px;
    	color: #333;
		}
    </style>
</head>
<body>

	<nav class="navbar">
	    <div class="logo">
	        <a href="#"><i class="fas fa-home"></i> Accueil</a>
	    </div>
	    <div class="search-bar">
	        <form action="rechercher.jsp" method="GET">
	            <input type="text" name="query" placeholder="Rechercher...">
	            <button type="submit"><i class="fas fa-search"></i></button>
	        </form>
	    </div>
	    <div class="logout">
        	<a href="logout.jsp"><i class="fas fa-sign-out-alt"></i> Déconnexion</a>
    	</div>
	    
	    
	    <div class="user-profile">
	        <a href="profil.jsp"><i class="fas fa-user"></i></a>
	    </div>
	    <div class="cart">
	        <a href="panier.jsp"><i class="fas fa-shopping-cart"></i></a>
	    </div>
	</nav>
	<div class="container">
        <h1>Page d'Agent de Location</h1>
        <div class="options">
             <div class="option"><a href="CatalogServlet" class="ess">Catalogue de Produits</a></div>
             <div class="option"><a href="GestionReservation">Gestion des Réservations</a></div>
             <div class="option"><a href="UserServlet">Gestion des Utilisateurs</a></div>
        </div>
    </div>

    
	
	
	
	
	<!-- Assurez-vous d'inclure Font Awesome dans votre projet -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script>
	<footer>
		<div class="contain">
	    	<p>&copy; 2024 Agence de Location. Tous droits réservés.</p>
	    </div>
	</footer>
</body>
</html>
