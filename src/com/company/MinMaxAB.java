package com.company;

import java.util.ArrayList;

/**
 * Implement 'depth' after completing MinMax with AB pruning for efficiency.
 * <p>
 * TODO: Utility, Terminal state, & Successor functions, and cut off timer
 */

//TODO TODAY: fix TERMINAL STATE -> fix maxVal/minVal -> TESTING
//
public class MinMaxAB {

    private static Board bestBoard;

    //TODO: return board

    public static Board MinMaxDecision(Board state, int depth) {      //choose best board

        maxValue(state, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
        // return state.max = max
        return MinMaxAB.bestBoard;
    }
    //TODO: fix maxMoves & possibly the terminal & utility
    // Main Comp player is MaxValue
    private static int maxValue(Board state, int alpha, int beta, int depth) { 

        //generates successor for O player (MAX PLAYER)
        ArrayList<Board> successor = PossibleBoards.generateSuccessors(state, "O");

        // return board here the best board with value X
        if (terminalTest(state, "X")){
            //set final Board here
            return utility(state);
        }
        if(depth == 0){ // can end time here
            //TODO: we are moving the Xs to Os 
            //also the gen successors changed to inverse
            return state.getNumberOfMovesO();
        }

        int value = Integer.MIN_VALUE; // -inf

        // add states to function to loop through them
        // passing each action of the current state
     
        Board currBoard = state;
        for (Board board : successor) {
            value = Math.max(value, minValue(board, alpha, beta, depth - 1)); //value is maxEVAL

            if (value >= beta) {
                currBoard = board;
                //set board with best value as actual board to Move to
                return value;
            }
            //add break with depth == 0 
            alpha = Math.max(alpha, value);
        }
        bestBoard = currBoard;
    
        return value;
    }

    // Main opponent is Min value
    private static int minValue(Board state, int alpha, int beta, int depth) {

        //generates successor for X player (MIN PLAYER)
        ArrayList<Board> successor = PossibleBoards.generateSuccessors(state, "X"); 
        
        if (terminalTest(state, "O")){
            //set final Board here
            return utility(state);
        }

        if(depth == 0){
            return state.getNumberOfMovesX();
        }
        int value = Integer.MAX_VALUE;

        // find actions is successors
        for (Board board : successor) {
            value = Math.min(value, maxValue(board, alpha, beta, depth - 1));
            if (value <= alpha) {
                return value;
            }
            beta = Math.min(beta, value);

        }
        return value;
    }

    // //generates successors
    // private static ArrayList<Board> successors(Board state, String player) { 
    //     return PossibleBoards.generateSuccessors(state, player);
    // }
    //
    // Evaluation function that SHOULD return a BOARD
    private static int utility(Board state) { 
        //TODO: fix adjust


        // utilityBoard = []

        // #populate the array with values to refer to 
        // for i in range(0, len(successors)):
        //     utilityBoard.append([getNumberofPossibleMoves - getNumberofPossibleMovesOpponent])

        // utilityBoard.sort(byBestValue)
        // return successorwithBestValue by index 0(which is a board) successor.getIndex(bestValue);

        return state.getNumberOfMovesX()*2 - state.getNumberOfMovesO(); //max Moves - minMoves.   AN INT
    }

    // Check if their is moves available
    private static boolean terminalTest(Board state, String player) {
        //Check if number of moves for X is 0 then over
        if(player.equals("O")){
                return state.getNumberOfMovesX() == 0;
         } 
         //Check if number of moves for O is 0 then over
         else 
            return state.getNumberOfMovesO() == 0;
    }

    //TESTING for min max & possible boards
    public static void main(String[] args) {
        Board mainBoard = new Board("C");
        mainBoard.printBoard("");
        Board testBoard = new Board(mainBoard, 0, 0, "X");

        ArrayList<Board> successorsX = PossibleBoards.generateSuccessors(testBoard, "X");
        int counter = 0;
        for (Board board : successorsX) {
            counter += 1;
            System.out.println(counter);
            board.printBoard("");
        }
        ArrayList<Board> successorsO = PossibleBoards.generateSuccessors(testBoard, "O");
        int counterO = 0;
        for (Board board : successorsO) {
            counterO += 1;
            System.out.println(counterO);
            board.printBoard("");
        }

        System.out.println("Total moves X : " + counter);
        System.out.println("Total moves O : " + counterO);
        System.out.println("Total moves: " + (counter + counterO));

        System.out.println(testBoard.getNumberOfMovesO());
        System.out.println(testBoard.getNumberOfMovesX());

         //TODO: depth & timer
        Board minMaxDecision = MinMaxAB.MinMaxDecision(testBoard, 2);
        minMaxDecision.printBoard("");
    }
}