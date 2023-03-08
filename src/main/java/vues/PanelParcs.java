package vues;

import controleurs.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PanelParcs extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();
    private JPanel panelTable = new JPanel();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btEnregistrer = new JButton("Enregistrer");
    private JTextField txtNom = new JTextField();
    private JTextField txtCapacite = new JTextField();
    private JTextField txtNbAttractionsTotales = new JTextField();
    private JTextField txtNbAttractionsFonctionnelles = new JTextField();
    
    //déclaration de table des parcs
    private JTable tableParcs;
    private Tableau unTableau;

    //implémentation d'un filtre
    private JTextField txtMot = new JTextField();
    private JButton btOk = new JButton("OK");
    
    public PanelParcs() {
        //construction du panelForm
        this.panelForm.setBounds(40, 80, 300, 300);
        this.panelForm.setLayout(new GridLayout(5, 2));
        this.panelForm.add(new JLabel("Nom : "));
        this.panelForm.add(this.txtNom);
        this.panelForm.add(new JLabel("Capacite : "));
        this.panelForm.add(this.txtCapacite);
        this.panelForm.add(new JLabel("Nombre d'attractions totales : "));
        this.panelForm.add(this.txtNbAttractionsTotales);
        this.panelForm.add(new JLabel("Nombre d'attractions fonctionnelles : "));
        this.panelForm.add(this.txtNbAttractionsFonctionnelles);
        this.panelForm.add(btAnnuler);
        this.panelForm.add(btEnregistrer);
        this.panelForm.setBackground(Color.white);
        this.panelForm.setVisible(true);

        this.add(panelForm);

        //construction du panelTable
        this.panelTable.setBounds(400, 80, 500, 300);
        //this.panelTable.setBackground(new Color(255, 184, 51));
        this.panelTable.setLayout(null);
        String entetes[] = {"ID Parc", "Nom", "Capacité", "Total attractions", "Total attractions fonctionnelles"};
        //instanciation de la classe Tableau du controleur
        unTableau = new Tableau(this.obtenirParcs(""), entetes);
        this.tableParcs = new JTable(unTableau);
        JScrollPane uneScroll = new JScrollPane(this.tableParcs);
        uneScroll.setBounds(0, 0, 500, 300);
        this.panelTable.add(uneScroll);

        this.add(panelTable);

        //rendre la JTable écoutable avec la suppression d'une ligne
        this.tableParcs.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = 0;
                if (e.getClickCount() == 2) {
                    numLigne = tableParcs.getSelectedRow();
                    int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce parc ?", "Suppression parc", JOptionPane.YES_NO_OPTION);
                    if (retour == 0) {
                        //suppression du parc dans la BDD
                        int idparc = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
                        C_Parc.deleteParc(idparc);
                        //suppression de la ligne dans le tableau
                        unTableau.supprimerLigne(numLigne);
                    }
                } else if (e.getClickCount() == 1) {
                    numLigne = tableParcs.getSelectedRow();
                    txtNom.setText((String) unTableau.getValueAt(numLigne, 1));
                    txtCapacite.setText(unTableau.getValueAt(numLigne, 2).toString());
                    txtNbAttractionsTotales.setText(unTableau.getValueAt(numLigne, 3).toString());
                    txtNbAttractionsFonctionnelles.setText(unTableau.getValueAt(numLigne, 4).toString());
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
    }

    public Object[][] obtenirParcs(String mot){
        ArrayList<Parc> lesParcs = C_Parc.selectAllParcs(mot);
        Object [][] matrice = new Object[lesParcs.size()][11];

        int i=0;
        for (Parc unParc : lesParcs) {
            matrice[i][0] = unParc.getIdParc() + "";
            matrice[i][1] = unParc.getNom();
            matrice[i][2] = unParc.getCapacite();
            matrice[i][3] = unParc.getNbAttractionsTotales();
            matrice[i][4] = unParc.getNbAttractionsFonctionnelles();
            i++;
        }
        return matrice;
    }

    public void viderChamps() {
        this.txtNom.setText("");
        this.txtCapacite.setText("");
        this.txtNbAttractionsTotales.setText("");
        this.txtNbAttractionsFonctionnelles.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btAnnuler) {
            this.viderChamps();
        } else if (e.getSource() == btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
            String nom = this.txtNom.getText();

            int capacite = 0;
            try{
                capacite = Integer.parseInt(this.txtCapacite.getText());
            } catch (NumberFormatException exp){
                JOptionPane.showMessageDialog(this, "Erreur de saisie de la capacité !");
            }
            int nbAttractionsTotales = 0;
            try{
                nbAttractionsTotales = Integer.parseInt(this.txtNbAttractionsTotales.getText());
            } catch (NumberFormatException exp){
                JOptionPane.showMessageDialog(this, "Erreur de saisie du nombre total d'attractions !");
            }
            int nbAttractionsFonctionnelles = 0;
            try{
                nbAttractionsFonctionnelles = Integer.parseInt(this.txtNbAttractionsFonctionnelles.getText());
            } catch (NumberFormatException exp){
                JOptionPane.showMessageDialog(this, "Erreur de saisie du nombre d'attractions fonctionnelles !");
            }

            boolean ok = true;
            if (nom.equals("")) {
                this.txtNom.setBackground(Color.red);
                ok = false;
            } else {
                this.txtNom.setBackground(Color.white);
            }

            if (ok) {
                //instanciation d'un parc
                Parc unParc = new Parc(nom, capacite, nbAttractionsTotales, nbAttractionsFonctionnelles);
                //insertion du new parc dans la BDD
                C_Parc.insertParc(unParc);
                //actualisation de la table d'affichage des parcs
                unParc = C_Parc.selectWhereParc(nom);
                int idParc = unParc.getIdParc();
                Object ligne[] = {idParc, nom, capacite, nbAttractionsTotales, nbAttractionsFonctionnelles};
                unTableau.insererLigne(ligne);
                JOptionPane.showMessageDialog(this, "Insertion réussie du parc !");
                this.viderChamps();
            }
        } else if (e.getSource() == btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
            String nom = this.txtNom.getText();

            int capacite = 0;
            try{
                capacite = Integer.parseInt(this.txtCapacite.getText());
            } catch (NumberFormatException exp){
                JOptionPane.showMessageDialog(this, "Erreur de saisie de la capacité !");
            }
            int nbAttractionsTotales = 0;
            try{
                nbAttractionsTotales = Integer.parseInt(this.txtNbAttractionsTotales.getText());
            } catch (NumberFormatException exp){
                JOptionPane.showMessageDialog(this, "Erreur de saisie du nombre total d'attractions !");
            }
            int nbAttractionsFonctionnelles = 0;
            try{
                nbAttractionsFonctionnelles = Integer.parseInt(this.txtNbAttractionsFonctionnelles.getText());
            } catch (NumberFormatException exp){
                JOptionPane.showMessageDialog(this, "Erreur de saisie du nombre d'attractions fonctionnelles !");
            }

            boolean ok = true;
            if (nom.equals("")) {
                this.txtNom.setBackground(Color.red);
                ok = false;
            } else {
                this.txtNom.setBackground(Color.white);
            }

            if (ok) {
                int numLigne = tableParcs.getSelectedRow();
                int idParc = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
                //instanciation d'un parc
                Parc unParc = new Parc(idParc, nom, capacite, nbAttractionsTotales, nbAttractionsFonctionnelles);
                //modification du parc dans la BDD
                C_Parc.updateParc(unParc);
                //actualisation de la table d'affichage des parcs
                Object ligne[] = {idParc, nom, capacite, nbAttractionsTotales, nbAttractionsFonctionnelles};
                unTableau.modifierLigne(numLigne, ligne);
                JOptionPane.showMessageDialog(this, "Modification réussie du parc !");
                this.viderChamps();
                this.btEnregistrer.setText("Enregistrer"); //on remet le bouton à enregistrer
            }
        } else if(e.getSource() == btOk) {
            String mot = this.txtMot.getText();
            Object [][] matrice = this.obtenirParcs(mot);
            unTableau.setDonnees(matrice);
        }
    }
}
