package com.company;

/*
Implement the rules part of the game:
-To do:
    do the isValid.
 */

public class Board {

    private String[][] gameState;
    private int boardLength = 8;
    private String player;
    private String currentXPosition;
    private String currentOPosition;
    private String opponentPiece = "O";
    private String computerPiece = "X";
    private Boolean gameOver = false;

    public Board() {
        currentXPosition = "A1"; //A1
        currentOPosition = "H8"; //H8
        gameState = new String[boardLength][boardLength];
        gameState[0][0] = "X";
        gameState[7][7] = "O";
        gameState[0][2] = "#";
//        gameState[4][1] = "#";
//        gameState[3][0] = "X";
      //  gameState[3][3] = "#";
    }

    //call isValid before calling updateGameState method
    public String[][] updateGameState(int newRowMove, int newColMove, String val) { //sample Code of how we utilize this method
        int prevRow;
        int prevCol;

        if (val.equals("O")) {
            prevRow = getRowVal(currentOPosition);
            prevCol = getColVal(currentOPosition);
        }
        //Movement is X
        else {
            prevRow = getRowVal(currentXPosition);
            prevCol = getColVal(currentXPosition);
        }

        this.gameState[prevRow][prevCol] = "#";

        this.gameState[newRowMove][newColMove] = val;

        return this.gameState;
    }

    public String[][] opponentTurnToMove(String moveInput){
        int rowVal = getRowVal(moveInput);
        int columnVal = getColVal(moveInput);

        //validOpponentPlacement() check for placement. If not valid than put somewhere else

        updateGameState(rowVal,columnVal,this.opponentPiece);
        //this.gameState[rowVal][columnVal] = this.opponentPiece;
        this.currentOPosition = moveInput;

        return this.gameState;
    }

    public String[][] computerTurnToMove(String moveInput){
        int rowVal = getRowVal(moveInput);
        int columnVal = getColVal(moveInput);

        //validPlacement() check for placement. If not valid than put somewhere else
        updateGameState(rowVal,columnVal,this.computerPiece);
        //this.gameState[rowVal][columnVal] = this.computerPiece;
        this.currentXPosition = moveInput;

        return this.gameState;
    }

    public boolean isGameOver(){ //game is over when there are no more moves.

        this.gameOver = true;

        return this.gameOver;
    }

    public int getRowVal(String input) {
        int rowVal = -1;

        String letter = input.substring(0, 1);

        if (letter.equals("A"))
            rowVal = 0;
        else if (letter.equals("B"))
            rowVal = 1;
        else if (letter.equals("C"))
            rowVal = 2;
        else if (letter.equals("D"))
            rowVal = 3;
        else if (letter.equals("E"))
            rowVal = 4;
        else if (letter.equals("F"))
            rowVal = 5;
        else if (letter.equals("G"))
            rowVal = 6;
        else if (letter.equals("H"))
            rowVal = 7;

        return rowVal;
    }
    public int getColVal(String moveInput){

        return Integer.parseInt(moveInput.substring(1)) - 1;
    }

    public String[][] getGameState(){
        return this.gameState;
    }

    public Board(String[][] gameState)
    {
        this.gameState = gameState;
    }

    public Board (String[][] gameState, String player, String currentXPosition, String currentOPosition) {
        this.gameState = gameState;
        this.player = player;
        this.currentXPosition = currentXPosition;
        this.currentOPosition = currentOPosition;
    }





    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        String letter = "";
        res.append("  1 2 3 4 5 6 7 8\t\tComputer Vs. Opponent\n");

        for(int i = 0; i < boardLength; i+= 1){

            if (i == 0)
                letter = "A";
            else if(i == 1)
                letter = "B";
            else if(i == 2)
                letter = "C";
            else if(i == 3)
                letter = "D";
            else if(i == 4)
                letter = "E";
            else if(i == 5)
                letter = "F";
            else if(i == 6)
                letter =  "G";
            else if(i == 7)
                letter = "H";

            res.append(letter + " ");
            for (int j = 0; j < boardLength; j++) {
                if (this.gameState[i][j] == null)
                    res.append("-" + " ");
                else {
                    res.append(this.gameState[i][j] + " ");
                }
            }
            res.append("\n");
        }

        return res.toString();
    }

    public String getXPosition() {
        return currentXPosition;
    }

    public String getOPosition() {
        return currentOPosition;
    }

    public Integer getORowNum() {
        return Integer.parseInt(currentOPosition.substring(0, 1));
    }

    public Integer getOColNum() {
        return Integer.parseInt(currentOPosition.substring(1));
    }

    public Integer getXRowNum() {
        return Integer.parseInt(currentOPosition.substring(0, 1));
    }

    public Integer getXColNum() {
        return Integer.parseInt(currentOPosition.substring(1));
    }

    //Check if the position is null
    public boolean isOccupied(int rowNum, int colNum) {
        return gameState[rowNum][colNum] != null;
    }

    public boolean isEmpty(int x, int y){
        return !isOccupied(x,y);
    }

    public boolean isNorth(String moveInput, String currentPlayerPosition)  //check if the intended move is North
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayerPosition.equals("O")){
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            if (currentColumnVal == nextColumnVal && nextRowVal < currentRowVal)
                return true;
        }
        else if (currentPlayerPosition.equals("C")){
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            if (currentColumnVal == nextColumnVal && nextRowVal < currentRowVal)
                return true;
        }

        return false;
    }

    public boolean isSouth(String moveInput, String currentPlayerPosition)  //check if the intended move is South
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayerPosition.equals("O")){
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            if (currentColumnVal == nextColumnVal && nextRowVal > currentRowVal)
                return true;
        }
        else if (currentPlayerPosition.equals("C")){
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            if (currentColumnVal == nextColumnVal && nextRowVal > currentRowVal)
                return true;
        }

        return false;
    }

    public boolean isEast(String moveInput, String currentPlayerPosition)  //check if the intended move is East
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayerPosition.equals("O")){
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            if (currentRowVal == nextRowVal && currentColumnVal < nextColumnVal)
                return true;
        }
        else if (currentPlayerPosition.equals("C")){
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            if (currentRowVal == nextRowVal && currentColumnVal < nextColumnVal)
                return true;
        }

        return false;
    }

    public boolean isWest(String moveInput, String currentPlayerPosition)  //check if the intended move is West
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayerPosition.equals("O")){
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            if (currentRowVal == nextRowVal && currentColumnVal > nextColumnVal)
                return true;
        }
        else if (currentPlayerPosition.equals("C")){
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            if (currentRowVal == nextRowVal && currentColumnVal > nextColumnVal)
                return true;
        }

        return false;
    }

    public boolean isNW(String moveInput, String currentPlayerPosition)  //check if the intended move is NW
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayerPosition.equals("O")){
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            int rowDifference = currentRowVal - nextRowVal;
            int colDifference = currentColumnVal - nextColumnVal;

            if (colDifference == rowDifference && currentRowVal > nextRowVal){
                return true;
            }

        }
        else if (currentPlayerPosition.equals("C")){
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            int rowDifference = currentRowVal - nextRowVal;
            int colDifference = currentColumnVal - nextColumnVal;

            if (colDifference == rowDifference && currentRowVal > nextRowVal){
                return true;
            }
        }

        return false;
    }

    public boolean isSE(String moveInput, String currentPlayerPosition)  //check if the intended move is SE
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayerPosition.equals("O")){
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            int rowDifference = currentRowVal - nextRowVal;
            int colDifference = currentColumnVal - nextColumnVal;

            if (colDifference == rowDifference && currentRowVal < nextRowVal){
                return true;
            }
        }
        else if (currentPlayerPosition.equals("C")){
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            int rowDifference = currentRowVal - nextRowVal;
            int colDifference = currentColumnVal - nextColumnVal;

            if (colDifference == rowDifference && currentRowVal < nextRowVal){
                return true;
            }
        }

        return false;
    }

    public boolean isNE(String moveInput, String currentPlayerPosition)  //check if the intended move is SE
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayerPosition.equals("O")){
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            int rowDifference = currentRowVal - nextRowVal;

            if ((currentColumnVal + rowDifference) == nextColumnVal)
                return true;

        }
        else if (currentPlayerPosition.equals("C")){
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            int rowDifference = currentRowVal - nextRowVal;

            if ((currentColumnVal + rowDifference) == nextColumnVal)
                return true;
        }

        return false;
    }

    public boolean isSW(String moveInput, String currentPlayerPosition)  //check if the intended move is SW
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayerPosition.equals("O")){
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            int rowDifference = nextRowVal - currentRowVal;

            if (rowDifference  == (currentColumnVal - nextColumnVal))
                return true;

        }
        else if (currentPlayerPosition.equals("C")){
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            int rowDifference = nextRowVal - currentRowVal;

            if (rowDifference  == (currentColumnVal - nextColumnVal))
                return true;
        }

        return false;
    }

    public boolean isDiagonal(String moveInput, String currentPlayerPosition){
        return isNW(moveInput,currentPlayerPosition) || isNE(moveInput,currentPlayerPosition) || isSW(moveInput,currentPlayerPosition) || isSE(moveInput,currentPlayerPosition);
    } //check if intended move is Diagonal

    public boolean isHorizontal(String moveInput, String currentPlayerPosition){
        return isEast(moveInput,currentPlayerPosition) || isWest(moveInput,currentPlayerPosition);
    } //check if intendedmove is Horizontal

    public boolean isVertical(String moveInput, String currentPlayerPosition){
        return isNorth(moveInput, currentPlayerPosition) || isSouth(moveInput, currentPlayerPosition);
    } //check if intended move is Vertical

    public boolean validMove(String moveInput, String currentPlayerPosition){ //check if there are forks in the road
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (this.gameState[nextRowVal][nextColumnVal] != null){
            System.out.println("Invalid move, something is already occupying this intended space.");
            return false;
        }

        if (currentPlayerPosition.equals("C")){
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            if (isVertical(moveInput, currentPlayerPosition)){ //check for occupied spaces vertically
                if(isSouth(moveInput,currentPlayerPosition)){
                    for(int i = currentRowVal + 1; i < nextRowVal; i++){
                        if(this.gameState[i][currentRowVal] != null) //there is a spot occupied here
                            return false;
                    }
                }
                if(isNorth(moveInput,currentPlayerPosition)){
                    for(int i = currentRowVal - 1; nextRowVal > i; i++){   //check this
                        if(this.gameState[i][currentRowVal] != null) //there is a spot occupied here
                            return false;
                    }
                }
            }
            else if (isHorizontal(moveInput, currentPlayerPosition)){
                if(isWest(moveInput, currentPlayerPosition)){
                    for(int i = currentRowVal+1; i < nextRowVal; i++){
                        if(this.gameState[currentRowVal][i] != null) //there is a spot occupied here
                            return false;
                    }
                }
                if(isEast(moveInput, currentPlayerPosition)){
                    for(int i = currentRowVal - 1; i > nextRowVal; i--){
                        if(this.gameState[currentRowVal][i] != null) //there is a spot occupied here
                            return false;
                    }
                }
            }
            else if (isDiagonal(moveInput,currentPlayerPosition)){

                if(isNW(moveInput, currentPlayerPosition)){ //check this
                    int col = currentColumnVal - 1;

                    for(int i = currentRowVal - 1; i > nextRowVal; i--){
                        if(this.gameState[i][col] != null) {
                            return false;
                        }
                        col -= 1;
                    }
                }

                if(isSE(moveInput, currentPlayerPosition)){ //WORKS!!!
                    int col = currentColumnVal + 1;
                    for(int i = currentRowVal + 1; i < nextRowVal; i++){
                        if(this.gameState[i][col] != null){
                            return false;
                        }
                        col ++;
                    }
                }

                if(isNE(moveInput, currentPlayerPosition)){
                    int col = currentColumnVal + 1;

                    for(int i = currentRowVal - 1; i > nextRowVal; i -= 1){
                        if(this.gameState[i][col] != null)
                            return false;

                        col += 1;
                    }
                }

                if(isSW(moveInput, currentPlayerPosition)){
                    int col = currentColumnVal - 1;
                    for(int i = currentRowVal+1; i < nextRowVal; i += 1){
                        if(this.gameState[i][col] != null)
                            return false;
                        col -= 1;
                    }
                }

            }
        }

        else if (currentPlayerPosition.equals("O")){
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            if (isVertical(moveInput, currentPlayerPosition)){ //check for occupied spaces vertically
                if(isSouth(moveInput,currentPlayerPosition)){
                    for(int i = currentRowVal + 1; i < nextRowVal; i++){
                        if(this.gameState[i][currentRowVal] != null) //there is a spot occupied here
                            return false;
                    }
                }
                if(isNorth(moveInput,currentPlayerPosition)){
                    for(int i = currentRowVal - 1; nextRowVal > i; i++){   //check this
                        if(this.gameState[i][currentRowVal] != null) //there is a spot occupied here
                            return false;
                    }
                }
            }
            else if (isHorizontal(moveInput, currentPlayerPosition)){
                if(isWest(moveInput, currentPlayerPosition)){
                    for(int i = currentRowVal+1; i < nextRowVal; i++){
                        if(this.gameState[currentRowVal][i] != null) //there is a spot occupied here
                            return false;
                    }
                }
                if(isEast(moveInput, currentPlayerPosition)){
                    for(int i = currentRowVal - 1; i > nextRowVal; i--){
                        if(this.gameState[currentRowVal][i] != null) //there is a spot occupied here
                            return false;
                    }
                }
            }
            else if (isDiagonal(moveInput,currentPlayerPosition)){

                if(isNW(moveInput, currentPlayerPosition)){ //check this
                    int col = currentColumnVal - 1;

                    for(int i = currentRowVal - 1; i > nextRowVal; i--){
                        if(this.gameState[i][col] != null) {
                            return false;
                        }
                        col -= 1;
                    }
                }

                if(isSE(moveInput, currentPlayerPosition)){ //WORKS!!!
                    int col = currentColumnVal + 1;
                    for(int i = currentRowVal + 1; i < nextRowVal; i++){
                        if(this.gameState[i][col] != null){
                            return false;
                        }
                        col ++;
                    }
                }

                if(isNE(moveInput, currentPlayerPosition)){
                    int col = currentColumnVal + 1;

                    for(int i = currentRowVal - 1; i > nextRowVal; i -= 1){
                        if(this.gameState[i][col] != null)
                            return false;

                        col += 1;
                    }
                }

                if(isSW(moveInput, currentPlayerPosition)){
                    int col = currentColumnVal - 1;
                    for(int i = currentRowVal+1; i < nextRowVal; i += 1){
                        if(this.gameState[i][col] != null)
                            return false;
                        col -= 1;
                    }
                }

            }
        }

        return true;
    }

    public boolean canMoveSouth(String currentPlayerPosition){
        if (currentPlayerPosition.equals("O")){
            int currentRowVal = getRowVal(currentOPosition);
            int currentColVal = getRowVal(currentOPosition);

            if(currentRowVal + 1 > 7)
                return false;

            if (this.gameState[currentRowVal + 1][currentColVal] == null)
                return true;
            return false;

        }
        else if (currentPlayerPosition.equals("C")){

            int currentRowVal = getRowVal(currentXPosition);
            int currentColVal = getColVal(currentXPosition);

            if(currentRowVal + 1 > 7)
                return false;

            if (this.gameState[currentRowVal + 1][currentColVal] == null)
                return true;
            return false;

        }

        return false;
    } //check to see if current position can move south
    public boolean canMoveNorth(String currentPlayerPosition){

        if (currentPlayerPosition.equals("O")){
            int currentRowVal = getRowVal(currentOPosition);
            int currentColVal = getRowVal(currentOPosition);

            if(currentRowVal - 1 < 0)
                return false;

            if (this.gameState[currentRowVal - 1][currentColVal] == null)
                return true;
            return false;

        }
        else if (currentPlayerPosition.equals("C")){

            int currentRowVal = getRowVal(currentXPosition);
            int currentColVal = getColVal(currentXPosition);

            if(currentRowVal - 1 < 0)
                return false;

            if (this.gameState[currentRowVal - 1][currentColVal] == null)
                return true;
            return false;

        }

        return false;
    } //check to see if current position can move north
    public boolean canMoveWest(String currentPlayerPosition){

        if (currentPlayerPosition.equals("O")){
            int currentRowVal = getRowVal(currentOPosition);
            int currentColVal = getRowVal(currentOPosition);

            if(currentColVal-1 < 0)
                return false;

            if (this.gameState[currentRowVal][currentColVal - 1] == null)
                return true;
            return false;

        }
        else if (currentPlayerPosition.equals("C")){

            int currentRowVal = getRowVal(currentXPosition);
            int currentColVal = getColVal(currentXPosition);

            if(currentColVal-1 < 0)
                return false;

            if (this.gameState[currentRowVal][currentColVal - 1] == null)
                return true;
            return false;
        }

        return false;
    } //check to see if current position can move west
    public boolean canMoveEast(String currentPlayerPosition){

        if (currentPlayerPosition.equals("O")){
            int currentRowVal = getRowVal(currentOPosition);
            int currentColVal = getRowVal(currentOPosition);

            if(currentColVal+1 > 7)
                return false;

            if (this.gameState[currentRowVal][currentColVal + 1] == null)
                return true;
            return false;

        }
        else if (currentPlayerPosition.equals("C")){

            int currentRowVal = getRowVal(currentXPosition);
            int currentColVal = getColVal(currentXPosition);

            if(currentColVal+1 > 7)
                return false;

            if (this.gameState[currentRowVal][currentColVal + 1] == null)
                return true;
            return false;
        }

        return false;
    } //check to see if current position can move east
    public boolean canMoveNE(String currentPlayerPosition){
        if (currentPlayerPosition.equals("O")){
            int currentRowVal = getRowVal(currentOPosition);
            int currentColVal = getRowVal(currentOPosition);

            if(currentRowVal - 1 < 0 || currentColVal + 1 > 7)
                return false;

            if (this.gameState[currentRowVal - 1][currentColVal + 1] == null)
                return true;
            return false;

        }
        else if (currentPlayerPosition.equals("C")){

            int currentRowVal = getRowVal(currentXPosition);
            int currentColVal = getColVal(currentXPosition);

            if(currentRowVal - 1 < 0 || currentColVal + 1 > 7)
                return false;

            if (this.gameState[currentRowVal - 1][currentColVal + 1] == null)
                return true;
            return false;

        }

        return false;
    } //check to see if current position can move NE
    public boolean canMoveNW(String currentPlayerPosition){
        if (currentPlayerPosition.equals("O")){
            int currentRowVal = getRowVal(currentOPosition);
            int currentColVal = getRowVal(currentOPosition);

            if(currentRowVal - 1 < 0 || currentColVal - 1 < 0)
                return false;

            if (this.gameState[currentRowVal - 1][currentColVal - 1] == null)
                return true;
            return false;

        }
        else if (currentPlayerPosition.equals("C")){

            int currentRowVal = getRowVal(currentXPosition);
            int currentColVal = getColVal(currentXPosition);

            if(currentRowVal - 1 < 0 || currentColVal - 1 < 0)
                return false;

            if (this.gameState[currentRowVal - 1][currentColVal - 1] == null)
                return true;
            return false;

        }

        return false;
    } //check to see if current position can move NW
    public boolean canMoveSE(String currentPlayerPosition){
        if (currentPlayerPosition.equals("O")){
            int currentRowVal = getRowVal(currentOPosition);
            int currentColVal = getRowVal(currentOPosition);

            if(currentRowVal + 1 > 7 || currentColVal + 1 > 7)
                return false;

            if (this.gameState[currentRowVal + 1][currentColVal + 1] == null)
                return true;
            return false;

        }
        else if (currentPlayerPosition.equals("C")){

            int currentRowVal = getRowVal(currentXPosition);
            int currentColVal = getColVal(currentXPosition);

            if(currentRowVal + 1 > 7 || currentColVal + 1 > 7)
                return false;

            if (this.gameState[currentRowVal + 1][currentColVal + 1] == null)
                return true;
            return false;

        }

        return false;
    } //check to see if current position can move SE
    public boolean canMoveSW(String currentPlayerPosition){
        if (currentPlayerPosition.equals("O")){
            int currentRowVal = getRowVal(currentOPosition);
            int currentColVal = getRowVal(currentOPosition);

            if(currentRowVal + 1 > 7 || currentColVal - 1 < 0)
                return false;

            if (this.gameState[currentRowVal + 1][currentColVal - 1] == null)
                return true;
            return false;

        }
        else if (currentPlayerPosition.equals("C")){

            int currentRowVal = getRowVal(currentXPosition);
            int currentColVal = getColVal(currentXPosition);

            if(currentRowVal + 1 > 7 || currentColVal - 1 < 0)
                return false;

            if (this.gameState[currentRowVal + 1][currentColVal - 1] == null)
                return true;
            return false;

        }

        return false;
    } //check to see if current position can move SW

    public boolean canMoveHorizontal(String currentPlayerPosition){
        return canMoveEast(currentPlayerPosition) || canMoveWest(currentPlayerPosition);
    }
    public boolean canMoveVertical(String currentPlayerPosition){
        return canMoveSouth(currentPlayerPosition) || canMoveNorth(currentPlayerPosition);
    }
    public boolean canMoveDiagonal(String currentPlayerPosition){
        return canMoveNE(currentPlayerPosition) || canMoveNW(currentPlayerPosition) || canMoveSE(currentPlayerPosition) || canMoveSW(currentPlayerPosition);
    }


    //gameOver when there are no more moves to make.
    public boolean gameOver(String currentPlayerPosition) {
        return !(canMoveHorizontal(currentPlayerPosition) || canMoveDiagonal(currentPlayerPosition) || canMoveVertical(currentPlayerPosition));
    }


    public static void main(String[] args){
        Board board = new Board();
        System.out.println(board.canMoveSW("O"));

        System.out.println(board.toString());
    }

}