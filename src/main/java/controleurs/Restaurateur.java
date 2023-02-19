package controleurs;

public class Restaurateur extends User {
    private String qualification, anciennete;

    public Restaurateur(int iduser, String nom, String prenom, String adresse, String email, String mdp, String tel, String role, String qualification, String anciennete) {
        super(iduser, nom, prenom, adresse, email, mdp, tel, role);
        this.role = "restaurateur";
        this.qualification = qualification;
        this.anciennete = anciennete;
    }

    public Restaurateur(String nom, String prenom, String adresse, String email, String mdp, String tel, String role, String qualification, String anciennete) {
        super(nom, prenom, adresse, email, mdp, tel, role);
        this.role = "restaurateur";
        this.qualification = qualification;
        this.anciennete = anciennete;
    }

    public Restaurateur(){
        super();
        this.role = "restaurateur";
        this.qualification = "";
        this.anciennete = "";
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getAnciennete() {
        return anciennete;
    }

    public void setAnciennete(String anciennete) {
        this.anciennete = anciennete;
    }
}
