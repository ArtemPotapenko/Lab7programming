package command;

import routes.Route;
import routes.Routes;
import transfer.PrintToClient;
import transfer.States;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;

/**
 * Вывод максимального элемента по полю локации въезда
 * Элементы сравниваются по хэшкоду
 * @version 1.1
 */
public class MaxByToCommand extends Commands{
    public MaxByToCommand(Routes routes) {
        super(routes);
    }

    @Override
    public States executeStart() throws IOException {
        States states=new States();
        if (routes.size()!=0){
        states.print(routes.getSet().stream().max(Comparator.comparing(Route::getTo)).toString());}
        else {
            states.print("Список не содержит элементов");
        }
        return states;


    }

    @Override
    public String ru_description() {
        return "вывести любой объект из коллекции, значение поля to которого является максимальным";
    }
}
