package com.company;

import java.util.ArrayList;

/**
 * Implement 'depth' after completing MinMax with AB pruning for efficiency.
 * 
 * TODO: Cut off timer
 */

public class MinMaxAB {
    public static int TIME_SET;
    public static long START_TIME;
      //Board boardToReturnWhenTimeExceeded = null;

    public static Board MinMaxDecision(Board state, int depth) { // choose best board
        // long startTime = System.currentTimeMillis(); //starts the time
        System.out.println(TIME_SET);
        START_TIME = System.currentTimeMillis()/1000;
        System.out.println(START_TIME);
        int maxValue = maxValue(state, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
        // return state.max = max   
        ArrayList<Board> children = state.getChildren();
        Board bestBoard = children.get(0);
        for(Board child : children){
            if(child.getFitnessFunction() == maxValue){   
                return child;
            }  
        }
        
       System.out.println(maxValue);
        return bestBoard;
    }

    // Main Comp player is MaxValue
    private static int maxValue(Board state, int alpha, int beta, int depth) {
         long elapsedTime = ((System.currentTimeMillis()/1000)- START_TIME); //starts the time
        System.out.println(elapsedTime);
        // generates successor for O player (MAX PLAYER)
        ArrayList<Board> successor = PossibleBoards.generateSuccessors(state, "X");
        System.out.println(elapsedTime);
        if(elapsedTime >= TIME_SET){
            return utility(state, "X");
        }
        // return board here the best board with value X
        if (terminalTest(state, "X")) {
            // set final Board here
            // PossibleBoards.generateSuccessors(state, "X");
            return utility(state, "X");
        }
        
        if (depth == 0) { // can end time here   IF DEPTH == 0 or elapsedTime > timeLimit:
            // also the gen successors changed to inverse
            return utility(state, "X");
        }

        int value = Integer.MIN_VALUE; // -inf

        // add states to function to loop through them
        // passing each action of the current state

        for (Board child : successor) {
            value = Math.max(value, minValue(child, alpha, beta, depth - 1)); // value is maxEVAL
            child.setFitnessFunction(value);
            if (value >= beta) {
                // set board with best value as actual board to Move to
             
                return value;
            }
            // add break with depth == 0
            
            //if v' > alpha v = alpha
            alpha = Math.max(alpha, value);

        }
        return value;
    }

    // Main opponent is Min value
    private static int minValue(Board state, int alpha, int beta, int depth) {
         //starts the time
        long elapsedTime = ((System.currentTimeMillis()/1000)- START_TIME); //starts the time

        // generates successor for X player (MIN PLAYER)
        ArrayList<Board> successor = PossibleBoards.generateSuccessors(state, "O");
        
        if(elapsedTime >= TIME_SET){
            return utility(state, "O");
        }

        if (terminalTest(state, "O")) {
            // set final Board here
            return utility(state, "O");
        }

        if (depth == 0) { //IF DEPTH == 0 or elapsedTime > timeLimit:
            return utility(state, "O");
        }

        int value = Integer.MAX_VALUE;

        // find actions is successors
        for (Board child : successor) {
            value = Math.min(value, maxValue(child, alpha, beta, depth - 1));
            child.setFitnessFunction(value);
            if (value <= alpha) {
                
                return value;
            }
            beta = Math.min(beta, value);

        }
        return value;
    }
    
    private static int utility(Board state, String player) {
        //get successors for X
        if(player.equals("X"))
            PossibleBoards.generateSuccessors(state, "O");
        else{
            //get successors for O
            PossibleBoards.generateSuccessors(state, "X");
        }
        return state.getNumberOfMovesX() * 2 - state.getNumberOfMovesO(); // max Moves - minMoves. AN INT
    }

    // Check if their is moves available
    private static boolean terminalTest(Board state, String player) {
        // Check if number of moves for X is 0 then over
        if (player.equals("X")) {
            return state.getNumberOfMovesX() == 0;
        }
        // Check if number of moves for O is 0 then over
        else
            return state.getNumberOfMovesO() == 0;
    }
}