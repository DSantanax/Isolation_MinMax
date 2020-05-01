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
        Board board = new Board(currentMove);
        String winnerWinnerChickenDinner = "";

        // TODO: Swap moves using if else for player and CPU for less code
        // use a variable or bool

        board.printBoard("");

        while (board.gameOver(COMP) && board.gameOver(OPPONENT)) {

            if (currentMove.equals(COMP)) {
                String computerMove = userInterface.getComputerMove();
                while (!board.validMove(computerMove, COMP)) {
                    System.out.println("Invalid input. Put in something legit.");
                    System.out.println("Current X position" + board.getOPosition());
                    computerMove = userInterface.getComputerMove();
                }
                board.playerTurnToMove(computerMove, COMP);

                System.out.println("New xPosition: " + board.getXPosition());
                board.printBoard(COMP);
                //other player's turn
                currentMove = OPPONENT;

            } else if (currentMove.equals(OPPONENT)) {

                String opponentMove = userInterface.getOpponentMove();
                while (!board.validMove(opponentMove, OPPONENT)) {
                    System.out.println("Invalid input. Put in something legit.");
                    System.out.println("Current O position" + board.getOPosition());
                    opponentMove = userInterface.getOpponentMove();
                }
                board.playerTurnToMove(opponentMove, OPPONENT);
                System.out.println("New oPosition: " + board.getOPosition());
                board.printBoard(OPPONENT);
                //other player's turn
                currentMove = COMP;
            }
        }
        if (board.gameOver(COMP))
            winnerWinnerChickenDinner = "Computer won!";
        else {
            winnerWinnerChickenDinner = "Player won!";
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

    // Input for opponent move
    public String getOpponentMove() {

        String opponentInput = "";

        while(opponentInput.length() != 2){
            System.out.println("Enter a valid opponent move: ");
            opponentInput = input.nextLine();
        }

        System.out.println("Opponent's move is: " + opponentInput);
        return opponentInput;
    }

    // Get PC move input
    public String getComputerMove() { // function will get modified to implement min max

        String computerMove = "";

        while(computerMove.length() != 2){
        System.out.println("Enter a valid computer move: ");
        computerMove = input.nextLine();
        }

        System.out.println("Computer's move is: " + computerMove);
        
        return computerMove;
    }
}