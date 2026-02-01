package gloo.projet.metier;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("e2ce5985-4692-40bb-a9b7-4574aa16f784")
public class Bloc {
    @objid ("61012f00-26ad-4599-a2ec-5057999c3151")
    private int numero;

    @objid ("43fa3be1-4fa9-4b6c-8db4-61b56eb765dc")
    private List<BlocElementaire> elements = new ArrayList<BlocElementaire> ();

    @objid ("d9f82ed8-5e23-4d21-acb8-27cfc5cfdeba")
    private Plateau plateau;

    @objid ("054ea661-0200-4993-ad47-c9f58fe8ac26")
    public Bloc(int numero, Plateau plateau) {
        this.numero = numero;
        this.plateau = plateau;
    }

    @objid ("b2531e13-2de3-4ea1-b0a8-0362cd39f6d9")
    public void ajouterElement(BlocElementaire be) {
        this.elements.add(be);
    }

    @objid ("d8e1b0d4-bef7-4608-ba03-fde54cee9abb")
    public int getNumero() {
        return numero;
    }

    @objid ("9ee697db-c46f-41a3-861e-e6a77123fe61")
    public boolean peutSeDeplacer(Direction dir) {
        for (BlocElementaire be : this.elements) {
            Position voisine = be.getPositionVoisine(dir);
            
            AbstractCase destination = this.plateau.getCase(voisine);
            if (destination == null) {
                return false;
            }
            
            if (!destination.peutEtreOccupeePar(this)) {
                return false;
            }
        }
        return true;
    }

    @objid ("efda5765-921a-4283-9ba2-4cb8b3bea4e5")
    public void deplacer(Direction dir) {
        if (!this.peutSeDeplacer(dir)) {
            System.out.println("Ne peux pas se deplacer");
            return; // bloque
        }
        
        for (BlocElementaire be : this.elements) {
            be.getCase().setOccupant(null);
        }
        
        for (BlocElementaire be : this.elements) {
            Position voisine = be.getPositionVoisine(dir);
            AbstractCase nouvelleCase = this.plateau.getCase(voisine);
            be.setCase(nouvelleCase);
            nouvelleCase.setOccupant(be);
        }
    }

	public List<BlocElementaire> getElements() {
		return elements;
	}

}
