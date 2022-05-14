package it.polimi.ingsw;

import it.polimi.ingsw.client.ClientCli;

import java.io.IOException;
public class ClientApp {
    public static void main(String[] args){
        if (args.length >= 1) {
            if (args[0].equals("cli")) {
                ClientCli client = new ClientCli("127.0.0.1", 50001);
                try {
                    client.run();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                }
            } else if (args[0].equals("gui")) {
                // We launch the java fx app
            } else {
                System.out.println("The option is not valid. Input format : ./file.class MODE\nMODE : { CLI to play by command line - GUI to play by graphic user interface }");
            }
        } else {
            System.out.println("The input format is wrong. Input format : ./file.class MODE\nMODE : { CLI to play by command line - GUI to play by graphic user interface }");
        }
    }
}
