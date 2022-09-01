package command;

import command.exception.IDException;
import routes.Route;
import routes.Routes;
import transfer.PrintToClient;
import transfer.States;

import java.io.File;
import java.io.IOException;

/**
 * Удалить по id
 * @version 1.1
 */
public class RemoveCommand extends CommandsWithArgument<Long> {

    public RemoveCommand(Routes routes) {
        super(routes);
        argument_name = "id";

    }

    @Override
    public States executeStart() throws IOException {
        States states=new States();

        boolean ok = false;
        for (Route x : routes.getSet()) {
            if (argument.equals(x.getId())) {
                ok = true;
                if (routes.remove(x)){
                states.print("Элемент удален");}
                else {
                    states.print("У Вас нет прав для удаления этого элемента.");
                }
                break;
            }
        }
        if (!ok) {
            states.print((new IDException("Элемента с этим ID нет в списке.")).getMessage());

        }


     return states;

    }

    @Override
    public String ru_description() {
        return "удалить элемент из коллекции по его id";

    }

    @Override
    public void String_to_Arg(String s) {
        this.setArgument(Long.parseLong(s));
    }
}
