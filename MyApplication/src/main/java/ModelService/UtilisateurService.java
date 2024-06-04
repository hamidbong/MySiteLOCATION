package ModelService;

import Model.Utilisateur;

import java.io.*;
import java.sql.*;

public class UtilisateurService {

	
	
	public UtilisateurService() {
		// TODO Auto-generated constructor stub
		
	}
	
	

	public void addUser() {
		
	}
	
	public boolean verifier(String user, String passd) {
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
      
        String requeteSQL = "SELECT * FROM utilisateur WHERE Adresse_email = ? AND Mot_de_passe = ?";
        
       
        try (Connection connection = DriverManager.getConnection(url, utilisateur, motDePasse);
             PreparedStatement statement = connection.prepareStatement(requeteSQL)) {
    
        	statement.setString(1, user);
            statement.setString(2, passd);
            
            ResultSet resultat = null;
            resultat = statement.executeQuery();

            if (resultat != null) {
            	return true;
            }
            else {
            	return false;
            }
            
            
            
            } catch (SQLException e){
            	
        	e.printStackTrace();
        	}
		return false;
		
		
	}

}
