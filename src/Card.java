/**
 * keeps the card's information
 * @author Mobina Hadavifar
 * @version 1.0
 */
public class Card {

    private int type;
    private String typeStr;

    /**
     * makes a new card
     * @param type 1, 2, 3, 4, 5
     */
    public Card(int type){
        this.type = type;
        typeStr = "order ";
        setTypeStr(type);
    }

    /**
     * @return the type
     */
    public int getType (){
        return type;
    }

    /**
     * sets the typeStr
     * @param type 1: choosing 1 unit - 2: choosing 2 units - 3: choosing 3 units - 4: choosing 4 units - 5: choosing 3 units of the same forces
     */
    private void setTypeStr (int type){
        switch (type){
            case 1:
                typeStr = typeStr + "1 unit";
                break;
            case 2:
                typeStr = typeStr + "2 units";
                break;
            case 3:
                typeStr = typeStr + "3 units";
                break;
            case 4:
                typeStr = typeStr + "4 units";
                break;
            case 5:
                typeStr = typeStr + "3* units";
                break;

        }
    }

    /**
     * prints the card
     */
    public void printCard (){
        String CYAN = "\u001B[36m";
        String RESET = "\u001B[0m";
        System.out.print(CYAN);
        System.out.println("----------------");
        System.out.println("|              |");
        System.out.println("|" + typeStr);
        System.out.println("|              |");
        System.out.println("----------------");
        System.out.print(RESET);
    }

    @Override
    public boolean equals (Object o){
        return (type == ((Card) o).type);
    }

}
