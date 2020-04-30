package com.company;

import java.util.ArrayList;

/**
 * Implement 'depth' after completing MinMax with AB pruning for efficiency.
 * <p>
 * TODO: Utility, Terminal state, & Successor functions, and cut off timer
 */

public class MinMaxAB {

    private static Board board;

    public static Board MinMaxDecision(Board state) {      //choose best board
        maxValue(state, Integer.MIN_VALUE, Integer.MAX_VALUE);
        // return state.max = max
        
        // board.setValue
        // board.getValue
        return MinMaxAB.board;
    }

    // Main Comp player is MaxValue
    private static int maxValue(Board state, int alpha, int beta) { 
        ArrayList<Board> successor = successors(state, "X");

        // return board here the best board with value X
        if (terminalTest(successor)){
            //set final Board here
            return utility(state);
        }

        int value = Integer.MIN_VALUE; // -inf

        // add states to function to loop through them
        // passing each action of the current state

        for (Board board : successor) {
            value = Math.max(value, minValue(board, alpha, beta));
            if (value >= beta) {
                MinMaxAB.board = state;           // set board with best value as actual board to MOve to
                return value;
            }
            alpha = Math.max(alpha, value);
        }
        return value;
    }

    // Main opponent is Min value
    private static int minValue(Board state, int alpha, int beta) {
        int value = Integer.MAX_VALUE;

        // find actions is successors
        ArrayList<Board> successor = successors(state, "O");
        state.setNumberOfMovesOpp(successor.size());
        for (Board board : successor) {
            value = Math.min(value, maxValue(board, alpha, beta));
            if (value <= alpha) {
                return value;
            }
            beta = Math.min(beta, value);

        }
        return value;
    }

    private static ArrayList<Board> successors(Board state, String player) {
        return PossibleBoards.successorsBoard(state, player);
    }

    // Evaluation function
    private static int utility(Board state) {
        return state.getNumberOfMoves()*2 - state.getNumberOfMovesOpp(); //max Moves - minMoves.   AN INT
    }

    // Check if their is moves available
    private static boolean terminalTest(ArrayList<Board> successors) {
        return successors.size() == 0;
        // return successors.size() == 0 ? true : false;
    }

    public static void main(String[] args) {
        Board mainBoard = new Board();
        mainBoard.printBoard("");
        Board testBoard = Board.getNewBoard(mainBoard, 0, 0, "X");

        ArrayList<Board> successorsX = PossibleBoards.successorsBoard(testBoard, "X");
        int counter = 0;
        for (Board board : successorsX) {
            counter += 1;
            System.out.println(counter);
            board.printBoard("");
        }
        ArrayList<Board> successorsO = PossibleBoards.successorsBoard(testBoard, "O");
        int counterO = 0;
        for (Board board : successorsO) {
            counterO += 1;
            System.out.println(counter);
            board.printBoard("");
        }
        System.out.println("Total moves X : " + counter);
        System.out.println("Total moves O : " + counterO);
        System.out.println("Total moves: " + (counter + counterO));


        Board minMaxDecision = MinMaxAB.MinMaxDecision(testBoard);
        minMaxDecision.printBoard("");
    }
}
