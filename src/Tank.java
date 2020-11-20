public class Tank extends Power {


    public Tank (int whichTeam){
        super(whichTeam);
        setNumber();
    }

    public void setNumber (){
        if (super.getWhichTeam() == 1)
            super.setNumber(4);
        else
            super.setNumber(3);
    }

}
