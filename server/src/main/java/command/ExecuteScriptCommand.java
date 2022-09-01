package command;

import stateHandle.ExecuteState;
import routes.Routes;
import transfer.PrintToClient;
import transfer.States;

import java.io.File;
import java.io.IOException;

/**
 * Команда исполнения
 */
public class ExecuteScriptCommand extends CommandsWithArgument<File> {
    public ExecuteScriptCommand(Routes routes) {
        super(routes);
        argument_name = "file_name";
    }


   /* @Override
    public void executeStart() throws IOException, ClassNotFoundException {
        ExecuteState condition= new ExecuteState(argument);
        PrintToClient.ConditionSend(condition);
        PrintToClient.getBuf().States();


    }*/

    @Override
    public States executeStart() throws IOException, ClassNotFoundException {
        States states=new States();
        ExecuteState condition= new ExecuteState(argument);
        states.ConditionSend(condition);
        return states;

    }

    @Override
    public String ru_description() {
        return "считать и исполнить скрипт из указанного файла.";
    }
}
