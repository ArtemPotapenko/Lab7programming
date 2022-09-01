package command;

import routes.Routes;
import transfer.PrintToClient;
import transfer.States;

import java.io.File;
import java.io.IOException;

/**
 * Закрыть программу
 * @version 1.1
 */
public class ExitCommand extends Commands{
    public ExitCommand(Routes routes) {
        super(routes);
    }

    /*@Override
    public void executeStart() throws IOException {
        PrintToClient.exit();
        PrintToClient.getBuf().States();

    }*/

    @Override
    public States executeStart() throws IOException, ClassNotFoundException {
        States states=new States();
        states.exit();
        return states;
    }

    @Override
    public String ru_description() {
        return "завершить программу (без сохранения в файл)";
    }
}
