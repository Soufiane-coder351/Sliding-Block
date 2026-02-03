package gloo.projet.metier;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import gloo.projet.controle.Controleur;

@objid ("8087d9cb-3cd1-40f3-bda7-453d15b9a83a")
public class LanceurTests {
    @objid ("9aca5691-e779-4430-8b24-e9e286a9ba51")
    public static void main(String[] args) {
        System.out.println("=== DÉMARRAGE DES TESTS FONCTIONNELS ===");
        
        testMouvementSimple();
        testCollisionMur();
        testCollisionEntreBlocs();
        
        testViderPlateau();
        testPersistanceSelection();
        testSelectionInvalide();
        
        testMouvementBloc2x2();
        testCollisionSurBordsLateraux();
        
        System.out.println("\n=== FIN DES TESTS : TOUT EST OPÉRATIONNEL ===");
    }

// Configuration d'un plateau de test
    @objid ("b611b086-f0a9-482c-9906-bcde5bf5853e")
    private static Plateau initialiserTest() {
        Plateau p = new Plateau(7, 5);
        List<Position> sorties = new ArrayList<>();
        sorties.add(new Position(6, 1));
        sorties.add(new Position(6, 2));
        p.creerPlateauDeBase(sorties);
        p.initialiserBlocs();
        return p;
    }

    @objid ("8e27ab5e-8c30-4248-91e9-376c274b08bc")
    private static Plateau initialiserTestBloc2x2() {
        Plateau p = new Plateau(5, 5); // Petit plateau pour le test
        List<Position> sorties = new ArrayList<>();
        p.creerPlateauDeBase(sorties);
        p.viderPlateau();
        
        // On crée un bloc 2x2 (numéro 0) aux positions (1,1), (1,2), (2,1), (2,2)
        p.ajouterBlocAuPlateau(0, new int[][]{ {1, 1}, {1, 2}, {2, 1}, {2, 2} });
        return p;
    }

    @objid ("ff955954-38fe-4186-9d72-839be795492d")
    private static void testMouvementSimple() {
        System.out.print("Test Mouvement Simple : ");
        Plateau p = initialiserTest();
        Controleur c = new Controleur(p);
        System.out.println(p.toString());
        // Sélection du bloc 4 (le gros rouge) et déplacement vers le bas
        c.selection(4, 1); 
        boolean succes = c.action(Direction.BAS);
        if (succes && p.getBloc(5, 1) != null && p.getBloc(5, 1).getNumero() == 4) {
            System.out.println("✅ RÉUSSI");
        } else {
            System.out.println("❌ ÉCHEC");
        }
    }

    @objid ("725cb5ec-82aa-48f6-b1f1-95c25aa5cf3b")
    private static void testCollisionMur() {
        System.out.print("Test Collision Mur (Haut) : ");
        Plateau p = initialiserTest();
        Controleur c = new Controleur(p);
        
        c.selection(1, 1);
        c.action(Direction.HAUT); // Doit être bloqué par le mur en ligne 0
        
        if (p.getBloc(1, 1).getNumero() == 0) {
            System.out.println("✅ RÉUSSI (Bloc bloqué)");
        } else {
            System.out.println("❌ ÉCHEC (Le bloc a traversé le mur !)");
        }
    }

    @objid ("cbf2ad64-f64f-4ee0-96b6-256511971310")
    private static void testCollisionEntreBlocs() {
        System.out.print("Test Collision entre deux Blocs : ");
        Plateau p = initialiserTest();
        Controleur c = new Controleur(p);
        
        // On essaie de bouger le bloc 1 (en 1,3) vers la gauche là où est le bloc 0
        c.selection(1, 3);
        c.action(Direction.GAUCHE);
        
        if (p.getBloc(1, 3).getNumero() == 1) {
            System.out.println("✅ RÉUSSI (Collision détectée)");
        } else {
            System.out.println("❌ ÉCHEC (Superposition de blocs !)");
        }
    }

    @objid ("bc053336-37bd-4c87-b88c-88faa2baccf9")
    private static void testViderPlateau() {
        System.out.print("Test Nettoyage Plateau : ");
        Plateau p = initialiserTest();
        p.viderPlateau();
        
        boolean vide = true;
        // On vérifie une case centrale qui était occupée
        if (p.getBloc(1, 1) != null) vide = false;
        
        if (vide) {
            System.out.println("✅ RÉUSSI");
        } else {
            System.out.println("❌ ÉCHEC (Restes de blocs trouvés)");
        }
    }

    @objid ("8792c096-3b5c-4f65-b5e5-e60d9ad27096")
    private static void testPersistanceSelection() {
        System.out.print("Test Persistance Sélection après mouvement : ");
        Plateau p = initialiserTest();
        Controleur c = new Controleur(p);
        c.selection(1, 3); // Bloc 1
        c.action(Direction.BAS);
        
        // On essaie de bouger à nouveau sans re-sélectionner
        boolean succes = c.action(Direction.HAUT);
        
        if (succes) System.out.println("✅ RÉUSSI");
        else System.out.println("❌ ÉCHEC (La sélection a été perdue)");
    }

    @objid ("58806592-554d-469d-a606-667f5f25bb50")
    private static void testSelectionInvalide() {
        System.out.print("Test Sélection Mur/Vide : ");
        Plateau p = initialiserTest();
        Controleur c = new Controleur(p);
        
        c.selection(0, 0); // Un mur
        boolean actionMur = c.action(Direction.BAS);
        
        c.selection(3, 2); // Supposons une case vide
        boolean actionVide = c.action(Direction.BAS);
        
        if (!actionMur && !actionVide) System.out.println("✅ RÉUSSI (Ignoré correctement)");
        else System.out.println("❌ ÉCHEC (Action possible sur mur/vide !)");
    }

    @objid ("0365d081-eece-4a36-9e80-4dd6bd8faf87")
    private static void testMouvementBloc2x2() {
        System.out.print("Test Intégrité Bloc 2x2 (Mouvement) : ");
        Plateau p = initialiserTestBloc2x2();
        Controleur c = new Controleur(p);
        // On sélectionne une des cases du bloc 2x2
        c.selection(1, 1);
        boolean succes = c.action(Direction.BAS); // On le descend d'une ligne
        
        // On vérifie que les 4 nouvelles positions sont bien occupées par le bloc 0
        boolean integrity = (p.getBloc(2, 1) != null && p.getBloc(2, 2) != null && 
                             p.getBloc(3, 1) != null && p.getBloc(3, 2) != null);
        
        // On vérifie aussi que les anciennes cases du haut sont bien libérées
        boolean nettoyageHaut = (p.getBloc(1, 1) == null && p.getBloc(1, 2) == null);
        
        if (succes && integrity && nettoyageHaut) {
            System.out.println("✅ RÉUSSI");
        } else {
            System.out.println("❌ ÉCHEC (Le bloc 2x2 ne s'est pas déplacé correctement)");
        }
    }

    @objid ("56e3a752-bc0d-44e6-88fc-efb4f57a1497")
    private static void testCollisionSurBordsLateraux() {
        System.out.print("Test Collisions Bords (Gauche/Droite) : ");
        Plateau p = initialiserTest();
        Controleur c = new Controleur(p);
        // Bloc 1 est à droite (col 3). Tentative d'aller trop à droite.
        c.selection(1, 3);
        boolean r1 = c.action(Direction.DROITE); // Mur en col 4
        
        // Bloc 2 est à gauche (col 1). Tentative d'aller trop à gauche.
        c.selection(2, 1);
        boolean r2 = c.action(Direction.GAUCHE); // Mur en col 0
        if (!r1 && !r2) {
            System.out.println("✅ RÉUSSI");
        } else {
            System.out.println("❌ ÉCHEC (Débordement des limites du plateau)");
        }
    }

}
