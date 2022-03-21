package it.polimi.ingsw.Model;
/////// Proposta : eliminiamo motherNature
public class MotherNature {
    private static MotherNature motherNature;
    private final static int STARTINGPOSITION = 0;
    private static int position;

    private MotherNature() {
        position = STARTINGPOSITION;
    }

    public static MotherNature getMotherNature() {
        if(motherNature == null) motherNature = new MotherNature();
        return motherNature;
    }

    public static int getPosition() {
        return MotherNature.position;
    }

    /*
    * Metodo che, alla fusione di due isole, sposta la posizione di madre natura alla prima delle due isole
    * perchè probabilmente implementeremo un modo per tenere su GameTable una unica posizione per l'isola fusa
    */
    public static void islandFused(int islandIndexOne, int islandIndexTwo) {
        if(MotherNature.position == islandIndexTwo)
            MotherNature.position =islandIndexOne;
    }

    public static void move(int numberOfSteps, int numberOfIsland) {
        MotherNature.position = (MotherNature.position + numberOfSteps) % numberOfIsland;
    }
}
