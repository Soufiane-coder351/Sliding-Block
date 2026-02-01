package gloo.projet.metier;

import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("f18c9e36-a759-4b55-ae2e-9cf5bfc42646")
public class Plateau {
    @objid ("4a337433-1954-47ad-bac4-ef5429e81396")
    private int nbLignes;

    @objid ("2aa972aa-8d37-4ef3-a107-4475769dbb00")
    private int nbColonnes;

    @objid ("770a35c0-d9dc-4e29-b2b0-f60396e23920")
    private Map<Position, AbstractCase> kases = new HashMap<Position, AbstractCase> ();

    @objid ("471ec8f4-ea1f-41e3-a19c-c9c4056a7690")
    private Map<Integer, Bloc> blocs = new HashMap<Integer, Bloc>();

    @objid ("d0c11cb6-1017-4b31-8371-da9bbfd5013d")
    public Plateau(int lignes, int colonnes) {
        this.nbLignes = lignes;
        this.nbColonnes = colonnes;
        // On peut même appeler la création ici pour gagner du temps
        this.creerPlateauDeBase();
    }
    
    @objid ("ab90ffef-dc88-4d0b-9251-a84aedc63d50")
    public Bloc getBloc(final int ligne, final int colonne) {
        Position p = new Position(ligne, colonne);
        AbstractCase c = getCase(p);
        if(c != null) {
            return c.getBloc();
        }
        return null;
    }
    
    public Bloc getBlocParNumero(int numero) {
        // Retourne le bloc correspondant à la clé 'numero' dans ta HashMap
        return this.blocs.get(numero);
    }

    @objid ("adfd552a-d5b1-41a9-8ebb-c395d9bb46e4")
    public AbstractCase getCase(Position p) {
        // On vérifie si p est dans les clous
        if (p.getLigne() < 0 || p.getLigne() >= nbLignes || 
            p.getColonne() < 0 || p.getColonne() >= nbColonnes) {
            return null; // Ou un Mur spécial "Hors Limites"
        }
        return kases.get(p);
    }
    
    public boolean verifierVictoire() {
        // On cherche si un élément du bloc 0 est sur une case de type Sortie
        Bloc b0 = blocs.get(0);
        for (BlocElementaire be : b0.getElements()) {
            if (be.getCase() instanceof Sortie) {
                return true;
            }
        }
        return false;
    }

    @objid ("e3020a7f-30cc-4f60-96d5-337ad6c14101")
    public void creerPlateauDeBase() {
        for (int l = 0; l < nbLignes; l++) {
            for (int c = 0; c < nbColonnes; c++) {
                Position p = new Position(l, c);
                if (l == nbLignes / 2 && c == nbColonnes - 1) {
                    kases.put(p, new Sortie(p, this));
                } 
                else if (l == 0 || l == nbLignes - 1 || c == 0 || c == nbColonnes - 1) {
                    kases.put(p, new Mur(p, this));
                } 
                else {
                    kases.put(p, new Case(p, this));
                }
            }
        }
    }

    @objid ("2661a79b-b129-4bac-a497-1a9ac24decbd")
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int l = 0; l < nbLignes; l++) {
            for (int c = 0; c < nbColonnes; c++) {
                AbstractCase laCase = getCase(new Position(l, c));
                // On demande simplement à la case son symbole
                // On ajoute un espace pour la lisibilité
                sb.append(laCase.getSymbole()).append(" "); 
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @objid ("54ff24fd-4159-411b-9870-73623675c456")
    public void ajouterBlocAuPlateau(int numero, int[][] coords) {
        Bloc nouveauBloc = new Bloc(numero, this);
        
        for(int[] c : coords) {
            Position pos = new Position(c[0], c[1]);
            AbstractCase laCaseAbstraite = getCase(pos);
            
            // verifier si c'est bien un case et pas un mur ou une sortie
            if (laCaseAbstraite instanceof Case) {
                Case laCase = (Case) laCaseAbstraite;
                
                // On vérifie aussi si la case n'est pas déjà occupée par un autre bloc !
                if (laCase.getOccupant() == null) {
                    BlocElementaire be = new BlocElementaire(nouveauBloc, laCase);
                } else {
                    System.out.println("Erreur : La case " + pos + " est déjà occupée.");
                    return;
                }
            } else {
                System.out.println("Erreur : Impossible de placer un bloc sur un Mur ou une Sortie en " + pos);
                return;
            }
        }
        this.blocs.put(numero, nouveauBloc);
    }

    @objid ("73a6cc5c-2b67-43cb-b3f9-065aa783bd6b")
    public void initialiserBlocs() {
        // Bloc 0 (le rouge) en vertical
        ajouterBlocAuPlateau(0, new int[][]{ {1,2}, {1,3} });
        
        // Bloc 1 (un petit carré)
        ajouterBlocAuPlateau(1, new int[][]{ {3,1} });
        
        // Bloc 2 (un bloc horizontal)
        ajouterBlocAuPlateau(2, new int[][]{ {2,4}, {1,4} });
    }

    

}
