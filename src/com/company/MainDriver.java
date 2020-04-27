package com.company;
import java.util.*;

/**
 * Authors: Brandon Dayauon, Daniel Santana
 * Course: AI 4200
 *
 *      Description:
 *
 */

public class MainDriver extends Board{
    /*
    Requirements:
- Your computer is required to prompt for “Who goes first, C for computer, O for opponent:
- Your computer will be required to make its move within 20 seconds.
- The board will be 8 x 8 with the coordinate A1 indicating the top left hand side of the board.
- The user interface must display the game as demonstrated above
- When a new game starts, It must prompt for the time limit per move and prompt for who goes first
(computer or opponent)
- Moves must be entered as shown above
- The game must be implemented using the minimax algorithm with alpha-beta pruning
- It will help if you also implement this with an iterative deepening.
     */

    public static void main(String[] args) {

        Board board = new Board();
        UI userInterface = new UI();
        possibleBoard pb = new possibleBoard(board);

        //ask to see who chooses AI symbol.
        String firstMove = userInterface.chooseAISymbol();

        if (firstMove.equals("C")) {

            System.out.println(board.toString());

            while (!board.gameOver("C") || !board.gameOver("O")){
                String computerMove = userInterface.getComputerMove();
                board.computerTurnToMove(computerMove);

                System.out.println(board.toString());

                String opponentMove = userInterface.getOpponentMove();
                board.opponentTurnToMove(opponentMove);

                System.out.println(board.toString());

            }
        }
        else if(firstMove.equals("O")){
            System.out.println(board.toString());

            while (!board.gameOver("C") || !board.gameOver("O")){
                String opponentMove = userInterface.getOpponentMove();
                board.opponentTurnToMove(opponentMove);

                String computerMove = userInterface.getComputerMove();
                board.computerTurnToMove(computerMove);

                System.out.println(board.toString());
            }


        }

        System.out.println(board.toString());
//        System.out.println("copy matrix");
//        board.copyMatrix()
//        System.out.println(board.copyMatrix());


    }


}