package com.company;

import java.util.ArrayList;
/*
copyMatrix- copies current Board (copy to reference to)
successorsBoard - has global counter to count each possible Move and return []
*/
public class PossibleBoards extends Board {

    public static ArrayList<Board> generateSuccessors(Board board, String player) { // DONE

        // TODO: add global local counter
        // set that counter in the end to the board.maxMoves()
        // In the min max we call the terminal for the maxMoves()
        int totalMoves = 0;
        totalMoves++;

        String[][] boardSuccessor = board.getGameState(); // completely dif board that we're gonna change
        // X player moves
        int colNum = 0;
        int rowNum = 0;
        if (player.equals("X")) {

            colNum = board.getColVal(board.getXColNum());
            rowNum = board.getRowVal(board.getXRowNum());
           
        } else {
        // O player moves
            colNum = board.getColVal(board.getOColNum());
            rowNum = board.getRowVal(board.getORowNum());
        }
        ArrayList<Board> successors = new ArrayList<>();

        // North
        for (int i = rowNum - 1; i >= 0; i--) {

            if (boardSuccessor[i][colNum] == null) {
                successors.add(Board.getNewBoard(board, i, colNum, player));
                totalMoves++;
            } else
                break;
        }

        // South
        for (int i = rowNum + 1; i < 8; i++) {
            if (boardSuccessor[i][colNum] == null) {
                successors.add(Board.getNewBoard(board, i, colNum, player));
                totalMoves++;
            } else
                break;
        }

        // EAST
        for (int i = colNum + 1; i < 8; i++) {
            if (boardSuccessor[rowNum][i] == null) {
                successors.add(Board.getNewBoard(board, rowNum, i, player));
                totalMoves++;
            }

            else
                break;
        }
        // WEST
        for (int i = colNum; i > 0; i--) {
            if (boardSuccessor[rowNum][i - 1] == null) {
                successors.add(Board.getNewBoard(board, rowNum, i - 1, player));
                totalMoves++;
            } else
                break;
        }
        // NE
        int maxNumberOfMovesTilOOB = Math.min(rowNum, ((8 - colNum) - 1));
        int curRow = rowNum;
        int curCol = colNum;
        for (int i = maxNumberOfMovesTilOOB; i > 0; i--) {
            curRow -= 1;
            curCol += 1;
            if (boardSuccessor[curRow][curCol] == null)
                successors.add(Board.getNewBoard(board, curRow, curCol, player));
            else
                break;
        }

        // NW
        maxNumberOfMovesTilOOB = Math.min(rowNum, colNum);
        curRow = rowNum;
        curCol = colNum;
        for (int i = maxNumberOfMovesTilOOB; i > 0; i--) {
            curRow -= 1;
            curCol -= 1;

            if (boardSuccessor[curRow][curCol] == null)
                successors.add(Board.getNewBoard(board, curRow, curCol, player));
            else
                break;
        }

        // SE
        maxNumberOfMovesTilOOB = Math.min((8 - rowNum) - 1, ((8 - colNum) - 1));
        curRow = rowNum;
        curCol = colNum;
        for (int i = maxNumberOfMovesTilOOB; i > 0; i--) {
            curCol += 1;
            curRow += 1;
            if (boardSuccessor[curRow][curCol] == null)
                successors.add(Board.getNewBoard(board, curRow, curCol, player));
            else
                break;
        }

        // SW
        maxNumberOfMovesTilOOB = Math.min(8 - rowNum - 1, colNum);
        curRow = rowNum;
        curCol = colNum;
        for (int i = maxNumberOfMovesTilOOB; i > 0; i--) {
            curCol -= 1;
            curRow += 1;
            if (boardSuccessor[curRow][curCol] == null)
                successors.add(Board.getNewBoard(board, curRow, curCol, player));
            else
                break;
        }

        if(player.equals("X")){
            board.setNumberOfMovesX(successors.size());
        }
        else
            board.setNumberOfMovesO(successors.size());
        
        //board.setNumberOfMovesX(totalMoves);

        //Possible idea
        // board.setSuccessorsX(successors);
        // board.setSuccessorsO(successors);

        return successors;
    }


}
