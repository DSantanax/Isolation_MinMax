package com.company;

import java.util.Scanner;

/**
 * UI Class for output and input
 * 
 */

public class UI {
    Scanner input = new Scanner(System.in);

    public void startGame() {

        UI userInterface = new UI();
        Scanner sc = new Scanner(System.in);
        final String OPPONENT = "O";
        final String COMP = "C";

        // ask to see who chooses AI symbol.
        String currentMove = userInterface.whoGoesFirst();
     
        String selectedFirst = currentMove;
        Board board = new Board(currentMove);
        String winnerWinnerChickenDinner = "";


        // use a variable or bool

        board.printBoard("");

        while (board.gameOver(COMP) && board.gameOver(OPPONENT)) {

            //COMP turn

            if(!currentMove.equals(selectedFirst))
                board.incrementRound();

            if (currentMove.equals(COMP)) {
                String computerMove = userInterface.getMove(COMP);
                while (!board.validMove(computerMove, COMP)) {
                    System.out.println("Invalid input. Put in something legit.");
                    System.out.println("Current X position" + board.getOPosition());
                    computerMove = userInterface.getMove(COMP);
                }
                board.playerTurnToMove(computerMove, COMP);

                System.out.println("New Computer: " + board.getXPosition());
                board.printBoard(COMP);
                //other player's turn
                currentMove = OPPONENT;

            //Opponent turn
            } else {

                String opponentMove = userInterface.getMove(OPPONENT);
                while (!board.validMove(opponentMove, OPPONENT)) {
                    System.out.println("Invalid input. Put in something legit.");
                    System.out.println("Current O position" + board.getOPosition());
                    opponentMove = userInterface.getMove(OPPONENT);
                }
                board.playerTurnToMove(opponentMove, OPPONENT);
                System.out.println("New Opponent: " + board.getOPosition());
                board.printBoard(OPPONENT);
                //other player's turn
                currentMove = COMP;
            }
        }
        if (board.gameOver(COMP))
            winnerWinnerChickenDinner = "Computer won!";
        else {
            winnerWinnerChickenDinner = "Opponent won!";
        }
        sc.close();

        board.printBoard("");
        System.out.println("---Game Over--- " + winnerWinnerChickenDinner);

    }

    public String whoGoesFirst() {

        System.out.println("\nWho goes first? C for Computer, O for opponent. (C or O).");
        String firstMove = input.nextLine();
        System.out.println("Going first: " + firstMove);
        return firstMove;
    }

    public String getMove(String player) { // function will get modified to implement min max

        String move = "";
        String playerMoving = (player.equals("C")) ? "Computer" : "Player";

        if (player.equals("C")) {
            while (move.length() != 2) {
                System.out.println("Enter a valid computer move: ");
                move = input.nextLine();
            }
        } else {
            while (move.length() != 2) {
                System.out.println("Enter a valid player move: ");
                move = input.nextLine();
            }
        }
        System.out.println(playerMoving + " move is: " + move);
        return move;
    }
}