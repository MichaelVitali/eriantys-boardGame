package it.polimi.ingsw.client.gui.guiControllers;

import it.polimi.ingsw.ClientApp;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.gui.GuiClient;
import it.polimi.ingsw.controller.message.ConnectionState;
import it.polimi.ingsw.controller.message.Message;
import it.polimi.ingsw.controller.message.SetupMessage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends GuiController {

    @FXML
    private Label nicknameMessage;
    @FXML
    private TextField nicknameBucket;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setClient(new Client(ClientApp.ip, ClientApp.port));
            new Thread(getClient()).start();
            getClient().addObserver(this);
        } catch (Exception e) {
            System.out.println("Unable to connect to the server");
        }
    }

    @Override
    public void update(Message message) {
        if(message instanceof SetupMessage) {
            SetupMessage setupMessage = (SetupMessage) message;
            if (setupMessage.getConnectionState() == ConnectionState.LOGIN) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        nicknameMessage.setText(setupMessage.getMessage());
                    }
                }
                );
            } else if (setupMessage.getConnectionState() == ConnectionState.MATCHMODE) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/matchModeScene.fxml"));
                        try {
                            Parent root = loader.load();
                            GuiController modeController = loader.getController();
                            getClient().removeObserver(LoginController.this);
                            modeController.setStage(getStage());
                            modeController.setScene(new Scene(root));
                            modeController.setRoot(root);
                            getClient().addObserver(modeController);
                            modeController.setClient(getClient());
                            modeController.getStage().setScene(modeController.getScene());
                            modeController.getStage().show();
                        } catch(IOException e) {
                            //Errore, spero non capiti
                        }
                    }
                }
                );

            } else {
                //Errore server
            }
        } else {
            //Errore
        }
    }

    public void login() {
        String nickname = nicknameBucket.getText();
        getClient().asyncWriteToSocket(new SetupMessage(ConnectionState.LOGIN, nickname));
    }
}
