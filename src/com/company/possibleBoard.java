package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;

public class possibleBoard extends Board {
    private String[][] board;
    private int boardLength = 8;

    possibleBoard(Board state) {
        board = copyMatrix(state);
    }

    //helper that makes successors
    private String[][] copyMatrix(Board board) {
        String[][] gameStateCopy = board.getGameState(); //gameState points to Board

        // System.out.println(Arrays.deepToString(gameState));
        if (gameStateCopy == null) {
            return null;
        }
        //create another 2d array that will copy elements of Board
        String[][] res = new String[boardLength][boardLength];
        for (int i = 0; i < boardLength; i += 1) {
            res[i] = gameStateCopy[i].clone();
        }

        return res;
    }

    public ArrayList<Board> successorsBoard() {
        ArrayList<Board> successors = new ArrayList<>();


        for (int i = 0; i < 10; i++) {


        }
        for (int i = 0; i < 10; i++) {

        }
        for (int i = 0; i < 10; i++) {

        }
        for (int i = 0; i < 10; i++) {

        }
        for (int i = 0; i < 10; i++) {

        }
        for (int i = 0; i < 10; i++) {

        }
        for (int i = 0; i < 10; i++) {

        }


        return successors;
    }

//    //takes in a possible board and produces other possible boards O
//    public ArrayList<possibleBoard> generateSuccessors(Board board)
//    {
//
////        Board childBoard[boardLength][boardLength] = board;
////
////        for(int i = 0; i < boardLength; i++)
////        {
////            for(int j = 0; j < boardLength; j++)
////            {
////
////            }
////        }
//
//
//
//    }
}
