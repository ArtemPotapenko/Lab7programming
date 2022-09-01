package command;

import request.Request;
import routes.Routes;
import transfer.States;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ClientExitCommand extends Commands{
    ExitCommand exitCommand;
    SaveCommand saveCommand;

    /**
     * Конструктор для задания команды.
     *
     * @param routes Коллекция элементов
     */
    public ClientExitCommand(Routes routes) {
        super(routes);
        exitCommand=new ExitCommand(routes);
        saveCommand=new SaveCommand(routes);
    }

   /* @Override
    public void executeStart() throws IOException {
        saveCommand.executeStart();
        exitCommand.executeStart();

    }*/

    @Override
    public States executeStart() throws IOException, ClassNotFoundException {
        States states=saveCommand.executeStart();
        states.getList().addAll(exitCommand.executeStart().getList());
        return states;
    }

    @Override
    public States executeContinue(ArrayList<Request> list) {
        return States.getStates();
    }

    @Override
    public String ru_description() {
        return exitCommand.ru_description();
    }
}
