package Game_Logic;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 *
 * A class which holds the methods that control Levels. It contains loading different levels and
 * creating the game.
 *
 * @author Liam O'Reilly, Adam Bartlett, Rhys Evans
 */
public class LevelController {
    // The dimensions of the window
    private static int WINDOW_WIDTH;
    private static int WINDOW_HEIGHT;

    // The width and height (in pixels) of each cell that makes up the game.
    private static final int GRID_CELL_WIDTH = 50;
    private static final int GRID_CELL_HEIGHT = 50;

    // The width of the grid in number of cells.
    private final Level testLevel = new Level("src/LevelFiles/Level1.txt");
    private final Level l2 = new Level("src/LevelFiles/Level2.txt");
    private final Level l3 = new Level("src/LevelFiles/Level3.txt");


    // update the grid width and height to match level width and height
    private int gridWidth;
    private int gridHeight;

    // The dimensions of the canvas
    private int canvasWidth;
    private int canvasHeight;

    // The canvas in the GUI. This needs to be a global variable
    // (in this setup) as we need to access it in different methods.
    // We could use FXML to place code in the controller instead.
    private Canvas canvas;

    private Text scoreDisplay = new Text();
    private Text timeDisplay = new Text();

    // Loaded images
    private Image playerImage;
    private Image dirtImage;
    private Image iconImage;

    // X and Y coordinate of player on the grid.
    private Character character;
    private Player player;
    private int playerX = 1;
    private int playerY = 1;
    // String to represent the key pressed.
    private String keyPressed = " ";

    // Timeline which will cause tick method to be called periodically.
    private Timeline tickTimeline;
    private Timeline clockTimeline;

    // Boolean to decide game status.
    private boolean gameOver = false;

    public LevelController() throws FileNotFoundException {
    }


    /**
     * Creates a template for each level creation.
     * @param desiredLevel The level to be loaded.
     */
    public void createLevelTemplate(ActionEvent openGame,Level desiredLevel){
        //Sets attributes for the level
        setGridWidth(desiredLevel.getWidth());
        setGridHeight(desiredLevel.getHeight());
        canvasHeight = gridHeight * GRID_CELL_HEIGHT;
        canvasWidth = gridWidth * GRID_CELL_WIDTH;
        WINDOW_WIDTH =  canvasWidth;
        WINDOW_HEIGHT = canvasHeight + 45;

        //Creates a new player and character
        player = new Player("John", desiredLevel);
        setCharacter(player.getPlayerCharacter());
        createGame(openGame, desiredLevel);

        //Starts the clock and tick as level is loaded
        tickTimeline.play();
        clockTimeline.play();
    }


    /**
     * Creates level ones display.
     * @param openGame button was pressed.
     */
    public void createLevel1(ActionEvent openGame) {
        createLevelTemplate(openGame ,testLevel);
    }


    /**
     * Creates level twos display.
     * @param openGame button was pressed.
     */
    public void createLevel2(ActionEvent openGame) {
        createLevelTemplate(openGame, l2);
    }

    /**
     * Creates level threes display.
     * @param openGame button was pressed.
     */
    public void createLevel3(ActionEvent openGame) {
        createLevelTemplate(openGame,l3);
    }


    /**
     * Updates stage to display the game.
     * @param openGame button was pressed.
     * @param desiredLevel The level to be displayed
     */
    public void createGame(ActionEvent openGame, Level desiredLevel) {
        //Removes previous scene
        Stage stage = (Stage) ((Node) openGame.getSource()).getScene().getWindow();

        // Load images. Note we use png images with a transparent background.
        playerImage = new Image("Images/player.png");
        dirtImage = new Image("Images/dirt.png");

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
        tickTimeline = new Timeline(new KeyFrame(Duration.millis(600), event -> tick(desiredLevel)));
        // Loop the timeline forever
        tickTimeline.setCycleCount(Animation.INDEFINITE);

        // Tick timeline for the timer, set to 1s.
        clockTimeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> clockTick(desiredLevel)));
        clockTimeline.setCycleCount(Animation.INDEFINITE);

        // Display the scene on the stage
        drawGame(desiredLevel);
        stage.setTitle("Boulder Dash Group 9");
        stage.setScene(scene);
        stage.show();
    }

    public void returnToMenu(ActionEvent e){
        Parent menu = null;
        try {
            // loads menu fxml file
            menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        // get present stage
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        // set new stage
        stage.setScene(new Scene(menu));
        stage.show();
    }

    //Score checking everytime a player finish a level
    public void scoreChecking(Player player, Level currentLevel, int score) {
        if (score > player.getHighScore()) {
            player.setHighScore(score);
        }
        currentLevel.newHighScore(score);
    }


    /**
     * Process a key event due to a key being pressed, e.g., to move the player.
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
        // Redraw game as the player may have moved.
        drawGame(currentLevel);
        // Consume the event. This means we mark it as dealt with. This stops other GUI nodes (buttons etc) responding to it.
        event.consume();
    }

    /**
     * Draw the game on the canvas.
     */
    public void drawGame(Level chosenLevel) {
        // Get the Graphic Context of the canvas. This is what we draw on.
        GraphicsContext gc = canvas.getGraphicsContext2D();
        scoreDisplay.setText("SCORE: " + Integer.toString(character.getScore()));
        timeDisplay.setText("TIME: " + Integer.toString(chosenLevel.getTimeToComplete()));


        // Clear canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Set the background to gray.
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw row of dirt images
        // We multiply by the cell width and height to turn a coordinate in our grid into a pixel coordinate.
        // We draw the row at y value 2.

        chosenLevel.initialiseLevel(gc);

        // Draw player at current location
        gc.drawImage(playerImage, playerX * GRID_CELL_WIDTH, playerY * GRID_CELL_HEIGHT);
    }

    /**
     * Reset the player's location and move them back to (0,0).
     */
    public void resetPlayerLocation(Level currentLevel) {
        character.setCharacterX(0);
        character.setCharacterY(0);
        drawGame(currentLevel);
    }

    /**
     * Move the player to roughly the center of the grid.
     */
    public void movePlayerToCenter(Level currentLevel) {
        // We just move the player to cell (5, 2)
        character.setCharacterX(5);
        character.setCharacterY(2);
        drawGame(currentLevel);
    }

    /**
     * This method is called periodically by the tick timeline
     * and would for, example move, perform logic in the game,
     * this might cause the bad guys to move (by e.g., looping
     * over them all and calling their own tick method).
     */
    public void tick(Level currentLevel) {
        // Move the character.
        character.moveCharacter(keyPressed, gridWidth, gridHeight);

        // Reset key pressed so that new movement is read or character doesn't move.
        keyPressed = " ";
        playerX = character.getCharacterX();
        playerY = character.getCharacterY();

        // Redraw the game with new X, Y.
        drawGame(currentLevel);
    }

    /**
     * Method to tick the clock down per second.
     * @param currentLevel the current level.
     */
    private void clockTick(Level currentLevel) {
        // get the current time to complete.
        int time = currentLevel.getTimeToComplete();

        // check the time/game state validity.
        if (time == 0) {
            gameOver = true;
        } else if (!gameOver){
            // decrease time to complete by 1.
            currentLevel.setTimeToComplete();
        }
        // redraw the graphics in current state.
        drawGame(currentLevel);
    }

    /**
     * Create the GUI.
     * @return The panel that contains the created GUI.
     */
    private Pane buildGUI(Level currentLevel) {
        // Create top-level panel that will hold all GUI nodes.
        BorderPane root = new BorderPane();
        // Create the canvas that we will draw on.
        // We store this as a gloabl variable so other methods can access it.
        canvas = new Canvas(canvasWidth, canvasHeight);
        root.setCenter(canvas);

        // Create a toolbar with some nice padding and spacing
        HBox toolbar = new HBox();
        toolbar.setSpacing(10);
        toolbar.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(toolbar);

        scoreDisplay.setFont(Font.font("Impact", 18));
        toolbar.getChildren().add(scoreDisplay);

        timeDisplay.setFont(Font.font("Impact", 18));
        toolbar.getChildren().add(timeDisplay);


        // Create the toolbar content
        // Return to main menu
        Button returnToMenu = new Button("Return to main menu");
        toolbar.getChildren().add(returnToMenu);

        returnToMenu.setOnAction(this::returnToMenu);

        // Tick Timeline buttons
        /**
        Button startTickTimelineButton = new Button("Play");
        Button stopTickTimelineButton = new Button("Pause");
        // We add both buttons at the same time to the timeline (we could have done this in two steps).
        toolbar.getChildren().addAll(startTickTimelineButton, stopTickTimelineButton);
        // Stop button is disabled by default
        stopTickTimelineButton.setDisable(true);

        // Setup the behaviour of the buttons.
        startTickTimelineButton.setOnAction(e -> {
            // Start the tick timeline and enable/disable buttons as appropriate.
            startTickTimelineButton.setDisable(true);
            tickTimeline.play();
            clockTimeline.play();
            stopTickTimelineButton.setDisable(false);
        });

        stopTickTimelineButton.setOnAction(e -> {
            // Stop the tick timeline and enable/disable buttons as appropriate.
            stopTickTimelineButton.setDisable(true);
            tickTimeline.stop();
            clockTimeline.stop();
            startTickTimelineButton.setDisable(false);
        });
         */

        // Setup a draggable image.
        ImageView draggableImage = new ImageView();
        draggableImage.setImage(iconImage);
        toolbar.getChildren().add(draggableImage);

        // This code allows the canvas to receive a dragged object within its bounds.
        // You probably don't need to change this (unless you wish to do more advanced things).
        canvas.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                // Mark the drag as acceptable if the source was the draggable image.
                // (for example, we don't want to allow the user to drag things or files into our application)
                if (event.getGestureSource() == draggableImage) {
                    // Mark the drag event as acceptable by the canvas.
                    event.acceptTransferModes(TransferMode.ANY);
                    // Consume the event. This means we mark it as dealt with.
                    event.consume();
                }
            }
        });

        // Finally, return the border pane we built up.
        return root;
    }

    public void setGridWidth(int gridWidth) {
        this.gridWidth = gridWidth;
    }

    public void setGridHeight(int gridHeight) {
        this.gridHeight = gridHeight;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public static void main(String[] args) throws FileNotFoundException {
        LevelController main = new LevelController();

        System.out.println("Actual height and width: ");
        System.out.println(main.gridHeight);
        System.out.println(main.gridWidth);

        System.out.println("Auxiliary level attributes: ");
        System.out.print("Highscores: ");
        for (int score : main.testLevel.getHighscores()) {
            System.out.print(score + " ");
        }
        System.out.println();
        System.out.println("Time to complete: " + main.testLevel.getTimeToComplete());
        System.out.println("Diamonds to win: " + main.testLevel.getDiamondsToWin());
        System.out.println("Amoeba spread rate: " + main.testLevel.getAmoebaSpreadRate());
        System.out.println("Amoeba max size: " + main.testLevel.getAmoebaMaxSize());
    }

}