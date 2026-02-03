package gloo.projet.controle;

import javax.swing.JOptionPane;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import gloo.projet.metier.Bloc;
import gloo.projet.metier.Direction;
import gloo.projet.metier.Plateau;

@objid ("0a996fee-6cc9-4413-bb96-791e393dec2a")
public class Controleur {
    @objid ("02627471-4159-48ca-8217-b6ec26fcc2e1")
    private Bloc blocSelectionne;

    @objid ("788af38b-9f17-40ca-a802-94bbc6b014c7")
    private Plateau plateau;

    @objid ("f204014b-9182-42ca-866f-357136e4753e")
    public void selectionParNumero(final int numero) {
        this.blocSelectionne = this.plateau.getBlocParNumero(numero);
        
        if (this.blocSelectionne == null) {
            System.out.println("Erreur : Le bloc " + numero + " n'existe pas.");
        }
    }

    @objid ("cebb37d8-b25b-44e2-a044-ba278e3882d3")
    public boolean action(final Direction direction) {
        if(direction == null) {
            System.out.println("Veuillez sélectionner une direction correcte.");
            return false;
        }
        if (this.blocSelectionne != null) {
            if(!this.blocSelectionne.deplacer(direction)) return false;
        
            if (this.plateau.verifierVictoire()) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("!!! BRAVO ! VOUS AVEZ GAGNÉ !!!");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        
                String[] options = {"Recommencer", "Quitter"};
                int choix = JOptionPane.showOptionDialog(
                        null, 
                        "Félicitations ! Vous avez gagné !", 
                        "Victoire !", 
                        JOptionPane.DEFAULT_OPTION, 
                        JOptionPane.INFORMATION_MESSAGE, 
                        null, 
                        options, 
                        options[0]
                    );
                if (choix == 0) { // L'utilisateur a cliqué sur "Recommencer"
                    this.plateau.initialiserBlocs(); // On réinitialise le modèle
                    this.blocSelectionne = null;     // On désélectionne le bloc
                    return true;
                } else { // L'utilisateur a cliqué sur "Quitter" ou a fermé la fenêtre
                    System.exit(0); // Ferme l'application
                }
                
                return true; // On signale la victoire
            }
            return true;
        } else {
            System.out.println("Veuillez sélectionner un bloc avant d'agir.");
            return false;
        }
    }

    @objid ("eec97c6e-429c-4877-a788-342caf4c382e")
    public Controleur(Plateau plateau) {
        this.plateau = plateau;
    }

    @objid ("f8f6c104-ba6d-46fc-a536-f74a6484ce72")
    public Bloc getBlocSelectionne() {
        return this.blocSelectionne;
    }

    @objid ("a8cafecf-0406-414e-8789-bc7b02c257a8")
    public void selection(final int ligne, final int colonne) {
        // On demande au plateau le bloc présent à ces coordonnées
        this.blocSelectionne = this.plateau.getBloc(ligne, colonne);
        
        if (this.blocSelectionne != null) {
            System.out.println("Bloc " + blocSelectionne.getNumero() + " sélectionné via IHM !");
        } else {
            System.out.println("Case vide ou mur sélectionné.");
        }
    }

}
