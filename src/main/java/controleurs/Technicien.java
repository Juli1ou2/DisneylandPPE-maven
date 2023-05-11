package controleurs;

public class Technicien extends User {
    private String qualification, dateEntree, roleBis;

    public Technicien(int iduser, String nom, String prenom, String adresse, String email, String mdp, String tel, String qualification, String dateEntree, String roleBis) {
        super(iduser, nom, prenom, adresse, email, mdp, tel, "technicien");
        this.qualification = qualification;
        this.dateEntree = dateEntree;
        this.roleBis = roleBis;
    }

    public Technicien(String nom, String prenom, String adresse, String email, String mdp, String tel, String qualification, String dateEntree, String roleBis) {
        super(nom, prenom, adresse, email, mdp, tel, "technicien");
        this.qualification = qualification;
        this.dateEntree = dateEntree;
        this.roleBis = roleBis;
    }

    public Technicien(){
        super();
        this.qualification = "";
        this.dateEntree = "";
        this.roleBis = "";
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

    public String getRoleBis() {
        return roleBis;
    }

    public void setRoleBis(String roleBis) {
        this.roleBis = roleBis;
    }
}
