package gloo.projet.metier;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import gloo.projet.controle.Controleur;

@objid ("07433810-3969-4acd-914b-775021821f1c")
public class Main {
    @objid ("6c16518e-85c1-4a3a-b8ac-d8ed9e85f7d0")
    public static void main(String[] args) {
        Plateau p = new Plateau(7, 7);
        p.initialiserBlocs();
        Controleur ctrl = new Controleur(p);
        java.util.Scanner reader = new java.util.Scanner(System.in);

        while (true) {
            System.out.println(p.toString());
            System.out.print("Commande (ex: '0 z' pour monter le bloc 0, 'exit' pour quitter) : ");
            String input = reader.next();

            if (input.equals("exit")) break;

            try {
                // 1. On lit le numéro du bloc
                int numBloc = Integer.parseInt(input);
                ctrl.selectionParNumero(numBloc);
                
                // 2. On lit la direction (z, q, s, d)
                String dirInput = reader.next();
                Direction directionChoisie = null;
                if (dirInput.equals("z")) directionChoisie = Direction.HAUT;
                else if (dirInput.equals("s")) directionChoisie = Direction.BAS;
                else if (dirInput.equals("q")) directionChoisie = Direction.GAUCHE;
                else if (dirInput.equals("d")) directionChoisie = Direction.DROITE;
                
                boolean gagne = ctrl.action(directionChoisie);
                if (gagne) {
                    System.out.println(p.toString());
                    break; // On arrête le jeu
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un numéro de bloc valide.");
            }
        }
        reader.close();
    }

}
