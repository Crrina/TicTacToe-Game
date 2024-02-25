package com.example.tictactoe;

/**
 * Represents a Tic Tac Toe game logic. It handles moves, checks for winners, and keeps track of the game state.
 */
public class TicTacToeGame {

    /**
     * The dimension of the Tic Tac Toe board.
     */
    private static final int DIM  = 3;

    /**
     * The game board represented as a two-dimensional array.
     */
    private Mark[][] board;

    /**
     * The current player.
     */
    private Mark currentPlayer;

    /**
     * Flag indicating whether the game is over.
     */
    private boolean gameOver;

    /**
     * Count of moves made in the game. Used to determine if the board is full.
     */
    private int moveCount;

    /**
     * Initializes a new Tic Tac Toe game with an empty board and sets the current player to X.
     */
    public TicTacToeGame(){
        this.board = new Mark[DIM][DIM];
        this.currentPlayer = Mark.X;
        this.moveCount = 0;
        this.gameOver = false;
    }


    /**
     * Attempts to make a move on the board.
     *
     * @param row The row index of the move.
     * @param col The column index of the move.
     * @return true if the move was successful,
     * false if the cell was already occupied or the game is over.
     */
    public boolean makeMove(int row, int col) {
        if (board[row][col] == null && !gameOver) {
            board[row][col] = currentPlayer;
            moveCount ++;
            checkGameOver();
            return true;
        }
        return false;
    }

    /**
     * Switches the current player from X to O or O to X.
     */
    public void switchPlayer() {
        currentPlayer = (currentPlayer == Mark.X) ? Mark.O : Mark.X;
    }

    /**
     * Returns the current player.
     * @return The current player Mark.
     */
    public Mark getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Checks if the game is over due to a win or a full board.
     */
    private void checkGameOver() {
        if (hasWinner() || boardFull()) {
            gameOver = true;
        }
    }

    /**
     * Returns whether the game is over.
     * @return true if the game is over, false otherwise.
     */
    public boolean getGameOver(){
        return gameOver;
    }


    /**
     * Checks for a winning condition on the board.
     * @return true if the current player has won, false otherwise.
     */
    public boolean hasWinner() {
        return hasDiagonal() || hasRow() || hasCol();
    }

    /**
     * Checks for a winning column condition for the current player.
     * @return true if a winning column exists, false otherwise.
     */
    private boolean hasCol() {
        for (int j = 0; j < board.length; j++) {
            boolean colWin = true;
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] != currentPlayer) {
                    colWin = false;
                    break; // Break out of the inner loop as soon as a mismatch is found
                }
            }
            if (colWin) {
                return true; // Return true if a winning column is found
            }
        }
        return false; // Return false if no winning column is found
    }



    /**
     * Checks for a winning row condition for the current player.
     * @return true if a winning row exists, false otherwise.
     */
    private boolean hasRow() {
        for (int i = 0; i < board.length; i++) {
            boolean rowWin = true;
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != currentPlayer) {
                    rowWin = false;
                    break; // Break out of the inner loop as soon as a mismatch is found
                }
            }
            if (rowWin) {
                return true; // Return true if a winning row is found
            }
        }
        return false; // Return false if no winning row is found
    }


    /**
     * Checks for a winning diagonal condition for the current player.
     * @return true if a winning diagonal exists, false otherwise.
     */
    private boolean hasDiagonal() {
        boolean mainDiagonalWin = true;
        boolean antiDiagonalWin = true;
        for (int i = 0; i < board.length; i++) {
            // Check main diagonal
            if (board[i][i] != currentPlayer) {
                mainDiagonalWin = false;
            }
            // Check anti-diagonal
            if (board[i][board.length - 1 - i] != currentPlayer) {
                antiDiagonalWin = false;
            }
        }
        return mainDiagonalWin || antiDiagonalWin;
    }


    /**
     * Checks if the board is full.
     * @return true if the board is full, false otherwise.
     */
    private boolean boardFull() {
        return moveCount == DIM * DIM;
    }


}