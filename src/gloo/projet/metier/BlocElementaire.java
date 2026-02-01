package gloo.projet.metier;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("26c6138e-e2aa-48ee-bbae-4c0ec6da1fa6")
public class BlocElementaire {
    @objid ("9da56cdd-e190-4cb4-af01-d024fe0d35bc")
    private Bloc bloc;

    @objid ("484a2dd9-34fa-4d39-8b67-ced8cd3da83a")
    private AbstractCase laCase;

    @objid ("f9bd9f8d-d282-40cf-91ab-ef9c2da3bea6")
    public BlocElementaire(Bloc bloc, Case laCase) {
    	this.bloc = bloc;
    	this.laCase = laCase;
    	this.bloc.ajouterElement(this);
    	this.laCase.setOccupant(this);
    }

    @objid ("6e1a18aa-57a9-45f8-8e0c-d64e2f3d19a1")
    public Bloc getBloc() {
        return this.bloc;
    }


    @objid ("7ffe15e4-eb82-4fdf-a10c-e1da8b8904b6")
    public String getSymbole() {
        return String.valueOf(this.bloc.getNumero()); // Délégation au bloc
    }

    @objid ("d55243bb-2fe7-424b-8003-461bc850024c")
    public AbstractCase getCase() {
        return this.laCase;
    }

    @objid ("8d080f9c-83a0-47e4-9e92-9483f7546d36")
    public void setCase(AbstractCase nouvelleCase) {
        this.laCase = nouvelleCase;
    }

    @objid ("a4ab4ecc-468a-42e0-848d-5879809a56c5")
    public Position getPositionVoisine(Direction dir) {
        return this.laCase.getVoisine(dir); // On ne demande que la voisine à la case
    }

}
