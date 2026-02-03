package gloo.projet.ihm;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import gloo.projet.metier.*;
import gloo.projet.metier.AbstractCase;
import gloo.projet.metier.Bloc;
import gloo.projet.metier.Plateau;
import gloo.projet.metier.Position;

@objid ("b5f2b763-6f3e-4cc6-b009-724a2f538b1f")
public class CaseGraphique extends JButton {
    @objid ("106bc7fb-8d74-47f2-8dde-6c6fa0835876")
    private Position position;

    @objid ("49d69a4c-4a6b-4c59-b607-66bef2d31c8f")
    private Plateau plateau;

    @objid ("6b706080-b501-4443-a022-a869f836b3eb")
    public CaseGraphique(AbstractCase c, Plateau p) {
        this.plateau = p;
        this.position = c.getVoisine(null); // Astuce pour récupérer sa position
        this.setFocusable(false); // Évite que le bouton ne capture les touches du clavier
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        mettreAJour(c, null);
    }

    @objid ("cf16afa4-2577-49ca-9f25-83bccc7575e2")
    public void mettreAJour(AbstractCase c, Bloc blocSelectionne) {
        this.setText(""); 
        Bloc blocActuel = c.getBloc();
        Position pos = c.getVoisine(null);
        int lig = pos.getLigne();
        int col = pos.getColonne();
        
        // 1. Couleurs de base
        if (c instanceof Mur) {
            this.setBackground(Color.BLACK);
            this.setBorder(null);
            return; 
        } else if (c instanceof Sortie) {
            this.setBackground(new Color(46, 139, 87));
            this.setText("S");
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        } else {
            this.setBackground(Color.DARK_GRAY);
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        }
        
        // 2. Rendu des Blocs
        if (blocActuel != null) {
            this.setBackground(blocActuel.getNumero() == 0 ? Color.RED : new Color(0, 51, 204));
        
            // Calcul des épaisseurs pour l'espace noir (fusion des cases du même bloc)
            int t = estMemeBloc(lig - 1, col, blocActuel) ? 0 : 3;
            int b = estMemeBloc(lig + 1, col, blocActuel) ? 0 : 3;
            int l = estMemeBloc(lig, col - 1, blocActuel) ? 0 : 3;
            int r = estMemeBloc(lig, col + 1, blocActuel) ? 0 : 3;
        
            Border bordureNoire = BorderFactory.createMatteBorder(t, l, b, r, Color.BLACK);
        
            // Gestion du marqueur de sélection externe uniquement
            if (blocActuel == blocSelectionne) {
                // On n'affiche le liseré jaune que s'il n'y a pas de voisin du même bloc dans cette direction
                int st = t > 0 ? 2 : 0;
                int sb = b > 0 ? 2 : 0;
                int sl = l > 0 ? 2 : 0;
                int sr = r > 0 ? 2 : 0;
        
                Border bordureJaune = BorderFactory.createMatteBorder(st, sl, sb, sr, Color.YELLOW);
                
                // On combine : le vide noir à l'extérieur, le liseré jaune à l'intérieur
                this.setBorder(BorderFactory.createCompoundBorder(bordureNoire, bordureJaune));
            } else {
                this.setBorder(bordureNoire);
            }
        }
    }

// Méthode utilitaire pour vérifier si une case voisine appartient au même bloc
    @objid ("c354dca3-d14f-4db0-9081-a05ba3d5f5da")
    private boolean estMemeBloc(int lig, int col, Bloc blocRef) {
        try {
            AbstractCase voisine = plateau.getCase(new Position(lig, col));
            return voisine != null && voisine.getBloc() == blocRef;
        } catch (Exception e) {
            return false;
        }
    }

}
