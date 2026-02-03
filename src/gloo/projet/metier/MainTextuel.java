package gloo.projet.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import gloo.projet.controle.Controleur;

@objid ("278781b0-1a78-4342-90d0-808cb98b92af")
public class MainTextuel {
    @objid ("d74824f9-6276-48ba-af49-88530376261e")
    public static void main(String[] args) {
        // 1. Initialisation identique au Main graphique
        Plateau p = new Plateau(7, 5); 
        List<Position> sorties = new ArrayList<>();
        sorties.add(new Position(6, 1));
        sorties.add(new Position(6, 2));
        
        p.creerPlateauDeBase(sorties);
        p.initialiserBlocs();
        
        Controleur c = new Controleur(p);
        Scanner scanner = new Scanner(System.in);
        boolean gagne = false;
        
        System.out.println("=== BIENVENUE DANS LE SLIDING BLOCK PUZZLE (MODE TEXTE) ===");
        
        // 2. Boucle de jeu textuelle
        while (!gagne) {
            System.out.println(p.toString()); // Affiche la grille actuelle
            System.out.println("Commandes : <num_bloc> <z|q|s|d> (ex: '0 s' pour descendre le bloc 0)");
            System.out.print("> ");
        
            try {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) break;
        
                String[] parts = input.split(" ");
                if (parts.length != 2) {
                    System.out.println("Format invalide. Utilisez : <num> <dir>");
                    continue;
                }
        
                // Sélection du bloc
                int numBloc = Integer.parseInt(parts[0]);
                c.selectionParNumero(numBloc);
        
                // Traduction de la direction
                Direction dir = null;
                switch (parts[1].toLowerCase()) {
                    case "z": dir = Direction.HAUT; break;
                    case "s": dir = Direction.BAS; break;
                    case "q": dir = Direction.GAUCHE; break;
                    case "d": dir = Direction.DROITE; break;
                    default: System.out.println("Direction inconnue."); continue;
                }
        
                // Exécution de l'action via le contrôleur
                gagne = c.action(dir);
        
            } catch (Exception e) {
                System.out.println("Erreur de saisie : " + e.getMessage());
            }
        }
        
        if (gagne) {
            System.out.println(p.toString());
            System.out.println("Victoire confirmée en mode textuel !");
        }
        scanner.close();
    }

}
