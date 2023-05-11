package vues;

import controleurs.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PanelRestaurants extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();
    private JPanel panelTable = new JPanel();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btEnregistrer = new JButton("Enregistrer");
    private JTextField txtNom = new JTextField();
    private JTextField txtTheme = new JTextField();
    private JTextField txtEffectifMax = new JTextField();
    private JComboBox<String> cbxAffluence = new JComboBox<String>();
    private JComboBox<String> cbxType = new JComboBox<String>();
    private JTextField txtCapacite = new JTextField();
    private JTextField txtUrl = new JTextField();
    private JComboBox<String> cbxIdUser = new JComboBox<String>();

    //déclaration de table des restaurants
    private JTable tableRestaurants;
    private Tableau unTableau;

    //implémentation d'un filtre
    private JTextField txtMot = new JTextField();
    private JButton btOk = new JButton("OK");

    public PanelRestaurants(Technicien unTechnicien) {
        //construction du panelForm
        this.panelForm.setBounds(40, 80, 300, 300);
        this.panelForm.setLayout(new GridLayout(9, 2));
        this.panelForm.add(new JLabel("Nom : "));
        this.panelForm.add(this.txtNom);
        this.panelForm.add(new JLabel("Thème : "));
        this.panelForm.add(this.txtTheme);
        this.panelForm.add(new JLabel("Effectif max : "));
        this.panelForm.add(this.txtEffectifMax);
        this.panelForm.add(new JLabel("Affluence : "));
        this.panelForm.add(this.cbxAffluence);
        this.panelForm.add(new JLabel("Type : "));
        this.panelForm.add(this.cbxType);
        this.panelForm.add(new JLabel("Capacite : "));
        this.panelForm.add(this.txtCapacite);
        this.panelForm.add(new JLabel("URL : "));
        this.panelForm.add(this.txtUrl);
        this.panelForm.add(new JLabel("Restaurateur : "));
        this.panelForm.add(this.cbxIdUser);
        this.panelForm.add(btAnnuler);
        this.panelForm.add(btEnregistrer);
        this.panelForm.setBackground(Color.white);
        if(unTechnicien.getRoleBis().equals("admin")){
            this.panelForm.setVisible(true);
        } else{
            this.panelForm.setVisible(false);
        }

        this.add(panelForm);

        //construction du panelTable
        this.panelTable.setBounds(400, 80, 500, 300);
        //this.panelTable.setBackground(new Color(255, 184, 51));
        this.panelTable.setLayout(null);
        String entetes[] = {"ID Restaurant", "Nom", "Thème", "Effectif max", "Affluence", "Type", "Capacité",
                "ID Restaurateur"};
        //instanciation de la classe Tableau du controleur
        unTableau = new Tableau(this.obtenirRestaurants(""), entetes);
        this.tableRestaurants = new JTable(unTableau);
        JScrollPane uneScroll = new JScrollPane(this.tableRestaurants);
        uneScroll.setBounds(0, 0, 500, 300);
        this.panelTable.add(uneScroll);

        this.add(panelTable);

        //rendre la JTable écoutable avec la suppression d'une ligne
        this.tableRestaurants.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = 0;
                if (e.getClickCount() == 2) {
                    numLigne = tableRestaurants.getSelectedRow();
                    int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce restaurant ?", "Suppression restaurant", JOptionPane.YES_NO_OPTION);
                    if (retour == 0) {
                        //suppression du restaurant dans la BDD
                        int idrestaurant = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
                        C_Restaurant.deleteRestaurant(idrestaurant);
                        //suppression de la ligne dans le tableau
                        unTableau.supprimerLigne(numLigne);
                    }
                } else if (e.getClickCount() == 1) {
                    numLigne = tableRestaurants.getSelectedRow();
                    txtNom.setText((String) unTableau.getValueAt(numLigne, 1));
                    txtTheme.setText((String) unTableau.getValueAt(numLigne, 2));
                    txtEffectifMax.setText(unTableau.getValueAt(numLigne, 3).toString());
                    cbxAffluence.setSelectedItem(unTableau.getValueAt(numLigne, 4));
                    cbxType.setSelectedItem(unTableau.getValueAt(numLigne, 5));
                    txtCapacite.setText(unTableau.getValueAt(numLigne, 6).toString());
                    cbxIdUser.setSelectedItem(unTableau.getValueAt(numLigne, 7));
                    btEnregistrer.setText("Modifier");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //rendre les boutons écoutables
        this.btAnnuler.addActionListener(this);
        this.btEnregistrer.addActionListener(this);
        this.btOk.addActionListener(this);

        //placement du filtre
        this.txtMot.setBounds(450, 40, 100, 30);
        this.add(this.txtMot);
        this.btOk.setBounds(570, 40, 100, 30);
        this.add(btOk);

        //remplir les comboBox
        this.remplirCBX();
    }

    public void remplirCBX() {
        this.cbxType.removeAllItems();
        this.cbxAffluence.removeAllItems();
        this.cbxIdUser.removeAllItems();
        
        this.cbxType.addItem("Service à Table");
        this.cbxType.addItem("Restauration à Emporter");
        this.cbxAffluence.addItem("Vide");
        this.cbxAffluence.addItem("10%");
        this.cbxAffluence.addItem("20%");
        this.cbxAffluence.addItem("30%");
        this.cbxAffluence.addItem("40%");
        this.cbxAffluence.addItem("50%");
        this.cbxAffluence.addItem("60%");
        this.cbxAffluence.addItem("70%");
        this.cbxAffluence.addItem("80%");
        this.cbxAffluence.addItem("90%");
        this.cbxAffluence.addItem("Pleine");
        ArrayList<Restaurateur> lesRestaurateurs = C_Restaurateur.selectAllRestaurateurs("");
        for (Restaurateur unRestaurateur : lesRestaurateurs) {
            this.cbxIdUser.addItem(unRestaurateur.getIduser() + "-" + unRestaurateur.getPrenom() + " " + unRestaurateur.getNom());
        }
    }

    public Object[][] obtenirRestaurants(String mot){
        ArrayList<Restaurant> lesRestaurants = C_Restaurant.selectAllRestaurants(mot);
        Object [][] matrice = new Object[lesRestaurants.size()][8];

        int i=0;
        for (Restaurant unRestaurant : lesRestaurants) {
            matrice[i][0] = unRestaurant.getIdRestaurant() + "";
            matrice[i][1] = unRestaurant.getNom();
            matrice[i][2] = unRestaurant.getTheme();
            matrice[i][3] = unRestaurant.getEffectifMax();
            matrice[i][4] = unRestaurant.getAffluence();
            matrice[i][5] = unRestaurant.getType();
            matrice[i][6] = unRestaurant.getCapacite();
            matrice[i][7] = unRestaurant.getIduser();
            i++;
        }
        return matrice;
    }

    public void viderChamps() {
        this.txtNom.setText("");
        this.txtTheme.setText("");
        this.txtCapacite.setText("");
        this.txtEffectifMax.setText("");
        this.txtUrl.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btAnnuler) {
            this.viderChamps();
        } else if (e.getSource() == btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
            String nom = this.txtNom.getText();
            String theme = this.txtTheme.getText();
            String url = this.txtUrl.getText();
            
            String affluence = this.cbxAffluence.getSelectedItem().toString();
            String type = this.cbxType.getSelectedItem().toString();
            
            int capacite = 0;
            try{
                capacite = Integer.parseInt(this.txtCapacite.getText());
            } catch (NumberFormatException exp){
                JOptionPane.showMessageDialog(this, "Erreur de saisie de la capacité !");
            }
            int effectifMax = 0;
            try{
                effectifMax = Integer.parseInt(this.txtEffectifMax.getText());
            } catch (NumberFormatException exp){
                JOptionPane.showMessageDialog(this, "Erreur de saisie de la capacité maximale !");
            }
            
            //récupération iduser
            String chaine = this.cbxIdUser.getSelectedItem().toString();
            String tab[] = chaine.split("-");
            int iduser = Integer.parseInt(tab[0]);

            boolean ok = true;
            if (nom.equals("")) {
                this.txtNom.setBackground(Color.red);
                ok = false;
            } else {
                this.txtNom.setBackground(Color.white);
            }

            if (ok) {
                //instanciation d'un restaurant
                Restaurant unRestaurant = new Restaurant(nom, theme, effectifMax, affluence, type, capacite, url, iduser);
                //insertion du new restaurant dans la BDD
                C_Restaurant.insertRestaurant(unRestaurant);
                //actualisation de la table d'affichage des restaurants
                unRestaurant = C_Restaurant.selectWhereRestaurant(nom);
                int idRestaurant = unRestaurant.getIdRestaurant();
                Object ligne[] = {idRestaurant, nom, theme, effectifMax, affluence, type, capacite, url, iduser};
                unTableau.insererLigne(ligne);
                JOptionPane.showMessageDialog(this, "Insertion réussie du restaurant !");
                this.viderChamps();
            }
        } else if (e.getSource() == btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
            String nom = this.txtNom.getText();
            String theme = this.txtTheme.getText();
            String url = this.txtUrl.getText();

            String affluence = this.cbxAffluence.getSelectedItem().toString();
            String type = this.cbxType.getSelectedItem().toString();

            int capacite = 0;
            try{
                capacite = Integer.parseInt(this.txtCapacite.getText());
            } catch (NumberFormatException exp){
                JOptionPane.showMessageDialog(this, "Erreur de saisie de la capacité maximale !");
            }
            int effectifMax = 0;
            try{
                effectifMax = Integer.parseInt(this.txtEffectifMax.getText());
            } catch (NumberFormatException exp){
                JOptionPane.showMessageDialog(this, "Erreur de saisie de l'effectif maximal !");
            }

            //récupération iduser
            String chaine = this.cbxIdUser.getSelectedItem().toString();
            String tab[] = chaine.split("-");
            int iduser = Integer.parseInt(tab[0]);

            boolean ok = true;
            if (nom.equals("")) {
                this.txtNom.setBackground(Color.red);
                ok = false;
            } else {
                this.txtNom.setBackground(Color.white);
            }
            
            if (ok) {
                int numLigne = tableRestaurants.getSelectedRow();
                int idRestaurant = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
                //instanciation d'un restaurant
                Restaurant unRestaurant = new Restaurant(idRestaurant, nom, theme, effectifMax, affluence, type, capacite, url, iduser);
                //modification du restaurant dans la BDD
                C_Restaurant.updateRestaurant(unRestaurant);
                //actualisation de la table d'affichage des Restaurants
                Object ligne[] = {idRestaurant, nom, theme, effectifMax, affluence, type, capacite, url, iduser};
                unTableau.modifierLigne(numLigne, ligne);
                JOptionPane.showMessageDialog(this, "Modification réussie du restaurant !");
                this.viderChamps();
                this.btEnregistrer.setText("Enregistrer"); //on remet le bouton à enregistrer
            }
        } else if(e.getSource() == btOk) {
            String mot = this.txtMot.getText();
            Object [][] matrice = this.obtenirRestaurants(mot);
            unTableau.setDonnees(matrice);
        }
    }
}
