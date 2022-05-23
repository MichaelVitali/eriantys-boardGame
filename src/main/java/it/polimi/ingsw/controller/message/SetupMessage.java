package it.polimi.ingsw.controller.message;

public class SetupMessage {
    private ConnectionState connectionState;
    private String playerMessage;

    public SetupMessage(ConnectionState connectionState, String playerMessage) {
        this.connectionState = connectionState;
        this.playerMessage = playerMessage;
    }

    public ConnectionState getConnectionState() {
        return connectionState;
    }

    public String getMessage() {
        return playerMessage;
    }

    /**
     * Sets the type of the connection
     * @param connectionState
     */
    public void setConnectionState(ConnectionState connectionState) {
        this.connectionState = connectionState;
    }
}
