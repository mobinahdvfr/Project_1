
/**
 * handles our game's map
 * @author Mobina Hadavifar
 * @version 1.0
 */
public class Map {

    private Hexagon[][] map;
    //current row of the force we want to move
    private int row;
    //current column of the force we want to move
    private int col;

    /**
     * makes a new map
     */
    public Map (){
        map = new Hexagon[9][13];
        setNormals();
        setHills();
        setJungles();
        setRivers();
        setBridges();
        setTowns();
        setShelter();
        setAfoot();
        setTanks();
        setArtillery();
    }

    /**
     * sets the row and col
     * @param row current row of the force we want to move
     * @param col current column of the force we want to move
     */
    public void setRowAndCol (int row , int col){
        this.row = row;
        this.col = col;
    }

    /**
     * @return the row and col
     */
    public int[] getRowAndCol (){
        int[] a = new int[] {row, col};
        return a;
    }

    /**
     * sets the normal ground
     */
    private void setNormals (){
        for (int i =0 ; i<9 ; i++){
            for (int j = 0 ; j<13 ; j++){
                map[i][j] = new Hexagon("normal");
            }
        }
    }

    /**
     * sets the hills
     */
    private void setHills (){
        map[0][0] = new Hexagon("hill");
        map[0][1] = new Hexagon("hill");
        map[1][7] = new Hexagon("hill");
        map[2][7] = new Hexagon("hill");
        map[4][5] = new Hexagon("hill");
        map[4][12] = new Hexagon("hill");
        map[5][4] = new Hexagon("hill");
        map[5][11] = new Hexagon("hill");
    }

    /**
     * sets the jungles
     */
    private void setJungles (){
        map[2][10] = new Hexagon("jungle");
        map[2][12] = new Hexagon("jungle");
        map[3][1] = new Hexagon("jungle");
        map[3][3] = new Hexagon("jungle");
        map[3][11] = new Hexagon("jungle");
        map[4][1] = new Hexagon("jungle");
        map[4][8] = new Hexagon("jungle");
        map[4][12] = new Hexagon("jungle");
        map[5][3] = new Hexagon("jungle");
        map[5][11] = new Hexagon("jungle");
        map[6][7] = new Hexagon("jungle");
        map[6][8] = new Hexagon("jungle");
        map[7][3] = new Hexagon("jungle");
        map[7][4] = new Hexagon("jungle");
        map[7][8] = new Hexagon("jungle");
    }

    /**
     * sets the rivers
     */
    private void setRivers (){
        map[1][1] = new Hexagon("river");
        map[1][2] = new Hexagon("river");
        map[1][3] = new Hexagon("river");
        map[2][1] = new Hexagon("river");
        map[4][0] = new Hexagon("river");
    }

    /**
     * sets the bridges
     */
    private void setBridges (){
        map[0][4] = new Hexagon("bridge");
        map[3][0] = new Hexagon("bridge");
    }

    /**
     * sets the towns
     */
    private void setTowns (){
        map[0][3] = new Hexagon("town");
        map[2][0] = new Hexagon("town");
        map[4][10] = new Hexagon("town");
        map[6][2] = new Hexagon("town");
        map[8][11] = new Hexagon("town");
    }

    /**
     * sets the shelters
     */
    private void setShelter (){
        map[1][4] = new Hexagon("shelter");
    }

    /**
     * sets the afoots
     */
    private void setAfoot (){
        map[0][1].setPower(new Afoot(1));
        map[0][2].setPower(new Afoot(1));
        map[0][7].setPower(new Afoot(1));
        map[0][10].setPower(new Afoot(1));
        map[0][12].setPower(new Afoot(1));
        map[1][4].setPower(new Afoot(1));
        map[1][8].setPower(new Afoot(1));
        map[4][1].setPower(new Afoot(2));
        map[4][6].setPower(new Afoot(2));
        map[4][8].setPower(new Afoot(2));
        map[4][11].setPower(new Afoot(2));
        map[5][3].setPower(new Afoot(2));
        map[6][7].setPower(new Afoot(2));
        map[7][0].setPower(new Afoot(2));
        map[7][9].setPower(new Afoot(2));
        map[8][8].setPower(new Afoot(2));
    }

    /**
     * sets the tanks
     */
    private void setTanks (){
        map[0][0].setPower(new Tank(1));
        map[0][5].setPower(new Tank(1));
        map[0][8].setPower(new Tank(1));
        map[0][11].setPower(new Tank(1));
        map[1][5].setPower(new Tank(1));
        map[1][10].setPower(new Tank(1));
        map[8][0].setPower(new Tank(2));
        map[8][1].setPower(new Tank(2));
        map[8][12].setPower(new Tank(2));
    }

    /**
     * sets the artillery
     */
    private void setArtillery (){
        map[7][1].setPower(new Artillery());
        map[7][5].setPower(new Artillery());
    }

    /**
     * moves a force if possible
     * @param direction R: right - L: left - UR: up right - UL: up left - DR: down right - DL: down left
     * @return -1 if moving is stopped. 0 if we can move more. 1 if one team got the special town of the other team
     */
    public int move (String direction){

        if (!(map[row][col].getPower().getCanMove() ) ){
            map[row][col].getPower().setCanMove(true);
            return -1;
        }

        int newRow;
        int newCol;
        for (int i = 0 ; i<1 ; i++){
            newRow = row;
            newCol = col;
            switch (direction){
                case "R":
                    newCol++;
                    break;
                case "L":
                    newCol--;
                    break;
                case "UR":
                    if (row%2 == 1){
                        newCol++;
                    }
                    newRow--;
                    break;
                case "UL":
                    if (row%2 == 0){
                        newCol--;
                    }
                    newRow--;
                    break;
                case "DR":
                    if (row%2 == 1){
                        newCol++;
                    }
                    newRow++;
                    break;
                case "DL":
                    if (row%2 == 0){
                        newCol--;
                    }
                    newRow++;
                    break;
            }
            if (newCol<0 || newCol>12 || newRow<0 || newRow>8 )
                return -1;
            if (map[newRow][newCol].getPower() != null)
                return -1;
            if (map[newRow][newCol].getType() == 'r')
                return -1;
            if (( (map[row][col].getPower() instanceof Tank) || (map[row][col].getPower() instanceof Artillery) )
                    && map[newRow][newCol].getType() == 's')
                return -1;
            move(row, col, newRow, newCol);
            setRowAndCol(newRow, newCol);
            if (map[newRow][newCol].getType() == 'j' || map[newRow][newCol].getType() == 't') {
                map[newRow][newCol].getPower().setCanMove(false);
                map[newRow][newCol].getPower().setCanAttack(false);
                if ((newRow==2 && newCol==0 && map[newRow][newCol].getPower().getWhichTeam()==2)
                        || (newRow==8 && newCol==11 && map[newRow][newCol].getPower().getWhichTeam()==1))
                    return 1;
                return -1;
            }
        }
        return 0;
    }

    /**
     * moves a force
     * @param oldRow old row of force
     * @param oldCol old column of force
     * @param newRow new row of force
     * @param newCol new column of force
     */
    private void move (int oldRow, int oldCol, int newRow, int newCol){
        map[newRow][newCol].setPower(map[oldRow][oldCol].getPower());
        map[oldRow][oldCol].setPower(null);
    }

    /**
     * attacks
     * @param aimRow row of force we want to attack
     * @param aimCol column of force we want to attack
     * @return true if all of the force is gone and false if not
     */
    public boolean attack (int aimRow, int aimCol){
        return map[aimRow][aimCol].attack();
    }

    /**
     * calculates the distance between two grounds
     * @param row1 row of first ground
     * @param col1 column of first ground
     * @param row2 row of second ground
     * @param col2 column of second ground
     * @return the distance
     */
    public int calculateDistance (int row1, int col1, int row2, int col2){
        int counter=0;
        while ( !(row1 == row2 && col1==col2) ) {
            if (row2 > row1 && col2 > col1) {
                if (row1%2 == 1){
                    col1++;
                }
                row1++;
                counter++;
            } else if (row2>row1 && col2==col1){
                row1++;
                counter++;
            } else if (row2==row1 && col2>col1){
                col1++;
                counter++;
            } else if (row2 < row1 && col2 < col1){
                if (row1%2 == 0){
                    col1--;
                }
                row1--;
                counter++;
            } else if (row2 > row1){
                if (row1%2 == 0){
                    col1--;
                }
                row1++;
                counter++;
            } else if (row2 < row1 && col2 > col1){
                if (row1%2 == 1){
                    col1++;
                }
                row1--;
                counter++;
            } else if (row2<row1){
                row1--;
                counter++;
            } else {
                col1--;
                counter++;
            }
        }
        return counter;
    }

    /**
     * prints the map
     */
    public void printMap (){
        String RESET = "\u001B[0m";
        String YELLOW = "\u001B[33m";
        System.out.print(YELLOW);
        System.out.println("HINT-> normal:<n> hill:<h> jungle:<j> river:<r> bridge:<b> town:<t> shelter:<s>");
        System.out.print("Afoot: A - Tank: T - Artillery: S -------- power.numOfPowers_teamNum<groundType>");
        System.out.println(RESET);
        for (int i = 0 ; i<9 ; i++){
            if (i%2 == 1)
                System.out.print("       ");
            for (int j = 0 ; j<12 ; j++){
                map[i][j].printHexagon();
                System.out.print("       ");
            }
            if (i%2 == 0)
                map[i][12].printHexagon();
            System.out.println();
            System.out.println();
        }
    }

    /**
     * returns the number of the team which its force is in the given location (if the ground is full)
     * @param row row of the force
     * @param col column of the force
     * @return team's number
     */
    public int whichTeam (int row, int col){
        if (map[row][col].getPower() != null)
            return map[row][col].getPower().getWhichTeam();
        else
            return 0;
    }

    /**
     * checks if the given location is empty or not
     * @param row row
     * @param col column
     * @return true if it's empty and false if it's not
     */
    public boolean isEmpty (int row, int col){
        return map[row][col].getPower() == null;
    }

    /**
     * @param row row
     * @param col column
     * @return the power in the given location if there is, null if the ground is empty
     */
    public Power getPower (int row, int col){
        return map[row][col].getPower();
    }


}
