public class Main {

    public static void main(String[] args){

        Map map = new Map();
        map.printMap();
//        System.out.println( map.calculateDistance(7,9,4,6) );
//        map.setRowAndCol(7,9);
//        map.move("DR");
//        map.move("UR");
//        map.printMap();

        Game game = new Game();
        game.showPage();
        int[] array = new int[] {1,2,5};
        game.attack(7, 9 , array );
//        game.getMove(7,9);
        game.showPage();

//        game.play();
//        Card card = new Card(4);
//        card.printCard();

    }
}
