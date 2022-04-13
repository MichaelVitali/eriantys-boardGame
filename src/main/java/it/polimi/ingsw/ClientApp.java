package it.polimi.ingsw;

import it.polimi.ingsw.client.ClientCLI;

import java.io.IOException;

public class ClientApp
{
    public static void main(String[] args){
        if(args.length == 1) {
            ClientCLI client = new ClientCLI("127.0.0.1", 40000);
            try {
                client.run();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.out.println("The input format is wrong. Input format : ./file.class MODE\nMODE : { CLI to play by command line - GUI to play by graphic user interface }");
        }
    }
}
