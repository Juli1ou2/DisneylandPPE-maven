package controleurs;

import modeles.ModeleAttraction;

import java.util.ArrayList;

public class C_Attraction {


    public static ArrayList<Attraction> selectAllAttractions(String mot) {
        return ModeleAttraction.selectAllAttractions(mot);
    }
}
