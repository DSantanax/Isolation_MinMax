package com.company;
import java.util.*;
import java.util.ArrayList;

public class Board {

    private int boardLength = 8;
    private String player;
    private String[][] gameState;
    private String xPosition;
    private String oPosition;
    private String opponentPiece = "O";
    private String computerPiece = "X";

    public Board() {
        gameState = new String[boardLength][boardLength];
        gameState[0][0] = "X";
        gameState[7][7] = "O";
        xPosition = "A1";
        oPosition = "H8";

    }

    //call isValid before calling updateGameState method
    public String[][] updateGameState(int newRowMove, int newColMove, String val) { //sample Code of how we utilize this method
        int prevRow;
        int prevCol;

        if (val.equals("O")) {
            prevRow = getORowNum();
            prevCol = getOColNum();
        }
        //Movement is X
        else {
            prevRow = getXRowNum();
            prevCol = getXColNum();
        }
        this.gameState[prevRow][prevCol] = "#";

        this.gameState[newRowMove][newColMove] = val;

        return this.gameState;
    }

    public String[][] opponentTurnToMove(String moveInput){
        int rowVal = getRowVal(moveInput);
        int columnVal = getColVal(moveInput);

        //validOpponentPlacement() check for placement. If not valid than put somewhere else

        this.gameState[rowVal][columnVal] = this.opponentPiece;

        return this.gameState;
    }

    public String[][] computerTurnToMove(String moveInput){
        int rowVal = getRowVal(moveInput);
        int columnVal = getColVal(moveInput);

        //validPlacement() check for placement. If not valid than put somewhere else

        this.gameState[rowVal][columnVal] = this.computerPiece;

        return this.gameState;
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

    public Board (String[][] gameState, String player, String xPosition, String oPosition) {
        this.gameState = gameState;
        this.player = player;
        this.xPosition = xPosition;
        this.oPosition = oPosition;
    }

//    public boolean isGameOver(){
//
//    }

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
        return xPosition;
    }

    public String getOPosition() {
        return oPosition;
    }

    public Integer getORowNum() {
        return Integer.parseInt(oPosition.substring(0, 1));
    }

    public Integer getOColNum() {
        return Integer.parseInt(oPosition.substring(1));
    }

    public Integer getXRowNum() {
        return Integer.parseInt(oPosition.substring(0, 1));
    }

    public Integer getXColNum() {
        return Integer.parseInt(oPosition.substring(1));
    }

    //Check if the position is null
    public boolean getPositionValid(int rowNum, int colNum) {
        return gameState[rowNum][colNum] != null;
    }
}
