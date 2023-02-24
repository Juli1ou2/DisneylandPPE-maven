package modeles;

import controleurs.Parc;

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
}
