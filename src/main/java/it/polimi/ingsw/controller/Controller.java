package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.message.PlayerMessage;
import it.polimi.ingsw.observer.Observer;

public class Controller implements Observer<PlayerMessage> {
    private Game model;

    public Controller(Game model) {
        this.model = model;
    }

    private void performMove(PlayerMessage playerMessage) {
        playerMessage.performMove(model);
    }

    @Override
    public void update(PlayerMessage message) {
        performMove(message);
    }
}
