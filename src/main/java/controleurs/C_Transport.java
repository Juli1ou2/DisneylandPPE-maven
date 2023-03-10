package controleurs;

import modeles.ModeleTransport;

import java.util.ArrayList;

public class C_Transport {


    public static void insertTransport(Transport unTransport){
        ModeleTransport.insertTransport(unTransport);
    }

    public static ArrayList<Transport> selectAllTransports(String mot) {
        return ModeleTransport.selectAllTransports(mot);
    }

    public static Transport selectWhereTransport(String libelle) {
        return ModeleTransport.selectWhereTransport(libelle);
    }

    public static void updateTransport(Transport unTransport) {
        ModeleTransport.updateTransport(unTransport);
    }

    public static void deleteTransport(int idTransport) {
        ModeleTransport.deleteTransport(idTransport);
    }
    
}
