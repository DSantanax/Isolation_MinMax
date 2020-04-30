package com.company;

import java.util.ArrayList;

/**
 * Implement 'depth' after completing MinMax with AB pruning for efficiency.
 * <p>
 * TODO: Utility, Terminal state, & Successor functions, and cut off timer
 */

public class MinMaxAB {

    private Board board;

    public void MinMaxDecision(Board state, int depth) {      //choose best board

        maxValue(state, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
        // return state.max = max
        
    }

    // Main Comp player is MaxValue
    private  int maxValue(Board state, int alpha, int beta, int depth) { 
        ArrayList<Board> successor = successors(state, "X");
        // return board here the best board with value X
        if (terminalTest(state)){
            //set final Board here
            return utility(state);
        }

        if(depth == 0){ // can end time here
            return state.getNumberOfMoves();
        }

        int value = Integer.MIN_VALUE; // -inf

        // add states to function to loop through them
        // passing each action of the current state
     
        for (Board board : successor) {
            value = Math.max(value, minValue(board, alpha, beta, depth - 1));

            if (value >= beta) {
                System.out.println("alskdjlaksjdlkajlskfjlkasldjalksjdlajslkdjalkjs");
                state.printBoard("");
                setBoard(state);           // set board with best value as actual board to MOve to
                return value;
            }
            //add break with depth == 0 
            alpha = Math.max(alpha, value);
        }
    
        return value;
    }

    // Main opponent is Min value
    private  int minValue(Board state, int alpha, int beta, int depth) {
        ArrayList<Board> successor = successors(state, "O");

        
        if (terminalTest(state)){
            //set final Board here
            return utility(state);
        }

        if(depth == 0){
            return state.getNumberOfMovesOpp();
        }
        int value = Integer.MAX_VALUE;

        // find actions is successors
        state.setNumberOfMovesOpp(successor.size());
   
        for (Board board : successor) {
            value = Math.min(value, maxValue(board, alpha, beta, depth - 1));
            if (value <= alpha) {
                return value;
            }
            beta = Math.min(beta, value);

        }
        return value;
    }

    private  ArrayList<Board> successors(Board state, String player) {
        return PossibleBoards.successorsBoard(state, player);
    }

    // Evaluation function
    private  int utility(Board state) {
        return state.getNumberOfMoves()*2 - state.getNumberOfMovesOpp(); //max Moves - minMoves.   AN INT
    }

    // Check if their is moves available
    private  boolean terminalTest(Board state) {
        return state.getNumberOfMoves() == 0 || state.getNumberOfMovesOpp() == 0;
        // return successors.size() == 0 ? true : false;
    }

    public Board getBoard(){
        return this.board;
    }
    public void setBoard(Board board){
        this.board = board;
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
            System.out.println(counterO);
            board.printBoard("");
        }
        System.out.println("Total moves X : " + counter);
        System.out.println("Total moves O : " + counterO);
        System.out.println("Total moves: " + (counter + counterO));


        //TODO: depth & timer
        MinMaxAB min = new MinMaxAB();
        
        min.MinMaxDecision(mainBoard, 5);
        min.getBoard().printBoard("");
    }
}
