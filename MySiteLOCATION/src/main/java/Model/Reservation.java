package Model;
import java.sql.Timestamp;
import java.util.UUID;

public class Reservation {
	private String ID_Reser, Statut;
	private Timestamp D_deb=new Timestamp(System.currentTimeMillis());
	private Timestamp D_fin=new Timestamp(System.currentTimeMillis());
	private Double Mont_To;
	private Utilisateur utilisateur;
	private Bien bien;
	public Reservation(String Statut, Timestamp D_deb, Timestamp D_fin, Double Mont_To) {
		// TODO Auto-generated constructor stub
		this.ID_Reser= generateUniqueId();
		this.Statut=Statut;
		this.D_deb=D_deb;
		this.D_fin=D_fin;
		this.Mont_To=Mont_To;
		
	}
	public String getID_Reser() {
		return ID_Reser;
	}
	public void setID_Reser(String iD_Reser) {
		this.ID_Reser = iD_Reser;
	}
	
	public String getStatut() {
		return Statut;
	}
	public void setStatut(String statut) {
		Statut = statut;
	}
	public Timestamp getD_deb() {
		return D_deb;
	}
	public void setD_deb(Timestamp d_deb) {
		D_deb = d_deb;
	}
	public Timestamp getD_fin() {
		return D_fin;
	}
	public void setD_fin(Timestamp d_fin) {
		D_fin = d_fin;
	}
	public Double getMont_To() {
		return Mont_To;
	}
	public void setMont_To(Double mont_To) {
		Mont_To = mont_To;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Bien getBien() {
		return bien;
	}
	public void setBien(Bien bien) {
		this.bien = bien;
	}
	private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

}
