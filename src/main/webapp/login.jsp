<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Authentification</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f0f0;
        }
        .login-container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 400px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .form-group input[type="text"],
        .form-group input[type="password"] {
            width: 380px;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .form-group input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 4px;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }
        button {
        	width: 100%;
            padding: 10px;
            border: none;
            border-radius: 4px;
            background-color: green;
            color: #fff;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Authentification</h2>
        <form id="loginForm" action="ServletVerification" method="post">
            <div class="form-group">
                <label for="username">ID d'utilisateur :</label>
                <input type="text" id="id_user" name="id_user" required>
            </div>
            <div class="form-group">
                <label for="password">Mot de passe :</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <input type="submit" value="Se connecter" name="Se connecter">
            </div>
        </form>
        <div id="message" style="color: red;"></div>
        <div><a href="CreateAccount.jsp"><button>Creer un compte</button></a></div>
    </div>

   
</body>
</html>
