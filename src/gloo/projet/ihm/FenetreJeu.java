package gloo.projet.ihm;

import java.awt.event.*;
import javax.swing.*;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import gloo.projet.controle.*;
import gloo.projet.controle.Controleur;
import gloo.projet.metier.*;
import gloo.projet.metier.Plateau;

@objid ("69a3e27f-b4a7-4915-8d1b-0fa30a660dd4")
public class FenetreJeu extends JFrame {
    @objid ("32734919-2085-40f5-b369-d7a6349eda62")
    private GrilleGraphique grille;

    @objid ("ac8fce38-6283-4416-893c-931c7522e8f9")
    private Controleur controleur;

    @objid ("ee5d8d00-0ac1-413b-a1a4-b256e0aeca26")
    public FenetreJeu(Plateau p, Controleur c, int lig, int col) {
        this.controleur = c;
        this.setTitle("Sliding Block Puzzle");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.grille = new GrilleGraphique(p, c, lig, col);
        this.add(grille);
        
        // Écouteur de touches pour le mouvement
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Direction dir = null;
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_UP: dir = Direction.HAUT; break;
                    case KeyEvent.VK_DOWN: dir = Direction.BAS; break;
                    case KeyEvent.VK_LEFT: dir = Direction.GAUCHE; break;
                    case KeyEvent.VK_RIGHT: dir = Direction.DROITE; break;
                }
                if (dir != null) {
                    controleur.action(dir);
                    grille.rafraichir(); // On redessine après l'action
                }
            }
        });
        
        this.pack(); // Ajuste la taille automatiquement
        this.setSize(500, 500);
        this.setVisible(true);
    }

}
