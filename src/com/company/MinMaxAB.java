package com.company;

public class MinMaxAB {

/**
 * Implement depth after completing MinMax with AB pruning for
 * efficiency.
 */

public class MinMaxAB {

    public static Object miniMaxDecision(Object state) {
        int max = maxValue(state, Integer.MIN_VALUE, Integer.MAX_VALUE);
        //return state.max = max
        return state;
    }

    private static int maxValue(Object state, int alpha, int beta) {
        if (terminalTest(state))
            return utility(state);

        int value = Integer.MIN_VALUE;

        //add states to function to loop through them
        //passing each action of the current state
        for (int i = 0; i < successors(state); i++) {
            value = Math.max(value, minValue(state, alpha, beta));
            if (value >= beta) {
                return value;
            }
            alpha = Math.max(alpha, value);
        }
        return value;
    }

    private static int successors(Object state) {
        return 0;
    }

    private static int minValue(Object state, int alpha, int beta) {
        int value = Integer.MAX_VALUE;

        //find actions is successors
        for (int i = 0; i < successors(state); i++) {
            value = Math.min(value, maxValue(state, alpha, beta));
            if (value <= alpha) {
                return value;
            }
            beta = Math.min(beta, value);

        }
        return value;
    }

    private static int utility(Object state) {
        return 0;
    }

    private static boolean terminalTest(Object state) {
        return true;
    }
  
}
