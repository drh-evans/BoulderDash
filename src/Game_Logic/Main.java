package Game_Logic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Launches the Game.
 * Activates the game and all of its graphical content and logic.
 * @author Rhys Evans, Adam Bartlett, Mohsin Lebdah, Nathan Welby, Davide Belsole,
 *         Haoyang Qian, Ahsan Mian.
 * @version 1.0
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