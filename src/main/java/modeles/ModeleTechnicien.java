package modeles;

import controleurs.Technicien;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeleTechnicien {
    private static Bdd uneBdd = new Bdd();

    public static Technicien selectWhereTechnicien(String email, String mdp) {
        String requete = "select * from vueTechniciens where email ='" + email + "' and mdp = '" + mdp + "';";
        Technicien leTechnicien = null;
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unResultat = unStat.executeQuery(requete);
            //tester si il y a un technicien
            if (unResultat.next()) {
                leTechnicien = new Technicien(
                        unResultat.getInt("iduser"),
                        unResultat.getString("nom"),
                        unResultat.getString("prenom"),
                        unResultat.getString("adresse"),
                        unResultat.getString("email"),
                        unResultat.getString("mdp"),
                        unResultat.getString("tel"),
                        "technicien",
                        unResultat.getString("qualification"),
                        unResultat.getString("dateEntree")
                );
            }
            unStat.close();
            uneBdd.seDeconnecter();
        } catch(SQLException e) {
            System.out.println("Erreur d'exécution de : " + requete + "\n" + e);
        }
        return leTechnicien;
    }
}
