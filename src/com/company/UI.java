package com.company;

import java.util.Scanner;

public class UI {
    private String opponentInput;
    private String firstMove;
    private String computerMove;

    public static boolean moveInputCheck(String opponentInput){
        Board board = new Board();

        int rowNum = board.getRowVal(opponentInput);
        int colNum = board.getColVal(opponentInput);

        boolean isValid = true;

        if (rowNum < 0 || rowNum > 7)
            isValid = false;
        if (colNum < 0 || colNum > 7)
            isValid = false;
        if (board.getPositionValid(rowNum, colNum))
            isValid = false;

        System.out.println("Index selection is " + rowNum + " " + colNum);
        System.out.printf("Movement: [%s][%s] \n", opponentInput.substring(0, 1), opponentInput.substring(1));

        return isValid;
    }

    public String getOpponentMove(){
        System.out.println("Enter opponent's move: ");
        Scanner input = new Scanner(System.in);

        opponentInput = input.nextLine();

        while (!moveInputCheck(opponentInput)){
            System.out.println("Invalid input. Put in something legit.");
            opponentInput = input.nextLine();
        }

        return opponentInput;
    }

    public String getComputerMove(){ //function will get modified to implement min max
        Scanner input = new Scanner(System.in);

        System.out.println("put in a computerMove");
        computerMove = input.nextLine();

        while(!moveInputCheck(computerMove)){
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
        System.out.println(moveInputCheck("A7"));

    }


}
