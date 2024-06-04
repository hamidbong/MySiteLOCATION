package Model;
public class Utilisateur {
	private String ID_User, Nom, Prenom, Email, PassWd;

	 public Utilisateur(String ID_User, String Nom, String Prenom, String Email, String PassWd) {
		// TODO Auto-generated constructor stub
		this.setID_User(ID_User);
		this.setNom(Nom);
		this.setPrenom(Prenom);
		this.setEmail(Email);
		this.setPassWd(PassWd);
		
		
	}
	

	public String getID_User() {
		return ID_User;
	}

	public void setID_User(String iD_User) {
		ID_User = iD_User;
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
	
	

}
