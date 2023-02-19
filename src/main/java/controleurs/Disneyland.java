package controleurs;

import vues.VueConnexion;
import vues.VueGenerale;

public class Disneyland {
    private static VueConnexion uneVueConnexion;
    private static VueGenerale uneVueGenerale;

    public static void main(String[] args){
        uneVueConnexion = new VueConnexion();
    }

    public static void gererVueConnexion(boolean action) {
        Disneyland.uneVueConnexion.setVisible(action);
    }

    public static void gererVueGenerale(boolean action, Technicien unTechnicien) {
        uneVueGenerale = new VueGenerale(unTechnicien);
        Disneyland.uneVueGenerale.setVisible(action);
    }
}
