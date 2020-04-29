package com.company;

import java.util.ArrayList;

public class possibleBoard extends Board {

    //helper that makes successors
    private static String[][] copyMatrix(Board board) {
        String[][] gameStateCopy = board.getGameState(); //gameState points to Board

        // System.out.println(Arrays.deepToString(gameState));

        //create another 2d array that will copy elements of Board
        String[][] res = new String[8][8];
        for (int i = 0; i < 8; i += 1) {
            res[i] = gameStateCopy[i].clone();
        }

        return res;
    }

    public static ArrayList<Board> successorsBoard(Board board, String player) {
        String[][] boardSuccessor = copyMatrix(board);  //completely dif board that we're gonna change
        //X player moves
        int colNum = 0;
        int rowNum = 0;
        if (player.equals("X")) {
            System.out.println("IN X");
            colNum = board.getColVal(board.getXColNum());
            rowNum = board.getRowVal(board.getXRowNum());
        //O player moves
        } else {
            System.err.println("IN O");
            colNum = board.getColVal(board.getOColNum());
            rowNum = board.getRowVal(board.getORowNum());
        }
        ArrayList<Board> successors = new ArrayList<>();
        //North IS CORRECT!!
        for (int i = rowNum - 1; i >= 0 ; i--) {           
            //new board we changed X only to a new position
            //[1][3]
            System.out.println(i + " " + " " + colNum);
            if(boardSuccessor[i][colNum] == null)
                successors.add(board.getNewBoard(board, i, colNum, player));
        }
    
        // TODO: 8 - rowNum for possible Maxmoves
        //South 
        for (int i = rowNum + 1; i < 8; i++) {
            if(boardSuccessor[i][colNum] == null)
                successors.add(board.getNewBoard(board, i, colNum, player));
        } 



        //EAST
        for (int i = colNum; i <= 8 - rowNum; i++) {
            if(boardSuccessor[rowNum][i + 1] == null)
                successors.add(board.getNewBoard(board, rowNum, i + 1, player));
        }
        //WEST
        for (int i = colNum; i > 0; i--) {
            if(boardSuccessor[rowNum][i-1] == null)
                successors.add(board.getNewBoard(board, rowNum, i - 1, player));
        }

        //TODO: FIX 4 WAYS DIAGONALS: NW NE SW SE
        //
        //NE
        //int maxNumberOfMovesTilOOB = Math.min(8 - rowNum, 8 - colNum);
        int maxMoves =Math.min(rowNum, colNum);
        System.out.println("--------------------"+rowNum + " " + colNum);
        for (int i = 0; i < maxMoves; i--) {
            System.out.println(i + " " + (i+1));
            if(boardSuccessor[i][colNum+i] == null)
                successors.add(board.getNewBoard(board, i, colNum+1, player));
        }

        //NW
        for (int i = rowNum - 1; i > 0; i--) {
            if(boardSuccessor[i][i-1] == null)
                successors.add(board.getNewBoard(board, i, i-1, player));
        }

        //SE

        int maxNumberOfMovesTilOOB = Math.min(8 - rowNum, 8 - colNum) - 1;
        for (int i = rowNum + 1; i < maxNumberOfMovesTilOOB; i++) {
            if(boardSuccessor[rowNum][i] == null)
                successors.add(board.getNewBoard(board, i, i+1, player));
        }
        //SW
        
        for (int i = rowNum + 1; i < maxNumberOfMovesTilOOB; i++) {
            if(boardSuccessor[rowNum][i] == null)
                successors.add(board.getNewBoard(board, i, i-1, player));
        }
        
        return successors;
    }
}
