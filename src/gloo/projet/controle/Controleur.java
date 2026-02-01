package gloo.projet.controle;

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

    public Controleur(Plateau plateau) {
        this.plateau = plateau;
    }
    
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
            this.blocSelectionne.deplacer(direction);

            if (this.plateau.verifierVictoire()) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("!!! BRAVO ! VOUS AVEZ GAGNÉ !!!");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                return true; // On signale la victoire
            }
        } else {
            System.out.println("Veuillez sélectionner un bloc avant d'agir.");
            return false;
        }
        return false;
    }

}
