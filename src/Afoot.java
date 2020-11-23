
/**
 * Afoot force
 * @author Mobina Hadavifar
 * @version 1.0
 */
public class Afoot extends Power {

    /**
     * makes a new afoot
     * @param whichTeam the number of the team
     */
    public Afoot (int whichTeam){
        super(whichTeam);
        setNumber();
    }

    /**
     * sets the number of forces (4)
     */
    private void setNumber (){
        super.setNumber(4);
    }

}
