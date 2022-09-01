package command;

import routes.Routes;
import transfer.PrintToClient;
import transfer.States;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Вывод в обратном порядке
 * @version 1.1
 */
public class PrintDescendingCommand extends Commands{
    public PrintDescendingCommand(Routes routes) {
        super(routes);
    }

    @Override
    public States executeStart() throws IOException {
        States states=new States();
        routes.getSet().stream().sorted(Comparator.reverseOrder()).forEachOrdered(x->states.getList().addAll(
                x.ShowPrint().getList()));
        if (routes.size()==0){
            states.print("В списке нет элементов");
        }
        return states;

    }

    @Override
    public String ru_description() {
        return "вывести элементы коллекции в порядке убывания";
    }
}
