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
		if (conn == null) {
			return 0;
		}
		
		try (PreparedStatement ps = conn.prepareStatement("insert into bein values(?,?,?,?,?)")) {
			ps.setString(1,b.getID_bien());
			ps.setString(2,b.getType());
			ps.setString(3,b.getDescription());
			ps.setDouble(4,b.getP_jour());
			ps.setString(5,b.getDispon());
			return ps.executeUpdate();
		} catch (SQLException excep) {
			excep.printStackTrace();
		}
		return 0;
	
	}
	
	public List<Bien> allBien(){
		Connection conn=SingletonConnection.getInstance();
		List<Bien> products = new ArrayList<>();
		if (conn == null) {
			return products;
		}
		
		try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM bein");
			 ResultSet resultat = ps.executeQuery()) {
			while (resultat.next()) {
				String id = resultat.getString("id_bien");
				String type = resultat.getString("typ");
				String description = resultat.getString("description");
				double price = resultat.getDouble("prix_par_jour");
				String disponible = resultat.getString("disponible");
				products.add(new Bien(id, type, description, price, disponible));
			}
		} catch (SQLException excep) {
			excep.printStackTrace();
		}
		
		return products;
	}
}
