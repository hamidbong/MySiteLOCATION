<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion du Profil</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            font-weight: bold;
        }
        input[type="text"],
        input[type="email"],
        input[type="password"],
        button {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 16px;
        }
        button {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Gestion du Profil</h1>
        <form id="profileForm" action="UpdateProfileServlet" method="post">
        
        	<label for="ID">ID :</label><br>
            <input type="text" id="ID" name="ID" value="<%= session.getAttribute("id_user") %>" readonly><br>
            
            <label for="username">Nom :</label><br>
            <input type="text" id="username" name="username" ><br>

            <label for="fullname">Prenom :</label><br>
            <input type="text" id="prenom" name="prenom" ><br>

            <label for="email">Email :</label><br>
            <input type="email" id="email" name="email"><br>
            
            <label for="motdepasse">Mot de passe :</label><br>
            <input type="password" id="password" name="password" required><br>
            
            <label for="confirmation_motdepasse">Confirmation du mot de passe :</label><br>
            <input type="password" id="confirmation_motdepasse" name="confirmation_motdepasse" required><br>

            <button type="submit">Mettre à jour</button>
        </form>
    </div>

    <script>
        // JavaScript pour valider le formulaire
        document.getElementById("profileForm").onsubmit = function() {
        	var username = document.getElementById("username").value.trim();
            var prenom = document.getElementById("prenom").value.trim();
            var email = document.getElementById("email").value.trim();
            var password = document.getElementById("password").value.trim();
            var confirmation_motdepasse = document.getElementById("confirmation_motdepasse").value.trim();
            
            if (username === "") {
                alert("Veuillez saisir votre nom.");
                return false;
            }
            if (password === "") {
                alert("Veuillez saisir votre mot de passe.");
                return false;
            }
            if (confirmation_motdepasse === "") {
                alert("Veuillez confirmer votre mot de passe.");
                return false;
            }

            if (prenom === "") {
                alert("Veuillez saisir votre prenom.");
                return false;
            }

            if (email === "") {
                alert("Veuillez saisir votre adresse email.");
                return fa<br>lse;
            }

            return true;
        };
    </script>
</body>
</html>
