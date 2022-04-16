package it.polimi.ingsw.view;

import it.polimi.ingsw.model.message.GameMessage;
import it.polimi.ingsw.model.message.PlayerMessage;
import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.server.ClientConnection;

public class RemoteView extends View {
    private String playerNickname;
    private ClientConnection clientConnection;

    private class MessageReceiver implements Observer<PlayerMessage> {
        @Override
        public void update(PlayerMessage message) {
            try{
                handleMove(message);
            }catch(IllegalArgumentException | ArrayIndexOutOfBoundsException e){
                clientConnection.asyncSend("Error!");
            }
        }

    }
    public RemoteView(int id, String playerNickname, ClientConnection clientConnection) {
        super(id);
        this.playerNickname = playerNickname;
        this.clientConnection = clientConnection;
    }

    public void update(GameMessage gameMessage) {
        // cosa deve fare quando bisogna inviare un messaggio
    }
}
