package com.company;
import java.util.*;
import java.util.ArrayList;

public class Board {
    ArrayList<Board> successors;

    private String[][] board;
    private int boardLength = 8;
    private String[][] gameState;
    private String xPosition;
    private String oPosition;

    public Board() {
        successors = new ArrayList<Board>();
        gameState = new String[boardLength][boardLength];
        gameState[0][0] = "X";
        gameState[7][7] = "O";
        xPosition = "A1";
        oPosition = "H8";
    }

    public Board (String[][] gameState, String xPosition, String oPosition) {
        this.gameState = gameState;
        successors = new ArrayList<Board>();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (gameState[i][j] == null){
                    res.append("-");
                }else  {res.append(gameState[i][j]);}

                res.append(" ");
            }
            res.append("\n");
        }

        return res.toString();
    }


    //// Print the current board state
    //// Get all legal moves

}


