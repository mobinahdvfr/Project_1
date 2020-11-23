
/**
 * Tank force
 * @author Mobina Hadavifar
 * @version 1.0
 */
public class Tank extends Power {

    /**
     * makes a new tank
     * @param whichTeam the number of the team
     */
    public Tank (int whichTeam){
        super(whichTeam);
        setNumber();
    }

    /**
     * sets the number of forces
     * 4 if it's Axis's and 3 if it's Allied's
     */
    private void setNumber (){
        if (super.getWhichTeam() == 1)
            super.setNumber(4);
        else
            super.setNumber(3);
    }

}
