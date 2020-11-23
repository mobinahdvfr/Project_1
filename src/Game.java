import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * handles our game
 * @author Mobina Hadavifar
 * @version 1.0
 */
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

    /**
     * makes a new game
     */
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

    /**
     * handles the playing
     */
    public void play (){
        setNames();
        Scanner scanner = new Scanner(System.in);
        while (!isGameOver()){
            showPage();
            scanCard();
            if (tmpCard.getType()<5) {
                for (int i = 0; i < tmpCard.getType(); i++) {
                    scanPower(null);
                }
            } else if (tmpCard.getType() == 5){
                Power p = scanPower(null);
                scanPower(p);
                scanPower(p);
            }

            if (teamTurn == 1)
                teamTurn++;
            else
                teamTurn--;
        }
        map.printMap();
        printWinner();

    }

    /**
     * checks if the game is over or not
     * @return true if it is and false if not
     */
    private boolean isGameOver (){
        return axis.getScore() == 1 || allied.getScore() == 1 ;
    }

    /**
     * it makes game's list of cards
     */
    private void setCards(){
        for (int i = 0 ; i<5 ; i++){
            cards[i] = new Card(i+1);
        }
    }

    /**
     * it sets the tams names
     */
    private void setNames (){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Axis team's name : ");
        nameAxis = scanner.next();
        System.out.print("Enter Allied team's name : ");
        nameAllied = scanner.next();
    }

    /**
     * it reads the direction from user
     * @param row row of the force
     * @param col column of the force
     */
    private void scanDirection (int row, int col){
        System.out.println("Enter the move directions.");
        if (map.whichTeam(row,col) != teamTurn){
            return;
        }
        String type = "";
        if (map.getPower(row,col) instanceof Afoot){
            type = "Afoot";
        } else if (map.getPower(row,col) instanceof Tank){
            type = "Tank";
        } else if (map.getPower(row,col) instanceof Artillery){
            type = "Artillery";
        }
        Scanner scanner = new Scanner(System.in);
        String read;
        read = scanner.nextLine();
        String[] moveStrings = read.split(" ");
        map.setRowAndCol(row, col);
        String dir;
        int counter=0;
        for (String moveString : moveStrings) {
            for (int j = 0; j < Integer.parseInt(moveString.toCharArray()[0] + "" ); j++) {
                dir = String.copyValueOf(moveString.toCharArray(), 1, moveString.toCharArray().length - 1);
                int test;
                test = map.move(dir);
                if (test == -1)
                    return;
                if (test == 1) {
                    addScore();
                    return;
                }
                counter++;
                //**********************************************************************
                if (type.equals("Afoot") && counter==2){
                    map.getPower(map.getRowAndCol()[0], map.getRowAndCol()[1]).setCanAttack(false);
                    return;
                } else if (type.equals("Tank") && counter==3){
                    return;
                } else if (type.equals("Artillery") && counter==1){
                    map.getPower(map.getRowAndCol()[0], map.getRowAndCol()[1]).setCanAttack(false);
                    return;
                }
            }
        }
    }

    /**
     * it scans a card from user
     */
    private void scanCard (){
        System.out.println("Which card will you choose?");
        Scanner scanner = new Scanner(System.in);
        String cardString = scanner.nextLine();
        cardString = cardString.split(" ")[1];
        switch (cardString){
            case "1":
                tmpCard=new Card(1);
                break;
            case "2":
                tmpCard=new Card(2);
                break;
            case "3":
                tmpCard=new Card(3);
                break;
            case "4":
                tmpCard=new Card(4);
                break;
            case "3*":
                tmpCard=new Card(5);
                break;
        }
        getTeamTurn().removeCard(tmpCard);
        getTeamTurn().addCard(shuffleCards(1));
    }

    /**
     * checks which team's turn it is
     * @return the team which it's its turn to play
     */
    private Team getTeamTurn (){
        if (teamTurn == 1)
            return axis;
        else if (teamTurn == 2)
            return allied;
        return null;
    }

    /**
     * adds a score to the current playing team
     */
    private void addScore (){
        if (teamTurn == 1)
            axis.addScore();
        else
            allied.addScore();
    }

    /**
     * it reads the current team's force location from user
     * @param p if it isn't null, the chosen force must be the same type as its type
     * @return the power user has chosen
     */
    private Power scanPower (Power p){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the row and column:");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        if (map.getPower(row-1, col-1) == null)
            return null;
        if (map.getPower(row-1, col-1).getWhichTeam() != teamTurn){
            return null;
        }

        if (p != null) {
            if (!((p instanceof Afoot && map.getPower(row - 1, col - 1) instanceof Afoot)
                    || (p instanceof Tank && map.getPower(row - 1, col - 1) instanceof Tank)
                    || (p instanceof Artillery && map.getPower(row - 1, col - 1) instanceof Artillery))) {
                return null;
            }
        }


        scanner.nextLine();
        System.out.println("do you want to move it?");
        String tmp = scanner.nextLine();
        if (tmp.equals("yes"))
            scanDirection(row-1,col-1);
        if (!map.getPower(map.getRowAndCol()[0], map.getRowAndCol()[1]).getCanAttack()){
            map.getPower(map.getRowAndCol()[0], map.getRowAndCol()[1]).setCanAttack(true);
            return map.getPower(map.getRowAndCol()[0], map.getRowAndCol()[1]);
        }
        System.out.println("do you want to attack?");
        tmp = scanner.nextLine();
        if (tmp.equals("yes"))
            scanAim(map.getRowAndCol()[0], map.getRowAndCol()[1]);

        return map.getPower(map.getRowAndCol()[0], map.getRowAndCol()[1]);
    }

    /**
     * it reads the location to be attacked (if possible)
     * @param row row of the force
     * @param col column of the force
     */
    private void scanAim (int row,int col){
        if (!map.getPower(row, col).getCanAttack()){
            map.getPower(row,col).setCanAttack(true);
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter aim's row and column:");
        int aimRow = scanner.nextInt();
        int aimCol = scanner.nextInt();
        scanner.nextLine();
        if (map.isEmpty(aimRow-1, aimCol-1))
            return;
        attack(row, col, aimRow-1, aimCol-1);

    }

    /**
     * attacks if the conditions are achieved
     * @param row row of user's force
     * @param col column of user's force
     * @param aimRow row of enemy's force
     * @param aimCol column of enemy's force
     */
    private void attack (int row , int col, int aimRow, int aimCol){

        int distance = map.calculateDistance(row, col, aimRow, aimCol);
        if (map.getPower(row,col) instanceof Afoot){
            if (distance > 3)
                return;
            attack(aimRow, aimCol, rollDice(4-distance));
        } else if (map.getPower(row,col) instanceof Tank){
            if (distance > 3)
                return;
            attack(aimRow, aimCol, rollDice(3));
        } else if (map.getPower(row,col) instanceof Artillery){
            if (distance > 6)
                return;
            if (distance == 1 || distance == 2)
                attack(aimRow, aimCol, rollDice(3));
            else if (distance == 3 || distance == 4)
                attack(aimRow, aimCol, rollDice(4));
            else if (distance == 5 || distance == 6)
                attack(aimRow, aimCol, rollDice(1));
        }
    }

    /**
     * attacks
     * @param aimRow row of enemy's force
     * @param aimCol column of enemy's force
     * @param dice dice numbers
     */
    private void attack (int aimRow, int aimCol, int[] dice){
        boolean bool = false;
        int whichTeam = map.whichTeam(aimRow, aimCol);
        for (int die : dice) {
            if (map.getPower(aimRow,aimCol) == null){
                return;
            }
            if (die == 1 || die == 6) {
                if (map.getPower(aimRow, aimCol) instanceof Afoot) {
                    bool = map.attack(aimRow, aimCol);
                }
            } else if (die == 2) {
                if (map.getPower(aimRow, aimCol) instanceof Tank) {
                    bool = map.attack(aimRow, aimCol);
                }
            } else if (die == 5) {
                bool = map.attack(aimRow, aimCol);
            }
            if (bool && whichTeam!=teamTurn)
                addScore();
        }
    }

    /**
     * prints the page of game
     */
    private void showPage (){
        map.printMap();
        printScores();
        if (teamTurn == 1) {
            System.out.println("It's Axis's turn.");
            axis.printCards();
        }else if (teamTurn == 2) {
            System.out.println("It's Allied's turn.");
            allied.printCards();
        }
    }

    /**
     * choose random cards from our cards list
     * @param n number of cards we want
     * @return a list of cards
     */
    private ArrayList<Card> shuffleCards (int n){
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

    /**
     * sets the number of each type of cards
     */
    private void setCardsNum (){
        cardsNum = new int[]{6,13,10,6,5};
    }

    /**
     * checks if the cards are empty
     * @return true if it is
     */
    private boolean isEmpty(){
        for (int i = 0 ; i<5 ; i++){
            if (cardsNum[i] != 0)
                return false;
        }
        return true;
    }

    /**
     * rolls dice
     * @param n number of dice we want to roll
     * @return an array of numbers
     */
    private int[] rollDice (int n){
        int[] array = new int[n];
        Random random = new Random();
        for (int i = 0 ; i<n ; i++){
            switch (i){
                case 0:
                    System.out.print("first ");
                    break;
                case 1:
                    System.out.print("; second ");
                    break;
                case 2:
                    System.out.print("; third ");
                    break;
                case 3:
                    System.out.print("; forth ");
                    break;
            }
            array[i] = random.nextInt(6)+1;
            System.out.print("dice: " + array[i]);
        }
        System.out.println();
        return array;
    }

    /**
     * prints the scores
     */
    private void printScores (){
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

    /**
     * prints the menu
     */
    public void printMenu (){
        System.out.println("1. Start the game.");
        System.out.println("2. Reenter the teams names.");
        System.out.println("3. Help.");
        System.out.println("4. About us.");
    }

    /**
     * prints the winner
     */
    public void printWinner(){
        printScores();
        if (axis.getScore() > 5){
            System.out.print(nameAxis);
        } else if (allied.getScore() > 5){
            System.out.print(nameAllied);
        }
        System.out.println( " has won!");
    }




}
