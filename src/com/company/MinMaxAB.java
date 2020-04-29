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
        ArrayList<Board> successor = successors(state, "X");
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
        ArrayList<Board> successor = successors(state, "X");
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
        return possibleBoard.successorsBoard(state, player);
    }

    //Evaluation function
    private static int utility(Board state) {


        return 0;
    }

    //Check if their is moves available
    private static boolean terminalTest(Board state) {
        String compPosition = state.getOPosition();


        return true;
    }

    public static void main(String[] args) {
        Board mainBoard = new Board();
        System.out.println(mainBoard.toString());
        Board testBoard = mainBoard.getNewBoard(mainBoard, 2, 3, "X");
    
        System.out.println(testBoard.toString());
        System.out.println(mainBoard.toString());
    
        ArrayList<Board> successorsBoard = possibleBoard.successorsBoard(testBoard, "X");
        int counter = 0;
        for(Board board: successorsBoard){
            counter += 1;
            System.out.println(counter);
            System.out.println(board.toString());
        }
        System.out.println(counter);
    }
}
