import java.util.ArrayList;

/**
 * keeps team's information
 * @author Mobina Hadavifar
 * @version 1.0
 */
public class Team {

    private ArrayList<Card> cards;
    private int teamNumber;
    private int score;

    /**
     * makes a new team
     * @param teamNumber the number of the team. 1 if it's Axis and 2 if it's Allied
     */
    public Team (int teamNumber){
        cards = new ArrayList<>();
        this.teamNumber = teamNumber;
        score =0;
    }

    /**
     * adds a list of cards to the cards list
     * @param newCards list of cards we want to add
     */
    public void addCard (ArrayList<Card> newCards){
        for (Card card : newCards){
            cards.add(card);
        }
    }

    /**
     * removes a card from the cards list
     * @param card we want to remove
     */
    public void removeCard (Card card){
        for (Card c : cards){
            if (card.equals(c)){
                cards.remove(c);
                return;
            }
        }
    }

    /**
     * @return the score
     */
    public int getScore (){
        return score;
    }

    /**
     * adds to score
     */
    public void addScore (){
        score++;
    }

    /**
     * prints the score
     */
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

    /**
     * prints the list of cards
     */
    public void printCards (){
        for (Card card : cards){
            card.printCard();
        }
    }

}
