package modeles;

import controleurs.Attraction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ModeleAttraction {
    private static Bdd uneBdd = new Bdd();

    public static void insertAttraction(Attraction uneAttraction) {
        String requete = "insert into attraction values(null, '"+
                uneAttraction.getNom()+"','"+
                uneAttraction.getStatus()+"','" +
                uneAttraction.getType()+"','" +
                uneAttraction.getCapacite()+"','"+
                uneAttraction.getAffluence()+"','"+
                uneAttraction.getPrix()+"','"+
                uneAttraction.getHeureOuv()+"','"+
                uneAttraction.getHeureFerm()+"','"+
                uneAttraction.getUrl()+"','"+
                uneAttraction.getIdParc()+"','"+
                uneAttraction.getIduser()+"');";
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

    public static ArrayList<Attraction> selectAllAttractions(String mot) {
        String requete = "";
        if (mot.equals("")) {
            requete = "select * from attraction;";
        } else {
            requete = "select * from attraction where nom like '%"+mot+"%' or status like '%"+mot+"%' or type like '%"+mot+"%' or"
                    + " capacite like '%"+mot+"%' or affluence like '%"+mot+"%' or prix like '%\"+mot+\"%' or heureOuv like" +
                    "'%"+mot+"%' or heureFerm like '%"+mot+"%' or idParc like '%"+mot+"%' or iduser like '%"+mot+"%';";
        }
        ArrayList<Attraction> lesAttractions = new ArrayList<Attraction>();
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet desResultats = unStat.executeQuery(requete);
            //parcourir les résultats et construire des objets clients
            while (desResultats.next()) {
                Attraction uneAttraction = new Attraction(
                        desResultats.getInt("idAttraction"),
                        desResultats.getString("nom"),
                        desResultats.getString("status"),
                        desResultats.getString("type"),
                        desResultats.getInt("capacite"),
                        desResultats.getString("affluence"),
                        desResultats.getFloat("prix"),
                        desResultats.getString("heureOuv"),
                        desResultats.getString("heureFerm"),
                        desResultats.getString("url"),
                        desResultats.getInt("idParc"),
                        desResultats.getInt("idUser")
                );
                //on ajoute l'instance client dans l'ArrayList
                lesAttractions.add(uneAttraction);
            }
            unStat.close();
            uneBdd.seDeconnecter();
        } catch(SQLException e) {
            System.out.println("Erreur d'exécution de : " + requete + "\n" + e);
        }
        return lesAttractions;
    }

    public static Attraction selectWhereAttraction(String nom) {
        String requete = "select * from attraction where nom ='" + nom + "';";
        Attraction lAttraction = null;
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unResultat = unStat.executeQuery(requete);
            //tester si il y a un client
            if (unResultat.next()) {
                lAttraction = new Attraction(
                        unResultat.getInt("idattraction"),
                        unResultat.getString("nom"),
                        unResultat.getString("status"),
                        unResultat.getString("type"),
                        unResultat.getInt("capacite"),
                        unResultat.getString("affluence"),
                        unResultat.getFloat("prix"),
                        unResultat.getString("heureOuv"),
                        unResultat.getString("heureFerm"),
                        unResultat.getString("url"),
                        unResultat.getInt("idParc"),
                        unResultat.getInt("iduser")
                );
            }
            unStat.close();
            uneBdd.seDeconnecter();
        } catch(SQLException e) {
            System.out.println("Erreur d'exécution de : " + requete + "\n" + e);
        }
        return lAttraction;
    }

    public static void updateAttraction(Attraction uneAttraction) {
        String requete = "update attraction set nom = '"+
                uneAttraction.getNom()+"', status ='"+
                uneAttraction.getStatus()+"', type ='" +
                uneAttraction.getType()+"', capacite ='" +
                uneAttraction.getCapacite()+"', affluence ='"+
                uneAttraction.getAffluence()+"', prix ='"+
                uneAttraction.getPrix()+"', heureOuv ='"+
                uneAttraction.getHeureOuv()+"', heureFerm ='"+
                uneAttraction.getHeureFerm()+"', url ='"+
                uneAttraction.getUrl()+"', idParc ='"+
                uneAttraction.getIdParc()+"', iduser ='"+
                uneAttraction.getIduser()+"' where idAttraction = " + uneAttraction.getIdAttraction() + ";";
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

    public static void deleteAttraction(int idattraction) {
        String requete = "delete from attraction where idAttraction = " + idattraction + ";";
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
