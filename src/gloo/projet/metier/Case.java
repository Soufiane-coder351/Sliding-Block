package gloo.projet.metier;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("8facd58a-a59d-4764-952d-4548a4e7bce0")
public class Case extends AbstractCase {
//    @objid ("011bd9fb-a198-43a6-897c-72bfcc22748d")
//    private BlocElementaire occupant; // a verifier si correcte
    @objid ("4e7f46fb-82f9-441c-8e56-48381607266c")
    public Case(Position pos, Plateau plateau) {
        super(pos, plateau);
    }

    @objid ("410fa786-aa52-4dea-ac87-5a58a1fc8261")
    public BlocElementaire getOccupant() {
        // TODO Auto-generated return
        return occupant;
    }

    @objid ("c4938d6d-6b07-43fb-bd25-2c6d406883f0")
    public void setOccupant(final BlocElementaire be) {
        this.occupant = be;
    }

    @objid ("115a05ee-32e3-4df4-a5b0-5586ec935f95")
    @Override
    public String getSymbole() {
        // Si occupée, on affiche le symbole du bloc, sinon le point
        return (this.occupant == null) ? "." : this.occupant.getSymbole();
    }

    @objid ("78caaa38-0cf9-479d-a9aa-70371ff15c0f")
    @Override
    public Bloc getBloc() {
        return (this.occupant != null) ? this.occupant.getBloc() : null;
    }

    @objid ("f8334390-9d62-427d-ae38-cc80d0ab62ab")
    @Override
    public boolean peutEtreOccupeePar(Bloc b) {
        return (this.occupant == null || this.occupant.getBloc() == b);
    }

}
