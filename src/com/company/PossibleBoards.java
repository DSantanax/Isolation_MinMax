package com.company;

import java.util.ArrayList;

/**
 * The possible Board class generates the successors for
 * the given state
 */

public class PossibleBoards{

    /**
     * Generate successors has global counter to count each possible Move that is
     * then set to each PC or Opponent and return a list of their successors
     * 
     * @param board  - The current board we will be creating children for
     * @param player - The player we will search the children (successors) for
     * @return - Return a list of the Successors boards
     *
     */

    public static ArrayList<Board> generateSuccessors(Board board, String player) {
        // set that counter in the end to the board.maxMoves()
        // In the min max we call the terminal for the maxMoves()
        ArrayList<Board> successors = new ArrayList<>();
        String[][] boardToChange = board.getGameState();
        int colNum;
        int rowNum;
        int totalMoves = 0;

        //Get the position of either the PC or Player
        if (player.equals("X")) {
            colNum = board.getColVal(board.getXColNum());
            rowNum = board.getRowVal(board.getXRowNum());
           
        } else {
            colNum = board.getColVal(board.getOColNum());
            rowNum = board.getRowVal(board.getORowNum());
        }

        // North
        for (int i = rowNum - 1; i >= 0; i--) {

            if (boardToChange[i][colNum] == null) {
                successors.add(new Board(board, i, colNum, player));
                totalMoves++;
            } else
                break;
        }

        // South
        for (int i = rowNum + 1; i < 8; i++) {
            if (boardToChange[i][colNum] == null) {
                successors.add(new Board(board, i, colNum, player));
                totalMoves++;
            } else
                break;
        }

        // EAST
        for (int i = colNum + 1; i < 8; i++) {
            if (boardToChange[rowNum][i] == null) {
                successors.add(new Board(board, rowNum, i, player));
                totalMoves++;
            }

            else
                break;
        }
        // WEST
        for (int i = colNum; i > 0; i--) {
            if (boardToChange[rowNum][i - 1] == null) {
                successors.add(new Board(board, rowNum, i - 1, player));
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
            if (boardToChange[curRow][curCol] == null){
                successors.add(new Board(board, curRow, curCol, player));
                totalMoves++; 
            }
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

            if (boardToChange[curRow][curCol] == null){
                successors.add(new Board(board, curRow, curCol, player));
                totalMoves++;
            }
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
            if (boardToChange[curRow][curCol] == null){
                successors.add(new Board(board, curRow, curCol, player));
                totalMoves++;
            }
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
            if (boardToChange[curRow][curCol] == null){
                successors.add(new Board(board, curRow, curCol, player));
                totalMoves++;
            }
                else
                    break;
        }
        //Set the move available for either PC or Opponent
        if(player.equals("X")){
            board.setNumberOfMovesX(totalMoves);
        }
        else{
            board.setNumberOfMovesO(totalMoves);
        }
        board.setChildren(successors);
        
        return successors;
    }
}