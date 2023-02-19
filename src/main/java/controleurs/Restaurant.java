package controleurs;

public class Restaurant {
    private int idRestaurant;
    private String nom, theme;
    private int effectifMax;
    private String affluence, type;
    private int capacite;
    private String url;
    private int iduser;

    public Restaurant(int idRestaurant, String nom, String theme, int effectifMax, String affluence, String type, int capacite, String url, int iduser) {
        this.idRestaurant = idRestaurant;
        this.nom = nom;
        this.theme = theme;
        this.effectifMax = effectifMax;
        this.affluence = affluence;
        this.type = type;
        this.capacite = capacite;
        this.url = url;
        this.iduser = iduser;
    }

    public Restaurant(String nom, String theme, int effectifMax, String affluence, String type, int capacite, String url, int iduser) {
        this.idRestaurant = 0;
        this.nom = nom;
        this.theme = theme;
        this.effectifMax = effectifMax;
        this.affluence = affluence;
        this.type = type;
        this.capacite = capacite;
        this.url = url;
        this.iduser = iduser;
    }

    public Restaurant() {
        this.idRestaurant = 0;
        this.nom = "";
        this.theme = "";
        this.effectifMax = 0;
        this.affluence = "";
        this.type = "";
        this.capacite = 0;
        this.url = "";
        this.iduser = 0;
    }

    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getEffectifMax() {
        return effectifMax;
    }

    public void setEffectifMax(int effectifMax) {
        this.effectifMax = effectifMax;
    }

    public String getAffluence() {
        return affluence;
    }

    public void setAffluence(String affluence) {
        this.affluence = affluence;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
}
