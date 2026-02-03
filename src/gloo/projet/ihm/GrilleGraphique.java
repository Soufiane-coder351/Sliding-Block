package gloo.projet.ihm;

import java.awt.*;
import javax.swing.*;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import gloo.projet.controle.Controleur;
import gloo.projet.metier.*;
import gloo.projet.metier.Plateau;

@objid ("526b11e6-cc53-4d70-8d52-9d98be162dea")
public class GrilleGraphique extends JPanel {
    @objid ("aa70631b-e35d-4a14-a131-b38f4c8388e4")
    private CaseGraphique[][] cases;

    @objid ("f8fc4cd8-9eb1-4da9-b146-533daedb4b75")
    private Plateau plateau;

    @objid ("4c1beb21-2d68-46b2-a17c-aaad0ad8e446")
    private Controleur controleur;

    @objid ("8d06af49-7269-4e47-a910-cee9d11659c7")
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

    @objid ("2b208165-cb9b-49bb-acf1-f4412d935ee4")
    public void rafraichir() {
        Bloc selectionne = controleur.getBlocSelectionne();
        for (int l = 0; l < cases.length; l++) {
            for (int c = 0; c < cases[0].length; c++) {
                cases[l][c].mettreAJour(plateau.getCase(new Position(l, c)), selectionne);
            }
        }
    }

}
