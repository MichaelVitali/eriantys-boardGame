package it.polimi.ingsw.client.gui.guiControllers;

import it.polimi.ingsw.controller.message.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class BoardTwoPlayersController extends GuiController {

    private GameMessage board;

    @FXML
    Circle myEntrance0;
    @FXML
    Circle myEntrance1;
    @FXML
    Circle myEntrance2;
    @FXML
    Circle myEntrance3;
    @FXML
    Circle myEntrance4;
    @FXML
    Circle myEntrance5;
    @FXML
    Circle myEntrance6;
    @FXML
    Circle myEntrance7;
    @FXML
    Circle myEntrance8;

    @FXML
    Circle entrance0;
    @FXML
    Circle entrance1;
    @FXML
    Circle entrance2;
    @FXML
    Circle entrance3;
    @FXML
    Circle entrance4;
    @FXML
    Circle entrance5;
    @FXML
    Circle entrance6;
    @FXML
    Circle entrance7;
    @FXML
    Circle entrance8;

    ImageView assistantImage;

    private int myPlayerId;
    private int studentMoved;
    private int state;

    public GameMessage getBoard() {
        return board;
    }

    public void setBoard(GameMessage board) {
        this.board = board;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void update(Message message) {

    }

    public void sendAssistantMessage() {
        //getClient().asyncWriteToSocket(new PlayAssistantMessage(myPlayerId));
    }

    public void assistantClick(ActionEvent event) {
        switch(((AnchorPane) event.getSource()).getId()) {
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
        switch(((Circle) event.getSource()).getId()) {
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

    public void myTablesClick(ActionEvent event) {
        if (state == 1) {
            getClient().asyncWriteToSocket(new AddStudentOnTableMessage(myPlayerId, studentMoved));
        }
        state = 0;
    }

    public void islandClick(ActionEvent event) {
        if (state == 1) {
            getClient().asyncWriteToSocket(new AddStudentOnTableMessage(myPlayerId, studentMoved));
        }
    }

    public void showAssistant(ActionEvent event) {
        /*String idAssistant = ((Circle) event.getSource()).getId();
        String imagePath = "/images/Assistant/" + idAssistant;
        System.out.println(imagePath);
        Image assistantImage = new Image(imagePath);*/
        //System.out.println("CIAO BELLO");
    }
}
