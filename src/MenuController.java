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
    public void openLevels(ActionEvent event) throws IOException {
        //Loads new fxml
        Parent root = FXMLLoader.load(getClass().getResource("Levels.fxml"));
        //Removes old and creates a new scene
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
