package controleurs;

public abstract class User {
    protected int iduser;
    protected String nom, prenom, adresse, email, mdp, tel, role;

    public User(int iduser, String nom, String prenom, String adresse, String email, String mdp, String tel, String role) {
        this.iduser = iduser;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.mdp = mdp;
        this.tel = tel;
        this.role = role;
    }

    public User(String nom, String prenom, String adresse, String email, String mdp, String tel, String role) {
        this.iduser = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.mdp = mdp;
        this.tel = tel;
        this.role = role;
    }

    public User() {
        this.iduser = 0;
        this.nom = "";
        this.prenom = "";
        this.adresse = "";
        this.email = "";
        this.mdp = "";
        this.tel = "";
        this.role = "";
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
