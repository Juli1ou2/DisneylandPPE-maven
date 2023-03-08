package modeles;

import controleurs.Parc;
import controleurs.Restaurant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ModeleParc {
    private static Bdd uneBdd = new Bdd();

    public static ArrayList<Parc> selectAllParcs(String mot) {
        String requete = "";
        if (mot.equals("")) {
            requete = "select * from parc;";
        } else {
            requete = "select * from parc where nom like '%"+mot+"%' or prenom like '%"+mot+"%' or email like '%"+mot+"%' or"
                    + " adresse like '%"+mot+"%' or tel like '%"+mot+"%';";
        }
        ArrayList<Parc> lesParcs = new ArrayList<Parc>();
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet desResultats = unStat.executeQuery(requete);
            //parcourir les résultats et construire des objets clients
            while (desResultats.next()) {
                Parc unParc = new Parc(
                        desResultats.getInt("idParc"),
                        desResultats.getString("nom"),
                        desResultats.getInt("capacite"),
                        desResultats.getInt("nbAttractionsTotales"),
                        desResultats.getInt("nbAttractionsFonctionnelles")
                );
                //on ajoute l'instance client dans l'ArrayList
                lesParcs.add(unParc);
            }
            unStat.close();
            uneBdd.seDeconnecter();
        } catch(SQLException e) {
            System.out.println("Erreur d'exécution de : " + requete + "\n" + e);
        }
        return lesParcs;
    }

    public static void deleteParc(int idparc) {
        String requete = "delete from parc where idParc = " + idparc + ";";
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeconnecter();
        } catch(SQLException e) {
            System.out.println("Erreur d'exécution de : " + requete + "\n" + e);
        }
    }

    public static Parc selectWhereParc(String nom) {
        String requete = "select * from parc where nom ='" + nom + "';";
        Parc leParc = null;
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unResultat = unStat.executeQuery(requete);
            //tester si il y a un parc
            if (unResultat.next()) {
                leParc = new Parc(
                        unResultat.getInt("idParc"),
                        unResultat.getString("nom"),
                        unResultat.getInt("capacite"),
                        unResultat.getInt("nbAttractionsTotales"),
                        unResultat.getInt("nbAttractionsFonctionnelles")
                );
            }
            unStat.close();
            uneBdd.seDeconnecter();
        } catch(SQLException e) {
            System.out.println("Erreur d'exécution de : " + requete + "\n" + e);
        }
        return leParc;
    }

    public static void insertParc(Parc unParc) {
        String requete = "insert into parc values(null, '"+
                unParc.getNom()+"','"+
                unParc.getCapacite()+"','"+
                unParc.getNbAttractionsTotales()+"','"+
                unParc.getNbAttractionsFonctionnelles()+"');";
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeconnecter();
        } catch(SQLException e) {
            System.out.println("Erreur d'exécution de : " + requete + "\n" + e);
        }
    }

    public static void updateParc(Parc unParc) {
        String requete = "update parc set nom = '"+
                unParc.getNom()+"', capacite ='"+
                unParc.getCapacite()+"', nbAttractionsTotales ='"+
                unParc.getNbAttractionsTotales()+"',  nbAttractionsFonctionnelles ='"+
                unParc.getNbAttractionsFonctionnelles()+"' where idParc = " + unParc.getIdParc() + ";";
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            unStat.execute(requete);
            unStat.close();
            uneBdd.seDeconnecter();
        } catch(SQLException e) {
            System.out.println("Erreur d'exécution de : " + requete + "\n" + e);
        }
    }
}
