package com.example.tictactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The TicTacToeApp class is the entry point for a simple Tic-Tac-Toe game application.
 * It sets up the primary stage with a welcome screen that allows the user to start a new game or quit.
 * This application demonstrates the use of JavaFX for creating a user interface.
 */
public class TicTacToeApp extends Application {

    /**
     * The width of the application window.
     */
    public static final double WINDOW_WIDTH = 800;

    /**
     * The height of the application window.
     */
    public static final double WINDOW_HEIGHT = 600;

    /**
     * Starts the application and displays the initial welcome screen.
     *
     * @param primaryStage The primary stage for this application, onto which
     *                     the application scene is set.
     */
    @Override
    public void start(Stage primaryStage) {
        TicTacToeGameController gameController = new TicTacToeGameController(primaryStage);

        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(new Scene(createStartScreen(gameController), WINDOW_WIDTH, WINDOW_HEIGHT));
        primaryStage.show();
    }

    /**
     * Creates the start screen for the application.
     *
     * @param gameController The controller that manages game logic and interactions.
     * @return A {@code VBox} containing the welcome message and buttons to start or quit the game.
     */
    private VBox createStartScreen(TicTacToeGameController gameController) {
        Text message = new Text("Welcome to the TicTacToe Game!");
        message.setFont(new Font(40));



        Button startButton = new Button("Press to start");
        startButton.setOnAction(e -> gameController.startNewGame());

        Button quitButton = new Button("Press to quit");
        quitButton.setOnAction(e -> System.exit(0));
        startButton.setStyle("-fx-font-size: 16px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        quitButton.setStyle("-fx-font-size: 16px; -fx-background-color: #f44336; -fx-text-fill: white;");

        HBox buttonBar = new HBox(20, startButton, quitButton);
        buttonBar.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(30, message, buttonBar);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: #ADD8E6;");

        return vbox;
    }

    /**
     * Main method that launches the game.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

