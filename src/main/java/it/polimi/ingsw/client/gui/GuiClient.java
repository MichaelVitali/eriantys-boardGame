package it.polimi.ingsw.client.gui;

import it.polimi.ingsw.ClientApp;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.gui.guiControllers.GuiController;
import it.polimi.ingsw.client.gui.guiControllers.LoginController;
import it.polimi.ingsw.controller.message.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;

public class GuiClient extends Application {

    private Client client;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/loginScene.fxml"));
        Parent root = loader.load();
        stage.setTitle("Eriantys");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        Image logo = new Image("/images/BLLoghi.png", 100, 300, true, false);
        stage.getIcons().add(logo);
        GuiController loginController = loader.getController();
        loginController.setStage(stage);
        loginController.setScene(scene);
        loginController.setRoot(root);

        client = loginController.getClient();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                /*if (getClient() != null)
                    getClient().asyncWriteToSocket(new TerminatorMessage("I wanna leave"));*/
                Platform.exit();
                System.exit(0);
            }
        });
        stage.show();
    }

    public Client getClient() {
        return client;
    }
}
