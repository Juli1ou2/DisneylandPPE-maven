package vues;

import controleurs.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    public PanelAttractions() {
        super(Color.blue);

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
        this.panelForm.setVisible(true);

        this.add(panelForm);

        //construction du panelTable
        this.panelTable.setBounds(400, 80, 500, 300);
        this.panelTable.setBackground(new Color(255, 184, 51));
        this.panelTable.setLayout(null);
        String entetes[] = {"ID Attraction", "Nom", "Statut", "Type", "Capacité", "Affluence", "Prix",
                "Heure d'ouverture", "Heure de fermeture", "ID Parc", "ID Technicien"};
        //instanciation de la classe Tableau du controleur
        unTableau = new Tableau(this.obtenirAttractions(""), entetes);
        this.tableAttractions = new JTable(unTableau);
        JScrollPane uneScroll = new JScrollPane(this.tableAttractions);
        uneScroll.setBounds(20, 20, 400, 250);
        this.panelTable.add(uneScroll);

        this.add(panelTable);

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

    }
}
