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
        
        while (!gagne) {
            System.out.println(p.toString());
            System.out.println("Commandes : <num_bloc> <z|q|s|d> (ex: '0 s' pour descendre)");
            System.out.print("> ");
        
            try {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) break;
        
                String[] parts = input.split(" ");
                if (parts.length != 2) {
                    System.out.println("Format invalide.");
                    continue;
                }
        
                int numBloc = Integer.parseInt(parts[0]);
                c.selectionParNumero(numBloc);
        
                Direction dir = null;
                switch (parts[1].toLowerCase()) {
                    case "z": dir = Direction.HAUT; break;
                    case "s": dir = Direction.BAS; break;
                    case "q": dir = Direction.GAUCHE; break;
                    case "d": dir = Direction.DROITE; break;
                    default: System.out.println("Direction inconnue."); continue;
                }
        
                // 1. On exécute l'action (bouge le bloc)
                boolean mouvementReussi = c.action(dir);
                
                if (mouvementReussi) {
                    // 2. On vérifie la victoire via le plateau après un mouvement réussi
                    gagne = p.verifierVictoire(); 
                    if (!gagne) System.out.println("Mouvement effectué !");
                } else {
                    System.out.println("Mouvement impossible !");
                }
        
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
        
        if (gagne) {
            System.out.println(p.toString());
            System.out.println("!!! FÉLICITATIONS : VICTOIRE EN MODE TEXTUEL !!!");
        }
        scanner.close();
    }

}
