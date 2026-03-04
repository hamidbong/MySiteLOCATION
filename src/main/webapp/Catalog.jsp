<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Produits</title>
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
    <h1>Liste des Produits</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Type</th>
                <th>Description</th>
                <th>Prix</th>
                <th>Disponibilité</th>
            </tr>
        </thead>
        <tbody>
            <% List<Bien> biens = (List<Bien>) session.getAttribute("products");
            if (biens != null) {
                for (Bien bien : biens) { %>
                    <tr>
                        <td><%= bien.getID_bien() %></td>
                        <td><%= bien.getType() %></td>
                        <td><%= bien.getDescription() %></td>
                        <td><%= bien.getP_jour() %></td>
                        <td><%= bien.getDispon() %></td>
                        <td>
                        	<%-- Formulaire pour le bouton de réservation --%>
                    		<form method="post" action="ReservationServlet">
                        		<input type="hidden" name="productId" value="<%= bien.getID_bien() %>">
                        		<input type="hidden" name="prix" value="<%= bien.getP_jour() %>">
                        		<input type="datetime-local" name="debut">
                        		<input type="datetime-local" name="fin">
                        		<input type="submit" value="Réserver">
                    		</form>
                		</td>
                    </tr>
                <% } %>
            <% } else { %>
                <tr>
                    <td colspan="5">Aucun bien trouvé.</td>
                </tr>
            <% } %>
        </tbody>
    </table>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script type="text/javascript">
    	var status = document.getElementById("statusRES").value;
		if(status === "success")){
			swal("Congrats","Account Created Successfully","success");
		}
    </script>
</body>
</html>
