package gloo.projet.metier;

import java.util.ArrayList;
import java.util.List;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import gloo.projet.controle.Controleur;
import gloo.projet.ihm.FenetreJeu;

@objid ("07433810-3969-4acd-914b-775021821f1c")
public class Main {
    @objid ("6c16518e-85c1-4a3a-b8ac-d8ed9e85f7d0")
    public static void main(String[] args) {
    	Plateau p = new Plateau(7, 5); 
        
        // Définition de la sortie double en bas
        List<Position> sorties = new ArrayList<>();
        sorties.add(new Position(6, 1));
        sorties.add(new Position(6, 2));
        
        p.creerPlateauDeBase(sorties);
        p.initialiserBlocs();
        
        Controleur c = new Controleur(p);
        new FenetreJeu(p, c, 7, 5);
    }

}
