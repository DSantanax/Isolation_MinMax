package com.company;

import java.util.ArrayList;

/**
 * The board class utilizes our methods to check for
 * moves, copy constructor, validity for moves, our log 
 * file & the print board method
 */

public class Board {

    private String[][] gameState;
    public ArrayList<String> logFile;
    private final int boardLength = 8;
    private String currentXPosition;
    private String currentOPosition;
    private String opponentPiece = "O";
    private String computerPiece = "X";
    private int maxMovesX;
    private int maxMovesO;
    private int fitnessNum;
    private ArrayList<Board> children;
    private String firstPlayer;

    public Board(String player) {
        maxMovesO = 0;
        maxMovesX = 0;
        fitnessNum = 0;
        children = new ArrayList<>();
        logFile = new ArrayList<>();
        gameState = new String[boardLength][boardLength];

        // This checks who was selected to go first
        if (player.equals("C")) {
            gameState[0][0] = "X";
            gameState[7][7] = "O";
            currentXPosition = "A1";
            currentOPosition = "H8";
        } else {
            gameState[7][7] = "X";
            gameState[0][0] = "O";
            currentXPosition = "H8";
            currentOPosition = "A1";
        }
    }

    // Copy constructor method
    public Board(Board board, int newRow, int newCol, String player2) {
        gameState = copyBoard(board);
        this.logFile = board.logFile;
        maxMovesO = 0;
        maxMovesX = 0;
        fitnessNum = 0;
        firstPlayer = board.firstPlayer;

        // Change X to new position
        if (player2.equals("X")) {
            // New X position
            currentXPosition = board.getXPosition();
            // Previous O position
            currentOPosition = board.getOPosition();
            // move X to new board index
            updateGameState(newRow, newCol, "X");
        }
        // Change O to new position
        else {
            // New O position
            currentOPosition = board.getOPosition();
            // Previous X position
            currentXPosition = board.getXPosition();
            // move O to new board index
            updateGameState(newRow, newCol, "O");
        }
    }

    private String[][] copyBoard(Board board) {
        String[][] copyBoard = new String[board.gameState.length][];
        for (int i = 0; i < board.gameState.length; i++)
            copyBoard[i] = board.gameState[i].clone();

        return copyBoard;
    }

    // Update the game state
    public void updateGameState(int newRowMove, int newColMove, String player) {
        int prevRow;
        int prevCol;

        if (player.equals("O")) {
            prevRow = getRowVal(currentOPosition);
            prevCol = getColVal(currentOPosition);
            currentOPosition = getRowCharacter(newRowMove).concat(String.valueOf(newColMove + 1));
        }
        // Movement is X
        else {
            prevRow = getRowVal(currentXPosition);
            prevCol = getColVal(currentXPosition);
            currentXPosition = getRowCharacter(newRowMove).concat(String.valueOf(newColMove + 1));
        }
        this.gameState[prevRow][prevCol] = "#";
        this.gameState[newRowMove][newColMove] = player;
    }

    public void playerTurnToMove(String moveInput, String currentPlayer) {
        int rowVal;
        int columnVal;

        if (currentPlayer.equals("O")) {
            rowVal = getRowVal(moveInput);
            columnVal = getColVal(moveInput);

            updateGameState(rowVal, columnVal, this.opponentPiece);
            this.currentOPosition = moveInput;

        } else if (currentPlayer.equals("C")) {

            rowVal = getRowVal(moveInput);
            columnVal = getColVal(moveInput);
            updateGameState(rowVal, columnVal, this.computerPiece);

            this.currentXPosition = moveInput;
        }
    }


    // This method prints the board 
    public void printBoard(int turns) {
        //offset for StringBuilder
        int offset = 59;
        StringBuilder res = new StringBuilder();
        int counter = 0;
        int round = turns/2;
        int index = 0;

        // Print title
        if (firstPlayer.equals("C"))
            res.append("  1 2 3 4 5 6 7 8" + "\t Computer Vs. Opponent\n");
        else
            res.append("  1 2 3 4 5 6 7 8" + "\t Opponent Vs. Computer\n");

        for (int i = 0; i < boardLength; i += 1) {
            String letter = getRowCharacter(i);

            res.append(letter).append(" ");
            for (int j = 0; j < boardLength; j += 1) {
                if (gameState[i][j] == null)
                    res.append("-" + " ");
                else {
                    res.append(gameState[i][j]).append(" ");
                }
            }
        }

        if (turns != 0) {
            // turns is even
            while (counter < round && counter < 8) {

                if (firstPlayer.equals("C")) {
                    res.insert(offset + 32 * counter, "\t   " + (counter + 1) + ". " + logFile.get(index) + " \t"
                            + logFile.get(index + 1) + "\n");
                } else {
                    res.insert(offset + 32 * counter, "\t   " + (counter + 1) + ". " + logFile.get(index) + " \t"
                            + logFile.get(index + 1) + "\n");

                }
                index += 2;
                counter++;
            }

            if (turns % 2 != 0 && counter < 8) {
                if (firstPlayer.equals("C")) {
                    // turns X
                    if (turns % 2 != 0) {
                        res.insert(offset + 32 * counter,
                                "\t   " + (counter + 1) + ". " + logFile.get(turns - 1) + " \t  \n");
                        counter++;
                    } else {
                        // else O
                        res.insert(offset + 32 * counter,
                                "\t   " + (counter + 1) + ". " + logFile.get(turns - 1) + " \t  \n");
                        counter++;
                    }
                }

                else if (firstPlayer.equals("O")) {
                    // First person turns
                    if (turns % 2 != 0) {
                        res.insert(offset + counter * 32,
                                "\t   " + (counter + 1) + ". " + logFile.get(turns - 1) + " \t  \n");
                        counter++;
                    } else {
                        res.insert(offset + 32 * counter,
                                "\t   " + (counter + 1) + ". " + logFile.get(turns - 1) + " \t  \n");
                        counter++;
                    }
                }
            }
        }
        //place holders (no rounds)
        for (int k = counter; k < 8; k++) {
            res.insert(offset + 32 * k, "\t         \t  \n");
        }

        System.out.print(res.toString());
        // After we pass the size of the board we print this
        int countHolder = 9;
        for (int j = 16; j < turns; j++) {
            if (j % 2 == 0) {
                System.out.print("\t\t\t   " + (countHolder) + ". " + logFile.get(j) + "\t");
            } else {
                System.out.println(logFile.get(j));
                countHolder += 1;
            }
        }
        System.out.println();
    }

    public boolean isOccupied(int rowNum, int colNum) {
        return gameState[rowNum][colNum] != null;
    }
    
    // These methods check the position for validity & movements 

    public boolean isNorth(String moveInput, String currentPlayer)
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            return currentColumnVal == nextColumnVal && nextRowVal < currentRowVal;
        } else if (currentPlayer.equals("C")) {
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            return currentColumnVal == nextColumnVal && nextRowVal < currentRowVal;
        }

        return false;
    }

    public boolean isSouth(String moveInput, String currentPlayer)
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            return currentColumnVal == nextColumnVal && nextRowVal > currentRowVal;
        } else if (currentPlayer.equals("C")) {
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            return currentColumnVal == nextColumnVal && nextRowVal > currentRowVal;
        }

        return false;
    }

    public boolean isEast(String moveInput, String currentPlayer) 
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            return currentRowVal == nextRowVal && currentColumnVal < nextColumnVal;
        } else if (currentPlayer.equals("C")) {
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            return currentRowVal == nextRowVal && currentColumnVal < nextColumnVal;
        }

        return false;
    }

    public boolean isWest(String moveInput, String currentPlayer) 
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            return currentRowVal == nextRowVal && currentColumnVal > nextColumnVal;
        } else if (currentPlayer.equals("C")) {
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            return currentRowVal == nextRowVal && currentColumnVal > nextColumnVal;
        }

        return false;
    }

    public boolean isNW(String moveInput, String currentPlayer) 
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            int rowDifference = currentRowVal - nextRowVal;
            int colDifference = currentColumnVal - nextColumnVal;

            return colDifference == rowDifference && currentRowVal > nextRowVal;

        } else if (currentPlayer.equals("C")) {
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            int rowDifference = currentRowVal - nextRowVal;
            int colDifference = currentColumnVal - nextColumnVal;

            return colDifference == rowDifference && currentRowVal > nextRowVal;
        }

        return false;
    }

    public boolean isSE(String moveInput, String currentPlayer) 
    {
        int nextRowVal = getRowVal(moveInput);
        int nextColumnVal = getColVal(moveInput);

        if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition);
            int currentColumnVal = getColVal(currentOPosition);

            int rowDifference = currentRowVal - nextRowVal;
            int colDifference = currentColumnVal - nextColumnVal;

            return colDifference == rowDifference && currentRowVal < nextRowVal;
        } else if (currentPlayer.equals("C")) {
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            int rowDifference = currentRowVal - nextRowVal;
            int colDifference = currentColumnVal - nextColumnVal;

            return colDifference == rowDifference && currentRowVal < nextRowVal;
        }

        return false;
    }

    public boolean isNE(String moveInput, String currentPlayer) 
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

    public boolean isSW(String moveInput, String currentPlayer) 
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
        return isNW(moveInput, currentPlayer) || isNE(moveInput, currentPlayer) || isSW(moveInput, currentPlayer)
                || isSE(moveInput, currentPlayer);
    } 

    public boolean isHorizontal(String moveInput, String currentPlayer) {
        return isEast(moveInput, currentPlayer) || isWest(moveInput, currentPlayer);
    } 

    public boolean isVertical(String moveInput, String currentPlayer) {
        return isNorth(moveInput, currentPlayer) || isSouth(moveInput, currentPlayer);
    } 

    public boolean validMove(String moveInput, String currentPlayer) {
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

        if (moveInput.length() > 2) 
            return false;

        if (currentPlayer.equals("C")) {
            int currentRowVal = getRowVal(currentXPosition);
            int currentColumnVal = getColVal(currentXPosition);

            if (isVertical(moveInput, currentPlayer)) { 
                if (isSouth(moveInput, currentPlayer)) {
                    for (int i = currentRowVal + 1; i < nextRowVal; i++) {
                        if (this.gameState[i][currentColumnVal] != null) 
                            return false;
                    }
                }
                if (isNorth(moveInput, currentPlayer)) {
                    for (int i = currentRowVal - 1; nextRowVal > i; i++) {
                        if (this.gameState[i][currentColumnVal] != null) 
                            return false;
                    }
                }
            } else if (isHorizontal(moveInput, currentPlayer)) {
                if (isWest(moveInput, currentPlayer)) {
                    for (int i = currentColumnVal - 1; i > nextColumnVal; i--) {

                        if (this.gameState[currentRowVal][i] != null) 
                            return false;
                    }
                }
                if (isEast(moveInput, currentPlayer)) {
                    for (int i = currentColumnVal + 1; i < nextColumnVal; i++) {

                        if (this.gameState[currentRowVal][i] != null) 
                            return false;
                    }
                }
            } else {
                return checkDiagonal(moveInput, currentPlayer, nextRowVal, currentRowVal, currentColumnVal);
            }
        } else if (currentPlayer.equals("O")) {
            int currentRowVal = getRowVal(currentOPosition); 
            int currentColumnVal = getColVal(currentOPosition); 

            if (isVertical(moveInput, currentPlayer)) { 
                if (isSouth(moveInput, currentPlayer)) {
                    for (int i = currentRowVal + 1; i < nextRowVal; i++) {
                        if (this.gameState[i][currentColumnVal] != null) // 
                            return false;
                    }
                }
                if (isNorth(moveInput, currentPlayer)) {
                    System.out.println("its going in is north");
                    for (int i = currentRowVal - 1; nextRowVal > i; i++) { 
                        if (this.gameState[i][currentColumnVal] != null) 
                            return false;
                    }
                }
            } else if (isHorizontal(moveInput, currentPlayer)) {
                if (isWest(moveInput, currentPlayer)) {
                    System.out.println("its going in is west");
                    for (int i = currentRowVal; i > nextRowVal; i--) {

                        if (this.gameState[currentRowVal][i] != null) { 
                            System.out.println("GOING WEST != null");
                            return false;
                        }
                    }
                }
                //
                if (isEast(moveInput, currentPlayer)) {
                    System.out.println("its going in isEast");

                    for (int i = currentColumnVal + 1; i < nextColumnVal; i++) {

                        if (this.gameState[currentRowVal][i] != null) 
                        {
                            System.out.println("GOING EAST != null");
                            return false;
                        }
                    }
                }
            } else
                return checkDiagonal(moveInput, currentPlayer, nextRowVal, currentRowVal, currentColumnVal);
        }

        return true;
    }

    private boolean checkDiagonal(String moveInput, String currentPlayer, int nextRowVal, int currentRowVal,
            int currentColumnVal) {
        if (isDiagonal(moveInput, currentPlayer)) {

            if (isNW(moveInput, currentPlayer)) { 
                int col = currentColumnVal - 1;

                for (int i = currentRowVal - 1; i > nextRowVal; i--) {
                    if (this.gameState[i][col] != null) {
                        return false;
                    }
                    col -= 1;
                }
            }

            if (isSE(moveInput, currentPlayer)) {
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
            return isHorizontal(moveInput, currentPlayer) || isVertical(moveInput, currentPlayer)
                    || isDiagonal(moveInput, currentPlayer);
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
    } 

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
    } 

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
    } 

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
    }

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
    } 

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
    }

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
    } 

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
    } 

    public boolean canMoveHorizontal(String currentPlayer) {  
        return canMoveEast(currentPlayer) || canMoveWest(currentPlayer);
    }

    public boolean canMoveVertical(String currentPlayer) { 
        return canMoveSouth(currentPlayer) || canMoveNorth(currentPlayer);
    }

    public boolean canMoveDiagonal(String currentPlayer) { 
        return canMoveNE(currentPlayer) || canMoveNW(currentPlayer) || canMoveSE(currentPlayer)
                || canMoveSW(currentPlayer);
    }

    public int getRowVal(String input) {
    int rowVal = -1;
    String letter = input.substring(0, 1).toUpperCase();

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

    private String getRowCharacter(int i) { 
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

    public void setFitnessFunction(int num) {
        this.fitnessNum = num;
    }

    public void setNumberOfMovesX(int num) {
        this.maxMovesX = num;
    }

    public void setNumberOfMovesO(int size) {
        this.maxMovesO = size;
    }
    public void setChildren(ArrayList<Board> successors) {
        this.children = successors;
    }

    public void setFirstPlayer(String selectedFirst) {
        this.firstPlayer = selectedFirst;
    }

    public int getFitnessFunction() {
        return fitnessNum;
    }

    public int getNumberOfMovesX() { 
        return this.maxMovesX;
    }
    public int getColVal(String moveInput) { 
        return Integer.parseInt(moveInput.substring(1)) - 1;
    }    

    public int getNumberOfMovesO() { 
        return this.maxMovesO;
    }

    public ArrayList<Board> getChildren() { 
        return this.children;
    }

    public String[][] getGameState() {
        return copyBoard(this);
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

    public boolean gameOver(String currentPlayer) {
        return canMoveHorizontal(currentPlayer) || canMoveDiagonal(currentPlayer) || canMoveVertical(currentPlayer);
    }
}