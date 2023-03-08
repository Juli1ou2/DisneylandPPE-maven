package modeles;

import controleurs.Restaurateur;
import controleurs.Technicien;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ModeleRestaurateur {
    private static Bdd uneBdd = new Bdd();

    public static ArrayList<Restaurateur> selectAllRestaurateurs(String mot) {
        String requete = "";
        if (mot.equals("")) {
            requete = "select * from vueRestaurateurs;";
        } else {
            requete = "select * from vueRestaurateurs where nom like '%"+mot+"%' or prenom like '%"+mot+"%' or email like '%"+mot+"%' or"
                    + " adresse like '%"+mot+"%' or qualification like '%"+mot+"%' or anciennete like '%"+mot+"%' or tel like '%"+mot+"%';";
        }
        ArrayList<Restaurateur> lesRestaurateurs = new ArrayList<Restaurateur>();
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet desResultats = unStat.executeQuery(requete);
            //parcourir les résultats et construire des objets restaurateurs
            while (desResultats.next()) {
                Restaurateur unRestaurateur = new Restaurateur(
                        desResultats.getInt("iduser"),
                        desResultats.getString("nom"),
                        desResultats.getString("prenom"),
                        desResultats.getString("adresse"),
                        desResultats.getString("email"),
                        desResultats.getString("mdp"),
                        desResultats.getString("tel"),
                        desResultats.getString("qualification"),
                        desResultats.getString("anciennete")
                );
                //on ajoute l'instance client dans l'ArrayList
                lesRestaurateurs.add(unRestaurateur);
            }
            unStat.close();
            uneBdd.seDeconnecter();
        } catch(SQLException e) {
            System.out.println("Erreur d'exécution de : " + requete + "\n" + e);
        }
        return lesRestaurateurs;
    }

    public static void insertRestaurateur(Restaurateur unRestaurateur) {
        String requete = "call insertRestaurateur ('" +
                unRestaurateur.getNom() + "', '" +
                unRestaurateur.getPrenom() + "', '" +
                unRestaurateur.getAdresse() + "', '" +
                unRestaurateur.getEmail() + "', '" +
                unRestaurateur.getMdp() + "', '" +
                unRestaurateur.getTel() + "', '" +
                unRestaurateur.getRole() + "', '" +
                unRestaurateur.getQualification() + "', '" +
                unRestaurateur.getAnciennete() + "') ;";

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

    public static Restaurateur selectWhereRestaurateur(String email, String mdp) {
        String requete = "select * from vueRestaurateurs where email ='" + email + "' and mdp = '" + mdp + "';";
        Restaurateur leRestaurateur = null;
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unResultat = unStat.executeQuery(requete);
            //tester si il y a un Restaurateur
            if (unResultat.next()) {
                leRestaurateur = new Restaurateur(
                        unResultat.getInt("iduser"),
                        unResultat.getString("nom"),
                        unResultat.getString("prenom"),
                        unResultat.getString("adresse"),
                        unResultat.getString("email"),
                        unResultat.getString("mdp"),
                        unResultat.getString("tel"),
                        unResultat.getString("qualification"),
                        unResultat.getString("anciennete")
                );
            }
            unStat.close();
            uneBdd.seDeconnecter();
        } catch(SQLException e) {
            System.out.println("Erreur d'exécution de : " + requete + "\n" + e);
        }
        return leRestaurateur;
    }

    public static void updateRestaurateur(Restaurateur unRestaurateur) {
        String requete = "call updateRestaurateur ('" +
                unRestaurateur.getNom() + "', '" +
                unRestaurateur.getPrenom() + "', '" +
                unRestaurateur.getAdresse() + "', '" +
                unRestaurateur.getEmail() + "', '" +
                unRestaurateur.getMdp() + "', '" +
                unRestaurateur.getTel() + "', '" +
                //unRestaurateur.getRole() + "', '" +
                unRestaurateur.getQualification() + "', '" +
                unRestaurateur.getAnciennete() + "', '" +
                unRestaurateur.getIduser() + "')";

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

    public static void deleteRestaurateur(int idrestaurateur) {
        String requete = "call deleteRestaurateur (" + idrestaurateur + ") ;";
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
