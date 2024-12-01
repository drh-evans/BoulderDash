import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Sample application that demonstrates the use of JavaFX Canvas for a Game.
 * This class is intentionally not structured very well. This is just a starting point to show
 * how to draw an image on a canvas, respond to arrow key presses, use a tick method that is
 * called periodically, and use drag and drop.
 *
 * Do not build the whole application in one file. This file should probably remain very small.
 *
 * @author Liam O'Reilly
 */
public class Main extends Application {

    /**
     * Loads the main menu
     * @param stage The stage that is to be used for the application.
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Main Menu!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}