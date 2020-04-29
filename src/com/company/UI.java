package com.company;

import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class UI {
    private String opponentInput;
    private String firstMove;
    private String computerMove;
    private Scanner input = new Scanner(System.in);


    public static boolean moveInputCheck(String moveInput, String currentPositionPlayer, Board board) {
        boolean isValid = true;

        if (!board.validMove(moveInput, currentPositionPlayer)) {
            System.out.println("aksdalsjdad");
            isValid = false;
        }

        return isValid;
    }

    public String getOpponentMove(Board board) {
        System.out.println("Enter opponent's move: ");

        opponentInput = input.nextLine();

        while (!moveInputCheck(opponentInput, "O", board) && !board.validMove(opponentInput, "O")) {
            System.out.println("Invalid input. Put in something legit.");
            System.out.println("Current O position" + board.getOPosition());
            opponentInput = input.nextLine();
        }

        return opponentInput;
    }

    public String getComputerMove(Board board) { //function will get modified to implement min max

        System.out.println("Computer's move is: ");
        computerMove = input.nextLine();

        while (!moveInputCheck(computerMove, "C", board)) {
            System.out.println("Invalid input. Put in something legit.");

            computerMove = input.nextLine();
        }

        System.out.println("Computer's move is: " + computerMove);

        return computerMove;
    }


    public String chooseAISymbol() { //can do input validation

        System.out.println("\nWho goes first? C for Computer, O for opponent. (C or O).");

        firstMove = input.nextLine();

        System.out.println("Going first: " + firstMove);

        return firstMove;
    }


    public static void main(String[] args) {
        //System.out.println(moveInputCheck("A7"));

    }

}