package gloo.projet.metier;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("f71f57e0-a450-4cd2-8178-6ba2e57e6bb2")
public class Mur extends AbstractCase {
    @objid ("46511313-180b-460f-ab47-383b98439c7b")
    public Mur(Position pos, Plateau plateau) {
        super(pos, plateau);
    }

    @objid ("8763d10f-ed42-4a8a-b09b-785df1ad2c58")
    @Override
    public String getSymbole() {
        return "#";
    }

    @objid ("8c567a49-b3cf-4b15-b43c-a3c6de9a3efa")
    @Override
    public Bloc getBloc() {
        return null; // Un mur n'a jamais de bloc
    }

    @objid ("6592620d-e212-4cd2-bea3-b57c02c5478a")
    @Override
    public boolean peutEtreOccupeePar(Bloc b) {
        return false; // Un mur ne peut jamais être occupé
    }

}
