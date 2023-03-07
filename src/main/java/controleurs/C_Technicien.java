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

    public static void insertTechnicien(Technicien unTechnicien) {
        ModeleTechnicien.insertTechnicien(unTechnicien);
    }

    public static void deleteTechnicien(int idtechnicien) {
        ModeleTechnicien.deleteTechnicien(idtechnicien);
    }

    public static void updateTechnicien(Technicien unTechnicien) {
        ModeleTechnicien.updateTechnicien(unTechnicien);
    }
}
