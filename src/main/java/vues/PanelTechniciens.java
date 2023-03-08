package vues;

import controleurs.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PanelTechniciens extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();
    private JPanel panelTable = new JPanel();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btEnregistrer = new JButton("Enregistrer");
    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtAdresse = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtMdp = new JTextField();
    private JTextField txtTel = new JTextField();
    private JTextField txtQualification = new JTextField();
    private JTextField txtDateEntree = new JTextField();

    //déclaration de table des techniciens
    private JTable tableTechniciens;
    private Tableau unTableau;

    //implémentation d'un filtre
    private JTextField txtMot = new JTextField();
    private JButton btOk = new JButton("OK");

    public PanelTechniciens() {

        //construction du panelForm
        this.panelForm.setBounds(40, 80, 300, 300);
        this.panelForm.setLayout(new GridLayout(9, 2));
        this.panelForm.add(new JLabel("Nom : "));
        this.panelForm.add(this.txtNom);
        this.panelForm.add(new JLabel("Prénom : "));
        this.panelForm.add(this.txtPrenom);
        this.panelForm.add(new JLabel("Adresse : "));
        this.panelForm.add(this.txtAdresse);
        this.panelForm.add(new JLabel("Email : "));
        this.panelForm.add(this.txtEmail);
        this.panelForm.add(new JLabel("Mot de passe : "));
        this.panelForm.add(this.txtMdp);
        this.panelForm.add(new JLabel("Téléphone : "));
        this.panelForm.add(this.txtTel);
        this.panelForm.add(new JLabel("Qualification : "));
        this.panelForm.add(this.txtQualification);
        this.panelForm.add(new JLabel("Date d'entrée : "));
        this.panelForm.add(this.txtDateEntree);
        this.panelForm.add(btAnnuler);
        this.panelForm.add(btEnregistrer);
        this.panelForm.setBackground(Color.white);
        this.panelForm.setVisible(true);

        this.add(panelForm);

        //construction du panelTable
        this.panelTable.setBounds(400, 80, 500, 300);
        //this.panelTable.setBackground(new Color(255, 184, 51));
        this.panelTable.setLayout(null);
        String entetes[] = {"ID Technicien", "Nom", "Prénom", "Adresse", "Email", "Mot de passe", "Téléphone",
                "Qualification", "Date d'entrée"};
        //instanciation de la classe Tableau du controleur
        unTableau = new Tableau(this.obtenirTechniciens(""), entetes);
        this.tableTechniciens = new JTable(unTableau);
        JScrollPane uneScroll = new JScrollPane(this.tableTechniciens);
        uneScroll.setBounds(0, 0, 500, 300);
        this.panelTable.add(uneScroll);

        this.add(panelTable);

        //rendre la JTable écoutable avec la suppression d'une ligne
        this.tableTechniciens.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = 0;
                if (e.getClickCount() == 2) {
                    numLigne = tableTechniciens.getSelectedRow();
                    int retour = JOptionPane.showConfirmDialog(null, "voulez-vous supprimer ce technicien ?", "Suppression technicien", JOptionPane.YES_NO_OPTION);
                    if (retour == 0) {
                        //suppression du technicien dans la BDD
                        int idtechnicien = Integer.parseInt((String) unTableau.getValueAt(numLigne, 0));
                        C_Technicien.deleteTechnicien(idtechnicien);
                        //suppression de la ligne dans le tableau
                        unTableau.supprimerLigne(numLigne);
                    }
                } else if (e.getClickCount() == 1) {
                    numLigne = tableTechniciens.getSelectedRow();
                    txtNom.setText((String) unTableau.getValueAt(numLigne, 1));
                    txtPrenom.setText((String) unTableau.getValueAt(numLigne, 2));
                    txtAdresse.setText((String) unTableau.getValueAt(numLigne, 3));
                    txtEmail.setText((String) unTableau.getValueAt(numLigne, 4));
                    txtMdp.setText((String) unTableau.getValueAt(numLigne, 5));
                    txtTel.setText((String) unTableau.getValueAt(numLigne, 6));
                    txtQualification.setText((String) unTableau.getValueAt(numLigne, 7));
                    txtDateEntree.setText((String) unTableau.getValueAt(numLigne, 8));
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

    public Object[][] obtenirTechniciens(String mot){
        ArrayList<Technicien> lesTechniciens = C_Technicien.selectAllTechniciens(mot);
        Object [][] matrice = new Object[lesTechniciens.size()][11];

        int i=0;
        for (Technicien unTechnicien : lesTechniciens) {
            matrice[i][0] = unTechnicien.getIduser() + "";
            matrice[i][1] = unTechnicien.getNom();
            matrice[i][2] = unTechnicien.getPrenom();
            matrice[i][3] = unTechnicien.getAdresse();
            matrice[i][4] = unTechnicien.getEmail();
            matrice[i][5] = unTechnicien.getMdp();
            matrice[i][6] = unTechnicien.getTel();
            matrice[i][7] = unTechnicien.getQualification();
            matrice[i][8] = unTechnicien.getDateEntree();
            i++;
        }
        return matrice;
    }

    public void viderChamps() {
        this.txtNom.setText("");
        this.txtPrenom.setText("");
        this.txtAdresse.setText("");
        this.txtEmail.setText("");
        this.txtMdp.setText("");
        this.txtTel.setText("");
        this.txtQualification.setText("");
        this.txtDateEntree.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btAnnuler) {
            this.viderChamps();
        } else if (e.getSource() == btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
            String nom = this.txtNom.getText();
            String prenom = this.txtPrenom.getText();
            String adresse = this.txtAdresse.getText();
            String email = this.txtEmail.getText();
            String mdp = this.txtMdp.getText();
            String tel = this.txtTel.getText();
            String qualification = this.txtQualification.getText();
            String dateEntree = this.txtDateEntree.getText();

            boolean ok = true;
            if (nom.equals("")) {
                this.txtNom.setBackground(Color.red);
                ok = false;
            } else {
                this.txtNom.setBackground(Color.white);
            }
            if (prenom.equals("")) {
                this.txtPrenom.setBackground(Color.red);
                ok = false;
            } else {
                this.txtPrenom.setBackground(Color.white);
            }
            if (dateEntree.equals("")) {
                this.txtDateEntree.setBackground(Color.red);
                ok = false;
            } else {
                this.txtDateEntree.setBackground(Color.white);
            }

            if (ok) {
                //instanciation d'un technicien
                Technicien unTechnicien = new Technicien(nom, prenom, adresse, email, mdp, tel, qualification, dateEntree);
                //insertion du new technicien dans la BDD
                C_Technicien.insertTechnicien(unTechnicien);
                //actualisation de la table d'affichage des techniciens
                unTechnicien = C_Technicien.selectWhereTechnicien(email, mdp);
                int idtechnicien = unTechnicien.getIduser();
                Object ligne[] = {idtechnicien, nom, prenom, adresse, email, mdp, tel, qualification, dateEntree};
                unTableau.insererLigne(ligne);
                JOptionPane.showMessageDialog(this, "Insertion réussie du technicien !");
                this.viderChamps();
            }
        } else if (e.getSource() == btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
            String nom = this.txtNom.getText();
            String prenom = this.txtPrenom.getText();
            String adresse = this.txtAdresse.getText();
            String email = this.txtEmail.getText();
            String mdp = this.txtMdp.getText();
            String tel = this.txtTel.getText();
            String qualification = this.txtQualification.getText();
            String dateEntree = this.txtDateEntree.getText();

            boolean ok = true;
            if (nom.equals("")) {
                this.txtNom.setBackground(Color.red);
                ok = false;
            } else {
                this.txtNom.setBackground(Color.white);
            }
            if (prenom.equals("")) {
                this.txtPrenom.setBackground(Color.red);
                ok = false;
            } else {
                this.txtPrenom.setBackground(Color.white);
            }
            if (dateEntree.equals("")) {
                this.txtDateEntree.setBackground(Color.red);
                ok = false;
            } else {
                this.txtDateEntree.setBackground(Color.white);
            }

            if (ok) {
                int numLigne = tableTechniciens.getSelectedRow();
                int idtechnicien = Integer.parseInt((String) unTableau.getValueAt(numLigne, 0));
                //instanciation d'un technicien
                Technicien unTechnicien = new Technicien(nom, prenom, adresse, email, mdp, tel, qualification, dateEntree);
                //modification du technicien dans la BDD
                C_Technicien.updateTechnicien(unTechnicien);
                //actualisation de la table d'affichage des techniciens
                Object ligne[] = {idtechnicien, nom, prenom, adresse, email, mdp, tel, qualification, dateEntree};
                unTableau.modifierLigne(numLigne, ligne);
                JOptionPane.showMessageDialog(this, "Modification réussie du technicien !");
                this.viderChamps();
                this.btEnregistrer.setText("Enregistrer"); //on remet le bouton à enregistrer
            }
        } else if(e.getSource() == btOk) {
            String mot = this.txtMot.getText();
            Object [][] matrice = this.obtenirTechniciens(mot);
            unTableau.setDonnees(matrice);
        }
    }
}
