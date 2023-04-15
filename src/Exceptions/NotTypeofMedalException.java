package Exceptions;

public class NotTypeofMedalException extends Exception {
    public NotTypeofMedalException(){
        super("El tipo de medalla no existe");
    }
}
