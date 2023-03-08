package modeles;

import controleurs.Attraction;
import controleurs.Restaurant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ModeleRestaurant {
    private static Bdd uneBdd = new Bdd();
    
    public static void deleteRestaurant(int idrestaurant) {
        String requete = "delete from restaurant where idRestaurant = " + idrestaurant + ";";
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

    public static ArrayList<Restaurant> selectAllRestaurants(String mot) {
        String requete = "";
        if (mot.equals("")) {
            requete = "select * from restaurant;";
        } else {
            requete = "select * from restaurant where nom like '%"+mot+"%' or theme like '%"+mot+"%' or effectifMax like '%"+mot+"%' or"
                    + " affluence like '%"+mot+"%' or type like '%"+mot+"%' or capacite like '%\"+mot+\"%' or url like" +
                    "'%"+mot+"%' or iduser like '%"+mot+"%';";
        }
        ArrayList<Restaurant> lesRestaurants = new ArrayList<Restaurant>();
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet desResultats = unStat.executeQuery(requete);
            //parcourir les résultats et construire des objets restaurants
            while (desResultats.next()) {
                Restaurant uneRestaurant = new Restaurant(
                        desResultats.getInt("idRestaurant"),
                        desResultats.getString("nom"),
                        desResultats.getString("theme"),
                        desResultats.getInt("effectifMax"),
                        desResultats.getString("affluence"),
                        desResultats.getString("type"),
                        desResultats.getInt("capacite"),
                        desResultats.getString("url"),
                        desResultats.getInt("idUser")
                );
                //on ajoute l'instance restaurant dans l'ArrayList
                lesRestaurants.add(uneRestaurant);
            }
            unStat.close();
            uneBdd.seDeconnecter();
        } catch(SQLException e) {
            System.out.println("Erreur d'exécution de : " + requete + "\n" + e);
        }
        return lesRestaurants;
    }

    public static void insertRestaurant(Restaurant unRestaurant) {
        String requete = "insert into restaurant values(null, '"+
                unRestaurant.getNom()+"','"+
                unRestaurant.getTheme()+"','" +
                unRestaurant.getEffectifMax()+"','" +
                unRestaurant.getAffluence()+"','"+
                unRestaurant.getType()+"','"+
                unRestaurant.getCapacite()+"','"+
                unRestaurant.getUrl()+"','"+
                unRestaurant.getIduser()+"');";
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

    public static Restaurant selectWhereRestaurant(String nom) {
        String requete = "select * from restaurant where nom ='" + nom + "';";
        Restaurant leRestaurant = null;
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unResultat = unStat.executeQuery(requete);
            //tester si il y a un restaurant
            if (unResultat.next()) {
                leRestaurant = new Restaurant(
                        unResultat.getInt("idRestaurant"),
                        unResultat.getString("nom"),
                        unResultat.getString("theme"),
                        unResultat.getInt("effectifMax"),
                        unResultat.getString("affluence"),
                        unResultat.getString("type"),
                        unResultat.getInt("capacite"),
                        unResultat.getString("url"),
                        unResultat.getInt("iduser")
                );
            }
            unStat.close();
            uneBdd.seDeconnecter();
        } catch(SQLException e) {
            System.out.println("Erreur d'exécution de : " + requete + "\n" + e);
        }
        return leRestaurant;
    }

    public static void updateRestaurant(Restaurant unRestaurant) {
        String requete = "update restaurant set nom = '"+
                unRestaurant.getNom()+"', theme ='"+
                unRestaurant.getTheme()+"', effectifMax ='" +
                unRestaurant.getEffectifMax()+"', affluence ='" +
                unRestaurant.getAffluence()+"', type ='"+
                unRestaurant.getType()+"', capacite ='"+
                unRestaurant.getCapacite()+"', url ='"+
                unRestaurant.getUrl()+"', iduser ='"+
                unRestaurant.getIduser()+"' where idRestaurant = " + unRestaurant.getIdRestaurant() + ";";
        System.out.println("requête : " + requete);
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
