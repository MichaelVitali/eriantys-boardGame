package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.message.PlayerMessage;

public class Controller {
    Game model;

    private void performMove(PlayerMessage playerMessage) {
        playerMessage.performMove(model);
    }
}
