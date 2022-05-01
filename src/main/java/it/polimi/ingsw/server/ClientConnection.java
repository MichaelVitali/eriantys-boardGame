package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.message.PlayerMessage;
import it.polimi.ingsw.observer.Observer;

public interface ClientConnection {

    void closeConnection();

    void addObserver(Observer<PlayerMessage> observer);

    public void send(Object message);

    void asyncSend(Object message);
}
