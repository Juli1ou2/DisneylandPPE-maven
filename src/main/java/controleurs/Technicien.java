package controleurs;

public class Technicien extends User {
    private String qualification, dateEntree;

    public Technicien(int iduser, String nom, String prenom, String adresse, String email, String mdp, String tel, String role, String qualification, String dateEntree) {
        super(iduser, nom, prenom, adresse, email, mdp, tel, role);
        this.role = "technicien";
        this.qualification = qualification;
        this.dateEntree = dateEntree;
    }

    public Technicien(String nom, String prenom, String adresse, String email, String mdp, String tel, String role, String qualification, String dateEntree) {
        super(nom, prenom, adresse, email, mdp, tel, role);
        this.role = "technicien";
        this.qualification = qualification;
        this.dateEntree = dateEntree;
    }

    public Technicien(){
        super();
        this.role = "technicien";
        this.qualification = "";
        this.dateEntree = "";
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(String dateEntree) {
        this.dateEntree = dateEntree;
    }
}
