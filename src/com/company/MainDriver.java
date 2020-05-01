package com.company;

/**
 * Authors: Brandon Dayauon, Daniel Santana 
 * Course: AI 4200
 * 
 * Description:
 *          We created a UI 
 */

public class MainDriver{
    /*
     * Requirements: 
     * - Your computer is required to prompt for “Who goes first, C for computer, O for opponent: 
     * - Your computer will be required to make its move within 20 seconds. 
     * - The board will be 8 x 8 with the coordinate A1 indicating the top left hand side of the board. 
     * - The user interface must display the game as demonstrated above 
     * - When a new game starts, It must prompt for the time limit per move and prompt for who goes first (computer or
     * opponent) 
     * - Moves must be entered as shown above 
     * - The game must be implemented using the minimax algorithm with alpha-beta pruning 
     * - It will help if you also implement this with an iterative deepening.
     */

    // TODO: gameOver, heuristic, combine methods, clean
    // TODO: remove 1 validMoves

    //Main starts the game
    public static void main(String[] args) {
        UI game = new UI();
        game.startGame();
    }
}