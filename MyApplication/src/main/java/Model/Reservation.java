package Model;
import java.sql.Date;

public class Reservation {
	private String ID_Reser, ID_User, ID_bien, Statut;
	private Date D_deb, D_fin;
	private Double Mont_To;
	public Reservation(String ID_Reser, String ID_User, String ID_bien, String Statut, Date D_deb, Date D_fin, Double Mont_To) {
		// TODO Auto-generated constructor stub
		this.setID_Reser(ID_Reser);
		this.setID_User(ID_User);
		this.setID_bien(ID_bien);
		this.setStatut(Statut);
		this.setD_deb(D_deb);
		this.setD_fin(D_fin);
		this.setMont_To(Mont_To);
		
	}
	public String getID_Reser() {
		return ID_Reser;
	}
	public void setID_Reser(String iD_Reser) {
		ID_Reser = iD_Reser;
	}
	public String getID_User() {
		return ID_User;
	}
	public void setID_User(String iD_User) {
		ID_User = iD_User;
	}
	public String getID_bien() {
		return ID_bien;
	}
	public void setID_bien(String iD_bien) {
		ID_bien = iD_bien;
	}
	public String getStatut() {
		return Statut;
	}
	public void setStatut(String statut) {
		Statut = statut;
	}
	public Date getD_deb() {
		return D_deb;
	}
	public void setD_deb(Date d_deb) {
		D_deb = d_deb;
	}
	public Date getD_fin() {
		return D_fin;
	}
	public void setD_fin(Date d_fin) {
		D_fin = d_fin;
	}
	public Double getMont_To() {
		return Mont_To;
	}
	public void setMont_To(Double mont_To) {
		Mont_To = mont_To;
	}

}
