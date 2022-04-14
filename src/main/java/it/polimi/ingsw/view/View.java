package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.message.GameMessage;
import it.polimi.ingsw.model.message.PlayerMessage;
import it.polimi.ingsw.observer.Observable;
import it.polimi.ingsw.observer.Observer;

public abstract class View extends Observable<PlayerMessage> implements Observer<GameMessage> {

    private int playerId;
    public View(int playerId){
        this.playerId = playerId;
    }

    public int getPlayer(){
        return playerId;
    }

    public abstract void update(GameMessage gameMessage);

    void handleMove(PlayerMessage message) {
        notify(message);
    }
}