package com.company;

public class possibleBoard {
    private char[][] board;
    int boardLength = 8;

    public possibleBoard(int boardLength)
    {
        this.boardLength = boardLength;
        board = new char[boardLength][boardLength];

        for(int i = 0; i < boardLength; i++)
        {
            for(int j = 0; j < boardLength; j++)
            {
                this.board[i][j] = '-';
            }
        }
    }

    //do alpha beta pruning here? *might make another class* 

}
