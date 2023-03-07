package controleurs;

import modeles.ModeleAttraction;

import java.util.ArrayList;

public class C_Attraction {

    public static void insertAttraction(Attraction uneAttraction){
        ModeleAttraction.insertAttraction(uneAttraction);
    }

    public static ArrayList<Attraction> selectAllAttractions(String mot) {
        return ModeleAttraction.selectAllAttractions(mot);
    }

    public static Attraction selectWhereAttraction(String nom) {
        return ModeleAttraction.selectWhereAttraction(nom);
    }

    public static void updateAttraction(Attraction uneAttraction) {
        ModeleAttraction.updateAttraction(uneAttraction);
    }

    public static void deleteAttraction(int idattraction) {
        ModeleAttraction.deleteAttraction(idattraction);
    }
}
