import java.util.ArrayList;

/**
 * The min max implementation returns a Board after the timer is up or the a
 * given depth has been reached. This uses a heuristic (evaluation function) of
 * the number of moves available for the PC. 
 * 
 */

public class MinMaxAB {
    public static int TIME_SET;
    public static long START_TIME;

    public static Board MinMaxDecision(Board state, int depth) {

        // Set timer
        START_TIME = System.currentTimeMillis() / 1000;
        // Return Max value
        int maxValue = maxValue(state, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);

        // search for the max value of the given State's children
        ArrayList<Board> children = state.getChildren();
        Board bestBoard = children.get(0);
        for (Board child : children) {
            if (child.getFitnessFunction() == maxValue) {
                return child;
            }
        }
        return bestBoard;
    }

    /**
     * The max value returns the best possible Board based on the best heuristic
     * (utility)
     * 
     * @param state - The Board
     * @param alpha - Alpha tries to maximize
     * @param beta  - Beta tries to minimize
     * @param depth - Depth of current search
     * @return - return the best board for min
     */
    private static int maxValue(Board state, int alpha, int beta, int depth) {
        long elapsedTime = ((System.currentTimeMillis() / 1000) - START_TIME);

        // Generate the successors (children) of the current Board
        // In other words the action available in 1 move
        ArrayList<Board> successor = PossibleBoards.generateSuccessors(state, "X");

        // If statements check whether the we return based on
        // time, end state, or depth, respectively.
        if (elapsedTime >= TIME_SET) {
            return utility(state, "X");
        }

        if (terminalTest(state, "X")) {
            return utility(state, "X");
        }

        if (depth == 0) {
            return utility(state, "X");
        }
        // Initially set to -INF
        int value = Integer.MIN_VALUE;

        // Loop through the successors (children) of the given state.
        for (Board child : successor) {
            value = Math.max(value, minValue(child, alpha, beta, depth - 1));
            child.setFitnessFunction(value);

            // set the best value for Max
            if (value >= beta) {
                return value;
            }
            // if v' > alpha v = alpha
            alpha = Math.max(alpha, value);

        }
        return value;
    }

    /**
     * The min value returns the the best possible Board based on the lowest
     * heuristic value (utility) test.
     * 
     * @param state - The Board
     * @param alpha - Minimize alpha
     * @param beta  - Maximize beta
     * @param depth - The current depth of the state
     * @return - returns the best board for min
     * 
     */

    private static int minValue(Board state, int alpha, int beta, int depth) {
        long elapsedTime = ((System.currentTimeMillis() / 1000) - START_TIME);

        // generate successors (children) for the Player
        ArrayList<Board> successor = PossibleBoards.generateSuccessors(state, "O");

        // If statements check whether the we return based on
        // time, end state, or depth, respectively.
        if (elapsedTime >= TIME_SET) {
            return utility(state, "O");
        }

        if (terminalTest(state, "O")) {
            return utility(state, "O");
        }

        if (depth == 0) {
            return utility(state, "O");
        }
        // Max value is initially set to +INF
        int value = Integer.MAX_VALUE;

        // Find the successors(children) of the current state
        // In other words the action available in 1 move
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

    /**
     * The utility function is our (evaluation/heuristic) this returns the Board's
     * value which we use to calculate for min & max based on the number of moves
     * available.
     * 
     * @param state  - The current state to check heuristic
     * @param player - The player we wish to generate the other's children used for
     *               heuristic
     * @return - The heuristic value of the given state
     */
    private static int utility(Board state, String player) {
        if (player.equals("X"))
            PossibleBoards.generateSuccessors(state, "O");
        else {
            PossibleBoards.generateSuccessors(state, "X");
        }
        return state.getNumberOfMovesX() * 2 - state.getNumberOfMovesO();
    }

    /**
     * The terminal test checks whether the state (board) has any movement left
     * 
     * @param state  - Current state to test
     * @param player - The player we wish to check their moves
     * @return - if there is no more moves available
     * 
     */

    private static boolean terminalTest(Board state, String player) {
        if (player.equals("X")) {
            return state.getNumberOfMovesX() == 0;
        } else
            return state.getNumberOfMovesO() == 0;
    }
}