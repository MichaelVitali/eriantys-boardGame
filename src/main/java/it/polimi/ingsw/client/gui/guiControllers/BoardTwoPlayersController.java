package it.polimi.ingsw.client.gui.guiControllers;

import it.polimi.ingsw.controller.message.GameMessage;
import it.polimi.ingsw.controller.message.Message;
import it.polimi.ingsw.controller.message.PlayAssistantMessage;
import it.polimi.ingsw.controller.message.PlayerMessage;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

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

    public void myEntrance0click() {
        studentMoved = 0;
        state = 1;
    }
    public void myEntrance1click() {
        studentMoved = 1;
        state = 1;
    }
    public void myEntrance2click() {
        studentMoved = 2;
        state = 1;
    }
    public void myEntrance3click() {
        studentMoved = 3;
    }
    public void myEntrance4click() {
        studentMoved = 4;
        state = 1;
    }
    public void myEntrance5click() {
        studentMoved = 5;
        state = 1;
    }
    public void myEntrance6click() {
        studentMoved = 6;
        state = 1;
    }
    public void myEntrance7click() {
        studentMoved = 7;
        state = 1;
    }
    public void myEntrance8click() {
        studentMoved = 8;
        state = 1;
    }

}
