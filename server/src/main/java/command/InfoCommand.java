package command;


import routes.Route;
import routes.Routes;
import transfer.PrintToClient;
import transfer.States;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Вывести информацию о коллекции
 * @version 1.1
 */
public class InfoCommand extends Commands{

    public InfoCommand(Routes routes) {
        super(routes);
    }

    /**
     * @return Дата создания
     */
    private Date DateCreate(){
        return routes.getSet().stream().min(Comparator.comparing(Route::getCreationDate)).get().getCreationDate();
    }

    /**
     *
     * @return Дата последней модификации
     */
    private Date DateLastModification(){
        return routes.getSet().stream().max(Comparator.comparing(Route::getCreationDate)).get().getCreationDate();
    }

   /* @Override
    public void executeStart() throws IOException {
        if(routes.size()==0){
            PrintToClient.print("Коллекция пуста");
        }
        else{
        PrintToClient.print("");
        PrintToClient.print("Дата инициализации: "+DateCreate().toString());
        PrintToClient.print("Количество элементов: " + String.valueOf(routes.size()));
        PrintToClient.print("Тип данных: " + routes.getSet().getClass().toString());
        PrintToClient.print("Дата посоеднего изменеия: "+DateLastModification().toString());}
        PrintToClient.getBuf().States();
    }*/

    @Override
    public States executeStart() throws IOException, ClassNotFoundException {
        States states=new States();
        if(routes.size()==0){
            states.print("Коллекция пуста");
        }
        else{
            states.print("");
            states.print("Дата инициализации: "+DateCreate().toString());
            states.print("Количество элементов: " + String.valueOf(routes.size()));
            states.print("Тип данных: " + routes.getSet().getClass().toString());
            states.print("Дата посоеднего изменеия: "+DateLastModification().toString());}
        return states;
    }

    @Override
    public String ru_description() {
        return "выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }

    @Override
    public boolean HaveArgument() {
        return false;
    }
}
