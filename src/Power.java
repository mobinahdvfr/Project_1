
/**
 * keeps force's information
 * @author Mobina Hadavifar
 * @version 1.0
 */
public class Power {

    private int whichTeam;
    private int number;
    private boolean canMove;
    private boolean canAttack;

    /**
     * makes a new force
     * @param whichTeam the number of team
     */
    public Power (int whichTeam){
        this.whichTeam = whichTeam;
        canMove = true;
        canAttack = true;
    }

    /**
     * sets that the force can move or not
     * @param bool true if it can and false if not
     */
    public void setCanMove (boolean bool){
        canMove = bool;
    }

    /**
     * @return true if the fore can move , false if not
     */
    public boolean getCanMove (){
        return canMove;
    }

    /**
     * sets that the force can attack or not
     * @param bool true if it can and false if not
     */
    public void setCanAttack (boolean bool){
        canMove = bool;
    }

    /**
     * @return true if the fore can attack , false if not
     */
    public boolean getCanAttack (){
        return canMove;
    }

    /**
     * @return the number of team
     */
    public int getWhichTeam (){
        return whichTeam;
    }

    /**
     * sets the number of forces
     * @param number number of forces
     */
    public void setNumber (int number){
        this.number = number;
    }

    /**
     * @return number of the forces
     */
    public int getNumber (){
        return number;
    }

}
