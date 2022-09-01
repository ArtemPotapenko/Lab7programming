package command;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import parse.DatabaseOpen;
import routes.Routes;
import transfer.States;

/**
 * Сохранение файла
 * @version 1.1
 */
public class SaveCommand extends Commands {

    public SaveCommand(Routes routes) {
        super(routes);
    }

    @Override
    public States executeStart() throws IOException {
        States states=new States();

        return states;

    }

    @Override
    public String ru_description() {
        return "сохранить коллекцию в файл";
    }



}
