package it.polimi.ingsw.client.gui;

import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiClient extends Application {

    private String ip;
    private int port;
    private boolean configurationDone = false;
    private boolean active = true;
    private int playerId = 0;
    /*private DisplayedBoard actualBoard;
/*
    public GuiClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    /*
    private String ip;
    private int port;
    private boolean configurationDone = false;
    private boolean active = true;
    private int playerId = 0;
    private DisplayedBoard actualBoard;

    public ClientGui(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public synchronized boolean isActive(){
        return active;
    }

    public synchronized void setActive(boolean active){
        this.active = active;
    }

    public Thread asyncReadFromSocket(final ObjectInputStream socketIn){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isActive()) {
                        Object inputObject = socketIn.readObject();
                        if(inputObject instanceof String) {
                            // initial configuration
                        } else if (inputObject instanceof DisplayedBoard){
                            actualBoard = ((DisplayedBoard) inputObject);
                            if(!configurationDone) {
                                configurationDone = true;
                                playerId = actualBoard.getPlayerId();
                            }
                        } else {
                            throw new IllegalArgumentException();
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    setActive(false);
                }
            }
        });
        t.start();
        return t;
    }

    public Thread asyncWriteToSocket(final ObjectOutputStream socketOut){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isActive()) {

                        socketOut.flush();
                        socketOut.reset();
                    }
                } catch(Exception e) {
                    setActive(false);
                }
            }
        });
        thread.start();
        return thread;
    }

    public void run() throws Exception {
        Socket socket = new Socket(ip, port);
        System.out.println("Connection established");
        ObjectOutputStream socketOut = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream socketIn = new ObjectInputStream(socket.getInputStream());

        try{
            Thread t0 = asyncReadFromSocket(socketIn);
            Thread t1 = asyncWriteToSocket(socketOut);
            t0.join();
            t1.join();
        } catch(InterruptedException | NoSuchElementException e) {
            System.out.println("Connection closed from the client side");
        } finally {
            socketIn.close();
            socketOut.close();
            socket.close();
        }
    }
    */

    @Override
    public void start(Stage stage) throws IOException {
        /*Parent root = FXMLLoader.load(getClass().getResource("/home/enrico/IdeaProjects/ing-sw-2022-Vitali-Tacca-Simionato/ing-sw-2022-Vitali-Tacca-Simionato/ing-sw-2022-Vitali-Tacca-Simionato/src/main/java/it/polimi/ingsw/client/gui/startingWindow.fxml"));*/


        Scene scene = getStartingScene();

        stage.setTitle("Eriantys");

        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) { launch(); }

    public Scene getStartingScene() {

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10,10,10,10));
        VBox centerBox = new VBox(5);
        Label label = new Label("Insert your nickname");
        centerBox.getChildren().add(label);
        TextField nicknameBucket = new TextField("nickname");
        nicknameBucket.setMaxSize(100, 20);
        centerBox.getChildren().add(nicknameBucket);
        Button confirmNickButton = new Button("Confirm");
        centerBox.getChildren().add(confirmNickButton);
        centerBox.setAlignment(Pos.CENTER);
        root.setCenter(centerBox);
        Scene scene = new Scene(root, 500, 600);
        //scene.getStylesheets().addAll(this.getClass().getResource("startingBackground.css").toExternalForm());
        return scene;
    }
}
