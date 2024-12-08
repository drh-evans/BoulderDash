package Game_Logic;

import Game_Objects.Entities.Boulder;
import Game_Objects.Entities.Diamond;
import Game_Objects.GameObject;
import Game_Objects.Tiles.Exit;
import Game_Objects.Tiles.MagicWall;
import Game_Objects.Entities.Amoeba;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/**
 * A class which holds the methods that control Levels and their visualisation.
 * It contains loading different levels and creating the game.
 * @author Liam O'Reilly, Adam Bartlett, Rhys Evans
 */
public class LevelController {
    // The width and height (in pixels) of each cell that makes up the game.
    private static final int GRID_CELL_WIDTH = 50;
    private static final int GRID_CELL_HEIGHT = 50;
    // The dimensions of the window
    private static int WINDOW_WIDTH;
    private static int WINDOW_HEIGHT;

    // The dimensions of the grid.
    private int gridWidth;
    private int gridHeight;

    // The dimensions of the canvas
    private int canvasWidth;
    private int canvasHeight;

    // The levels that exist in the game.
    private final Level level1 = new Level("src/LevelFiles/Level1.txt");
    private final Level level2 = new Level("src/LevelFiles/Level2.txt");
    private final Level level3 = new Level("src/LevelFiles/Level3.txt");
    private final Level level4 = new Level("src/LevelFiles/Level4.txt");
    private final Level level5 = new Level("src/LevelFiles/Level5.txt");
    private final Level level6 = new Level("src/LevelFiles/Level6.txt");
    private final Level level7 = new Level("src/LevelFiles/Level7.txt");
    private final Level level8 = new Level("src/LevelFiles/Level8.txt");
    // The canvas that the game will be drawn on.
    private Canvas canvas;

    // Buttons to display the current score and time.
    private Text scoreDisplay = new Text();
    private Text timeDisplay = new Text();

    // The image for the character.
    private Image playerImage;

    // The player and character representing the current player.
    private Player player;
    private Character character;

    // String to represent the key pressed.
    private String keyPressed = " ";

    // Timelines which will cause tick methods to be called periodically.
    private Timeline playerTickTimeline;
    private Timeline magicWallTickTimeline;
    private Timeline clockTimeline;
    private Timeline amoebaTickTimeline;
    private Timeline gravityTickTimeline;
    private Timeline rollTickTimeline;

    // Boolean to decide game status.
    private boolean gameOver = false;

    // The stage and scene for the game to be placed on.
    private Stage stage;
    private Scene scene;
    private boolean playerDied = false;
    private int currentLevelNumber;

    /**
     * Instantiates the level controller.
     * @throws FileNotFoundException if a level is not found.
     */
    public LevelController() throws FileNotFoundException {
    }

    public static void main(String[] args) throws FileNotFoundException {
        LevelController main = new LevelController();

        System.out.println("Actual height and width: ");
        System.out.println(main.gridHeight);
        System.out.println(main.gridWidth);

        System.out.println("Auxiliary level attributes: ");
        System.out.print("Highscores: ");
        for (int score : main.level1.getHighscores()) {
            System.out.print(score + " ");
        }
        System.out.println();
        System.out.println("Time to complete: " + main.level1.getTimeToComplete());
        System.out.println("Diamonds to win: " + main.level1.getDiamondsToWin());
        System.out.println("Amoeba spread rate: " + main.level1.getAmoebaSpreadRate());
        System.out.println("Amoeba max size: " + main.level1.getAmoebaMaxSize());
    }

    /**
     * Creates a template for each level creation.
     * @param desiredLevel The level to be loaded.
     */
    public void createLevelTemplate(ActionEvent openGame, Level desiredLevel) {
        //Sets attributes for the level
        setGridWidth(desiredLevel.getWidth());
        setGridHeight(desiredLevel.getHeight());
        canvasHeight = gridHeight * GRID_CELL_HEIGHT;
        canvasWidth = gridWidth * GRID_CELL_WIDTH;
        WINDOW_WIDTH = canvasWidth;
        WINDOW_HEIGHT = canvasHeight + 45;

        //Creates a new player and character
        player = new Player(player.getName(), player.getLevelUnlocked(), desiredLevel);
        System.out.println(player.getName());
        System.out.println(player.getLevelUnlocked());

        setCharacter(player.getPlayerCharacter());
        createGame(openGame, desiredLevel);

        //Starts the clock and tick as level is loaded
        gameRunning(true);
    }

    /**
     * Creates level ones display.
     *
     * @param openGame The button was pressed.
     */
    public void createLevel1(ActionEvent openGame) {
        player = PlayerController.getCurrentPlayer();
        currentLevelNumber = 1;
        if (player.getLevelUnlocked() >= 1) {
            createLevelTemplate(openGame, level1);
        }
        System.out.println("not unlocked");
    }

    /**
     * Creates level twos display.
     *
     * @param openGame The button was pressed.
     */
    public void createLevel2(ActionEvent openGame) {
        player = PlayerController.getCurrentPlayer();
        currentLevelNumber = 2;
        if (player.getLevelUnlocked() >= 2) {
            createLevelTemplate(openGame, level2);
        }
        System.out.println("not unlocked");
    }

    /**
     * Creates level threes display.
     *
     * @param openGame The button was pressed.
     */
    public void createLevel3(ActionEvent openGame) {
        player = PlayerController.getCurrentPlayer();
        currentLevelNumber = 3;
        if (player.getLevelUnlocked() >= 3) {
            createLevelTemplate(openGame, level3);
        }
        System.out.println("not unlocked");
    }
    /**
     * Creates level fours display.
     *
     * @param openGame The button was pressed.
     */
    public void createLevel4(ActionEvent openGame) {
        player = PlayerController.getCurrentPlayer();
        currentLevelNumber = 4;
        if (player.getLevelUnlocked() >= 4) {
            createLevelTemplate(openGame, level4);
        }
        System.out.println("not unlocked");
    }

    /**
     * Creates level fives display.
     *
     * @param openGame The button was pressed.
     */
    public void createLevel5(ActionEvent openGame) {
        player = PlayerController.getCurrentPlayer();
        currentLevelNumber = 5;
        if (player.getLevelUnlocked() >= 5) {
            createLevelTemplate(openGame, level5);
        }
        System.out.println("not unlocked");
    }

    /**
     * Creates level sixes display.
     *
     * @param openGame The button was pressed.
     */
    public void createLevel6(ActionEvent openGame) {
        player = PlayerController.getCurrentPlayer();
        currentLevelNumber = 6;
        if (player.getLevelUnlocked() >= 6) {
            createLevelTemplate(openGame, level6);
        }
        System.out.println("not unlocked");
    }

    /**
     * Creates level sevens display.
     *
     * @param openGame The button was pressed.
     */
    public void createLevel7(ActionEvent openGame) {
        player = PlayerController.getCurrentPlayer();
        currentLevelNumber = 7;
        if (player.getLevelUnlocked() >= 7) {
            createLevelTemplate(openGame, level7);
        }
        System.out.println("not unlocked");
    }

    /**
     * Creates level eights display.
     *
     * @param openGame The button was pressed.
     */
    public void createLevel8(ActionEvent openGame) {
        player = PlayerController.getCurrentPlayer();
        currentLevelNumber = 8;
        if (player.getLevelUnlocked() >= 8) {
            createLevelTemplate(openGame, level8);
        }
        System.out.println("not unlocked");
    }


    /**
     * Updates stage to display the game.
     *
     * @param openGame     button was pressed.
     * @param desiredLevel The level to be displayed
     */
    public void createGame(ActionEvent openGame, Level desiredLevel) {
        //Removes previous scene
        stage = (Stage) ((Node) openGame.getSource()).getScene().getWindow();

        // Load images. Note we use png images with a transparent background.
        playerImage = new Image("Images/player.png");

        // Build the GUI
        Pane root = buildGUI(desiredLevel);
        // root.setBackground(Background.fill(Color.MIDNIGHTBLUE));
        // Create a scene from the GUI
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.setFill(Color.MIDNIGHTBLUE);

        // Register an event handler for key presses.
        // This causes the processKeyEvent method to be called each time a key is pressed.
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event, desiredLevel));

        // Register a tick method to be called periodically.
        // Make a new timeline with one keyframe that triggers the tick method every half a second.
        playerTickTimeline = new Timeline(new KeyFrame(Duration.millis(600), event -> {
            try {
                playerTick(desiredLevel);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        // Loop the timeline forever
        playerTickTimeline.setCycleCount(Animation.INDEFINITE);

        // Magic wall tick rate
        magicWallTickTimeline = new Timeline(new KeyFrame(Duration.millis(500), event -> magicWallTick(desiredLevel)));
        // Loop the timeline forever
        magicWallTickTimeline.setCycleCount(Animation.INDEFINITE);

        // Amoeba tick rate
        amoebaTickTimeline = new Timeline(new KeyFrame(Duration.millis(3000), event -> amoebaTick(desiredLevel)));
        amoebaTickTimeline.setCycleCount(Animation.INDEFINITE);

        // Gravity tick rate
        gravityTickTimeline = new Timeline(new KeyFrame(Duration.millis(400), event -> gravityTick(desiredLevel)));
        gravityTickTimeline.setCycleCount(Animation.INDEFINITE);

        // Gravity tick rate
        rollTickTimeline = new Timeline(new KeyFrame(Duration.millis(400), event -> rollTick(desiredLevel)));
        rollTickTimeline.setCycleCount(Animation.INDEFINITE);

        // Tick timeline for the timer, set to 1s.
        clockTimeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            try {
                clockTick(desiredLevel);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        clockTimeline.setCycleCount(Animation.INDEFINITE);

        // Display the scene on the stage
        drawGame(desiredLevel);
        stage.setTitle("Boulder Dash Group 9");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Go back to menu from levels
     *
     * @param event button is pressed
     */
    @FXML
    public void backButton(ActionEvent event) throws IOException {
        //Loads new fxml
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        //Removes old and creates a new scene
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Return a user to the main menu.
     * @param e The button was pressed.
     */
    public void returnToMenu(ActionEvent e) {
        //If return to menu is called stop game ticks.
        gameRunning(false);

        // Current menu is null.
        Parent menu = null;

        try {
            // Loads the menu.fxml file
            menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        // Get the present stage
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        // Set the new stage
        stage.setScene(new Scene(menu));
        stage.show();
    }

    @FXML
    public void openHelp(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Help.fxml"));
            Stage popoutStage = new Stage();
            popoutStage.setTitle("Help Window");
            Scene scene = new Scene(root);
            popoutStage.setScene(scene);
            popoutStage.initModality(Modality.NONE);
            popoutStage.show();
        } catch (IOException e) {
            // Handle the exception and provide feedback
            e.printStackTrace();  // Print the stack trace to help debug the issue
            System.out.println("Error loading Help.fxml");
        }
    }



    //Score checking everytime a player finish a level
    /*
    public void scoreChecking(Player player, Level currentLevel, int finalScore) {
        if (finalScore > player.getLevelScore(currentLevel)) {
            player.setLevelScore(finalScore);
        }
        currentLevel.newHighScore(currentLevel, finalScore);
    }

     */

    /**
     * Process a key event due to a key being pressed, subsequently moves the player.
     * @param event The key event that was pressed.
     */
    public void processKeyEvent(KeyEvent event, Level currentLevel) {
        // character.moveCharacter(event, gridWidth, gridHeight);
        switch (event.getCode()) {
            case UP, W -> keyPressed = "UP";
            case DOWN, S -> keyPressed = "DOWN";
            case RIGHT, D -> keyPressed = "RIGHT";
            case LEFT, A -> keyPressed = "LEFT";
            default -> keyPressed = " ";
        }
        // Redraw the game as the player may have moved.
        drawGame(currentLevel);
        // Consume the event.
        event.consume();
    }

    /**
     * Draw the game on the canvas.
     * @param chosenLevel The selected level by the player.
     */
    public void drawGame(Level chosenLevel) {
        // Get the Graphic Context of the canvas. This is what we draw on.
        GraphicsContext gc = canvas.getGraphicsContext2D();
        scoreDisplay.setText("SCORE: " + Integer.toString(player.getLevelScore()));
        timeDisplay.setText("TIME: " + Integer.toString(chosenLevel.getTimeToComplete()));

        // Clear canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Set the background to gray.
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Initialise the level and its graphics.
        chosenLevel.initialiseLevel(gc);

        // Draw player at the current position.
        gc.drawImage(playerImage, character.getCharacterX() * GRID_CELL_WIDTH, character.getCharacterY() * GRID_CELL_HEIGHT);
    }

    /**
     * Reset the player's location and move them back to (1,1).
     * @param currentLevel The current level being played.
     */
    public void resetPlayerLocation(Level currentLevel) {
        character.setCharacterX(1);
        character.setCharacterY(1);
        drawGame(currentLevel);
    }


    /**
     * Returns the user to the level's menu.
     * @throws IOException If there isn't a valid fxml file.
     */
    @FXML
    public void openLevels() throws IOException {
        //Loads new fxml
        Parent root = FXMLLoader.load(getClass().getResource("Levels.fxml"));

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void menu() throws IOException {
        //Loads new fxml
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Controls the magic wall's transformation of objects.
     * @param currentLevel The current level being played.
     */
    public void magicWallTick(Level currentLevel) {
        GameObject[] gameBoard = currentLevel.getGameBoard();
        MagicWall.magicWallLogic(gameBoard, gridWidth, character);

        // Redraw the game
        drawGame(currentLevel);
    }

    /**
     * Controls the amoebas spreading.
     * @param currentLevel The current level being played.
     */
    public void amoebaTick(Level currentLevel) {
        GameObject[] gameBoard = currentLevel.getGameBoard();
        for (GameObject object : gameBoard) {
            if (object.getObjectToken().equals("A")) {
                int tilePosition = object.getY() * gridWidth + object.getX();
                Amoeba a = (Amoeba) gameBoard[tilePosition];
                a.spread(gameBoard, gridWidth);
            }
        }
    }

    /**
     * This method is called periodically by the tick timeline
     * and would for, example move, perform logic in the game,
     * this might cause the bad guys to move (by e.g., looping
     * over them all and calling their own tick method).
     */
    public void playerTick(Level currentLevel) throws IOException {
        // Move the character.
        character.moveCharacter(keyPressed, gridWidth, gridHeight);

        // Reset key pressed so that new movement is read or character doesn't move.
        keyPressed = " ";

        // Redraw the game with new X, Y.
        drawGame(currentLevel);

        // Check if the exit tile has been contacted.
        if (Exit.exitTileLogic(currentLevel, character, gridWidth)) {
            if(player.getLevelUnlocked() == currentLevelNumber) {
                increaseLevel();
            }
            //scoreChecking(player, currentLevel, player.getFinalLevelScore());
            updateHighScore(player, currentLevel, player.getFinalLevelScore());
            gameRunning(false);
            menu();
        } else if (playerDied) {
            gameRunning(false);
            System.out.println("You DIED!!!");
            openLevels();
        }
    }
    public void updateHighScore(Player player, Level current, int newHighScore) throws IOException {
        /*
        for (int i = 0; i < current.highscores.length; i++) {
            if (newHighScore > current.highscores[i]) {
                for (int j = current.highscores.length - 1; j > i; j--) {
                    current.highscores[j] = highscores[j - 1];
                }
                current.highscores[i] = newHighScore;
                break;
            }
        }
         */

        File ScoresData = new File("src/LevelFiles/LevelHighScores.txt");
        Scanner in = new Scanner(ScoresData);
        List<String> lines = new ArrayList<>();
        List<String> playerScoreList = new ArrayList<>(10);
        String[] playerAndScore;
        String newPlayerAndScore = player.getName() + ":" + newHighScore;
        //read the file
        while (in.hasNextLine()) {
            String line = in.nextLine();
            lines.add(line);
        }
        //process data
        for (int i = 0; i < lines.size() - 1; i++) {
            String line = lines.get(i);
            //check the current level with file level is the same or not
            if (line.startsWith(current.getLevelFile().getName().replace("src/LevelFiles/", "").replace(".txt", "").toLowerCase() + ";")) {
                String[] level = line.split(";");
                String[] data = level[1].split(",");
                playerScoreList.clear();
                playerScoreList.addAll(List.of(data));
                for (int j = 0 ;j < playerScoreList.size(); j++) {
                    playerAndScore = playerScoreList.get(j).split(":");
                    //check is the player record exist
                    if (playerAndScore[0].equals(player.getName())) {
                        //update the personal high score
                        if (Integer.parseInt(playerAndScore[1]) < newHighScore) {
                            playerScoreList.set(j, newPlayerAndScore);
                            break;
                        }
                    } else {
                        if (Integer.parseInt(playerAndScore[1]) < newHighScore) {
                            playerScoreList.add(newPlayerAndScore);
                            break;
                        }
                    }
                }
                //rank the players name by their score
                playerScoreList.sort((a, b) -> Integer.compare(Integer.parseInt(b.split(":")[1]), Integer.parseInt(a.split(":")[1])));
                //keep the rank only top 10
                if (playerScoreList.size() > 10) {
                    playerScoreList = playerScoreList.subList(0, 10);
                }
                //replace that level's top 10 ranking
                lines.set(i, current.getLevelFile().toString().replace("src/LevelFiles/", "").replace(".txt", "").toLowerCase() + ";" + String.join(",", playerScoreList));
                System.out.println();
            }
            //
        }
        //update new line back to file
        FileWriter fw = new FileWriter(ScoresData);
        for (String newLine : lines) {
            fw.write(newLine + "\n");
        }
        System.out.println(playerScoreList);
        System.out.println(lines);
        in.close();
        fw.close();
    }

    public void increaseLevel() throws IOException {
        File Players = new File("src/LevelFiles/Players.txt");
        Scanner in = new Scanner(Players);

        // Current player string
        String currentPlayer = player.getName() + " " + player.getLevelUnlocked();

        // Updating players level
        player.progressLevel();
        String newPlayer = player.getName() + " " + player.getLevelUnlocked();

        //Getting current file
        StringBuilder fileContent = new StringBuilder();
        if (in.hasNextLine()) {
            fileContent.append(in.nextLine());
        }

        // Replaces old player with new player
        String newPlayerList = fileContent.toString().replaceAll(currentPlayer, newPlayer);
        FileWriter fw = new FileWriter(Players);

        fw.write(newPlayerList);
        in.close();
        fw.close();

    }

    /**
     * Control the clock component of the game.
     * @param currentLevel The current level.
     */
    private void clockTick(Level currentLevel) throws IOException {
        // Get the current time to complete.
        int time = currentLevel.getTimeToComplete();

        // Check the time/game state validity.
        if (time == 0) {
            // If time is zero stop the game.
            gameRunning(false);
            openLevels();
            gameOver = true;
        } else if (!gameOver) {
            // Decrease time to complete by 1.
            currentLevel.setTimeToComplete();
        }
        // Redraw the graphics in current state.
        drawGame(currentLevel);
    }

    public void gravityTick(Level currentLevel) {
        GameObject[] gameBoard = currentLevel.getGameBoard();
        int levelWidth = currentLevel.getWidth();

        for (int i = 0; i < gameBoard.length; i++) {
            // Loops though the game board finding boulders or diamonds
            if (!gameBoard[i].isFalling()) {
                // if the object is a diamond run drop diamond
                if (gameBoard[i].getObjectToken().equals("*")) {
                    Diamond diamond = new Diamond();
                    diamond.dropDiamond(gameBoard, levelWidth, i, character);
                    killCheck(diamond);
                }
                // if it is a boulder run drop boulder
                if (gameBoard[i].getObjectToken().equals("B")) {
                    Boulder boulder = new Boulder();
                    boulder.dropBoulder(gameBoard, levelWidth, i, character);
                    killCheck(boulder);
                }
            }
        }

        // Reset any falling objects for next method call
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i].isFalling()) {
                gameBoard[i].setFalling(false);
            }
        }
        drawGame(currentLevel);
    }

    public void rollTick(Level currentLevel) {
        GameObject[] gameBoard = currentLevel.getGameBoard();
        int levelWidth = currentLevel.getWidth();

        for (int i = 0; i < gameBoard.length; i++) {
            // Loops though the game board finding boulders or diamonds
            if (!gameBoard[i].isFalling()) {
                // if the object is a diamond run drop diamond
                if (gameBoard[i].getObjectToken().equals("*")) {
                    Diamond diamond = new Diamond();
                    diamond.roll(gameBoard, levelWidth, i, character);
                    killCheck(diamond);
                }
                // if it is a boulder run drop boulder
                if (gameBoard[i].getObjectToken().equals("B")) {
                    Boulder boulder = new Boulder();
                    boulder.roll(gameBoard, levelWidth, i, character);
                    killCheck(boulder);
                }
            }
        }

        // Reset any falling objects for next method call
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i].isFalling()) {
                gameBoard[i].setFalling(false);
            }
        }

        drawGame(currentLevel);
    }

    private void killCheck(GameObject object) {
        if (object.isFalling()) {
            int x = object.getX();
            int y = object.getY();

            if (character.getCharacterX() == x &&
                    character.getCharacterY() == y + 1) {
                character.resetCharacterPosition();
                playerDied = true;
            }
        }
    }



    /**
     * Create the games GUI.
     * @return The panel that contains the created GUI.
     */
    private Pane buildGUI(Level currentLevel) {
        // Create top-level panel that will hold all GUI nodes.
        BorderPane root = new BorderPane();

        // Create the canvas will be drawn on.
        canvas = new Canvas(canvasWidth, canvasHeight);
        root.setCenter(canvas);

        // Create a toolbar.
        HBox toolbar = new HBox();
        toolbar.setSpacing(10);
        toolbar.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(toolbar);

        // Add the current score text.
        scoreDisplay.setFont(Font.font("Impact", 18));
        toolbar.getChildren().add(scoreDisplay);

        // Add the current time left text.
        timeDisplay.setFont(Font.font("Impact", 18));
        toolbar.getChildren().add(timeDisplay);

        // Add the option to return to the main menu.
        Button returnToMenu = new Button("Quit");
        toolbar.getChildren().add(returnToMenu);
        returnToMenu.setOnAction(this::returnToMenu);

        Button helpButton = new Button("Help");
        toolbar.getChildren().add(helpButton);
        helpButton.setOnAction(this::openHelp);

        // Return the border pane that has been built.
        return root;
    }

    /**
     * Starts the game ticks if the game is running, otherwise ends them.
     * @param gameRunning a boolean, whether game is running or not.
     */
    private void gameRunning(boolean gameRunning) {
        if (gameRunning) {
            magicWallTickTimeline.play();
            playerTickTimeline.play();
            clockTimeline.play();
            amoebaTickTimeline.play();
            gravityTickTimeline.play();
            rollTickTimeline.play();
        } else {
            clockTimeline.stop();
            playerTickTimeline.stop();
            magicWallTickTimeline.stop();
            amoebaTickTimeline.stop();
            gravityTickTimeline.stop();
            rollTickTimeline.stop();
        }
    }

    // Getters and setters for all attributes.

    /**
     * Get the grid width.
     * @return the grid width.
     */
    public int getGridWidth() {
        return gridWidth;
    }

    /**
     * Set the grid width.
     * @param gridWidth an int, the new grid width.
     */
    public void setGridWidth(int gridWidth) {
        this.gridWidth = gridWidth;
    }

    /**
     * Get the grid height.
     * @return the grid height.
     */
    public int getGridHeight() {
        return gridHeight;
    }

    /**
     * Set the grid height.
     * @param gridHeight an int, the new grid height.
     */
    public void setGridHeight(int gridHeight) {
        this.gridHeight = gridHeight;
    }

    /**
     * Get the canvas width.
     * @return the canvas width.
     */
    public int getCanvasWidth() {
        return canvasWidth;
    }

    /**
     * Set the canvas width.
     * @param canvasWidth The canvas' width.
     */
    public void setCanvasWidth(int canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    /**
     * Get the canvas height.
     * @return The canvas' height.
     */
    public int getCanvasHeight() {
        return canvasHeight;
    }

    /**
     * Set the canvas height.
     * @param canvasHeight The canvas height.
     */
    public void setCanvasHeight(int canvasHeight) {
        this.canvasHeight = canvasHeight;
    }

    /**
     * Get the current character.
     * @return The current player character.
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Set the character.
     * @param character The new character.
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

    /**
     * Get the current player.
     * @return The current player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Set the current player.
     * @param player The current player.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
}