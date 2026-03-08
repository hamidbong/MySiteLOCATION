package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Model.Bien;
import Model.Reservation;
import Model.Utilisateur;

public class ReservationDAO {

	public ReservationDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public int AddReservation(Reservation r, String id_bien, String id_user) {
		Connection conn=SingletonConnection.getInstance();
		if (conn == null) {
			return 0;
		}
		
		try (PreparedStatement ps = conn.prepareStatement("insert into reservation values(?,?,?,?,?,?,?)")) {
			ps.setString(1,r.getID_Reser());
			ps.setString(2,id_user);
			ps.setString(3,id_bien);
			ps.setTimestamp(4,r.getD_deb());
			ps.setTimestamp(5,r.getD_fin());
			ps.setString(6,r.getStatut());
			ps.setDouble(7,r.getMont_To());
			return ps.executeUpdate();
		} catch (SQLException excep) {
			excep.printStackTrace();
		}
		return 0;
	}
	
	
	public int DelReservation(String id_reser, String id_user) {
		Connection conn=SingletonConnection.getInstance();
		if (conn == null) {
			return 0;
		}
		
		try (PreparedStatement ps = conn.prepareStatement("delete from reservation where id_reservation=? and id_utilisateur=?")) {
			ps.setString(1,id_reser);
		    ps.setString(2,id_user);
		    return ps.executeUpdate();
		}
		catch (SQLException excep) {
			excep.printStackTrace();
		}
		return 0;
	}
	
	public List<Reservation> allReservation(String id_user){
		Connection conn=SingletonConnection.getInstance();
		List<Reservation> listofRESERVATION = new ArrayList<>();
		if (conn == null || id_user == null) {
			return listofRESERVATION;
		}

		String reservationSql = "SELECT * FROM reservation where id_utilisateur=?";
		String bienSql = "Select * from bein where id_bien=?";
		String userSql = "Select * from utilisateur where id_utilisateur=?";
		
		try (PreparedStatement ps = conn.prepareStatement(reservationSql)) {
			ps.setString(1,id_user);
			try (ResultSet resultat = ps.executeQuery()) {
				while (resultat.next()) {
		    	//String id_utilisateur = resultat.getString("id_utilisateur");
		    	String id_reservation = resultat.getString("id_reservation");
                Timestamp Date_debut= resultat.getTimestamp("Date_debut");
                Timestamp Date_fin= resultat.getTimestamp("Date_fin");
                String Statut = resultat.getString("Statut");
                double Montant_total = resultat.getDouble("Montant_total");
                // Ajouter le produit à la liste
                Reservation reservation = new Reservation(Statut, Date_debut, Date_fin, Montant_total);
                reservation.setID_Reser(id_reservation);
                
                String id_bien = resultat.getString("id_bien");
					try (PreparedStatement ps2 = conn.prepareStatement(bienSql)) {
						ps2.setString(1,id_bien);
						try (ResultSet rs2 = ps2.executeQuery()) {
							if(rs2.next()) {
								String ID_bien = rs2.getString("ID_bien");
								String type = rs2.getString("typ");
								String description = rs2.getString("description");
								Double Prix_par_jour =rs2.getDouble("Prix_par_jour");
								String disponible = rs2.getString("disponible");
								
								Bien bien =new Bien(ID_bien, type, description, Prix_par_jour, disponible);
								reservation.setBien(bien);
							}
						}
					}
		        
					try (PreparedStatement ps3 = conn.prepareStatement(userSql)) {
						ps3.setString(1,id_user);
						try (ResultSet rs3 = ps3.executeQuery()) {
							if(rs3.next()) {
								String Nom = rs3.getString("Nom");
								String Prenom = rs3.getString("Prenom");
								String Adresse_email = rs3.getString("Adresse_email");
								String Mot_de_passe = rs3.getString("Mot_de_passe");
								
								Utilisateur user =new Utilisateur(Nom, Prenom, Adresse_email, Mot_de_passe);
								user.setID_User(id_user);
								reservation.setUtilisateur(user);
							}
						}
					}
		        
                listofRESERVATION.add(reservation);
                
                }
			}
		}
		catch (SQLException excep) {
			excep.printStackTrace();
		}
		
		return listofRESERVATION;
	}
	
	

}
