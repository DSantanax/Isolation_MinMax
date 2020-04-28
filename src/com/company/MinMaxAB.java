package com.company;


import java.util.ArrayList;

/**
 * Implement 'depth' after completing MinMax with AB pruning for
 * efficiency.
 * <p>
 * TODO: Utility, Terminal state, & Successor functions
 */

public class MinMaxAB {

    public static Board miniMaxDecision(Board state) {
        int max = maxValue(state, Integer.MIN_VALUE, Integer.MAX_VALUE);
        //return state.max = max
        return state;
    }

    //Main Comp player is MaxValue
    private static int maxValue(Board state, int alpha, int beta) {
        if (terminalTest(state))
            return utility(state);

        int value = Integer.MIN_VALUE;

        //add states to function to loop through them
        //passing each action of the current state
        ArrayList<Board> successor = successors(state);
        for (Board board : successor) {
            value = Math.max(value, minValue(board, alpha, beta));
            if (value >= beta) {
                return value;
            }
            alpha = Math.max(alpha, value);
        }
        return value;
    }


    //Main opponent is Min value
    private static int minValue(Board state, int alpha, int beta) {
        int value = Integer.MAX_VALUE;

        //find actions is successors
        ArrayList<Board> successor = successors(state);
        for (Board board : successor) {
            value = Math.min(value, maxValue(board, alpha, beta));
            if (value <= alpha) {
                return value;
            }
            beta = Math.min(beta, value);

        }
        return value;
    }

    private static ArrayList<Board> successors(Board state) {
        possibleBoard pb = new possibleBoard(state);
        return pb.successorsBoard();
    }

    //Evaluation function
    private static int utility(Board state) {


        return 0;
    }

    //Check if their is moves available
    private static boolean terminalTest(Board state) {
        String compPosition = state.getOPosition();
        int rowPos = state.getORowNum();
        int colPos = state.getOColNum();


        return true;
    }

}
