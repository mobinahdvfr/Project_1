import java.net.PortUnreachableException;

public class Hexagon {

    private char type;
    private Power power;
    private int powerNum;
    private boolean isFull;

    public Hexagon (String typeStr){
        setType(typeStr);
        power = null;
        isFull = false;
        powerNum = 0;
    }

    private void setType (String typeStr){
        //types: normal - hill - jungle - river - bridge - town - shelter
        type = typeStr.toCharArray()[0];
    }

    public char getType (){
        return type;
    }

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

    public boolean attack (){
        powerNum --;
        if (powerNum == 0) {
            power = null;
            return true;
        } else
            return false;
    }

    public Power getPower (){
        return power;
    }

    public int getPowerNum (){
        return powerNum;
    }

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
