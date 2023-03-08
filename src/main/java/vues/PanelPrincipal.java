package vues;

import java.awt.Color;
import javax.swing.JPanel;

//Abstract permet d'influencer toutes les filles
public abstract class PanelPrincipal extends JPanel {
    public PanelPrincipal () {
        this.setBounds(100, 170, 1000, 550);
        this.setBackground(Color.lightGray);
        this.setLayout(null);
        this.setVisible(false);
    }
}
