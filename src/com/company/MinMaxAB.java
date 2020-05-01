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

    public static void MinMaxDecision(Board state, int depth) {      //choose best board

        maxValue(state, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
        // return state.max = max
    }

    // Main Comp player is MaxValue
    private static int maxValue(Board state, int alpha, int beta, int depth) { 
        ArrayList<Board> successor = successors(state, "X");
        // return board here the best board with value X
        if (terminalTest(state)){
            //set final Board here
            return utility(state);
        }

        if(depth == 0){ // can end time here
            return state.getNumberOfMovesX();
        }

        int value = Integer.MIN_VALUE; // -inf

        // add states to function to loop through them
        // passing each action of the current state
     
        for (Board board : successor) {
            value = Math.max(value, minValue(board, alpha, beta, depth - 1));

            if (value >= beta) {
                System.out.println("alskdjlaksjdlkajlskfjlkasldjalksjdlajslkdjalkjs");
                state.printBoard("");
                        // set board with best value as actual board to MOve to
                return value;
            }
            //add break with depth == 0 
            alpha = Math.max(alpha, value);
        }
    
        return value;
    }

    // Main opponent is Min value
    private static int minValue(Board state, int alpha, int beta, int depth) {
        ArrayList<Board> successor = successors(state, "O");

        
        if (terminalTest(state)){
            //set final Board here
            return utility(state);
        }

        if(depth == 0){
            return state.getNumberOfMovesO();
        }
        int value = Integer.MAX_VALUE;

        // find actions is successors
        state.setNumberOfMovesO(successor.size());
   
        for (Board board : successor) {
            value = Math.min(value, maxValue(board, alpha, beta, depth - 1));
            if (value <= alpha) {
                return value;
            }
            beta = Math.min(beta, value);

        }
        return value;
    }

    //generates successors
    private static ArrayList<Board> successors(Board state, String player) { 
        return PossibleBoards.generateSuccessors(state, player);
    }

    // Evaluation function
    private static int utility(Board state) {
        return state.getNumberOfMovesX()*2 - state.getNumberOfMovesO(); //max Moves - minMoves.   AN INT
    }

    // Check if their is moves available
    private static boolean terminalTest(Board state) {
        return state.getNumberOfMovesX() == 0 || state.getNumberOfMovesO() == 0;
    }

    public static void main(String[] args) {
        Board mainBoard = new Board();
        mainBoard.printBoard("");
        Board testBoard = Board.getNewBoard(mainBoard, 0, 0, "X");

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

        //TODO: depth & timer

        // MinMaxAB.MinMaxDecision(testBoard, 3);
     
    }
}