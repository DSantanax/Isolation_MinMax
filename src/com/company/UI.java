package com.company;

import java.util.Scanner;

/**
 * UI Class for output and input
 * 
 */

public class UI {
    Scanner input = new Scanner(System.in);
    private String opponentInput;
    private String firstMove;
    private String computerMove;

    public void startGame(){

        Board board = new Board();
        UI userInterface = new UI();
        Scanner sc = new Scanner(System.in);

        // ask to see who chooses AI symbol.
        String currentMove = userInterface.whoGoesFirst();
        String winnerWinnerChickenDinner = "";

        // TODO: Swap moves using if else for player and CPU for less code
        // use a variable or bool

        System.out.println(board.toString());

        while (!board.gameOver("C") && !board.gameOver("O")) {

            if (currentMove.equals("C")) {
                String computerMove = userInterface.getComputerMove(board);
                while (!board.validMove(computerMove, "C")) {
                    System.out.println("Invalid input. Put in something legit.");
                    System.out.println("Current X position" + board.getOPosition());
                    computerMove = userInterface.getComputerMove(board);
                }
                board.playerTurnToMove(computerMove, "C");
                
                System.out.println("New xPosition: " + board.getXPosition());
                System.out.println(board.toString());
                currentMove = "O";

            } else if (currentMove.equals("O")) {

                String opponentMove = userInterface.getOpponentMove(board);
                while (!board.validMove(opponentMove, "O")) {
                    System.out.println("Invalid input. Put in something legit.");
                    System.out.println("Current O position" + board.getOPosition());
                    opponentMove = userInterface.getOpponentMove(board);
                }
                board.playerTurnToMove(opponentMove, "O");
                System.out.println("New oPosition: " + board.getOPosition());
                System.out.println(board.toString());
                currentMove = "C";
            }
        }
        if (!board.gameOver("C"))
            winnerWinnerChickenDinner =  "Player won!";
        else {
            winnerWinnerChickenDinner = "Computer won!";
        }
        sc.close();

        System.out.println(board.toString());
        System.out.println("Game Over!" + winnerWinnerChickenDinner);
        
    }
    
    public String whoGoesFirst() { 

        System.out.println("\nWho goes first? C for Computer, O for opponent. (C or O).");
        firstMove = input.nextLine();
        System.out.println("Going first: " + firstMove);
        return firstMove;
    }

    //Input for opponent move
    public String getOpponentMove(Board board) {
        System.out.println("Enter opponent's move: ");
        opponentInput = input.nextLine();
        System.out.println("Opponent's move is: " + computerMove);
        return opponentInput;
    }
    //Get PC move input
    public String getComputerMove(Board board) { //function will get modified to implement min max

        System.out.println("Enter computer move: ");
        computerMove = input.nextLine();
        System.out.println("Computer's move is: " + computerMove);

        return computerMove;
    }

}