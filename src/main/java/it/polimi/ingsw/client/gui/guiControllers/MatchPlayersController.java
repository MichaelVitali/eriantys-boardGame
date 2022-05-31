package it.polimi.ingsw.client.gui.guiControllers;

import it.polimi.ingsw.controller.message.ConnectionState;
import it.polimi.ingsw.controller.message.GameMessage;
import it.polimi.ingsw.controller.message.Message;
import it.polimi.ingsw.controller.message.SetupMessage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class MatchPlayersController extends GuiController{

    @FXML
    Label matchPlayersMessage;

    @FXML
    Circle entrance1;

    private int studentMoved;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void update(Message message) {
        if(message instanceof SetupMessage) {
            SetupMessage setupMessage = (SetupMessage) message;
            if (setupMessage.getConnectionState() == ConnectionState.NUMBEROFPLAYERS) {
                Platform.runLater(new Runnable() {
                                      @Override
                                      public void run() {matchPlayersMessage.setText(setupMessage.getMessage());
                                      }
                                  }
                );
            } else if (setupMessage.getConnectionState() == ConnectionState.SUCCESS) {
                if (message instanceof SetupMessage) {
                    Platform.runLater(new Runnable() {
                                          @Override
                                          public void run() {
                                              FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/waitingPlayersScene.fxml"));
                                              try {
                                                  Parent root = loader.load();
                                                  GuiController waitingController = loader.getController();
                                                  getClient().removeObserver(MatchPlayersController.this);
                                                  waitingController.setStage(getStage());
                                                  waitingController.setScene(new Scene(root));
                                                  waitingController.setRoot(root);
                                                  getClient().addObserver(waitingController);
                                                  waitingController.setClient(getClient());
                                                  waitingController.getStage().setScene(waitingController.getScene());
                                                  waitingController.getStage().show();
                                              } catch(IOException e) {
                                                  //Errore, spero non capiti
                                              }
                                          }
                                      }
                    );
                }

            } else {
                //Errore server
            }
        } else if (message instanceof GameMessage) {
            Platform.runLater(new Runnable() {
                                  @Override
                                  public void run() {
                                      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/boardSceneTwoPlayers.fxml"));
                                      try {
                                          Parent root = loader.load();
                                          GuiController boardController = loader.getController();
                                          getClient().removeObserver(MatchPlayersController.this);
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

    public void twoPlayers() {
        getClient().asyncWriteToSocket(new SetupMessage(ConnectionState.NUMBEROFPLAYERS, "2"));
    }

    public void threePlayers() {
        getClient().asyncWriteToSocket(new SetupMessage(ConnectionState.NUMBEROFPLAYERS, "3"));
    }

    public void fourPlayers() {
        getClient().asyncWriteToSocket(new SetupMessage(ConnectionState.NUMBEROFPLAYERS, "4"));
    }
}
