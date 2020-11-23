import java.net.PortUnreachableException;

/**
 * keeps the ground's information
 * @author Mobina Hadavifar
 * @version 1.0
 */
public class Hexagon {

    private char type;
    private Power power;
    private int powerNum;
    private boolean isFull;

    /**
     * makes a new ground
     * @param typeStr type of the ground (: normal - hill - jungle - river - bridge - town - shelter)
     */
    public Hexagon (String typeStr){
        setType(typeStr);
        power = null;
        isFull = false;
        powerNum = 0;
    }

    /**
     * sets the type of the ground
     * @param typeStr first char of the type of the ground
     */
    private void setType (String typeStr){
        //types: normal - hill - jungle - river - bridge - town - shelter
        type = typeStr.toCharArray()[0];
    }

    /**
     * @return the type
     */
    public char getType (){
        return type;
    }

    /**
     * sets the power
     * @param power the power we want to out in the ground. or null
     */
    public void setPower (Power power){
        this.power = power;
        if (power == null){
            isFull = false;
            powerNum = 0;
        } else {
            isFull = true;
            powerNum = power.getNumber();
        }
    }

    /**
     * it attacks the force in the ground
     * @return true if all the forces are destroyed and false if not
     */
    public boolean attack (){
        powerNum --;
        if (powerNum == 0) {
            power = null;
            return true;
        } else
            return false;
    }

    /**
     * @return the power in the ground. null if there's not
     */
    public Power getPower (){
        return power;
    }

    /**
     * prints the hexagon's information
     * HINT-> normal:<n> hill:<h> jungle:<j> river:<r> bridge:<b> town:<t> shelter:<s>
     * Afoot: A - Tank: T - Artillery: S -------- power.numOfPowers_teamNum<groundType>
     */
    public void printHexagon (){
        if (power != null) {
            if (power instanceof Afoot)
                System.out.print("A");
            else if (power instanceof Tank)
                System.out.print("T");
            else if (power instanceof Artillery)
                System.out.print("S");

            System.out.print(powerNum + "_" + power.getWhichTeam());
        } else
            System.out.print("____");
        System.out.print("<" + type + ">");
    }

}
