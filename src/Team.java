import java.util.ArrayList;

public class Team {

    private ArrayList<Card> cards;
    private int teamNumber;
    private int score;

    public Team (int teamNumber){
        cards = new ArrayList<>();
        this.teamNumber = teamNumber;
        score =0;
    }

    public void addCard (ArrayList<Card> newCards){
        for (Card card : newCards){
            cards.add(card);
        }
    }

    public void removeCard (Card card){
        for (Card c : cards){
            if (card.equals(c)){
                cards.remove(c);
                return;
            }
        }
    }

    public void move (){

    }

    public void attack (){

    }

    public int getScore (){
        return score;
    }

    public void addScore (){
        score++;
    }

    public void printScore (){
        String BLUE = "\u001B[34m";
        String RESET = "\u001B[0m";
        System.out.print(BLUE);
        if (this instanceof Axis)
            System.out.print("Axis=");
        else if (this instanceof Allied)
            System.out.print("Allied=");
        System.out.print(score);
        System.out.print(RESET);
    }

    public void printCards (){
        for (Card card : cards){
            card.printCard();
        }
    }

}
