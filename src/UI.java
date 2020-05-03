import java.util.Scanner;

/**
 * The UI class starts our game & calls the available methods from the MinMax &
 * the Board class. We prompt the user for a depth & time. This is used for the
 * min max algorithm.
 */

public class UI {
    Scanner input = new Scanner(System.in);

    public void startGame(int depth) {
        int turns = 0;
        UI userInterface = new UI();
        Scanner sc = new Scanner(System.in);
        final String OPPONENT = "O";
        final String COMP = "C";

        MinMaxAB.TIME_SET = userInterface.promptTimePerMove();

        String currentMove = userInterface.whoGoesFirst();

        String selectedFirst = currentMove;
        Board board = new Board(currentMove);
        board.setFirstPlayer(selectedFirst);
        String winnerWinnerChickenDinner;

        board.printBoard(turns);

        while (board.gameOver(COMP) && board.gameOver(OPPONENT)) {
            // PC's turn
            if (currentMove.equals(COMP)) {

                // ------This is used for PVP ---------
                // String computerMove = userInterface.getMove(COMP);
                // while (!board.validMove(computerMove, COMP)) {
                // System.out.println("Invalid input. Put in something legit.");
                // System.out.println("Current X position" + board.getOPosition());
                // computerMove = userInterface.getMove(COMP);
                // }
                // board.playerTurnToMove(computerMove, COMP);

                // ------ PvC -----------
                board = MinMaxAB.MinMaxDecision(board, depth);
                board.logFile.add(board.getXPosition());
                System.out.println("Computer's move is: " + board.getXPosition());

                turns++;
                board.printBoard(turns);
                // Other's move
                currentMove = OPPONENT;

                // Opponent's turn
            } else {

                String opponentMove = userInterface.getMove(OPPONENT);
                while (!board.validMove(opponentMove, OPPONENT) && opponentMove.length() == 2) {
                    System.out.println("Invalid input. Put in something legit.");
                    System.out.println("Current O position: " + board.getOPosition());
                    System.out.println();
                    opponentMove = userInterface.getMove(OPPONENT);
                }
                System.out.println("Player's move is: " + opponentMove);
                board.playerTurnToMove(opponentMove, OPPONENT);

                board.logFile.add(board.getOPosition());
                turns++;
                board.printBoard(turns);
                // Other's move
                currentMove = COMP;

            }
        }
        if (board.gameOver(COMP))
            winnerWinnerChickenDinner = "Computer won!";
        else {
            winnerWinnerChickenDinner = "Opponent won!";
        }
        sc.close();
        
        board.printBoard(turns);
        System.out.println("\n\n---Game Over--- " + winnerWinnerChickenDinner);
    }

    public String whoGoesFirst() {
        System.out.println("\nWho goes first? C for Computer, O for opponent. (C or O).");
        String firstMove = input.nextLine().toUpperCase();
        System.out.println("Going first: " + firstMove);
        System.out.println("----------------------Game Start-----------------------------\n");
        return firstMove;
    }

    public String getMove(String player) {
        String move = "";
        if (player.equals("C")) {
                System.out.println("Enter a valid computer move: ");
                move = input.nextLine();
            
        } else {
                System.out.println("Enter a valid player move: ");
                move = input.nextLine();
        }
        return move;
    }

    public int promptTimePerMove() {
        System.out.println("Enter the time limit per move in seconds: ");
        int time = input.nextInt();
        System.out.println("The time limit per move is : " + time);
        input.nextLine();
        return time;
    }
}