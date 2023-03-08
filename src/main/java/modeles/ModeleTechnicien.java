package modeles;

import controleurs.Technicien;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    public static ArrayList<Technicien> selectAllTechniciens(String mot) {
        String requete = "";
        if (mot.equals("")) {
            requete = "select * from vueTechniciens;";
        } else {
            requete = "select * from vueTechniciens where nom like '%"+mot+"%' or prenom like '%"+mot+"%' or email like '%"+mot+"%' or"
                    + " adresse like '%"+mot+"%' or qualification like '%"+mot+"%' or dateentree like '%"+mot+"%' or tel like '%"+mot+"%';";
        }
        ArrayList<Technicien> lesTechniciens = new ArrayList<Technicien>();
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet desResultats = unStat.executeQuery(requete);
            //parcourir les résultats et construire des objets techniciens
            while (desResultats.next()) {
                Technicien unTechnicien = new Technicien(
                        desResultats.getInt("iduser"),
                        desResultats.getString("nom"),
                        desResultats.getString("prenom"),
                        desResultats.getString("adresse"),
                        desResultats.getString("email"),
                        desResultats.getString("mdp"),
                        desResultats.getString("tel"),
                        desResultats.getString("qualification"),
                        desResultats.getString("dateentree")
                );
                //on ajoute l'instance client dans l'ArrayList
                lesTechniciens.add(unTechnicien);
            }
            unStat.close();
            uneBdd.seDeconnecter();
        } catch(SQLException e) {
            System.out.println("Erreur d'exécution de : " + requete + "\n" + e);
        }
        return lesTechniciens;
    }

    public static void insertTechnicien(Technicien unTechnicien) {
        String requete = "call insertTechnicien ('" +
                unTechnicien.getNom() + "', '" +
                unTechnicien.getPrenom() + "', '" +
                unTechnicien.getAdresse() + "', '" +
                unTechnicien.getEmail() + "', '" +
                unTechnicien.getMdp() + "', '" +
                unTechnicien.getTel() + "', '" +
                unTechnicien.getRole() + "', '" +
                unTechnicien.getQualification() + "', '" +
                unTechnicien.getDateEntree() + "') ;";

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

    public static void deleteTechnicien(int idtechnicien) {
        String requete = "call deleteTechnicien (" + idtechnicien + ") ;";
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

    public static void updateTechnicien(Technicien unTechnicien) {
        String requete = "call updateTechnicien ('" +
                unTechnicien.getNom() + "', '" +
                unTechnicien.getPrenom() + "', '" +
                unTechnicien.getAdresse() + "', '" +
                unTechnicien.getEmail() + "', '" +
                unTechnicien.getMdp() + "', '" +
                unTechnicien.getTel() + "', '" +
                unTechnicien.getRole() + "', '" +
                unTechnicien.getQualification() + "', '" +
                unTechnicien.getDateEntree() + "', '" +
                unTechnicien.getIduser() + "')";

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
