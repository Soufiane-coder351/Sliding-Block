package gloo.projet.metier;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("7559d099-8fda-4052-b474-2ed1293484be")
public class Sortie extends AbstractCase {
    @objid ("cd73642d-77a1-48de-b4bd-0ba601a2dccd")
    public Sortie(Position pos, Plateau plat) {
        super(pos, plat);
    }

    @objid ("31ab9f7a-4bb8-45b0-afd6-a0eb0eb26e70")
    @Override
    public String getSymbole() {
        // TRÈS IMPORTANT : Si le bloc 0 est dessus, on veut voir le "0", pas le "S"
        return (this.occupant == null) ? "S" : this.occupant.getSymbole();
    }

    @objid ("199786b0-a271-4f42-8331-9bcf91925b56")
    @Override
    public Bloc getBloc() {
        return (this.occupant != null) ? this.occupant.getBloc() : null;
    }

    @objid ("2f3b803c-5572-420b-b289-ad6b41f7349b")
    @Override
    public boolean peutEtreOccupeePar(Bloc b) {
        return (b.getNumero() == 0);
    }

}
