package controleurs;

import javax.swing.table.AbstractTableModel;

public class Tableau extends AbstractTableModel {
    private Object [][] donnees;
    private String [] entetes;

    public Tableau (Object [][] donnees, String [] entetes) {
        this.donnees = donnees;
        this.entetes = entetes;
    }

    @Override
    public int getRowCount() {
        //retourne le nombre de lignes
        return this.donnees.length;
    }

    @Override
    public int getColumnCount() {
        //retourne le nombre de colonnes
        return this.entetes.length;
    }

    @Override
    public Object getValueAt(int ligne, int colonne) {
        //retourne la valeur se trouvant au croisement ligne, colonne
        return this.donnees[ligne][colonne];
    }

    @Override
    public String getColumnName(int colonne) {
        //retourne le nom de la colonne
        return this.entetes[colonne];
    }

    //méthode : insertion d'une ligne dans la matrice donnees
    public void insererLigne(Object [] ligne) {
        Object [][] matrice = new Object[this.donnees.length+1][this.entetes.length];
        for (int i=0; i<this.donnees.length; i++) {
            matrice[i] = this.donnees[i];
        }
        matrice[this.donnees.length] = ligne;
        this.donnees = matrice;
        this.fireTableDataChanged(); //actualiser la modification des données
    }

    //méthode : suppression d'une ligne dans la matrice donnees
    public void supprimerLigne(int numLigne) {
        Object [][] matrice = new Object[this.donnees.length-1][this.entetes.length];
        int j=0;
        for (int i=0; i<this.donnees.length; i++) {
            if (i != numLigne) {
                matrice[j] = this.donnees[i];
                j++;
            }
        }
        this.donnees = matrice;
        this.fireTableDataChanged(); //actualiser la modification des données
    }

    //méthode : modification d'une ligne dans la matrice donnees
    public void modifierLigne(int numLigne, Object [] ligne) {
        Object [][] matrice = new Object[this.donnees.length][this.entetes.length];
        for (int i=0; i<this.donnees.length; i++) {
            if (i == numLigne) {
                matrice[i] = ligne;
            } else {
                matrice[i] = this.donnees[i];
            }
        }
        this.donnees = matrice;
        this.fireTableDataChanged(); //actualiser la modification des données
    }

    public void setDonnees(Object [][] matrice) {
        this.donnees = matrice;
        this.fireTableDataChanged(); //actualiser la modification des données
    }
}