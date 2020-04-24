package com.company;

import java.util.Scanner;

public class UI {
    private String opponentInput;
    private String firstMove;

    public static boolean moveInputCheck(String opponentInput){
        Board board = new Board();

        int number = board.getRowVal(opponentInput);
        int opponentInputNumber = board.getColVal(opponentInput);

        boolean isValid = true;

        if (number < 0)
            isValid = false;
        else if (number > 7)
            isValid = false;
        if (opponentInputNumber < 0)
            isValid = false;
        else if (opponentInputNumber > 7)
            isValid = false;

        System.out.println("Number is " + number);

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

    public String chooseAISymbol(){ //can do input validation

        Scanner input = new Scanner(System.in);

        System.out.println("\nWho goes first? C for Computer, O for opponent. (C or O).");

        this.firstMove = input.nextLine();

        System.out.println("Going first: " + this.firstMove);

        return this.firstMove;
    }



    public static void main(String [] args){
        System.out.println(moveInputCheck("100"));

    }


}
