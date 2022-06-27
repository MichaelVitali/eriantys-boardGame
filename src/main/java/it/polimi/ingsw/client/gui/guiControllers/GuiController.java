package it.polimi.ingsw.client.gui.guiControllers;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.controller.message.GameMessage;
import it.polimi.ingsw.controller.message.Message;
import it.polimi.ingsw.controller.message.SetupMessage;
import it.polimi.ingsw.observer.Observer;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class GuiController implements Initializable, Observer<Message> {
    private Client client;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public void terminate() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/terminatorScene.fxml"));
                try {
                    Parent root = loader.load();
                    GuiController terminatorController = loader.getController();
                    getClient().removeObserver(GuiController.this);
                    terminatorController.setStage(getStage());
                    terminatorController.setScene(new Scene(root));
                    terminatorController.setRoot(root);
                    getClient().addObserver(terminatorController);
                    terminatorController.setClient(getClient());
                    terminatorController.getStage().setScene(terminatorController.getScene());
                    terminatorController.getStage().show();
                    terminatorController.getStage().centerOnScreen();
                    /*Platform.exit();
                    System.exit(0);*/
                } catch (IOException e) {
                    Platform.exit();
                    System.exit(1);
                }
                }
            }
        );
    }
}
