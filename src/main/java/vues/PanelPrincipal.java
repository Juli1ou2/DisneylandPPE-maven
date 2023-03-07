package vues;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;

//Abstract permet d'influencer toutes les filles
public abstract class PanelPrincipal extends JPanel {
    public PanelPrincipal (Color uneCouleur) {
        this.setBounds(100, 170, 1000, 550);
        this.setBackground(uneCouleur);
        this.setLayout(null);
        this.setVisible(false);
    }
}
