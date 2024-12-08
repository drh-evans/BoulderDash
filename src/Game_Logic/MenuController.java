package Game_Logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * A class containing the functionality of opening different options
 * in the menu.
 *
 * @author Adam Bartlett, Ashan Mian
 */
public class MenuController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    /**
     * Opens stage for user to create name
     *
     * @param event button is press
     * @throws IOException If an error happens when processing the file.
     */
    @FXML
    public void openCreatePlayer(ActionEvent event) throws IOException {
        //Loads new fxml
        Parent root = FXMLLoader.load(getClass()
                .getResource("CreatePlayer.fxml"));
        //Removes old and creates a new scene
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Opens stage for user to select profile
     *
     * @param event button is press
     * @throws IOException If an error happens when processing the file.
     */
    @FXML
    public void openSelectPlayer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass()
                .getResource("SelectPlayer.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens stage for user to see high score table
     *
     * @param event button is press
     * @throws IOException If an error happens when processing the file.
     */
    @FXML
    public void openHighScoreTable(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass()
                .getResource("HighScoreTable.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens stage to display helpful information to player
     *
     * @param event button is press
     * @throws IOException If an error happens when processing the file.
     */
    @FXML
    public void openHelp(ActionEvent event) throws IOException {
        // Load the Help.fxml file
        Parent root = FXMLLoader.load(getClass().getResource("Help.fxml"));

        Stage popoutStage = new Stage();
        popoutStage.setTitle("Help Window");
        Scene scene = new Scene(root);
        popoutStage.setScene(scene);
        popoutStage.initModality(Modality.NONE);
        popoutStage.show();
    }

}
