package com.company;
import java.util.*;
import java.util.ArrayList;

public class Board {
    private String[][] board;
    private int boardLength = 8;
    private String[][] gameState;

    ArrayList<Board> children;

    public Board()
    {
        gameState = new String[boardLength][boardLength];
        gameState[0][0] = "X";
        gameState[7][7] = "Y";

    }


    public Board (String[][] gameState) //put positionX and positionO
    {
        this.gameState = gameState;
    }


//    public Board(){ //prints the Board
//            // public static void printMatrix(char[][] board) {
//            // 8x8 board
//            char[][] boardState = new char[boardLength][boardLength];
//            for (int i = 0; i < boardLength; i++) {
//                for (int j = 0; j < boardLength; j++) {
//                    boardState[i][j] = '-';
//                }
//                System.out.println();
//            }
////            System.out.println("------------------------------");
//
//            this.board = boardState;
//        }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        //res.append("------------------------------\n");
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (gameState[i][j] == null){
                    res.append('-');
                }
                res.append(board[i][j] +  ' ');
                // System.out.print(board[i][j] + "|");
            }
            res.append("\n");
        }
     //  res.append("------------------------------\n");

        return res.toString();
    }





    //// Print the current board state
    //// Get all legal moves

}


