package vues;

import controleurs.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PanelRestaurateurs extends PanelPrincipal implements ActionListener {

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
    private JTextField txtAnciennete = new JTextField();

    //déclaration de table des restaurateurs
    private JTable tableRestaurateurs;
    private Tableau unTableau;

    //implémentation d'un filtre
    private JTextField txtMot = new JTextField();
    private JButton btOk = new JButton("OK");
    
    public PanelRestaurateurs() {
        super(Color.lightGray);

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
        this.panelForm.add(new JLabel("Ancienneté : "));
        this.panelForm.add(this.txtAnciennete);
        this.panelForm.add(btAnnuler);
        this.panelForm.add(btEnregistrer);
        this.panelForm.setBackground(Color.white);
        this.panelForm.setVisible(true);

        this.add(panelForm);

        //construction du panelTable
        this.panelTable.setBounds(400, 80, 500, 300);
        //this.panelTable.setBackground(new Color(255, 184, 51));
        this.panelTable.setLayout(null);
        String entetes[] = {"ID Restaurateur", "Nom", "Prénom", "Adresse", "Email", "Mot de passe", "Téléphone",
                "Qualification", "Ancienneté"};
        //instanciation de la classe Tableau du controleur
        unTableau = new Tableau(this.obtenirRestaurateurs(""), entetes);
        this.tableRestaurateurs = new JTable(unTableau);
        JScrollPane uneScroll = new JScrollPane(this.tableRestaurateurs);
        uneScroll.setBounds(0, 0, 500, 300);
        this.panelTable.add(uneScroll);

        this.add(panelTable);

        //rendre la JTable écoutable avec la suppression d'une ligne
        this.tableRestaurateurs.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = 0;
                if (e.getClickCount() == 2) {
                    numLigne = tableRestaurateurs.getSelectedRow();
                    int retour = JOptionPane.showConfirmDialog(null, "voulez-vous supprimer ce restaurateur ?", "Suppression restaurateur", JOptionPane.YES_NO_OPTION);
                    if (retour == 0) {
                        //suppression du restaurateur dans la BDD
                        int idrestaurateur = Integer.parseInt((String) unTableau.getValueAt(numLigne, 0));
                        C_Restaurateur.deleteRestaurateur(idrestaurateur);
                        //suppression de la ligne dans le tableau
                        unTableau.supprimerLigne(numLigne);
                    }
                } else if (e.getClickCount() == 1) {
                    numLigne = tableRestaurateurs.getSelectedRow();
                    txtNom.setText((String) unTableau.getValueAt(numLigne, 1));
                    txtPrenom.setText((String) unTableau.getValueAt(numLigne, 2));
                    txtAdresse.setText((String) unTableau.getValueAt(numLigne, 3));
                    txtEmail.setText((String) unTableau.getValueAt(numLigne, 4));
                    txtMdp.setText((String) unTableau.getValueAt(numLigne, 5));
                    txtTel.setText((String) unTableau.getValueAt(numLigne, 6));
                    txtQualification.setText((String) unTableau.getValueAt(numLigne, 7));
                    txtAnciennete.setText((String) unTableau.getValueAt(numLigne, 8));
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

    public Object[][] obtenirRestaurateurs(String mot){
        ArrayList<Restaurateur> lesRestaurateurs = C_Restaurateur.selectAllRestaurateurs(mot);
        Object [][] matrice = new Object[lesRestaurateurs.size()][11];

        int i=0;
        for (Restaurateur unRestaurateur : lesRestaurateurs) {
            matrice[i][0] = unRestaurateur.getIduser() + "";
            matrice[i][1] = unRestaurateur.getNom();
            matrice[i][2] = unRestaurateur.getPrenom();
            matrice[i][3] = unRestaurateur.getAdresse();
            matrice[i][4] = unRestaurateur.getEmail();
            matrice[i][5] = unRestaurateur.getMdp();
            matrice[i][6] = unRestaurateur.getTel();
            matrice[i][7] = unRestaurateur.getQualification();
            matrice[i][8] = unRestaurateur.getAnciennete();
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
        this.txtAnciennete.setText("");
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
            String anciennete = this.txtAnciennete.getText();

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

            if (ok) {
                //instanciation d'un restaurateur
                Restaurateur unRestaurateur = new Restaurateur(nom, prenom, adresse, email, mdp, tel, qualification, anciennete);
                //insertion du new restaurateur dans la BDD
                C_Restaurateur.insertRestaurateur(unRestaurateur);
                //actualisation de la table d'affichage des restaurateurs
                unRestaurateur = C_Restaurateur.selectWhereRestaurateur(email, mdp);
                int idRestaurateur = unRestaurateur.getIduser();
                Object ligne[] = {idRestaurateur, nom, prenom, adresse, email, mdp, tel, qualification, anciennete};
                unTableau.insererLigne(ligne);
                JOptionPane.showMessageDialog(this, "Insertion réussie du restaurateur !");
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
            String anciennete = this.txtAnciennete.getText();

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

            if (ok) {
                int numLigne = tableRestaurateurs.getSelectedRow();
                int idRestaurateur = Integer.parseInt((String) unTableau.getValueAt(numLigne, 0));
                //instanciation d'un restaurateur
                Restaurateur unRestaurateur = new Restaurateur(nom, prenom, adresse, email, mdp, tel, qualification, anciennete);
                //modification du restaurateur dans la BDD
                C_Restaurateur.updateRestaurateur(unRestaurateur);
                //actualisation de la table d'affichage des restaurateurs
                Object ligne[] = {idRestaurateur, nom, prenom, adresse, email, mdp, tel, qualification, anciennete};
                unTableau.modifierLigne(numLigne, ligne);
                JOptionPane.showMessageDialog(this, "Modification réussie du restaurateur !");
                this.viderChamps();
                this.btEnregistrer.setText("Enregistrer"); //on remet le bouton à enregistrer
            }
        } else if(e.getSource() == btOk) {
            String mot = this.txtMot.getText();
            Object [][] matrice = this.obtenirRestaurateurs(mot);
            unTableau.setDonnees(matrice);
        }
    }
}
