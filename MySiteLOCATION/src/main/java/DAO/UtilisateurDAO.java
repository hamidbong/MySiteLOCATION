package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Utilisateur;

public class UtilisateurDAO {

	public UtilisateurDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public int AddUser(Utilisateur e) {
		Connection conn=SingletonConnection.getInstance();	
		try {
		    PreparedStatement ps;
			ps = conn.prepareStatement("insert into Utilisateur values(?,?,?,?,?)");
		    ps.setString(1,e.getID_User());
		    ps.setString(2,e.getNom());
		    ps.setString(3,e.getPrenom());
		    ps.setString(4,e.getEmail());
		    ps.setString(5,e.getPassWd());
		    int rowcount = ps.executeUpdate();
		    ps.close();
		    return rowcount;
		} catch (SQLException excep) {
			// TODO Auto-generated catch block
			excep.printStackTrace();
		}
		return 0;
	
	}
	
	public int ProfilUpdate(Utilisateur e, String ID_user) {
		Connection conn=SingletonConnection.getInstance();
		
		try {
		    PreparedStatement ps;
		    ps = conn.prepareStatement("UPDATE Utilisateur SET nom=?, prenom=?, Adresse_email=?, Mot_de_passe=? WHERE  ID_Utilisateur=?");
		   
		    ps.setString(1,e.getNom());
		    ps.setString(2,e.getPrenom());
		    ps.setString(3,e.getEmail());
		    ps.setString(4,e.getPassWd());
		    ps.setString(5,ID_user);
		    
		    int rowcount = ps.executeUpdate();
		    ps.close();
		    return rowcount;
		} catch (SQLException excep) {
			// TODO Auto-generated catch block
			excep.printStackTrace();
		}
		
		return 0;
		
	}
	
	
	
	
	public boolean login(String ID_user, String password) {
		Connection conn=SingletonConnection.getInstance();
		
		try {
		    PreparedStatement ps;
			ps = conn.prepareStatement("SELECT * FROM utilisateur WHERE ID_utilisateur = ? AND Mot_de_passe = ?");
			ps.setString(1, ID_user);
            ps.setString(2, password);
         
            ResultSet resultat = null;
            resultat = ps.executeQuery();
            
            while (resultat.next()) {
		    	
                String ID_utilisateur = resultat.getString("ID_utilisateur");
                String Mot_de_passe = resultat.getString("Mot_de_passe");
                if (ID_utilisateur.equals(ID_user) && Mot_de_passe.equals(password)) {
                	return true;
                	}
                }
		    ps.close();
		} catch (SQLException excep) {
			// TODO Auto-generated catch block
			excep.printStackTrace();
		}
		return false;
		
		
	}
}
