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
            //choose car;2z d
            scanPower();
            System.out.println("Enter row col");
            int a,b;
            a=scanner.nextInt();
            b=scanner.nextInt();
            scanDirection(a,b);

            if (teamTurn == 1)
                teamTurn++;
            else
                teamTurn--;
        }
        map.printMap();
        printWinner();

        scanDirection(0,0);
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


    public void scanDirection (int row, int col){
        if (map.whichTeam(row,col) != teamTurn){
            return;
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
                    if (teamTurn == 1)
                        addScore();
                }
                counter++;
                if (map.getPower(row,col) instanceof Afoot && counter==2){
                    map.getPower(row,col).setCanAttack(false);
                    return;
                } else if (map.getPower(row,col) instanceof Tank && counter==3){
                    return;
                } else if (map.getPower(row,col) instanceof Afoot && counter==1){
                    map.getPower(row,col).setCanAttack(false);
                    return;
                }
            }
        }
    }

    public void addScore (){
        if (teamTurn == 1)
            axis.addScore();
        else
            allied.addScore();
    }

    public void scanPower (){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the row and column:");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        scanner.nextLine();
        System.out.println("do you want to move it?");
        String tmp = scanner.nextLine();
        if (tmp.equals("yes"))
            scanDirection(row-1,col-1);
        System.out.println("do you want to attack?");
        tmp = scanner.nextLine();
        if (tmp.equals("yes"))
            scanAim(row-1,col-1);


    }

    public void scanAim (int row,int col){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter aim's row and column:");
        int aimRow = scanner.nextInt();
        int aimCol = scanner.nextInt();
        scanner.nextLine();
        if (map.isEmpty(aimRow-1, aimCol-1))
            return;
        attack(row, col, aimRow, aimCol);

    }

    public void attack (int row , int col, int aimRow, int aimCol){
        if (!map.getPower(row, col).getCanAttack()){
            map.getPower(row,col).setCanAttack(true);
            return;
        }
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

    public void attack (int aimRow, int aimCol, int[] dice){
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
        }
        if (bool && whichTeam!=teamTurn)
            addScore();

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
