package Model;
public class Bien {
	private String ID_bien, Type, Description;
	private Double P_jour;
	private String Dispon;

	public Bien(String ID_bien, String Type, String Description,Double P_jour, String Dispon) {
		// TODO Auto-generated constructor stub
		this.setID_bien(ID_bien);
		this.setType(Type);
		this.setDescription(Description);
		this.setP_jour(P_jour);
		this.setDispon(Dispon);
	}

	public String getID_bien() {
		return ID_bien;
	}

	public void setID_bien(String iD_bien) {
		ID_bien = iD_bien;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Double getP_jour() {
		return P_jour;
	}

	public void setP_jour(Double p_jour) {
		P_jour = p_jour;
	}

	public String getDispon() {
		return Dispon;
	}

	public void setDispon(String dispon) {
		Dispon = dispon;
	}

}
