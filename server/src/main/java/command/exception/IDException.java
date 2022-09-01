package command.exception;

import command.RemoveCommand;
import command.UpdateCommand;

/**
 * Обшибки связанные с некоторректным выбором id
 * @see RemoveCommand
 * @see UpdateCommand
 * @version 1.0
 */
public class IDException extends RuntimeException{
    public IDException(String message){
        super(message);
    }

}
