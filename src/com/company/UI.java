package com.company;

import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class UI {
    private String opponentInput;
    private String firstMove;
    private String computerMove;

    public static boolean moveInputCheck(String moveInput, String currentPositionPlayer){
        Board board = new Board();
        boolean isValid = true;

        if(!board.validMove(moveInput,currentPositionPlayer)) {
            System.out.println("aksdalsjdad");
            isValid = false;
        }

        return isValid;
    }

    public String getOpponentMove(){
        System.out.println("Enter opponent's move: ");
        Scanner input = new Scanner(System.in);
        Board board = new Board();

        opponentInput = input.nextLine();

        while (!moveInputCheck(opponentInput,"O") && !board.validMove(opponentInput,"O")){
            System.out.println("Invalid input. Put in something legit.");
            System.out.println("Current O position" + board.getOPosition());
            opponentInput = input.nextLine();
        }



        return opponentInput;
    }

    public String getComputerMove(){ //function will get modified to implement min max
        Scanner input = new Scanner(System.in);


        System.out.println("Computer's move is: ");
        computerMove = input.nextLine();

        while (!moveInputCheck(computerMove, "C")) {
            System.out.println("Invalid input. Put in something legit.");

            computerMove = input.nextLine();
        }

        System.out.println("Computer's move is: " + computerMove);

        return computerMove;
    }


    public String chooseAISymbol(){ //can do input validation

        Scanner input = new Scanner(System.in);

        System.out.println("\nWho goes first? C for Computer, O for opponent. (C or O).");

        firstMove = input.nextLine();

        System.out.println("Going first: " + firstMove);

        return firstMove;
    }



    public static void main(String [] args){
        //System.out.println(moveInputCheck("A7"));

    }

}