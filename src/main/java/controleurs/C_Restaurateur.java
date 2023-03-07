package controleurs;

import modeles.ModeleRestaurateur;

import java.util.ArrayList;

public class C_Restaurateur {

    public static ArrayList<Restaurateur> selectAllRestaurateurs(String mot) {
        return ModeleRestaurateur.selectAllRestaurateurs(mot);
    }

    public static void insertRestaurateur(Restaurateur unRestaurateur) {
        ModeleRestaurateur.insertRestaurateur(unRestaurateur);
    }

    public static Restaurateur selectWhereRestaurateur(String email, String mdp) {
        return ModeleRestaurateur.selectWhereRestaurateur(email, mdp);
    }

    public static void updateRestaurateur(Restaurateur unRestaurateur) {
        ModeleRestaurateur.updateRestaurateur(unRestaurateur);
    }

    public static void deleteRestaurateur(int idrestaurateur) {
        ModeleRestaurateur.deleteRestaurateur(idrestaurateur);
    }
}
