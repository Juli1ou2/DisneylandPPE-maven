package controleurs;

import modeles.ModeleTechnicien;

import java.util.ArrayList;

public class C_Technicien {

    public static Technicien selectWhereTechnicien(String email, String mdp) {
        return ModeleTechnicien.selectWhereTechnicien(email, mdp);
    }

    public static ArrayList<Technicien> selectAllTechniciens(String mot) {
        return ModeleTechnicien.selectAllTechniciens(mot);
    }
}
