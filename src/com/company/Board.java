package com.company;

public class Board {
    private char[][] board;
    private int boardLength = 8;

    public static void main(String[] args){

    }


    public Board (int boardLength)
    {
        this.boardLength = boardLength;
        this.board = new char [boardLength][boardLength];
    }


    public Board(Board oldBoard){ //prints the Board
              // public static void printMatrix(char[][] board) {
            // 8x8 board
            this.boardLength = oldBoard.boardLength;
            this.board = new char [boardLength][boardLength];
            for (int i = 0; i < boardLength; i++) {
                for (int j = 0; j < boardLength; j++) {
                    this.board[i][j] = oldBoard.board[i][j];
                   // System.out.print(board[i][j] + "|");
                }
                System.out.println();
            }
            System.out.println("------------------------------");
        }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        System.out.println("------------------------------");
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                res.append(board[i][j] + "|");
                // System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("------------------------------");

        return res.toString();
    }
}


