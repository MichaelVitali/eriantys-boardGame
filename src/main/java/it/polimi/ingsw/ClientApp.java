package it.polimi.ingsw;

import it.polimi.ingsw.client.cli.ClientCli;
import it.polimi.ingsw.client.gui.GuiClient;
import javafx.application.Application;

import java.io.IOException;
public class ClientApp {

    public static void main(String[] args){
        if (args.length >= 1) {
            if (args[0].equals("cli")) {
                ClientCli client = new ClientCli("127.0.0.1", 50001);
                try {
                    client.run();
                } catch (IOException e) {
                    System.out.println("The server is currently not running");
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                }
            } else if (args[0].equals("gui")) {
                /*ClientGui client = new ClientGui("127.0.0.1", 50001);
                Application.launch(client);
                Application.launch(GuiClient.class);*/
            } else {
                System.out.println("The option is not valid. Input format : ./file.class MODE\nMODE : { CLI to play by command line - GUI to play by graphic user interface }");
            }
        } else {
            System.out.println("The input format is wrong. Input format : ./file.class MODE\nMODE : { CLI to play by command line - GUI to play by graphic user interface }");
        }
    }

}
