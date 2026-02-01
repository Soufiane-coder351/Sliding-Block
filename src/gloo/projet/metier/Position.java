package gloo.projet.metier;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("90b3cad6-5b8c-4db4-a438-59a62e558d72")
public class Position {
    @objid ("8860bab9-8432-4737-85ce-271d02d894a9")
    private int ligne;

    @objid ("8274f7b9-8674-44ff-a0f9-996387fc7a54")
    private int colonne;

    @objid ("30615ee8-10d4-4b36-b9a3-778a0953af48")
    @Override
    public int hashCode() {
        return Objects.hash(colonne, ligne);
    }

    @objid ("48e355dc-4f70-48d0-9566-a1160c4dd1bb")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        return colonne == other.colonne && ligne == other.ligne;
    }

    @objid ("d9ff30ec-f71b-4164-9ec7-3d4cd296ced9")
    public Position(final int ligne, final int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    @objid ("7b904209-5f61-464f-8869-40a2481473b9")
    public int getLigne() {
        return ligne;
    }

    @objid ("afcc37ac-13af-4dc4-94ae-68b7cf120d08")
    public int getColonne() {
        return colonne;
    }

    @objid ("f9959345-3628-4402-a446-073f90f41fe5")
    public Position getVoisine(Direction dir) {
        int nl = this.ligne;
        int nc = this.colonne;
        
        if (dir == Direction.HAUT) nl--;
        else if (dir == Direction.BAS) nl++;
        else if (dir == Direction.GAUCHE) nc--;
        else if (dir == Direction.DROITE) nc++;
        return new Position(nl, nc);
    }

}
