package gloo.projet.metier;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("6b6319d2-0218-4f53-9a47-2a4dc10c6ede")
public abstract class AbstractCase {
//    @objid ("5da7b28b-5f71-4923-b3a3-24df27539245")
//    protected BlocElementaire blocElementaire;
    @objid ("2911ecde-6d23-4b26-8cf0-721ff7689f62")
    private Position position;

    @objid ("8927d70e-4901-45aa-b8bd-a64a1d41a608")
    protected Plateau plateau;
    
    protected BlocElementaire occupant;

    @objid ("7e1e8c04-c7cc-4ae8-a2db-f45cf411bdec")
    public AbstractCase(Position pos, Plateau plat) {
    	this.position = pos;
    	this.plateau = plat;
    }
 
    public void setOccupant(BlocElementaire be) {
        this.occupant = be;
    }
    public BlocElementaire getOccupant() {
        return this.occupant;
    }

    @objid ("efd97e9f-dc49-4e2c-a9e9-ee95792ba464")
    public abstract Bloc getBloc();
    @objid ("7b5ecdc6-8625-4fea-a366-6fe8713f97e5")
    public abstract String getSymbole();
    @objid ("4b01e516-1b11-4e49-8426-c847a66acc43")
    public abstract boolean peutEtreOccupeePar(Bloc b);

    @objid ("6bae5cfc-b905-48e7-b0ed-2ccda05a1bd3")
    public Position getVoisine(Direction dir) {
        return this.position.getVoisine(dir); // La case gère sa propre position
    }


}
