package controleurs;

import modeles.ModeleRestaurant;

import java.util.ArrayList;

public class C_Restaurant {

    public static void deleteRestaurant(int idrestaurant) {
        ModeleRestaurant.deleteRestaurant(idrestaurant);
    }

    public static ArrayList<Restaurant> selectAllRestaurants(String mot) {
        return ModeleRestaurant.selectAllRestaurants(mot);
    }

    public static void insertRestaurant(Restaurant unRestaurant) {
        ModeleRestaurant.insertRestaurant(unRestaurant);
    }

    public static Restaurant selectWhereRestaurant(String nom) {
        return ModeleRestaurant.selectWhereRestaurant(nom);
    }

    public static void updateRestaurant(Restaurant unRestaurant) {
        ModeleRestaurant.updateRestaurant(unRestaurant);
    }
}
