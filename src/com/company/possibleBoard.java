package com.company;

import java.util.ArrayList;
import java.util.*;

public class possibleBoard extends Board{
    private String[][] board;
    int boardLength = 8;


    public String[][] copyMatrix(Board board)
    {
        String[][] gameState = board.getArr(); //gameState points to Board

        //edgeCase if gameState is ever null
        if (gameState == null){
            return null;
        }

        //create another 2d array that will copy elements of Board
        String[][] res  = new String[boardLength][boardLength];
        for(int i = 0; i < boardLength; i+= 1){
            res[i] = gameState[i].clone();
        }

        return res;
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

    public static void main(String[] args){
        Board board = new Board();
        possibleBoard pb = new possibleBoard();

        System.out.println(board.toString());
        System.out.println("hi");

        //String[][] res = pb.copyMatrix(board);

        // System.out.println(Arrays.deepToString(res)); //just prints it in array form

        System.out.println(pb.copyMatrix(board).toString());
    }



    //do alpha beta pruning here? *might make another class*

}
