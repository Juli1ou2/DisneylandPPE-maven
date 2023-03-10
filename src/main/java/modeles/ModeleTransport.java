package modeles;

import controleurs.Transport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ModeleTransport {
    private static Bdd uneBdd = new Bdd();

    public static void insertTransport(Transport unTransport) {
        String requete = "insert into transport values(null, '"+
                unTransport.getLibelle ()+"','"+
                unTransport.getType()+"','" +
                unTransport.getCapacite()+"','"+
                unTransport.getAffluence()+"','"+
                unTransport.getHoraire()+"','"+
                unTransport.getPrix()+"');";
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

    public static ArrayList<Transport> selectAllTransports(String mot) {
        String requete = "";
        if (mot.equals("")) {
            requete = "select * from transport;";
        } else {
            requete = "select * from transport where libelle like '%"+mot+"%' or type like '%"+mot+"%' or"
                    + " capacite like '%"+mot+"%' or affluence like '%"+mot+"%' or horaire like '%+"+mot+"%' or prix like '%\"+mot+\"%';";
        }
        ArrayList<Transport> lesTransports = new ArrayList<Transport>();
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet desResultats = unStat.executeQuery(requete);
            //parcourir les résultats et construire des objets Transports
            while (desResultats.next()) {
                Transport unTransport = new Transport(
                        desResultats.getInt("idTransport"),
                        desResultats.getString("libelle"),
                        desResultats.getString("type"),
                        desResultats.getInt("capacite"),
                        desResultats.getString("affluence"),
                        desResultats.getString("horaire"),
                        desResultats.getFloat("prix")
                );
                //on ajoute l'instance transport dans l'ArrayList
                lesTransports.add(unTransport);
            }
            unStat.close();
            uneBdd.seDeconnecter();
        } catch(SQLException e) {
            System.out.println("Erreur d'exécution de : " + requete + "\n" + e);
        }
        return lesTransports;
    }

    public static Transport selectWhereTransport(String libelle) {
        String requete = "select * from transport where libelle ='" + libelle + "';";
        Transport leTransport = null;
        try {
            uneBdd.seConnecter();
            Statement unStat = uneBdd.getMaConnexion().createStatement();
            ResultSet unResultat = unStat.executeQuery(requete);
            //tester si il y a un transport
            if (unResultat.next()) {
                leTransport = new Transport(
                        unResultat.getInt("idTransport"),
                        unResultat.getString("libelle"),
                        unResultat.getString("type"),
                        unResultat.getInt("capacite"),
                        unResultat.getString("affluence"),
                        unResultat.getString("horaire"),
                        unResultat.getFloat("prix")
                );
            }
            unStat.close();
            uneBdd.seDeconnecter();
        } catch(SQLException e) {
            System.out.println("Erreur d'exécution de : " + requete + "\n" + e);
        }
        return leTransport;
    }

    public static void updateTransport(Transport unTransport) {
        String requete = "update transport set libelle = '"+
                unTransport.getLibelle ()+"', type ='"+
                unTransport.getType()+"', capacite ='" +
                unTransport.getCapacite()+"', affluence ='"+
                unTransport.getAffluence()+"', horaire ='"+
                unTransport.getHoraire()+"', prix ='"+
                unTransport.getPrix()+"' where idTransport = " + unTransport.getIdTransport() + ";";
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

    public static void deleteTransport(int idTransport) {
        String requete = "delete from transport where idTransport = " + idTransport + ";";
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
