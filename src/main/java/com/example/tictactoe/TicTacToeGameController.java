package com.example.tictactoe;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controls the flow of the Tic Tac Toe game, including starting a new game,
 * handling player moves, and displaying game over screens.
 */
public class TicTacToeGameController {
    private final Stage stage;
    private TicTacToeGame game;

    /**
     * Constructs a new game controller.
     *
     * @param stage The primary stage for the application.
     */
    public TicTacToeGameController(Stage stage) {
        this.stage = stage;
    }

    /**
     * Starts a new Tic Tac Toe game by initializing the game model and
     * setting the scene for the game grid.
     */
    public void startNewGame() {
        this.game = new TicTacToeGame();
        stage.setScene(new Scene(createGameGrid(), TicTacToeApp.WINDOW_WIDTH, TicTacToeApp.WINDOW_HEIGHT));
    }

    /**
     * Creates the game grid as a GridPane with buttons for each cell.
     *
     * @return A GridPane representing the game grid.
     */
    private GridPane createGameGrid() {
        GridPane grid = new GridPane();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button cell = new Button();
                cell.setPrefSize(TicTacToeApp.WINDOW_WIDTH, TicTacToeApp.WINDOW_HEIGHT);
                int row = i, col = j;
                cell.setOnAction(e -> handleCellClick(row, col, cell));
                grid.add(cell, j, i);
            }
        }
        return grid;
    }

    /**
     * Handles a click on a cell. Updates the game state and checks for game over.
     *
     * @param row  The row of the board cell.
     * @param col  The column of the board cell.
     * @param cell The button that was clicked.
     */
    private void handleCellClick(int row, int col, Button cell) {
           if (game.makeMove(row, col)) {
                String imageFile = game.getCurrentPlayer() == Mark.X ? "x.png" : "o.png";
                Image image = new Image(getClass().getResourceAsStream(imageFile));
                ImageView markImage = new ImageView(image);


                markImage.setFitWidth(120);
                markImage.setFitHeight(120);

                markImage.setPreserveRatio(true);

                cell.setGraphic(markImage);
                cell.setStyle("-fx-background-color: transparent;");

                if (game.getGameOver()) {
                    showGameOverScreen();

                }
                game.switchPlayer();
            }
    }

    /**
     * Shows the game over screen with the result of the game and options to quit or restart.
     */
    private void showGameOverScreen() {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Game Over");

        Text gameOverText = new Text("Game is over! " + (game.hasWinner() ?
                "The winner is: " + game.getCurrentPlayer() : "It's a draw!"));
        gameOverText.setFont(new Font(30));
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> System.exit(0));
        Button restartButton = new Button("Restart");
        restartButton.setOnAction(e -> {
            dialogStage.close();
            startNewGame();
        });

        VBox vbox = new VBox(10, gameOverText, restartButton, quitButton);
        vbox.setAlignment(Pos.CENTER);

        Scene dialogScene = new Scene(vbox, 450, 300);
        dialogStage.setScene(dialogScene);
        dialogStage.show();
    }
}

