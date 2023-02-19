package controleurs;

public class Transport {
    private int idTransport;
    private String libelle, type;
    private int capacite;
    private String affluence, horaire;
    private float prix;

    public Transport(int idTransport, String libelle, String type, int capacite, String affluence, String horaire, float prix) {
        this.idTransport = idTransport;
        this.libelle = libelle;
        this.type = type;
        this.capacite = capacite;
        this.affluence = affluence;
        this.horaire = horaire;
        this.prix = prix;
    }

    public Transport(String libelle, String type, int capacite, String affluence, String horaire, float prix) {
        this.idTransport = 0;
        this.libelle = libelle;
        this.type = type;
        this.capacite = capacite;
        this.affluence = affluence;
        this.horaire = horaire;
        this.prix = prix;
    }

    public Transport() {
        this.idTransport = 0;
        this.libelle = "";
        this.type = "";
        this.capacite = 0;
        this.affluence = "";
        this.horaire = "";
        this.prix = 0;
    }

    public int getIdTransport() {
        return idTransport;
    }

    public void setIdTransport(int idTransport) {
        this.idTransport = idTransport;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}
