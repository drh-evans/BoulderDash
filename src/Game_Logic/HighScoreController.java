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
import java.io.FileNotFoundException;
import java.io.IOException;

public class HighScoreController {

    @FXML
    private ListView<String> highScoresList;
    @FXML
    private TilePane levelButtons;


    @FXML
    public void initialize() {
        addLevelButtons();
    }

    private void addLevelButtons() {
        String [] levelFiles = {"Level1.txt", "Level2.txt", "Level3.txt", "Level4.txt", "Level5.txt", "Level6.txt",
                                "Level7.txt", "Level8.txt" };

        for (String levelFile : levelFiles) {
            Button levelButton = new Button(levelFile.replace(".txt", ""));
            levelButton.setOnAction(event -> showTop10HighScores(levelFile));
            levelButtons.getChildren().add(levelButton);
        }
    }

    private void showTop10HighScores(String levelFile) {
        try {
            Level level = new Level("src/LevelFiles/" + levelFile);
            int [] highScores = level.getHighscores();
            highScoresList.getItems().clear();
            for (int i = 0;i < highScores.length;i++) {
                highScoresList.getItems().add((i + 1) + ": " + highScores[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Returns user back to main menu
     * @param e If an event occurs
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
