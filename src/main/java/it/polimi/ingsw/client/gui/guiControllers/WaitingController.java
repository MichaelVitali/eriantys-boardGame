package it.polimi.ingsw.client.gui.guiControllers;

import it.polimi.ingsw.ClientApp;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.gui.GuiClient;
import it.polimi.ingsw.controller.message.ConnectionState;
import it.polimi.ingsw.controller.message.GameMessage;
import it.polimi.ingsw.controller.message.Message;
import it.polimi.ingsw.controller.message.SetupMessage;
import it.polimi.ingsw.model.Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WaitingController extends GuiController {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void update(Message message) {
        if (message instanceof GameMessage){
            Platform.runLater(new Runnable() {
                                  @Override
                                  public void run() {
                                      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/boardSceneTwoPlayers.fxml"));
                                      try {
                                          Parent root = loader.load();
                                          GuiController boardController = loader.getController();
                                          getClient().removeObserver(WaitingController.this);
                                          boardController.setStage(getStage());
                                          boardController.setScene(new Scene(root));
                                          boardController.setRoot(root);
                                          getClient().addObserver(boardController);
                                          boardController.setClient(getClient());
                                          boardController.getStage().setScene(boardController.getScene());
                                          boardController.getStage().show();
                                      } catch(IOException e) {
                                          //Errore, spero non capiti
                                      }
                                  }
                              }
            );
        }
    }
}
