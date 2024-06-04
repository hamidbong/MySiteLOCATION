<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Création de compte</title>
    <link rel="stylesheet" href="CreataAccount.css"> <!-- Ajoutez un lien vers votre fichier CSS -->
    <link rel="stylesheet" href="alert/dist/sweetalert.css">
    <style>
    	button {
        	width: 100px;
        	float: right;
            padding: 10px;
            margin-top: -35px;
            border: none;
            border-radius: 4px;
            background-color: green;
            color: #fff;
            cursor: pointer;
        }
        .inp input{
        	width: 100px;
        	padding: 10px;
            margin-top: -20px;
            border: none;
            border-radius: 4px;
            background-color: green;
            color: #fff;
            cursor: pointer;
        }
        
    </style>
</head>
<body>
	<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
    <div class="container">
        <h1>Création de compte</h1>
        <form action="CreateAccount" method="post"> 
            <div class="form-group">
                <label for="nom">Nom :</label>
                <input type="text" id="nom" name="nom" required>
            </div>
            <div class="form-group">
                <label for="prenom">Prénom :</label>
                <input type="text" id="prenom" name="prenom" required>
            </div>
            <div class="form-group">
                <label for="email">Email :</label>
                <input type="email" id="email" name="email" required>
            </div>
            
            <div class="form-group">
                <label for="motdepasse">Mot de passe :</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="confirmation_motdepasse">Confirmation du mot de passe :</label>
                <input type="password" id="confirmation_motdepasse" name="confirmation_motdepasse" required>
            </div>
            <div class="inp">
            	<input type="submit" class="iputt" value="S'inscrire" name="S'inscrire">
            </div>
            
        </form>
        <div><a href="login.jsp"><button>Se connecter</button></a></div>
    </div>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script type="text/javascript">
    	var status = document.getElementById("status").value;
    	if(status=="success"){
    		swal("Congrats","Account Created Successfully","success");
    	}

    </script>
    
</body>
</html>
