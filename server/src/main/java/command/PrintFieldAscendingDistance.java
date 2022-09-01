package command;

import routes.Route;
import routes.Routes;
import transfer.PrintToClient;
import transfer.States;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;

/**
 * Вывод отсортированный полей дистанции.
 * @version 1.1
 */
public class PrintFieldAscendingDistance extends Commands {

    public PrintFieldAscendingDistance(Routes routes) {
        super(routes);
    }

    @Override
    public States executeStart() throws IOException {
        States states=new States();

        states.print("");
        routes.getSet().stream().sorted(Comparator.comparing(Route::getDistance)).forEachOrdered(x-> states.print(x.getDistance().toString()+" "));
        if(routes.size()==0){states.print("В списке нет элементов");}
        return states;

    }

    @Override
    public String ru_description() {
        return  "вывести значения поля distance всех элементов в порядке возрастания";
    }
}
