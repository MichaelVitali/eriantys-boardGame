package it.polimi.ingsw.Model.exception;

public class OutOfBoundException extends Exception{
    private String error;

    public OutOfBoundException(String error){
        this.error = error;
    }
}
