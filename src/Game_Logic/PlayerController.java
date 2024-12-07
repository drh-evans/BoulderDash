package Game_Logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class PlayerController {
    String name;
    @FXML
    private Label myLabel;
    @FXML
    private TextField myTextField;
    @FXML
    private Button myButton;
    private ArrayList<String> names = new ArrayList<>();
    @FXML
    private ListView<String> playerList;
    @FXML
    private Button selectButton;;
    @FXML
    private Label statusLabel;
    private Player currentPlayer;
    @FXML
    private LevelController levelController;

    /**
     * Allows user to create a player
     */
    @FXML
    public void submit(ActionEvent event) {
        //names = new ArrayList<>();
        readArray();
        name = myTextField.getText();
        if (names.contains(name)) {
            myLabel.setText("The name has already been entered. Try again!");
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(5), myLabel);
            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.0);
            fadeTransition.setOnFinished(e -> myLabel.setText(""));
            fadeTransition.play();

        } else {
            names.add(name);
            writeNames();
            levelController.createLevel1(event);
        }
    }

    /**
     * Reads the players that have already been created
     */
    public void readArray() {
        try {
            File players = new File("src/LevelFiles/Players.txt");
            Scanner in = new Scanner(players);
            in.useDelimiter(",");
            while (in.hasNext()) {
                String name = in.next();
                names.add(name);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



    /**
     * Adds names from player array to a txt file containing their names
     */
    public void writeNames() {
        try {
            FileWriter myWriter = new FileWriter("src/LevelFiles/Players.txt");
            for (String name : names) {
                myWriter.write(name + ",");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
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

    public void initialize() {
        if (playerList != null) {
            readArray();
            playerList.getItems().addAll(names);
        }


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Levels.fxml"));
            AnchorPane levelContent = loader.load();
            levelController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void selectProfile(ActionEvent event) {
        String selectedProfile = playerList.getSelectionModel().getSelectedItem();
        if (selectedProfile != null) {
            currentPlayer = new Player("adam",1 );
            returnToMenu(event);
        } else {
            statusLabel.setText("Please select a profile.");
        }
    }

}
