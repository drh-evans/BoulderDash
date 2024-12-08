package Game_Logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class controlling the high score table.
 * @author Elbu Cheung.
 * @version 1.0.
 */
public class HighScoreController {
    @FXML
    private ListView<String> highScoresList;
    @FXML
    private TilePane levelButtons;

    /**
     * Initialize the level display buttons.
     */
    @FXML
    public void initialize() {
        addLevelButtons();
    }

    /**
     * Add the level buttons.
     */
    private void addLevelButtons() {
        String [] levels = {"Level1", "Level2", "Level3", "Level4", "Level5", "Level6",
                                "Level7", "Level8" };

        for (String level : levels) {
            Button levelButton = new Button(level);
            levelButton.setOnAction(event -> showTop10HighScores(level));
            levelButtons.getChildren().add(levelButton);
        }
    }

    /**
     * Display the scores for a level.
     * @param level The current level selected.
     */
    private void showTop10HighScores(String level) {
        try {
            File ScoresData = new File("src/LevelFiles/LevelHighScores.txt");
            Scanner in = new Scanner(ScoresData);;
            highScoresList.getItems().clear();

            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.startsWith(level.toLowerCase() + ";")) {
                    String[] levelNameAndData = line.split(";");
                    String[] data = levelNameAndData[1].split(",");

                    for (int i = 0;i < data.length;i++) {
                        String[] playerAndScore = data[i].split(":");
                        highScoresList.getItems().add((i + 1) + ": " + playerAndScore[0] + "  " + playerAndScore[1]);
                    }
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Return the user to the menu.
     * @param e The event of the back being activated.
     */
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
}
