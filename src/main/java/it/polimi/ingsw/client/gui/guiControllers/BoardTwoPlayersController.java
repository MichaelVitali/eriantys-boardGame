package it.polimi.ingsw.client.gui.guiControllers;

import it.polimi.ingsw.controller.message.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardTwoPlayersController extends GuiController {

    private GameMessage board;

    @FXML
    Button myEntrance0;
    @FXML
    Button myEntrance1;
    @FXML
    Button myEntrance2;
    @FXML
    Button myEntrance3;
    @FXML
    Button myEntrance4;
    @FXML
    Button myEntrance5;
    @FXML
    Button myEntrance6;
    @FXML
    Button myEntrance7;
    @FXML
    Button myEntrance8;


    @FXML
    ImageView entrance0;
    @FXML
    Label entrance1;
    @FXML
    Label entrance2;
    @FXML
    Label entrance3;
    @FXML
    Label entrance4;
    @FXML
    Label entrance5;
    @FXML
    Label entrance6;
    @FXML
    Label entrance7;
    @FXML
    Label entrance8;

    @FXML
    Button island0;
    /*@FXML
    Button island1;
    @FXML
    Button island2;
    @FXML
    Button island3;
    @FXML
    Button island4;
    @FXML
    Button island5;
    @FXML
    Button island6;
    @FXML
    Button island7;
    @FXML
    Button island8;
    @FXML
    Button island9;
    @FXML
    Button island10;
    @FXML
    Button island11;*/

    @FXML
    Button player2;
    @FXML
    Button player3;
    @FXML
    Button player4;

    private int myPlayerId;
    private int studentMoved;
    private int state;

    public GameMessage getBoard() {
        return board;
    }

    public void setBoard(GameMessage board) {
        this.board = board;
        myPlayerId = board.getPlayerId();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void adaptSceneToPlayers() {
        System.out.println("Siamo qui");
        if(board.getNumberOfPLayers() == 2) {
            player2.setVisible(false);
            player3.setVisible(false);
            player4.setVisible(false);
        } else if(board.getNumberOfPLayers() == 3) {
            player4.setVisible(false);
        }
        System.out.println("Ora qui");
    }

    public void displayBoard(int PlayerId) {
        entrance0.setImage(new Image("/images/BLLoghi.png"));
    }

    public void changeBoard(ActionEvent event) {
        switch(((Button) event.getSource()).getId()) {
            case "player2":
                displayBoard(2);
                break;
            case "player3":
                displayBoard(3);
                break;
            case "player4":
                displayBoard(4);
                break;
        }
    }
    @Override
    public void update(Message message) {

    }

    public void sendAssistantMessage() {
        //getClient().asyncWriteToSocket(new PlayAssistantMessage(myPlayerId));
    }

    @FXML
    public void assistantClick(ActionEvent event) {
        switch(((Button) event.getSource()).getId()) {
            case "myEntrance0":
                studentMoved = 0;
                state = 1;
                break;
            case "myEntrance1":
                studentMoved = 1;
                state = 1;
                break;
            case "myEntrance2":
                studentMoved = 2;
                state = 1;
                break;
            case "myEntrance3":
                studentMoved = 3;
                state = 1;
                break;
            case "myEntrance4":
                studentMoved = 4;
                state = 1;
                break;
            case "myEntrance5":
                studentMoved = 5;
                state = 1;
                break;
            case "myEntrance6":
                studentMoved = 6;
                state = 1;
                break;
            case "myEntrance7":
                studentMoved = 7;
                state = 1;
                break;
            case "myEntrance8":
                studentMoved = 8;
                state = 1;
                break;
        }
    }

    public void myEntranceClick(ActionEvent event) {
        System.out.println(((Button) event.getSource()).getId() + " pressed");
        switch(((Button) event.getSource()).getId()) {
            case "myEntrance0":
                studentMoved = 0;
                state = 1;
                break;
            case "myEntrance1":
                studentMoved = 1;
                state = 1;
                break;
            case "myEntrance2":
                studentMoved = 2;
                state = 1;
                break;
            case "myEntrance3":
                studentMoved = 3;
                state = 1;
                break;
            case "myEntrance4":
                studentMoved = 4;
                state = 1;
                break;
            case "myEntrance5":
                studentMoved = 5;
                state = 1;
                break;
            case "myEntrance6":
                studentMoved = 6;
                state = 1;
                break;
            case "myEntrance7":
                studentMoved = 7;
                state = 1;
                break;
            case "myEntrance8":
                studentMoved = 8;
                state = 1;
                break;
        }

        System.out.println("Student");
    }

    public void myTablesClick(ActionEvent event) {
        if (state == 1) {
            getClient().asyncWriteToSocket(new AddStudentOnTableMessage(myPlayerId, studentMoved));
        }
        state = 0;
    }

    public void islandClick(ActionEvent event) {
        int islandIndex = -1;
        System.out.println(((Button) event.getSource()).getId() + " pressed");
        switch(((Button) event.getSource()).getId()) {
            case "island0":
                islandIndex = 0;
                break;
            case "island1":
                islandIndex = 1;
                break;
            case "myEntrance2":
                islandIndex = 2;
                break;
            case "myEntrance3":
                islandIndex = 3;
                break;
            case "myEntrance4":
                islandIndex = 4;
                break;
            case "myEntrance5":
                islandIndex = 5;
                break;
            case "myEntrance6":
                islandIndex = 6;
                break;
            case "myEntrance7":
                islandIndex = 7;
                break;
            case "myEntrance8":
                islandIndex = 8;
                break;
            case "myEntrance9":
                islandIndex = 9;
                break;
            case "myEntrance10":
                islandIndex = 10;
                break;
            case "myEntrance11":
                islandIndex = 11;
                break;
        }
        if(islandIndex < 12 && islandIndex > -1) {
            if (state == 1) {
                getClient().asyncWriteToSocket(new AddStudentOnIslandMessage(myPlayerId, studentMoved, islandIndex));
                System.out.println("Porca troia funziona");
            }
        }
        System.out.println("mano");
    }
}
