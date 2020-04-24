package com.company;

import java.util.Scanner;

public class UI {
    private String opponentInput;

    public static boolean moveInputCheck(String opponentInput){
        String letter = opponentInput.substring(0,1);
        int number = 9;
        boolean isValid = true;

        if (letter.equals("A"))
            number = 0;
        else if (letter.equals("B"))
            number = 1;
        else if (letter.equals("C"))
            number = 2;
        else if (letter.equals("D"))
            number = 3;
        else if (letter.equals("E"))
            number = 4;
        else if (letter.equals("F"))
            number = 5;
        else if (letter.equals("G"))
            number = 6;
        else if (letter.equals("H"))
            number = 7;

        int opponentInputNumber = Integer.parseInt(opponentInput.substring(1)) - 1;

        if (number < 0)
            isValid = false;
        else if (number > 7)
            isValid = false;
        if (opponentInputNumber < 0)
            isValid = false;
        else if (opponentInputNumber > 7)
            isValid = false;

        return isValid;
    }

    public String chooseAISymbol(){ //can do input validation

        Scanner input = new Scanner(System.in);

        System.out.println("\nWho goes first? C for Computer, O for opponent. (C or O).");

        String firstMove = input.nextLine();

        System.out.println("Going first: " + firstMove);

        return firstMove;
    }

    public static void main(String [] args){
        System.out.println(moveInputCheck("A2"));
    }


}
