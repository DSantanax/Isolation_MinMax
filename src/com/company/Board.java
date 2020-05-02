package com.company;

import java.util.ArrayList;

public class Board {

	private String[][] gameState;
    private ArrayList<String> logFileO;
    private ArrayList<String> logFileX;
    private final int boardLength = 8;
    private String currentXPosition;
    private String currentOPosition;
    private String opponentPiece = "O";
    private String computerPiece = "X";
    private int maxMovesX;
    private int maxMovesO;
    private int boardRound;
    private int fitnessNum;
    private ArrayList<Board> children;

    public Board(String player) {
        children = new ArrayList<>();
        logFileX = new ArrayList<>();
        logFileO = new ArrayList<>();
        gameState = new String[boardLength][boardLength];
        //This we check who is going first
        if(player.equals("C")){
        gameState[0][0] = "X";
        gameState[7][7] = "O";
        currentXPosition = "A1"; //A1
        currentOPosition = "H8"; //H8
        }
        else{
            gameState[7][7] = "X";
            gameState[0][0] = "O";
            currentXPosition = "H8"; //H8
            currentOPosition = "A1"; //A1
        }
        maxMovesO = 0;
        maxMovesX = 0;
        boardRound=0;
        fitnessNum = 0;
    }

    //copies board
    
    public Board(Board board, int newRow, int newCol, String player2) {
        gameState = copyBoard(board);  //copies the original board.
        this.logFileO = board.logFileO;
        this.logFileX = board.logFileX;

        //Change X to new position
        if (player2.equals("X")) {
            //New X position 
            currentXPosition = board.getXPosition();
            //Previous O position
            currentOPosition = board.getOPosition();
            //move X to new board index
            updateGameState(newRow, newCol, "X");
        }
        //Change O to new position
        else {
            //New O position
            currentOPosition = board.getOPosition();
            //Previous X position
            currentXPosition = board.getXPosition();
            //move O to new board index
            updateGameState(newRow, newCol, "O");
            //move X to board index
        }
        maxMovesO = 0;
        maxMovesX = 0;
        fitnessNum = 0;
    }
    
    private String[][] copyBoard(Board board) {
        String[][] copyBoard = new String[board.gameState.length][];
        for (int i = 0; i < board.gameState.length; i++)
            copyBoard[i] = board.gameState[i].clone();

        return copyBoard;
    }

     public String[][] getGameState() {
        return copyBoard(this);
    }

    //call isValid before calling updateGameState method
    public void updateGameState(int newRowMove, int newColMove, String player) { //sample Code of how we utilize this method
        int prevRow;
        int prevCol;

        if (player.equals("O")) {

            prevRow = getRowVal(currentOPosition);
            prevCol = getColVal(currentOPosition);
            currentOPosition = stringValRow(newRowMove).concat(String.valueOf(newColMove + 1));
        }
        //Movement is X
        else {

            prevRow = getRowVal(currentXPosition);
            prevCol = getColVal(currentXPosition);
            currentXPosition = stringValRow(newRowMove).concat(String.valueOf(newColMove + 1));
        }

        this.gameState[prevRow][prevCol] = "#";

        this.gameState[newRowMove][newColMove] = player;

    }

    public void playerTurnToMove(String moveInput, String currentPlayer) {

        if (currentPlayer.equals("O")) {
            int rowVal = getRowVal(moveInput);
            int columnVal = getColVal(moveInput);

            if (validMove(moveInput, currentPlayer))
                updateGameState(rowVal, columnVal, this.opponentPiece);

            this.currentOPosition = moveInput;
        } else if (currentPlayer.equals("C")) {
            int rowVal = getRowVal(moveInput);
            int columnVal = getColVal(moveInput);

            if (validMove(moveInput, currentPlayer))
                updateGameState(rowVal, columnVal, this.computerPiece);

            this.currentXPosition = moveInput;
        }
    }

    public int getRowVal(String input) { //input = "A3"
        int rowVal = -1;

        String letter = input.substring(0, 1);

        switch (letter) {
            case "A":
                rowVal = 0;
                break;
            case "B":
                rowVal = 1;
                break;
            case "C":
                rowVal = 2;
                break;
            case "D":
                rowVal = 3;
                break;
            case "E":
                rowVal = 4;
                break;
            case "F":
                rowVal = 5;
                break;
            case "G":
                rowVal = 6;
                break;
            case "H":
                rowVal = 7;
                break;
        }

        return rowVal;
    }

    public int getColVal(String moveInput) { //A4  [0][3]
        return Integer.parseInt(moveInput.substring(1)) - 1;
    }

  //Our version of toString()
  public void printBoard(String currentPlayer) {
    
    //Use array list size

    //once we hit double doubles we use 10
    int offset = 59;
    //add to log file the move
    if (currentPlayer.equals("C"))
        logFileX.add(getXPosition());
    else if (currentPlayer.equals("O"))
        logFileO.add(getOPosition());
   
      StringBuilder res = new StringBuilder();
        String letter = "";
        res.append("  1 2 3 4 5 6 7 8" + "\t Computer Vs. Opponent\n");
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
            for(int j = 0; j < boardLength; j+= 1){
                if(gameState[i][j] == null)
                    res.append("-" + " ");
                else {
                    res.append(gameState[i][j] + " ");
                }
            }
        }
       

    //if we pass 9 we do this
    // res.insert(offset+32*k, "\t   "+ (k + 1) + "." + getOPosition() + " \t"+ getXPosition() +"\n");

    //put 1 only alternate
  
    int counter =0;
        //TODO: add for alternating players & who goes first player
    // if(turn == 0){ //if first move
    //     res.insert(offset, "\t   "+ (k + 1) + ". " + getXPosition() + " \t  \n");
    //     counter += 1;
    // }

    // for (int k = 0; k < boardRound; k++) {
    //         if(k % 2 == 0){
    //             res.insert(offset+32*k, "\t   "+ (k + 1) + ". " + getXPosition() + " \t  \n");
    //         }
    //         else {
    //             res.insert(offset+32*k, "\t   "+ (k + 1) + ". " + getOPosition() + " \t  \n");
    //         } 
    // }

     //we put both
     //we run this first to fill values if needed
     while(counter < boardRound && counter < 8){
            res.insert(offset+32*counter, "\t   "+ (counter + 1) + ". " + logFileX.get(counter) + " \t"+ logFileO.get(counter) +"\n");
            counter++;
    }

    //pre fill place holders
    //run last
    for (int k = counter; k < 8; k++) {
        //9 space between gap for fill
        //2 spaces after gap
        res.insert(offset+32*k, "\t         \t  \n");
    }
        System.out.print(res.toString());
        for (int j = 8; j < boardRound; j++) {
            System.out.println("\t\t\t   " + (j+1) + ". " + logFileX.get(j) + "   \t" + logFileO.get(j));
        }
        System.out.println();
}



    public String getXPosition() {
        return currentXPosition;
    }

    public String getOPosition() {
        return currentOPosition;
    }

    public String getORowNum() {
        return currentOPosition;
    }

    public String getOColNum() {
        return currentOPosition;
    }

    public String getXRowNum() {
        return currentXPosition;
    }

    public String getXColNum() {
        return currentXPosition;
    }

    //Check if the position is null
    public boolean isOccupied(int rowNum, int colNum) {
        return gameState[rowNum][colNum] != null;
    }

    public boolean isNorth(String moveInput, String currentPlayer)  //check if the intended move is North
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            if (currentColumnVal == nextColumnVal && nextRowVal < currentRowVal)
                return true;
        } else if (currentPlayer.equals("C")) {
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            if (currentColumnVal == nextColumnVal && nextRowVal < currentRowVal)
                return true;
        }

        return false;
    }

    public boolean isSouth(String moveInput, String currentPlayer)  //check if the intended move is South
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            if (currentColumnVal == nextColumnVal && nextRowVal > currentRowVal)
                return true;
        } else if (currentPlayer.equals("C")) {
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            if (currentColumnVal == nextColumnVal && nextRowVal > currentRowVal)
                return true;
        }

        return false;
    }

    public boolean isEast(String moveInput, String currentPlayer)  //check if the intended move is East
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            if (currentRowVal == nextRowVal && currentColumnVal < nextColumnVal)
                return true;
        } else if (currentPlayer.equals("C")) {
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            if (currentRowVal == nextRowVal && currentColumnVal < nextColumnVal)
                return true;
        }

        return false;
    }

    public boolean isWest(String moveInput, String currentPlayer)  //check if the intended move is West
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            if (currentRowVal == nextRowVal && currentColumnVal > nextColumnVal)
                return true;
        } else if (currentPlayer.equals("C")) {
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            if (currentRowVal == nextRowVal && currentColumnVal > nextColumnVal)
                return true;
        }

        return false;
    }

    public boolean isNW(String moveInput, String currentPlayer)  //check if the intended move is NW
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            int rowDifference = currentRowVal - nextRowVal;
            int colDifference = currentColumnVal - nextColumnVal;

            if (colDifference == rowDifference && currentRowVal > nextRowVal) {
                return true;
            }

        } else if (currentPlayer.equals("C")) {
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            int rowDifference = currentRowVal - nextRowVal;
            int colDifference = currentColumnVal - nextColumnVal;

            if (colDifference == rowDifference && currentRowVal > nextRowVal) {
                return true;
            }
        }

        return false;
    }

    public boolean isSE(String moveInput, String currentPlayer)  //check if the intended move is SE
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            int rowDifference = currentRowVal - nextRowVal;
            int colDifference = currentColumnVal - nextColumnVal;

            if (colDifference == rowDifference && currentRowVal < nextRowVal) {
                return true;
            }
        } else if (currentPlayer.equals("C")) {
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            int rowDifference = currentRowVal - nextRowVal;
            int colDifference = currentColumnVal - nextColumnVal;

            if (colDifference == rowDifference && currentRowVal < nextRowVal) {
                return true;
            }
        }

        return false;
    }

    public boolean isNE(String moveInput, String currentPlayer)  //check if the intended move is SE
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            int rowDifference = currentRowVal - nextRowVal;

            return (currentColumnVal + rowDifference) == nextColumnVal;

        } else if (currentPlayer.equals("C")) {
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            int rowDifference = currentRowVal - nextRowVal;

            return (currentColumnVal + rowDifference) == nextColumnVal;
        }

        return false;
    }

    public boolean isSW(String moveInput, String currentPlayer)  //check if the intended move is SW
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            int rowDifference = nextRowVal - currentRowVal;

            return rowDifference == (currentColumnVal - nextColumnVal);

        } else if (currentPlayer.equals("C")) {
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            int rowDifference = nextRowVal - currentRowVal;

            return rowDifference == (currentColumnVal - nextColumnVal);
        }

        return false;
    }

    public boolean isDiagonal(String moveInput, String currentPlayer) {
        return isNW(moveInput, currentPlayer) || isNE(moveInput, currentPlayer) || isSW(moveInput, currentPlayer) || isSE(moveInput, currentPlayer);
    } //check if intended move is Diagonal

    public boolean isHorizontal(String moveInput, String currentPlayer) {
        return isEast(moveInput, currentPlayer) || isWest(moveInput, currentPlayer);
    } //check if intended move is Horizontal

    public boolean isVertical(String moveInput, String currentPlayer) {
        return isNorth(moveInput, currentPlayer) || isSouth(moveInput, currentPlayer);
    } //check if intended move is Vertical

    public boolean validMove(String moveInput, String currentPlayer) { //check if there are forks in the road
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (nextRowVal < 0 || nextRowVal > 7) {
            System.out.println("out of bounds for rows");
            return false;
        }
        if (nextColumnVal < 0 || nextColumnVal > 7) {
            System.out.println("Out of bounds for columns");
            return false;
        }
        if (isOccupied(nextRowVal, nextColumnVal)) {
            return false;
        }

        if (moveInput.length() > 2) //A22
            return false;

        if (currentPlayer.equals("C")) {
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            if (isVertical(moveInput, currentPlayer)) { //check for occupied spaces vertically
                if (isSouth(moveInput, currentPlayer)) {
                    for (int i = currentRowVal + 1; i < nextRowVal; i++) {
                        if (this.gameState[i][currentRowVal] != null) //there is a spot occupied here
                            return false;
                    }
                }
                if (isNorth(moveInput, currentPlayer)) {
                    for (int i = currentRowVal - 1; nextRowVal > i; i++) {   //check this
                        if (this.gameState[i][currentRowVal] != null) //there is a spot occupied here
                            return false;
                    }
                }
            } else if (isHorizontal(moveInput, currentPlayer)) {
                if (isWest(moveInput, currentPlayer)) {
                    for (int i = currentColumnVal - 1; i > nextColumnVal; i--) {

                        if (this.gameState[currentRowVal][i] != null) //there is a spot occupied here
                            return false;
                    }
                }
                if (isEast(moveInput, currentPlayer)) {
                    for (int i = currentColumnVal + 1; i < nextColumnVal; i++) {

                        if (this.gameState[currentRowVal][i] != null) //there is a spot occupied here
                            return false;
                    }
                }
            } else {
                return checkDiagonal(moveInput, currentPlayer, nextRowVal, currentRowVal, currentColumnVal);
            }
        } else if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition); //A3  its getting the array index of A
            int currentColumnVal = getColVal(currentOPosition); //A3 its getting the index 3-1 so 2

            if (isVertical(moveInput, currentPlayer)) { //check for occupied spaces vertically
                if (isSouth(moveInput, currentPlayer)) {
                    for (int i = currentRowVal + 1; i < nextRowVal; i++) {
                        System.out.println("its going in is south");
                        if (this.gameState[i][currentRowVal] != null) //there is a spot occupied here
                            return false;
                    }
                }
                if (isNorth(moveInput, currentPlayer)) {
                    System.out.println("its going in is north");
                    for (int i = currentRowVal - 1; nextRowVal > i; i++) {   //check this
                        if (this.gameState[i][currentRowVal] != null) //there is a spot occupied here
                            return false;
                    }
                }
            } else if (isHorizontal(moveInput, currentPlayer)) {
                if (isWest(moveInput, currentPlayer)) {
                    System.out.println("its going in is west");
                    for (int i = currentRowVal; i > nextRowVal; i--) {

                        if (this.gameState[currentRowVal][i] != null) { //there is a spot occupied here
                            System.out.println("GOING WEST != null");
                            return false;
                        }
                    }
                }
                //
                if (isEast(moveInput, currentPlayer)) {
                    System.out.println("its going in isEast");

                    for (int i = currentColumnVal + 1; i < nextColumnVal; i++) {

                        if (this.gameState[currentRowVal][i] != null) //there is a spot occupied here
                        {
                            System.out.println("GOING EAST != null");
                            return false;
                        }
                    }
                }
            } else return checkDiagonal(moveInput, currentPlayer, nextRowVal, currentRowVal, currentColumnVal);
        }

        return true;
    }

    private boolean checkDiagonal(String moveInput, String currentPlayer, int nextRowVal, int currentRowVal, int currentColumnVal) {
        if (isDiagonal(moveInput, currentPlayer)) {

            if (isNW(moveInput, currentPlayer)) { //check this
                int col = currentColumnVal - 1;

                for (int i = currentRowVal - 1; i > nextRowVal; i--) {
                    if (this.gameState[i][col] != null) {
                        return false;
                    }
                    col -= 1;
                }
            }

            if (isSE(moveInput, currentPlayer)) { //WORKS!!!
                int col = currentColumnVal + 1;
                for (int i = currentRowVal + 1; i < nextRowVal; i++) {
                    if (this.gameState[i][col] != null) {
                        return false;
                    }
                    col++;
                }
            }

            if (isNE(moveInput, currentPlayer)) {
                int col = currentColumnVal + 1;

                for (int i = currentRowVal - 1; i > nextRowVal; i -= 1) {
                    if (this.gameState[i][col] != null)
                        return false;

                    col += 1;
                }
            }

            if (isSW(moveInput, currentPlayer)) {
                int col = currentColumnVal - 1;
                for (int i = currentRowVal + 1; i < nextRowVal; i += 1) {
                    if (this.gameState[i][col] != null)
                        return false;
                    col -= 1;
                }
            }

        } else
            return isHorizontal(moveInput, currentPlayer) || isVertical(moveInput, currentPlayer) || isDiagonal(moveInput, currentPlayer);
        return true;
    }

    public boolean canMoveSouth(String currentPlayer) {
        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColVal = getColVal(currentOPosition);

            if (currentRowVal + 1 > 7)
                return false;

            return this.gameState[currentRowVal + 1][currentColVal] == null;

        } else if (currentPlayer.equals("C")) {

            int currentRowVal = getRowVal(currentXPosition);
            int currentColVal = getColVal(currentXPosition);

            if (currentRowVal + 1 > 7)
                return false;

            return this.gameState[currentRowVal + 1][currentColVal] == null;

        }

        return false;
    } //check to see if current position can move south

    public boolean canMoveNorth(String currentPlayer) {

        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColVal = getColVal(currentOPosition);

            if (currentRowVal - 1 < 0)
                return false;

            return this.gameState[currentRowVal - 1][currentColVal] == null;

        } else if (currentPlayer.equals("C")) {

            int currentRowVal = getRowVal(currentXPosition);
            int currentColVal = getColVal(currentXPosition);

            if (currentRowVal - 1 < 0)
                return false;

            return this.gameState[currentRowVal - 1][currentColVal] == null;

        }

        return false;
    } //check to see if current position can move north

    public boolean canMoveWest(String currentPlayer) {

        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColVal = getColVal(currentOPosition);

            if (currentColVal - 1 < 0)
                return false;

            return this.gameState[currentRowVal][currentColVal - 1] == null;

        } else if (currentPlayer.equals("C")) {

            int currentRowVal = getRowVal(currentXPosition);
            int currentColVal = getColVal(currentXPosition);

            if (currentColVal - 1 < 0)
                return false;

            return this.gameState[currentRowVal][currentColVal - 1] == null;
        }

        return false;
    } //check to see if current position can move west

    public boolean canMoveEast(String currentPlayer) {

        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColVal = getColVal(currentOPosition);

            if (currentColVal + 1 > 7)
                return false;

            return this.gameState[currentRowVal][currentColVal + 1] == null;

        } else if (currentPlayer.equals("C")) {

            int currentRowVal = getRowVal(currentXPosition);
            int currentColVal = getColVal(currentXPosition);

            if (currentColVal + 1 > 7)
                return false;

            return this.gameState[currentRowVal][currentColVal + 1] == null;
        }

        return false;
    } //check to see if current position can move east

    public boolean canMoveNE(String currentPlayer) {
        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColVal = getColVal(currentOPosition);

            if (currentRowVal - 1 < 0 || currentColVal + 1 > 7)
                return false;

            return this.gameState[currentRowVal - 1][currentColVal + 1] == null;

        } else if (currentPlayer.equals("C")) {

            int currentRowVal = getRowVal(currentXPosition);
            int currentColVal = getColVal(currentXPosition);

            if (currentRowVal - 1 < 0 || currentColVal + 1 > 7)
                return false;

            return this.gameState[currentRowVal - 1][currentColVal + 1] == null;

        }

        return false;
    } //check to see if current position can move NE

    public boolean canMoveNW(String currentPlayer) {
        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColVal = getColVal(currentOPosition);

            if (currentRowVal - 1 < 0 || currentColVal - 1 < 0)
                return false;

            return this.gameState[currentRowVal - 1][currentColVal - 1] == null;

        } else if (currentPlayer.equals("C")) {

            int currentRowVal = getRowVal(currentXPosition);
            int currentColVal = getColVal(currentXPosition);

            if (currentRowVal - 1 < 0 || currentColVal - 1 < 0)
                return false;

            return this.gameState[currentRowVal - 1][currentColVal - 1] == null;

        }

        return false;
    } //check to see if current position can move NW

    public boolean canMoveSE(String currentPlayer) {
        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColVal = getColVal(currentOPosition);

            if (currentRowVal + 1 > 7 || currentColVal + 1 > 7)
                return false;

            return this.gameState[currentRowVal + 1][currentColVal + 1] == null;

        } else if (currentPlayer.equals("C")) {

            int currentRowVal = getRowVal(currentXPosition);
            int currentColVal = getColVal(currentXPosition);

            if (currentRowVal + 1 > 7 || currentColVal + 1 > 7)
                return false;

            return this.gameState[currentRowVal + 1][currentColVal + 1] == null;

        }

        return false;
    } //check to see if current position can move SE

    public boolean canMoveSW(String currentPlayer) {
        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColVal = getColVal(currentOPosition);

            if (currentRowVal + 1 > 7 || currentColVal - 1 < 0)
                return false;

            return this.gameState[currentRowVal + 1][currentColVal - 1] == null;

        } else if (currentPlayer.equals("C")) {

            int currentRowVal = getRowVal(currentXPosition);
            int currentColVal = getColVal(currentXPosition);

            if (currentRowVal + 1 > 7 || currentColVal - 1 < 0)
                return false;

            return this.gameState[currentRowVal + 1][currentColVal - 1] == null;

        }

        return false;
    } //check to see if current position can move SW

    public boolean canMoveHorizontal(String currentPlayer) {
        return canMoveEast(currentPlayer) || canMoveWest(currentPlayer);
    }

    public boolean canMoveVertical(String currentPlayer) {
        return canMoveSouth(currentPlayer) || canMoveNorth(currentPlayer);
    }

    public boolean canMoveDiagonal(String currentPlayer) {
        return canMoveNE(currentPlayer) || canMoveNW(currentPlayer) || canMoveSE(currentPlayer) || canMoveSW(currentPlayer);
    }

    //gameOver when there are no more moves to make.
    //TODO: fix
    //pass the if O or if X player to clean code so we wont have any duplicate code!
    public boolean gameOver(String currentPlayer) {
        return canMoveHorizontal(currentPlayer) || canMoveDiagonal(currentPlayer) || canMoveVertical(currentPlayer);
    }

    public String stringValRow(int i) {
        return getCharacter(i);
    }

    private String getCharacter(int i) {
        String letter;

        if (i == 0)
            letter = "A";
        else if (i == 1)
            letter = "B";
        else if (i == 2)
            letter = "C";
        else if (i == 3)
            letter = "D";
        else if (i == 4)
            letter = "E";
        else if (i == 5)
            letter = "F";
        else if (i == 6)
            letter = "G";
        else
            letter = "H";

        return letter;
    }

    
    public void setFitnessFunction(int num){
        this.fitnessNum = num;
    }

    public int getFitnessFunction(){
        return fitnessNum;
    }

    public int getNumberOfMovesX() {
        return this.maxMovesX;
    }

    public void setNumberOfMovesX(int num) {
        this.maxMovesX = num;
    }

    //change the number of moves for Opponent
	public void setNumberOfMovesO(int size) { 
        this.maxMovesO = size;
	}

	public int getNumberOfMovesO() {
		return this.maxMovesO;
    }

	public void incrementRound() {
        this.boardRound++;
    }

    public int getRound() {
        return this.boardRound;
    }
    public ArrayList<Board> getChildren(){
        return this.children;
    }

	public void setChildren(ArrayList<Board> successors) {
        this.children = successors;
    }
    
}