package vues;

import controleurs.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PanelTransports extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();
    private JPanel panelTable = new JPanel();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btEnregistrer = new JButton("Enregistrer");
    private JTextField txtLibelle = new JTextField();
    private JTextField txtType = new JTextField();
    private JTextField txtCapacite = new JTextField();
    private JComboBox<String> cbxAffluence = new JComboBox<String>();
    private JTextField txtPrix = new JTextField();
    private JTextField txtHoraire = new JTextField();

    //déclaration de table des transports
    private JTable tableTransports;
    private Tableau unTableau;

    //implémentation d'un filtre
    private JTextField txtMot = new JTextField();
    private JButton btOk = new JButton("OK");

    public PanelTransports() {

        //construction du panelForm
        this.panelForm.setBounds(40, 80, 300, 300);
        this.panelForm.setLayout(new GridLayout(7, 2));
        this.panelForm.add(new JLabel("Libelle  : "));
        this.panelForm.add(this.txtLibelle );
        this.panelForm.add(new JLabel("Type : "));
        this.panelForm.add(this.txtType);
        this.panelForm.add(new JLabel("Capacité : "));
        this.panelForm.add(this.txtCapacite);
        this.panelForm.add(new JLabel("Affluence : "));
        this.panelForm.add(this.cbxAffluence);
        this.panelForm.add(new JLabel("Horaire : "));
        this.panelForm.add(this.txtHoraire);
        this.panelForm.add(new JLabel("Prix : "));
        this.panelForm.add(this.txtPrix);

        this.panelForm.add(btAnnuler);
        this.panelForm.add(btEnregistrer);
        this.panelForm.setBackground(Color.white);
        this.panelForm.setVisible(true);

        this.add(panelForm);

        //construction du panelTable
        this.panelTable.setBounds(400, 80, 500, 300);
        //this.panelTable.setBackground(new Color(255, 184, 51));
        this.panelTable.setLayout(null);
        String entetes[] = {"ID Transport", "Libelle ", "Type ", "Capacité", "Affluence", "Horaire", "Prix"};
        //instanciation de la classe Tableau du controleur
        unTableau = new Tableau(this.obtenirTransports(""), entetes);
        this.tableTransports = new JTable(unTableau);
        JScrollPane uneScroll = new JScrollPane(this.tableTransports);
        uneScroll.setBounds(0, 0, 500, 300);
        this.panelTable.add(uneScroll);

        this.add(panelTable);

        //rendre la JTable écoutable avec la suppression d'une ligne
        this.tableTransports.addMouseListener(new MouseListener() {

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
                    numLigne = tableTransports.getSelectedRow();
                    int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce transport ?", "Suppression transport", JOptionPane.YES_NO_OPTION);
                    if (retour == 0) {
                        //suppression de Transport dans la BDD
                        int idTransport = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
                        C_Transport.deleteTransport(idTransport);
                        //suppression de la ligne dans le tableau
                        unTableau.supprimerLigne(numLigne);
                    }
                } else if (e.getClickCount() == 1) {
                    numLigne = tableTransports.getSelectedRow();
                    txtLibelle.setText((String) unTableau.getValueAt(numLigne, 1));
                    txtType.setText((String) unTableau.getValueAt(numLigne, 2));
                    txtCapacite.setText(unTableau.getValueAt(numLigne, 3).toString());
                    cbxAffluence.setSelectedItem(unTableau.getValueAt(numLigne, 4));
                    txtHoraire.setText(unTableau.getValueAt(numLigne, 5).toString());
                    txtPrix.setText(unTableau.getValueAt(numLigne, 6).toString());

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
        this.cbxAffluence.removeAllItems();


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
    }


    public Object[][] obtenirTransports(String mot){
        ArrayList<Transport> lesTransports = C_Transport.selectAllTransports(mot);
        Object [][] matrice = new Object[lesTransports.size()][11];
        int i=0;
        for (Transport unTransport : lesTransports) {
            matrice[i][0] = unTransport.getIdTransport() + "";
            matrice[i][1] = unTransport.getLibelle();
            matrice[i][2] = unTransport.getType();
            matrice[i][3] = unTransport.getCapacite();
            matrice[i][4] = unTransport.getAffluence();
            matrice[i][5] = unTransport.getHoraire();
            matrice[i][6] = unTransport.getPrix();
            i++;
        }
        return matrice;
    }

    public void viderChamps() {
        this.txtLibelle.setText("");
        this.txtType.setText("");
        this.txtCapacite.setText("");
        this.txtHoraire.setText("");
        this.txtPrix.setText("");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btAnnuler) {
            this.viderChamps();
        } else if (e.getSource() == btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
            String Libelle = this.txtLibelle.getText();
            String Type = this.txtType.getText();
            int capacite = 0;
            try{
                capacite = Integer.parseInt(this.txtCapacite.getText());
            } catch (NumberFormatException exp){
                JOptionPane.showMessageDialog(this, "Erreur de saisie de la capacité maximale !");
            }
            String affluence = this.cbxAffluence.getSelectedItem().toString();
            String horaire  = this.txtHoraire.getText();
            float prix = 0;
            try{
                prix = Float.parseFloat(this.txtPrix.getText());
            } catch (NumberFormatException exp){
                JOptionPane.showMessageDialog(this, "Erreur de saisie du prix !");
            }

            boolean ok = true;
            if (Libelle.equals("")) {
                this.txtLibelle.setBackground(Color.red);
                ok = false;
            } else {
                this.txtLibelle.setBackground(Color.white);
            }


            if (ok) {
                //instanciation d'un Transport
                Transport unTransport = new Transport(0, Libelle, Type, capacite  , affluence , horaire , prix);
                //insertion de Transport dans la BDD
                C_Transport.insertTransport(unTransport);
                //actualisation de la table d'affichage des Transports
                unTransport = C_Transport.selectWhereTransport(Libelle);
                int idTransport = unTransport.getIdTransport();
                Object ligne[] = {idTransport, Libelle , Type , capacite  , affluence , capacite, horaire , prix};
                unTableau.insererLigne(ligne);
                JOptionPane.showMessageDialog(this, "Insertion réussie de transport !");
                this.viderChamps();
            }
        } else if (e.getSource() == btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
            String libelle = this.txtLibelle.getText();
            String type = this.txtType.getText();
            int capacite = 0;
            try{
                capacite = Integer.parseInt(this.txtCapacite.getText());
            } catch (NumberFormatException exp){
                JOptionPane.showMessageDialog(this, "Erreur de saisie de la capacité !");
            }
            String affluence = this.cbxAffluence.getSelectedItem().toString();
            String horaire = this.txtHoraire.getText();

            float prix = 0;
            try{
                prix = Float.parseFloat(this.txtPrix.getText());
            } catch (NumberFormatException exp){
                JOptionPane.showMessageDialog(this, "Erreur de saisie du prix !");
            }


            boolean ok = true;
            if (libelle.equals("")) {
                this.txtLibelle.setBackground(Color.red);
                ok = false;
            } else {
                this.txtLibelle.setBackground(Color.white);
            }

            if (ok) {
                int numLigne = tableTransports.getSelectedRow();
                int idTransport = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
                //instanciation d'un transport
                Transport unTransport = new Transport(idTransport, libelle ,type , capacite  , affluence , horaire , prix);
                //modification de Transport dans la BDD
                C_Transport.updateTransport(unTransport);
                //actualisation de la table d'affichage des transports
                Object ligne[] = {idTransport, libelle, type, capacite, affluence, horaire, prix };
                unTableau.modifierLigne(numLigne, ligne);
                JOptionPane.showMessageDialog(this, "Modification réussie de Transport !");
                this.viderChamps();
                this.btEnregistrer.setText("Enregistrer"); //on remet le bouton à enregistrer
            }
        } else if(e.getSource() == btOk) {
            String mot = this.txtMot.getText();
            Object [][] matrice = this.obtenirTransports(mot);
            unTableau.setDonnees(matrice);
        }
    }
}
