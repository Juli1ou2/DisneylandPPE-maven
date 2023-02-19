package controleurs;

public class Client extends User {
    private int fidelite;
    private String dateNaissance;
    private float promotion;

    public Client(int iduser, String nom, String prenom, String adresse, String email, String mdp, String tel, String role, int fidelite, String dateNaissance, float promotion) {
        super(iduser, nom, prenom, adresse, email, mdp, tel, role);
        this.role = "client";
        this.fidelite = fidelite;
        this.dateNaissance = dateNaissance;
        this.promotion = promotion;
    }

    public Client(String nom, String prenom, String adresse, String email, String mdp, String tel, String role, int fidelite, String dateNaissance, float promotion) {
        super(nom, prenom, adresse, email, mdp, tel, role);
        this.role = "client";
        this.fidelite = fidelite;
        this.dateNaissance = dateNaissance;
        this.promotion = promotion;
    }

    public Client(){
        super();
        this.role = "client";
        this.fidelite = 0;
        this.dateNaissance = "";
        this.promotion = 0;
    }

    public int getFidelite() {
        return fidelite;
    }

    public void setFidelite(int fidelite) {
        this.fidelite = fidelite;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public float getPromotion() {
        return promotion;
    }

    public void setPromotion(float promotion) {
        this.promotion = promotion;
    }
}
