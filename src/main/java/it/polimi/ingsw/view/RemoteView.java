package it.polimi.ingsw.view;

import it.polimi.ingsw.controller.message.*;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.server.ClientConnection;

public class RemoteView extends View {
    private String playerNickname;
    private ClientConnection clientConnection;
    private PlayerMessage playerMessage;
    private boolean onIslandMessage;
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
        GameMessage message = null;
        if (model.getPlayer(super.getPlayerId()).isError()) {
            message = new ErrorMessage(model, super.getPlayerId());
            model.getPlayer(super.getPlayerId()).setError(false);
        } else if (model.getRound().getRoundState() == 0)
            message = new PostPlayAssistantMessage(model, super.getPlayerId());
        else if (model.getRound().getRoundState() == 1 && model.getRound().getPreviousState() == 0)
            message = new PostPlayAssistantMessage(model, super.getPlayerId());
        else if (model.getRound().getRoundState() == 1 && model.getRound().getPreviousState() == 3)
            message = new PostGetStudentsFromCloudsMessage(model, super.getPlayerId());
        else if (model.getRound().getRoundState() == 1 && !onIslandMessage)
            message = new PostAddStudentOnTableMessage(model, super.getPlayerId());
        else if (model.getRound().getRoundState() == 1 && onIslandMessage)
            message = new PostAddStudentOnIslandMessage(model, super.getPlayerId());
        else if (model.getRound().getRoundState() == 2)
            message = new PostChangeMotherNaturePosition(model, super.getPlayerId());
        else if (model.getRound().getRoundState() == 3)
            message = new PostChangeMotherNaturePosition(model, super.getPlayerId());
        else
            System.out.println("Problema penso, non esiste alcun messaggio adatto all'invio (RemoteView)");
            //message = new GameMessage(model, super.getPlayerId());
        if (message != null)
            clientConnection.asyncSend(message);
    }

    void handleMove(PlayerMessage message) {
        playerMessage = message;
        message.setPlayerId(super.getPlayerId());
        if (message instanceof AddStudentOnIslandMessage)
            onIslandMessage = true;
        else if (message instanceof AddStudentOnTableMessage)
            onIslandMessage = false;
        notify(message);
    }
}
