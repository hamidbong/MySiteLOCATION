
package Controllers;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;

import ModelService.UtilisateurService;

import java.io.*;
import java.sql.*;

public class ServletVerification extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String action = request.getParameter("action");
    	
        String res = request.getParameter("inputtt");
        response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	
    	String url = "jdbc:mysql://localhost:3306/locationenligne?serverTimezone=UTC";
        String utilisateur = "root";
        String motDePasse = "root";
        
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	}
        catch (ClassNotFoundException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
			}
        
        if("S'inscrire".equals(res)){
        	String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String ID = request.getParameter("ID");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            String requeteSQL = "INSERT INTO utilisateur (ID_utilisateur, nom, prenom, adresse_email, mot_de_passe) VALUES (?,?,?,?,?)";
            
            
            try (Connection connection = DriverManager.getConnection(url, utilisateur, motDePasse);
                    PreparedStatement statement = connection.prepareStatement(requeteSQL)) {
            	statement.setString(1, ID);
            	statement.setString(2, nom);
            	statement.setString(3, prenom);
            	statement.setString(4, email);
            	statement.setString(5, password);
            	
            	int rowsAffected = statement.executeUpdate(); // Utilisez executeUpdate() pour une requête INSERT
                if (rowsAffected > 0) {
                    System.out.println("Insertion réussie !");
                } else {
                    System.out.println("Aucune ligne n'a été insérée.");
                }
                
                } catch (SQLException e) {
                	e.printStackTrace();
            }
        }
        
        else if("Se connecter".equals(res)) {
            String email = request.getParameter("username");
            String password = request.getParameter("password");
            
            
            
            UtilisateurService US = new UtilisateurService();
            boolean connection = US.verifier(email, password);
            if(connection == true) {
            	 RequestDispatcher dispatcher = request.getRequestDispatcher("accueil33.jsp");
                 // Transmettre la requête et la réponse à la page JSP
                 dispatcher.forward(request, response);
            }
            else {
            	out.println("<!DOCTYPE html>");
                out.println("<html lang=\"fr\">");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Accueil</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h2>Bienvenue sur la page de vérification</h2>");
                out.println("<p>Veuillez utiliser le formulaire pour vous identifier.</p>");
                out.println("<li><a href=\"http://localhost:8080/TP1/IdentificationAdmin.html\">Vers le Formulaire</a></li>");
                out.println("</body>");
                out.println("</html>");
            	
            }
            
            //INSERT INTO utilisateur (ID_utilisateur, nom, prenom, adresse_email, mot_de_passe) VALUES ("P4","Hamid", "bong","br@gmail.com","bb");
             
            
            /*String requeteSQL = "SELECT * FROM utilisateur WHERE Adresse_email = ? AND Mot_de_passe = ?";
            
           
            try (Connection connection = DriverManager.getConnection(url, utilisateur, motDePasse);
                 PreparedStatement statement = connection.prepareStatement(requeteSQL)) {
                statement.setString(1, email);
                statement.setString(2, password);
                ResultSet resultat = statement.executeQuery();
                // Traiter les résultats de la requête
                while (resultat.next()) {
                    // Récupérer les données de chaque ligne de résultat
                    String id = resultat.getString("ID_utilisateur");
                    String nom = resultat.getString("nom");
                    String email1 = resultat.getString("Adresse_email");
                    String mot = resultat.getString("Mot_de_passe");
                    System.out.println("Utilisateur trouvé - ID: " + id + ", Nom: " + nom + ", Email: " + email1+" , password: "+mot);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                }*/
            }else {
            	System.out.println("noooo");
            }
        
                
                
            
        
        }
    }

