package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Bien;

public class BienDAO {

	public BienDAO() {
		// TODO Auto-generated constructor stub
	}

	
	public int AddBien(Bien b) {
		Connection conn=SingletonConnection.getInstance();
		
		try {
		    PreparedStatement ps;
			ps = conn.prepareStatement("insert into Bein values(?,?,?,?,?)");
		    ps.setString(1,b.getID_bien());
		    ps.setString(2,b.getType());
		    ps.setString(3,b.getDescription());
		    ps.setDouble(4,b.getP_jour());
		    ps.setString(5,b.getDispon());
		    int rowcount = ps.executeUpdate();
		    ps.close();
		    return rowcount;
		} catch (SQLException excep) {
			// TODO Auto-generated catch block
			excep.printStackTrace();
		}
		return 0;
	
	}
	
	public List<Bien> allBien(){
		Connection conn=SingletonConnection.getInstance();
		List<Bien> products = new ArrayList<>();
		
		try {
		    PreparedStatement ps;
			ps = conn.prepareStatement("SELECT * FROM bein");
			ResultSet resultat = null;
            
            resultat = ps.executeQuery();
		    while (resultat.next()) {
		    	
                String id = resultat.getString("id_bien");
                String type = resultat.getString("typ");
                String description = resultat.getString("description");
                double price = resultat.getDouble("prix_par_jour");
                String disponible = resultat.getString("disponible");
                // Ajouter le produit à la liste
                products.add(new Bien(id, type, description, price, disponible));
                }
		    ps.close();
		    }
		catch (SQLException excep) {
			// TODO Auto-generated catch block
			excep.printStackTrace();
			}
		
		return products;
	}
}
