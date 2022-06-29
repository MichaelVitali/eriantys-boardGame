package it.polimi.ingsw;

import it.polimi.ingsw.client.cli.ClientCli;
import it.polimi.ingsw.client.gui.GuiClient;
import javafx.application.Application;

import java.io.IOException;
import java.util.Scanner;

public class ClientApp {

    public static String ip = "127.0.0.1";
    public static int port = 50000;

    public static void main(String[] args){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        ip = args[1];
        if (args.length >= 1) {
            if (args[0].equals("cli")) {
                ClientCli client = new ClientCli(ip, port);
                Scanner stdin = new Scanner(System.in);
                String input = stdin.nextLine();
                while(input.equals("\n")) {
                    System.out.println("Press Enter to continue...");
                    input = stdin.nextLine();
                }
                System.out.print("\033[H\033[2J");
                System.out.flush();
                try {
                    client.run();
                } catch (IOException e) {
                    System.out.println("The server is currently not running");
                    System.err.println(e.getMessage());
                    //e.printStackTrace();
                }
            } else if (args[0].equals("gui")) {
                Application.launch(GuiClient.class);
            } else {
                System.out.println("The option is not valid. Input format : ./file.class MODE\nMODE : { CLI to play by command line - GUI to play by graphic user interface }");
            }
        } else {
            System.out.println("The input format is wrong. Input format : ./file.class MODE\nMODE : { CLI to play by command line - GUI to play by graphic user interface }");
        }
    }
}
