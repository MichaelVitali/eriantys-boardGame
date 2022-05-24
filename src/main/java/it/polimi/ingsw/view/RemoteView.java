package it.polimi.ingsw.view;

import it.polimi.ingsw.controller.message.GameMessage;
import it.polimi.ingsw.controller.message.PlayerMessage;
import it.polimi.ingsw.model.Game;
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
    public RemoteView(int playerId, String playerNickname, ClientConnection clientConnection) {
        super(playerId);
        this.playerNickname = playerNickname;
        this.clientConnection = clientConnection;
        clientConnection.addObserver(new MessageReceiver());
    }

    @Override
    public void update(Game model) {
        GameMessage board = new GameMessage(model, super.getPlayerId());
        clientConnection.asyncSend(board);
    }

    void handleMove(PlayerMessage message) {
        message.setPlayerId(super.getPlayerId());
        notify(message);
    }
}
