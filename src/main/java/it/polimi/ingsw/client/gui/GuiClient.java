package it.polimi.ingsw.client.gui;

import it.polimi.ingsw.ClientApp;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.gui.guiControllers.GuiController;
import it.polimi.ingsw.client.gui.guiControllers.LoginController;
import it.polimi.ingsw.controller.message.ConnectionState;
import it.polimi.ingsw.controller.message.GameMessage;
import it.polimi.ingsw.controller.message.Message;
import it.polimi.ingsw.controller.message.SetupMessage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;

public class GuiClient extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/loginScene.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginScene.fxml"));
        Parent root = loader.load();
        System.out.println("Ciao");
        stage.setTitle("Eriantys");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        GuiController loginController = loader.getController();
        loginController.setStage(stage);
        loginController.setScene(scene);
        loginController.setRoot(root);
        stage.show();
    }
}
