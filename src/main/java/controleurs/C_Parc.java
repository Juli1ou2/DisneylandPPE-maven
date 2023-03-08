package controleurs;

import modeles.ModeleParc;

import java.util.ArrayList;

public class C_Parc {

    public static ArrayList<Parc> selectAllParcs(String mot) {
        return ModeleParc.selectAllParcs(mot);
    }

    public static void deleteParc(int idparc) {
        ModeleParc.deleteParc(idparc);
    }

    public static Parc selectWhereParc(String nom) {
        return ModeleParc.selectWhereParc(nom);
    }

    public static void insertParc(Parc unParc) {
        ModeleParc.insertParc(unParc);
    }

    public static void updateParc(Parc unParc) {
        ModeleParc.updateParc(unParc);
    }
}
