public class Power {

    private int whichTeam;
    private int number;
    private boolean canMove;
    private boolean canAttack;
//    private Power aimPower;

    public Power (int whichTeam){
        this.whichTeam = whichTeam;
        canMove = true;
        canAttack = true;
    }

    public void setCanMove (boolean bool){
        canMove = bool;
    }

    public boolean getCanMove (){
        return canMove;
    }

    public void setCanAttack (boolean bool){
        canMove = bool;
    }

    public boolean getCanAttack (){
        return canMove;
    }

    public int getWhichTeam (){
        return whichTeam;
    }

    public void setNumber (int number){
        this.number = number;
    }

    public int getNumber (){
        return number;
    }

    public void move (){

    }

    public void attack (){

    }

}
