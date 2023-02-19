package controleurs;

public class Attraction {
    private int idAttraction;
    private String nom, status, type;
    private int capacite;
    private String affluence;
    private float prix;
    private String heureOuv, heureFerm, url;
    private int idParc, iduser;

    public Attraction(int idAttraction, String nom, String status, String type, int capacite, String affluence, float prix, String heureOuv, String heureFerm, String url, int idParc, int iduser) {
        this.idAttraction = idAttraction;
        this.nom = nom;
        this.status = status;
        this.type = type;
        this.capacite = capacite;
        this.affluence = affluence;
        this.prix = prix;
        this.heureOuv = heureOuv;
        this.heureFerm = heureFerm;
        this.url = url;
        this.idParc = idParc;
        this.iduser = iduser;
    }

    public Attraction(String nom, String status, String type, int capacite, String affluence, float prix, String heureOuv, String heureFerm, String url, int idParc, int iduser) {
        this.idAttraction = 0;
        this.nom = nom;
        this.status = status;
        this.type = type;
        this.capacite = capacite;
        this.affluence = affluence;
        this.prix = prix;
        this.heureOuv = heureOuv;
        this.heureFerm = heureFerm;
        this.url = url;
        this.idParc = idParc;
        this.iduser = iduser;
    }

    public Attraction() {
        this.idAttraction = 0;
        this.nom = "";
        this.status = "";
        this.type = "";
        this.capacite = 0;
        this.affluence = "";
        this.prix = 0;
        this.heureOuv = "";
        this.heureFerm = "";
        this.url = "";
        this.idParc = 0;
        this.iduser = 0;
    }

    public int getIdAttraction() {
        return idAttraction;
    }

    public void setIdAttraction(int idAttraction) {
        this.idAttraction = idAttraction;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getAffluence() {
        return affluence;
    }

    public void setAffluence(String affluence) {
        this.affluence = affluence;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getHeureOuv() {
        return heureOuv;
    }

    public void setHeureOuv(String heureOuv) {
        this.heureOuv = heureOuv;
    }

    public String getHeureFerm() {
        return heureFerm;
    }

    public void setHeureFerm(String heureFerm) {
        this.heureFerm = heureFerm;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdParc() {
        return idParc;
    }

    public void setIdParc(int idParc) {
        this.idParc = idParc;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
}
