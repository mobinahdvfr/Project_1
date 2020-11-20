public class Card {

    private int type;
    private String typeStr;

    public Card(int type){
        this.type = type;
        typeStr = "order ";
        setTypeStr(type);
    }

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
