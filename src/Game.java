import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private Map map;
    private Team axis;
    private Team allied;
    private int teamTurn;
    private String nameAxis;
    private String nameAllied;
    private Card tmpCard;
    private Card[] cards;
    private int[] cardsNum;



    public Game (){
        map = new Map();
        axis = new Axis();
        allied = new Allied();
        teamTurn = 2;
        tmpCard = null;
        cards = new Card[5];
        cardsNum = new int[]{6,13,10,6,5};
        setCards();
        axis.addCard(shuffleCards(2));
        allied.addCard(shuffleCards(4));
    }

    public void play (){
//        setNames();
        Scanner scanner = new Scanner(System.in);
        while (!isGameOver()){
            showPage();

            System.out.println("Enter row col");
            int a,b;
            a=scanner.nextInt();
            b=scanner.nextInt();
            getMove(a,b);

            if (teamTurn == 1)
                teamTurn++;
            else
                teamTurn--;
        }


        getMove(0,0);
    showPage();

    }

    public boolean isGameOver (){
        return axis.getScore() == 6 || allied.getScore() == 6;
    }

    private void setCards(){
        for (int i = 0 ; i<5 ; i++){
            cards[i] = new Card(i+1);
        }
    }

    public void setNames (){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Axis team's name : ");
        nameAxis = scanner.next();
        System.out.print("Enter Allied team's name : ");
        nameAllied = scanner.next();
    }


    public void getMove (int row, int col){
        Scanner scanner = new Scanner(System.in);
        String[] moveStrings = scanner.nextLine().split(" ");
        map.setRowAndCol(row, col);
        for (int i = 0 ; i<moveStrings.length ; i++){
            int test;
            test = map.move(moveStrings[i]);
            if (test == -1)
                return;
            if (test == 1){
                if (teamTurn == 1)
                    axis.addScore();
                else
                    allied.addScore();
                return;
            }
        }

    }

    public void showPage (){
        map.printMap();
        printScores();
        if (teamTurn == 1) {
            System.out.println("It's Axis's turn.");
            axis.printCards();
        }else if (teamTurn == 2) {
            System.out.println("It's Allied's turn.");
            allied.printCards();
        }
        System.out.println("Which card will you choose?");
    }

    public ArrayList<Card> shuffleCards (int n){
        Random random = new Random();
        int i =0;
        int tmp;
        ArrayList<Card> arrayList = new ArrayList<>();
        while (i < n){
            tmp = random.nextInt(5);
            if (cardsNum[tmp] != 0){
                arrayList.add(cards[tmp]);
                cardsNum[tmp] --;
                i++;
            } else {
                if (isEmpty())
                    setCardsNum();
            }
        }
        return arrayList;
    }

    private void setCardsNum (){
        cardsNum = new int[]{6,13,10,6,5};
    }

    private boolean isEmpty(){
        for (int i = 0 ; i<5 ; i++){
            if (cardsNum[i] != 0)
                return false;
        }
        return true;
    }

    public int[] rollDice (int n){
        int[] array = new int[n];
        Random random = new Random();
        for (int i = 0 ; i<n ; i++){
            switch (i){
                case 1:
                    System.out.print("first ");
                    break;
                case 2:
                    System.out.print("; second ");
                    break;
                case 3:
                    System.out.print("; third ");
                    break;
                case 4:
                    System.out.print("; forth ");
                    break;
            }
            array[i] = random.nextInt(6);
            System.out.print("dice: " + array[i]);
        }
        return array;
    }

    public void printScores (){
        String BLUE = "\u001B[34m";
        String RESET = "\u001B[0m";
        System.out.print(BLUE);
        System.out.print("Scores: ");
        axis.printScore();
        System.out.print(BLUE);
        System.out.print("; ");
        allied.printScore();
        System.out.println();
        System.out.print(RESET);
    }

    public void printMenu (){
        System.out.println("1. Start the game.");
        System.out.println("2. Reenter the teams names.");
        System.out.println("3. Help.");
        System.out.println("4. About us.");
    }






}
