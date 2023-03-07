package vues;


//import de la libairy java
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


//import des controleurs
import controleurs.Disneyland;
import controleurs.Technicien;





public class VueGenerale extends JFrame implements ActionListener, KeyListener {

    private JPanel panelMenu= new JPanel();
	private JButton btParcs= new JButton("Parcs");
	private JButton btAttractions= new JButton("Attractions");
	private JButton btTechniciens= new JButton("Techniciens");
    private JButton btRestaurants= new JButton("Restaurants");
    private JButton btRestaurateurs= new JButton("Restaurateurs");
    private JButton btTransports= new JButton("Transports");
	// private JButton btParcs= new JButton("Mon Profil");
	private JButton btQuitter= new JButton("Quitter");
	
	private static PanelParcs unPanelParcs = new PanelParcs();
	private static PanelAttractions unPanelAttractions= new PanelAttractions();
	private static PanelTechniciens unPanelTechniciens= new PanelTechniciens();
	private static PanelRestaurants unPanelRestaurants= new PanelRestaurants();
	private static PanelRestaurateurs unPanelRestaurateurs= new PanelRestaurateurs();
	private static PanelTransports unPanelTransports= new PanelTransports();

    public VueGenerale(Technicien unTechnicien) {

		this.setTitle("Gestion Disneyland Paris");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(251, 253, 253) ); // couleur de background du panel generale (Blanc)
		this.setBounds(200, 15, 1200, 800); // Emplacement et Taille du panel generale lors de l'ouverture 
		this.setLayout(null);

		//Logo de Disney
		ImageIcon logo = new ImageIcon("src/main/java/images/logoVueGenerale.png");
		JLabel monLogo = new JLabel(logo);
		monLogo.setBounds(100, 20, 248, 70);
		this.add(monLogo);
		
		this.panelMenu.setBounds(100, 100, 1000, 40);
		this.panelMenu.setBackground(new Color(251, 253, 253)); // couleur de background du panel generale (Blanc)
		//1 ligne sur 7 colonnes
		this.panelMenu.setLayout(new GridLayout(1, 7));
		this.panelMenu.add(this.btParcs);
		this.panelMenu.add(this.btAttractions);
        this.panelMenu.add(this.btTechniciens);
		this.panelMenu.add(this.btRestaurants);
		this.panelMenu.add(this.btRestaurateurs);
        this.panelMenu.add(this.btTransports);
		this.panelMenu.add(this.btQuitter);
		this.add(this.panelMenu);

		//Rendre les boutons ecoutables
		this.btQuitter.addActionListener(this);
		this.btParcs.addActionListener(this);
		this.btAttractions.addActionListener(this);
		this.btTechniciens.addActionListener(this);
		this.btRestaurants.addActionListener(this);
		this.btRestaurateurs.addActionListener(this);
		this.btTransports.addActionListener(this);

		//Insertion des paneaux dans la fenêtre
		this.add(unPanelParcs);
		this.add(unPanelAttractions);
		this.add(unPanelTechniciens);
		this.add(unPanelRestaurants);
		this.add(unPanelRestaurateurs);
		this.add(unPanelTransports);

		this.setVisible(false);
    }
	
	
	public static void activerPanel(int choix) {
		unPanelParcs.setVisible(false);
		unPanelAttractions.setVisible(false);
		unPanelTechniciens.setVisible(false);
		unPanelRestaurants.setVisible(false);
		unPanelRestaurateurs.setVisible(false);
		unPanelTransports.setVisible(false);
		switch(choix) {
		case 1: unPanelParcs.setVisible(true); break;
		case 2: unPanelAttractions.setVisible(true); break;
		case 3: unPanelTechniciens.setVisible(true); break;
		case 4: unPanelRestaurants.setVisible(true); break;
		case 5: unPanelRestaurateurs.setVisible(true); break;
		case 6: unPanelTransports.setVisible(true); break;
		}
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btQuitter) {
			int retour= JOptionPane.showConfirmDialog(this, "Voulez vous quitter l'application ? ", 
					"Quitter l'application ? " ,JOptionPane.YES_NO_OPTION);
			if(retour==0) {
				this.dispose();
				Disneyland.gererVueConnexion(true);
			}
		}
		else if(e.getSource()== this.btParcs) {
			activerPanel(1);
		}
		else if(e.getSource()== this.btAttractions) {
			activerPanel(2);
		}
		else if(e.getSource()== this.btTechniciens) {
			activerPanel(3);
		}
		else if(e.getSource()== this.btRestaurants) {
			activerPanel(4);
		}
		else if(e.getSource()== this.btRestaurateurs) {
			activerPanel(5);
		}
		else if(e.getSource()== this.btTransports) {
			activerPanel(6);
		}
	}


    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

}
