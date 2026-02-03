package gloo.projet.ihm;

import java.awt.BorderLayout;
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
        
             // 1. Utiliser un BorderLayout pour organiser les composants
        this.setLayout(new BorderLayout());
        
        // 2. Créer le panneau du haut avec le bouton Recommencer
        JPanel barreOutils = new JPanel();
        JButton boutonReset = new JButton("Recommencer");
        boutonReset.setFocusable(false); // Important pour ne pas voler le focus du clavier
        
        boutonReset.addActionListener(e -> {
            p.initialiserBlocs(); // Appelle ta méthode qui fait viderPlateau + ajout blocs
            c.selection(-1, -1);  // Désélectionne tout (en passant des coordonnées hors plateau)
            grille.rafraichir();
        });
        
        barreOutils.add(boutonReset);
        this.add(barreOutils, BorderLayout.NORTH); // Place la barre en haut
        
        // 3. Ajouter la grille au centre
        this.grille = new GrilleGraphique(p, c, lig, col);
        this.add(grille, BorderLayout.CENTER);
        
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
