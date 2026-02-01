package gloo.projet.ihm;

import javax.swing.*;
import java.awt.*;

import gloo.projet.controle.Controleur;
import gloo.projet.metier.*;

public class GrilleGraphique extends JPanel {
    private CaseGraphique[][] cases;
    private Plateau plateau;
    private Controleur controleur;

    public GrilleGraphique(Plateau p, Controleur ctrl, int lig, int col) {
        this.plateau = p;
        this.controleur = ctrl;
        this.setLayout(new GridLayout(lig, col));
        this.cases = new CaseGraphique[lig][col];

        for (int l = 0; l < lig; l++) {
            for (int c = 0; c < col; c++) {
                AbstractCase ac = p.getCase(new Position(l, c));
                cases[l][c] = new CaseGraphique(ac, plateau);
                
                // On ajoute un listener pour la sélection au clic
                final int fl = l; final int fc = c;
                cases[l][c].addActionListener(e -> {
                	this.controleur.selection(fl, fc);
                    this.rafraichir();
                });

                this.add(cases[l][c]);
            }
        }
    }

    public void rafraichir() {
    	Bloc selectionne = controleur.getBlocSelectionne();
        for (int l = 0; l < cases.length; l++) {
            for (int c = 0; c < cases[0].length; c++) {
                cases[l][c].mettreAJour(plateau.getCase(new Position(l, c)), selectionne);
            }
        }
    }
}