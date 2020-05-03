package com.company;

import java.util.Scanner;

/**
 * UI Class for output and input
 * 
 */

public class UI {
    Scanner input = new Scanner(System.in);

    public void startGame(int depth) {
        int turns = 0;
        
        UI userInterface = new UI();
        Scanner sc = new Scanner(System.in);
        final String OPPONENT = "O";
        final String COMP = "C";

        // ask for time limit per move
        int timeLimit = userInterface.promptTimePerMove();
        
        // ask to see who chooses AI symbol.
        String currentMove = userInterface.whoGoesFirst();
     
        String selectedFirst = currentMove;
        Board board = new Board(currentMove);
        board.setFirstPlayer(selectedFirst);
        String winnerWinnerChickenDinner;
        
        // use a variable or bool
        board.printBoard("");

        while (board.gameOver(COMP) && board.gameOver(OPPONENT)) {

            //TODO: fix to print out log
            if(!currentMove.equals(selectedFirst)){ 
                board.incrementRound();          
            }

            //COMP turn
            if (currentMove.equals(COMP)) {
                //PVP
                // String computerMove = userInterface.getMove(COMP);
                // while (!board.validMove(computerMove, COMP)) {
                //     System.out.println("Invalid input. Put in something legit.");
                //     System.out.println("Current X position" + board.getOPosition());
                //     computerMove = userInterface.getMove(COMP);
                // }
                // board.playerTurnToMove(computerMove, COMP);

                //Min max
                //Time here
                board = MinMaxAB.MinMaxDecision(board, depth);
                //find currentTime: endTime - StartTime
                
                //then throw
                board.logFileX.add(board.getXPosition());
                System.out.println("New Computer move: " + board.getXPosition());

                //always print
                board.printBoard("X");

                //other player's turn
                currentMove = OPPONENT;
              
            //Opponent turn
            } else {

                String opponentMove = userInterface.getMove(OPPONENT); //input opponent move
                while (!board.validMove(opponentMove, OPPONENT)) {
                    System.out.println("Invalid input. Put in something legit.");
                    System.out.println("Current O position: " + board.getOPosition());
                    opponentMove = userInterface.getMove(OPPONENT);
                }
                System.out.println("Player move is: " + opponentMove);
                board.playerTurnToMove(opponentMove, OPPONENT);

                board.logFileO.add(board.getOPosition());
                System.out.println("New Opponent move: " + board.getOPosition());
                board.printBoard("O");
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
        String firstMove = input.nextLine().toUpperCase();
        System.out.println("Going first: " + firstMove);
        System.out.println("----------------------Game Start-----------------------------\n");
        return firstMove;
    }

    public String getMove(String player) { // function will get modified to implement min max

        String move = "";

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
        return move;
    }

    public int promptTimePerMove(){
        System.out.println("Enter the time limit per move: ");
        int time = input.nextInt();
        System.out.println("The time limit per move is :" + time);
        input.nextLine();
        return time;
    }


}