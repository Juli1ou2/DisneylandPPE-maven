package controleurs;

import modeles.ModeleParc;

import java.util.ArrayList;

public class C_Parc {

    public static ArrayList<Parc> selectAllParcs(String mot) {
        return ModeleParc.selectAllParcs(mot);
    }
}
