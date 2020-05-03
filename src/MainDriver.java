/**
 * Authors: Brandon Dayauon 
 *          Daniel Santana 
 * Course: 
 *          AI 4200
 * 
 * Description: 
 *          In this project we created the Isolation game in PvP & PvC mode.
 *          We also assigned a timer and depth to the min max algorithm with AB pruning
 *          this allows the PC to select a move at a giving amount of time & depth.
 * 
 */

public class MainDriver {
    /*
     * Instructions & Requirements:
     *  
     * - Your computer is required to prompt for “Who goes first, C for computer, O for opponent: 
     * - Your computer will be required to make its move within 20 seconds. 
     * - The board will be 8 x 8 with the coordinate A1 indicating the top left hand side of the board. 
     * - The user interface must display the game as demonstrated above 
     * - When a new game starts, It must prompt for the time limit per move and 
     *  prompt for who goes first (computer or opponent) 
     * - Moves must be entered as shown above 
     * - The game must be implemented using the minimax algorithm with alpha-beta pruning 
     * - It will help if you also implement this with an iterative deepening.
     * 
     * ---------------The Opponent (Player) is O ----- The PC Player is X ---------
     */

    // TODO: Fix if crash depth is long <6 (due to successors size being large)

    // The main method which starts our Isolation game.
    public static void main(String[] args) {
        //Set the depth
        final int depth = 6;
        UI game = new UI();
        game.startGame(depth);
    }
}