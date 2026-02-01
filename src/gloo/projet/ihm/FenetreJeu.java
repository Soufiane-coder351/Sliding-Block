package gloo.projet.ihm;

import javax.swing.*;
import java.awt.event.*;
import gloo.projet.metier.*;
import gloo.projet.controle.*;

public class FenetreJeu extends JFrame {
    private GrilleGraphique grille;
    private Controleur controleur;

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