package Model;

import java.util.UUID;

public class Utilisateur {
	private String ID_User, Nom, Prenom, Email, PassWd;

	 public Utilisateur(String Nom, String Prenom, String Email, String PassWd) {
		// TODO Auto-generated constructor stub
		this.ID_User= generateUniqueId();
		this.Nom=Nom;
		this.Prenom=Prenom;
		this.Email=Email;
		this.PassWd=PassWd;
		
		
	}
	

	public String getID_User() {
		return ID_User;
	}

	public void setID_User(String ID_User) {
		this.ID_User = ID_User;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassWd() {
		return PassWd;
	}

	public void setPassWd(String passWd) {
		PassWd = passWd;
	}
	private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
	
	

}
