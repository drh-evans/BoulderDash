package Game_Logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


/**
 * A class which holds the methods that control menu.
 * @author Adam Bartlett
 */
public class MenuController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void openCreatePlayer(ActionEvent event) throws IOException {
        //Loads new fxml
        Parent root = FXMLLoader.load(getClass().getResource("CreatePlayer.fxml"));
        //Removes old and creates a new scene
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void openSelectPlayer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SelectPlayer.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void openHighScoreTable(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HighScoreTable.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
