package command;

import routes.Routes;
import transfer.PrintToClient;
import transfer.States;

import java.io.File;
import java.io.IOException;

/**
 * Вывод коллекции
 * @version 1.1
 */
public class ShowCommand extends Commands{
    public ShowCommand(Routes routes) {
        super(routes);
    }

    @Override
    public States executeStart() throws IOException {
        States states=new States();
        routes.getSet().forEach(x->states.getList().addAll(x.ShowPrint().getList()));
        if (routes.size()==0){
            states.print("В списке нет элементов");
        }
        return states;

    }

    @Override
    public String ru_description() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
