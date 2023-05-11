package vues;

import controleurs.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

public class PanelAttractions extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();
    private JPanel panelTable = new JPanel();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btEnregistrer = new JButton("Enregistrer");
    private JTextField txtNom = new JTextField();
    private JComboBox<String> cbxStatut = new JComboBox<String>();
    private JComboBox<String> cbxType = new JComboBox<String>();
    private JTextField txtCapacite = new JTextField();
    private JComboBox<String> cbxAffluence = new JComboBox<String>();
    private JTextField txtPrix = new JTextField();
    private JTextField txtHeureOuv = new JTextField();
    private JTextField txtHeureFerm = new JTextField();
    private JTextField txtUrl = new JTextField();
    private JComboBox<String> cbxIdParc = new JComboBox<String>();
    private JComboBox<String> cbxIdUser = new JComboBox<String>();

    //déclaration de table des attractions
    private JTable tableAttractions;
    private Tableau unTableau;

    //implémentation d'un filtre
    private JTextField txtMot = new JTextField();
    private JButton btOk = new JButton("OK");

    public PanelAttractions(Technicien unTechnicien) {

        //construction du panelForm
        this.panelForm.setBounds(40, 80, 300, 300);
        this.panelForm.setLayout(new GridLayout(12, 2));
        this.panelForm.add(new JLabel("Nom : "));
        this.panelForm.add(this.txtNom);
        this.panelForm.add(new JLabel("Statut : "));
        this.panelForm.add(this.cbxStatut);
        this.panelForm.add(new JLabel("Type : "));
        this.panelForm.add(this.cbxType);
        this.panelForm.add(new JLabel("Capacité : "));
        this.panelForm.add(this.txtCapacite);
        this.panelForm.add(new JLabel("Affluence : "));
        this.panelForm.add(this.cbxAffluence);
        this.panelForm.add(new JLabel("Prix : "));
        this.panelForm.add(this.txtPrix);
        this.panelForm.add(new JLabel("Heure d'ouverture : "));
        this.panelForm.add(this.txtHeureOuv);
        this.panelForm.add(new JLabel("Heure de fermeture : "));
        this.panelForm.add(this.txtHeureFerm);
        this.panelForm.add(new JLabel("URL de l'image : "));
        this.panelForm.add(this.txtUrl);
        this.panelForm.add(new JLabel("Parc : "));
        this.panelForm.add(this.cbxIdParc);
        this.panelForm.add(new JLabel("Technicien : "));
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
        String entetes[] = {"ID Attraction", "Nom", "Statut", "Type", "Capacité", "Affluence", "Prix",
                "Heure d'ouverture", "Heure de fermeture", "ID Parc", "ID Technicien"};
        //instanciation de la classe Tableau du controleur
        unTableau = new Tableau(this.obtenirAttractions(""), entetes);
        this.tableAttractions = new JTable(unTableau);
        JScrollPane uneScroll = new JScrollPane(this.tableAttractions);
        uneScroll.setBounds(0, 0, 500, 300);
        this.panelTable.add(uneScroll);

        this.add(panelTable);

        //rendre la JTable écoutable avec la suppression d'une ligne
        this.tableAttractions.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = 0;
                if (e.getClickCount() == 2) {
                    numLigne = tableAttractions.getSelectedRow();
                    int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette attraction ?", "Suppression attraction", JOptionPane.YES_NO_OPTION);
                    if (retour == 0) {
                        //suppression de l'attraction dans la BDD
                        int idattraction = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
                        C_Attraction.deleteAttraction(idattraction);
                        //suppression de la ligne dans le tableau
                        unTableau.supprimerLigne(numLigne);
                    }
                } else if (e.getClickCount() == 1) {
                    numLigne = tableAttractions.getSelectedRow();
                    txtNom.setText((String) unTableau.getValueAt(numLigne, 1));
                    cbxStatut.setSelectedItem(unTableau.getValueAt(numLigne, 2));
                    cbxType.setSelectedItem(unTableau.getValueAt(numLigne, 3));
                    txtCapacite.setText(unTableau.getValueAt(numLigne, 4).toString());
                    cbxAffluence.setSelectedItem(unTableau.getValueAt(numLigne, 5));
                    txtPrix.setText(unTableau.getValueAt(numLigne, 6).toString());
                    txtHeureOuv.setText((String) unTableau.getValueAt(numLigne, 7));
                    txtHeureFerm.setText((String) unTableau.getValueAt(numLigne, 8));
                    cbxIdParc.setSelectedItem(unTableau.getValueAt(numLigne, 9));
                    cbxIdUser.setSelectedItem(unTableau.getValueAt(numLigne, 10));
                    btEnregistrer.setText("Modifier");
                }
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
        this.cbxStatut.removeAllItems();
        this.cbxType.removeAllItems();
        this.cbxAffluence.removeAllItems();
        this.cbxIdParc.removeAllItems();
        this.cbxIdUser.removeAllItems();

        this.cbxStatut.addItem("Ouverte");
        this.cbxStatut.addItem("Fermée");
        this.cbxStatut.addItem("En Travaux");
        this.cbxType.addItem("Spectacle");
        this.cbxType.addItem("Montagne Russe");
        this.cbxType.addItem("Manège");
        this.cbxType.addItem("Dark Ride");
        this.cbxType.addItem("Simulateur de vol");
        this.cbxType.addItem("Chute dans le vide");
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
        ArrayList<Parc> lesParcs = C_Parc.selectAllParcs("");
        for (Parc unParc : lesParcs) {
            this.cbxIdParc.addItem(unParc.getIdParc() + "-" + unParc.getNom());
        }
        ArrayList<Technicien> lesTechniciens = C_Technicien.selectAllTechniciens("");
        for (Technicien unTechnicien : lesTechniciens) {
            this.cbxIdUser.addItem(unTechnicien.getIduser() + "-" + unTechnicien.getPrenom() + " " + unTechnicien.getNom());
        }
    }

    public Object[][] obtenirAttractions(String mot){
        ArrayList<Attraction> lesAttractions = C_Attraction.selectAllAttractions(mot);
        Object [][] matrice = new Object[lesAttractions.size()][11];

        int i=0;
        for (Attraction uneAttraction : lesAttractions) {
            matrice[i][0] = uneAttraction.getIdAttraction() + "";
            matrice[i][1] = uneAttraction.getNom();
            matrice[i][2] = uneAttraction.getStatus();
            matrice[i][3] = uneAttraction.getType();
            matrice[i][4] = uneAttraction.getCapacite();
            matrice[i][5] = uneAttraction.getAffluence();
            matrice[i][6] = uneAttraction.getPrix();
            matrice[i][7] = uneAttraction.getHeureOuv();
            matrice[i][8] = uneAttraction.getHeureFerm();
            matrice[i][9] = uneAttraction.getIdParc();
            matrice[i][10] = uneAttraction.getIduser();
            i++;
        }
        return matrice;
    }

    public void viderChamps() {
        this.txtNom.setText("");
        this.txtCapacite.setText("");
        this.txtPrix.setText("");
        this.txtHeureOuv.setText("");
        this.txtHeureFerm.setText("");
        this.txtUrl.setText("");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btAnnuler) {
            this.viderChamps();
        } else if (e.getSource() == btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
            String nom = this.txtNom.getText();
            String statut = this.cbxStatut.getSelectedItem().toString();
            String type = this.cbxType.getSelectedItem().toString();
            int capacite = 0;
            try{
                capacite = Integer.parseInt(this.txtCapacite.getText());
            } catch (NumberFormatException exp){
                JOptionPane.showMessageDialog(this, "Erreur de saisie de la capacité maximale !");
            }
            String affluence = this.cbxAffluence.getSelectedItem().toString();
            float prix = 0;
            try{
                prix = Float.parseFloat(this.txtPrix.getText());
            } catch (NumberFormatException exp){
                JOptionPane.showMessageDialog(this, "Erreur de saisie du prix !");
            }
            String heureOuv = this.txtHeureOuv.getText();
            String heureFerm = this.txtHeureFerm.getText();
            String url = this.txtUrl.getText();
            //récupération des idparc et iduser
            String chaine = this.cbxIdParc.getSelectedItem().toString();
            String tab[] = chaine.split("-");
            int idParc = Integer.parseInt(tab[0]);
            chaine = this.cbxIdUser.getSelectedItem().toString();
            tab = chaine.split("-");
            int idUser = Integer.parseInt(tab[0]);

            boolean ok = true;
            if (nom.equals("")) {
                this.txtNom.setBackground(Color.red);
                ok = false;
            } else {
                this.txtNom.setBackground(Color.white);
            }

            if (ok) {
                //instanciation d'une attraction
                Attraction uneAttraction = new Attraction(0, nom, statut, type, capacite, affluence, prix,
                        heureOuv, heureFerm, url, idParc, idUser);
                //insertion de la new attraction dans la BDD
                C_Attraction.insertAttraction(uneAttraction);
                //actualisation de la table d'affichage des attractions
                uneAttraction = C_Attraction.selectWhereAttraction(nom);
                int idattraction = uneAttraction.getIdAttraction();
                Object ligne[] = {idattraction, nom, statut, type, capacite, affluence, prix,
                        heureOuv, heureFerm, url, idParc, idUser};
                unTableau.insererLigne(ligne);
                JOptionPane.showMessageDialog(this, "Insertion réussie de l'attraction !");
                this.viderChamps();
            }
        } else if (e.getSource() == btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
            String nom = this.txtNom.getText();
            String statut = this.cbxStatut.getSelectedItem().toString();
            String type = this.cbxType.getSelectedItem().toString();
            int capacite = 0;
            try{
                capacite = Integer.parseInt(this.txtCapacite.getText());
            } catch (NumberFormatException exp){
                JOptionPane.showMessageDialog(this, "Erreur de saisie de la capacité !");
            }
            String affluence = this.cbxAffluence.getSelectedItem().toString();
            float prix = 0;
            try{
                prix = Float.parseFloat(this.txtPrix.getText());
            } catch (NumberFormatException exp){
                JOptionPane.showMessageDialog(this, "Erreur de saisie du prix !");
            }
            String heureOuv = this.txtHeureOuv.getText();
            String heureFerm = this.txtHeureFerm.getText();
            String url = this.txtUrl.getText();
            //récupération des idparc et iduser
            String chaine = this.cbxIdParc.getSelectedItem().toString();
            String tab[] = chaine.split("-");
            int idParc = Integer.parseInt(tab[0]);
            chaine = this.cbxIdUser.getSelectedItem().toString();
            tab = chaine.split("-");
            int idUser = Integer.parseInt(tab[0]);

            boolean ok = true;
            if (nom.equals("")) {
                this.txtNom.setBackground(Color.red);
                ok = false;
            } else {
                this.txtNom.setBackground(Color.white);
            }

            if (ok) {
                int numLigne = tableAttractions.getSelectedRow();
                int idAttraction = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
                //instanciation d'une attraction
                Attraction uneAttraction = new Attraction(idAttraction, nom, statut, type, capacite, affluence, prix,
                        heureOuv, heureFerm, url, idParc, idUser);
                //modification de l'attraction dans la BDD
                C_Attraction.updateAttraction(uneAttraction);
                //actualisation de la table d'affichage des attractions
                Object ligne[] = {idAttraction, nom, statut, type, capacite, affluence, prix,
                        heureOuv, heureFerm, url, idParc, idUser};
                unTableau.modifierLigne(numLigne, ligne);
                JOptionPane.showMessageDialog(this, "Modification réussie de l'attraction !");
                this.viderChamps();
                this.btEnregistrer.setText("Enregistrer"); //on remet le bouton à enregistrer
            }
        } else if(e.getSource() == btOk) {
            String mot = this.txtMot.getText();
            Object [][] matrice = this.obtenirAttractions(mot);
            unTableau.setDonnees(matrice);
        }
    }
}
