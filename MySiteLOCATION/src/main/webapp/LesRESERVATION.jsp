<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="Model.Reservation" %>
<%@ page import="Model.Bien" %>
<%@ page import="Model.Utilisateur" %>
<%@ page import="java.util.List" %>
<%
	if(session.getAttribute("id_user")==null){
		response.sendRedirect("login.jsp");
	}
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Reservations</title>
    <style type="text/css">
    	/* Style général */
		body {
		    font-family: Arial, sans-serif;
		    background-color: #f4f4f4;
		    margin: 0;
		    padding: 0;
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
		    margin-bottom: 20px;
		}
		
		table {
		    width: 100%;
		    border-collapse: collapse;
		    margin-top: 20px;
		}
		
		th, td {
		    padding: 10px;
		    border: 1px solid #ddd;
		    text-align: left;
		}
		
		th {
		    background-color: #f2f2f2;
		}
		
		/* Style spécifique pour le message "Aucun bien trouvé." */
		.no-items {
		    font-style: italic;
		    text-align: center;
		}
		    	
    </style>
    <link rel="stylesheet" type="text/css" href="style4.css">
    <link rel="stylesheet" href="alert/dist/sweetalert.css">
</head>
<body>
    <h1>Liste des Reservations</h1>
    <table>
        <thead>
            <tr>
                <th>Description du bien</th>
                <th>Type</th>
                <th>DATE debut</th>
                <th>Date fin</th>
                <th>Prix par Jour</th>
                <th>Montant Total</th>
                <th>Statut</th>
            </tr>
        </thead>
        <tbody>
            <% List<Reservation> reservations = (List<Reservation>) session.getAttribute("listRESERVATION");
            if (reservations != null) {
                for (Reservation bien : reservations){%>
                 <tr>
                        <td><%= bien.getBien().getDescription() %></td>
                        <td><%= bien.getBien().getType() %></td>
                        <td><%= bien.getD_deb() %></td>
                        <td><%= bien.getD_fin() %></td>
                        <td><%= bien.getBien().getP_jour() %></td>
                        <td><%= bien.getMont_To() %></td>
                        <td><%= bien.getStatut() %></td>
                        <td>
                        	<%-- Formulaire pour le bouton de réservation --%>
                    		<form method="post" action=AnnulReservation>
                        		<input type="hidden" name="id_reser" value="<%= bien.getID_Reser() %>">
                        		<input type="hidden" name="id_user" value="<%= bien.getUtilisateur().getID_User() %>">
                        		<input type="submit" value="Annuler Reservation">
                    		</form>
                		</td>
                  </tr><%
                  }
              }%>
        </tbody>
    </table>
    
</body>
</html>
