package controleurs;

import modeles.ModeleTechnicien;

public class C_Technicien {

    public static Technicien selectWhereTechnicien(String email, String mdp) {
        return ModeleTechnicien.selectWhereTechnicien(email, mdp);
    }
}
