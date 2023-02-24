package modeles;

import controleurs.Attraction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ModeleAttraction {
    private static Bdd uneBdd = new Bdd();

    public static ArrayList<Attraction> selectAllAttractions(String mot) {
        String requete = "";
        if (mot.equals("")) {
            requete = "select * from attraction;";
        } else {
            requete = "select * from attraction where nom like '%"+mot+"%' or prenom like '%"+mot+"%' or email like '%"+mot+"%' or"
                    + " adresse like '%"+mot+"%' or tel like '%"+mot+"%';";
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
}
