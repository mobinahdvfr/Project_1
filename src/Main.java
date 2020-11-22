public class Main {

    public static void main(String[] args){

        Map map = new Map();
//        map.printMap();
//        map.setRowAndCol(7,9);
//        map.move("DR");
//        map.move("UR");
//        map.printMap();

        Game game = new Game();
        game.showPage();

        game.getMove(7,9);
        game.showPage();

//        game.play();
//        Card card = new Card(4);
//        card.printCard();

    }
}
