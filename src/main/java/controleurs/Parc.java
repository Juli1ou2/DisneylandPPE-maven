package controleurs;

public class Parc {
    private int idParc;
    private String nom;
    private int capacite, nbAttractionsTotales, nbAttractionsFonctionnelles;

    public Parc(int idParc, String nom, int capacite, int nbAttractionsTotales, int nbAttractionsFonctionnelles) {
        this.idParc = idParc;
        this.nom = nom;
        this.capacite = capacite;
        this.nbAttractionsTotales = nbAttractionsTotales;
        this.nbAttractionsFonctionnelles = nbAttractionsFonctionnelles;
    }

    public Parc(String nom, int capacite, int nbAttractionsTotales, int nbAttractionsFonctionnelles) {
        this.idParc = 0;
        this.nom = nom;
        this.capacite = capacite;
        this.nbAttractionsTotales = nbAttractionsTotales;
        this.nbAttractionsFonctionnelles = nbAttractionsFonctionnelles;
    }

    public Parc() {
        this.idParc = 0;
        this.nom = "";
        this.capacite = 0;
        this.nbAttractionsTotales = 0;
        this.nbAttractionsFonctionnelles = 0;
    }

    public int getIdParc() {
        return idParc;
    }

    public void setIdParc(int idParc) {
        this.idParc = idParc;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getNbAttractionsTotales() {
        return nbAttractionsTotales;
    }

    public void setNbAttractionsTotales(int nbAttractionsTotales) {
        this.nbAttractionsTotales = nbAttractionsTotales;
    }

    public int getNbAttractionsFonctionnelles() {
        return nbAttractionsFonctionnelles;
    }

    public void setNbAttractionsFonctionnelles(int nbAttractionsFonctionnelles) {
        this.nbAttractionsFonctionnelles = nbAttractionsFonctionnelles;
    }
}
