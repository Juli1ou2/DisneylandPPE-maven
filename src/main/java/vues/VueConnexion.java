package vues;

import controleurs.C_Technicien;
import controleurs.Disneyland;
import controleurs.Technicien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class VueConnexion extends JFrame implements ActionListener, KeyListener {

    private JTextField txtEmail = new JTextField();
    private JPasswordField txtMdp = new JPasswordField();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btSeConnecter = new JButton("Se connecter");

    private JPanel panelCon = new JPanel();

    public VueConnexion() {
        this.setTitle("Connexion au portail Disneyland Paris");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.getContentPane().setBackground(new Color(255, 184, 51));
        this.setBounds(200, 200, 480, 340);
        this.setLayout(null);

        ImageIcon logo = new ImageIcon("src/main/java/images/logoConnexion.png");
        JLabel monLogo = new JLabel(logo);
        monLogo.setBounds(40, 40, 380, 107);
        this.add(monLogo);

        //construction du panel connexion
        this.panelCon.setBounds(40, 180, 380, 100);
        //this.panelCon.setBackground(Color.cyan);
        this.panelCon.setLayout(new GridLayout(3, 2)); //segmentation en matrice : 3 lignes et 2 colonnes
        this.panelCon.add(new JLabel("Email"));
        this.panelCon.add(this.txtEmail);
        this.panelCon.add(new JLabel("Mot de passe"));
        this.panelCon.add(this.txtMdp);
        this.panelCon.add(this.btAnnuler);
        this.panelCon.add(this.btSeConnecter);
        this.add(panelCon);

        //rendre les 2 boutons écoutables
        this.btAnnuler.addActionListener(this);
        this.btSeConnecter.addActionListener(this);

        //ajouter événements frappes de touches
        this.txtEmail.addKeyListener(this);
        this.txtMdp.addKeyListener(this);

        this.setVisible(true);
    }

    public void traitement() {
        String email = this.txtEmail.getText();
        String mdp = new String(this.txtMdp.getPassword()); //le mdp est un tableau de caractères ex : char txt[50]

        if (email.equals("") || mdp.equals("")) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir vos identifiants !");
        } else {
            //vérification dans la BDD
            Technicien unTechnicien = C_Technicien.selectWhereTechnicien(email, mdp);
            if (unTechnicien == null) {
                JOptionPane.showMessageDialog(this, "Veuillez vérifier vos identifiants !");
            } else {
                JOptionPane.showMessageDialog(this, "Bienvenue MM/M. " + unTechnicien.getNom());
                this.txtEmail.setText("");
                this.txtMdp.setText("");
                //ouverture de session
                Disneyland.gererVueConnexion(false);
                Disneyland.gererVueGenerale(true, unTechnicien);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtEmail.setText("");
            this.txtMdp.setText("");
        } else if (e.getSource() == this.btSeConnecter) {
            this.traitement();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.traitement();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}
