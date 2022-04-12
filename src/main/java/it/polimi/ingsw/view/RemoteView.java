package it.polimi.ingsw.view;

import it.polimi.ingsw.model.message.GameMessage;
import it.polimi.ingsw.server.ClientConnection;

public class RemoteView extends View {
    private int id;
    private String playerNickname;
    private ClientConnection clientConnection;

    public RemoteView(int id, String playerNickname, ClientConnection clientConnection) {
        this.id = id;
        this.playerNickname = playerNickname;
        this.clientConnection = clientConnection;
    }

    public void update(GameMessage gameMessage) {

    }
}
