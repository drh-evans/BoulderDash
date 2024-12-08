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

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.animation.FadeTransition;
import javafx.util.Duration;

/**
 * This class controls the creation,selection and deletion of a player profile.
 *
 * @Author Adam Bartlett, Elbu Cheung, Mohsin Lebdah
 */

public class PlayerController {
    private static Player currentPlayer;
    String name;
    @FXML
    private Label myLabel;
    @FXML
    private TextField myTextField;
    @FXML
    private Button deleteButton;
    private ArrayList<String> names = new ArrayList<>();
    @FXML
    private ListView<String> playerList;
    ;
    @FXML
    private Button selectButton;
    @FXML
    private Label statusLabel;
    @FXML
    private LevelController levelController;

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * A button a user presses to create a profile.
     *
     * @param event When the button is pushed.
     */
    @FXML
    public void submit(ActionEvent event) {
        name = myTextField.getText();

        if (nameFound(name)) {
            // Stop player creating a profile with the same name
            myLabel.setText("The name has already been entered. Try again!");
            // Fade animation for warning text
            FadeTransition fadeTransition =
                    new FadeTransition(Duration.seconds(5), myLabel);
            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.0);
            fadeTransition.setOnFinished(e -> myLabel.setText(""));
            fadeTransition.play();

        } else {
            // Adds player name
            names.add(name + " 1");
            writeNames();
            returnToMenu(event);
        }
    }

    /**
     * Find if a players name has already been created.
     *
     * @param usersName The users inputted name.
     * @return If users name has already been created.
     */
    public Boolean nameFound(String usersName) {
        readArray();
        for (String name : names) {
            String playerName = name.split(" ")[0];

            if (playerName.equals(usersName)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Stage to display all levels.
     *
     * @param event A button is pressed by a user.
     */
    public void loadLevels(ActionEvent event) {
        Parent menu = null;
        try {
            // loads menu fxml file
            menu = FXMLLoader.load(getClass().getResource("Levels.fxml"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        // get present stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // set new stage
        stage.setScene(new Scene(menu));
        stage.show();
    }

    /**
     * Reads the players that have already been created.
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
     * Adds players information to a txt file.
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
     * Returns user back to main menu.
     *
     * @param event If an event occurs.
     */
    public void returnToMenu(ActionEvent event) {
        Parent menu = null;
        try {
            // loads menu fxml file
            menu = FXMLLoader.load(getClass()
                    .getResource("Menu.fxml"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        // get present stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene()
                .getWindow();
        // set new stage
        stage.setScene(new Scene(menu));
        stage.show();
    }

    /**
     * Initializes the playerList.
     */
    public void initialize() {
        // Clearing data from Players.txt
        if (playerList != null) {
            names.clear();
            playerList.getItems().clear();
            //reading the profiles from Players.txt
            readArray();
            //populates the names list and playerList
            playerList.getItems().addAll(names);
        }


        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("Levels.fxml"));
            AnchorPane levelContent = loader.load();
            levelController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Allows a user to select their desired profile.
     * @param event A button is pressed.
     */
    public void selectProfile(ActionEvent event) {
        //Players selection
        String selectedProfile = playerList.getSelectionModel()
                .getSelectedItem();
        if (selectedProfile != null) {
            //Splits players information into name and level unlocked
            String[] playerDetails = selectedProfile.split(" ");
            String name = playerDetails[0];
            int levelUnlocked = Integer.parseInt(playerDetails[1]);
            //Creates a new player
            currentPlayer = new Player(name, levelUnlocked);
            levelController.setPlayer(currentPlayer);
            loadLevels(event);
        } else {
            statusLabel.setText("Please select a profile.");
        }
    }

    /**
     * Allows the User to delete a profile.
     *
     * @param event If an event occurs.
     */
    @FXML
    public void deleteProfile(ActionEvent event) {
        String selectedProfile = playerList.getSelectionModel()
                .getSelectedItem();
        if (selectedProfile != null) {
            names.removeIf(name -> name.equals(selectedProfile));

            writeNames();
            returnToMenu(event);
        }
    }
}

