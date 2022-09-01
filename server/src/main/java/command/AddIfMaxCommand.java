package command;


import request.Request;
import request.RequestRoute;
import routes.Route;
import routes.Routes;
import transfer.States;

import java.io.IOException;
import java.util.*;

/**
 * Добавить элемент если он больше всех элементов массива.
 * Элементы сравниваются по имени.
 * @version 1.1
 */
public class AddIfMaxCommand extends Commands{

    public AddIfMaxCommand(Routes routes) {
        super(routes);
    }


    @Override
    public States executeStart() throws IOException, ClassNotFoundException {
        States states =new States();
        states.ReadRoute();
        return states;
    }

    /* long my_id=1;
            Set<Long> S_id = new HashSet<>();
            Route route = new Route(PrintToClient.getBuf().ReadRoute(), routes.getUniqueID());

            if (routes.size()==0 || route.compareTo(Collections.max(routes))>0){
                routes.add(route);
                PrintToClient.print( "Элемент добавлен");

            }else {
                PrintToClient.print("Элемент не добавлен");
            }
            PrintToClient.getBuf().States();
            }
    */
    @Override
    public States executeContinue(ArrayList<Request> list) {
        States states =new States();
        for (Request x: list){
            if (x instanceof RequestRoute){
               Route route= new Route((RequestRoute)x,routes.getUniqueID());
                if (routes.size()==0 || route.compareTo(Collections.max(routes))>0){
                    routes.add(route);
                    states.print( "Элемент добавлен");

                }else {
                    states.print("Элемент не добавлен");
                }
            }
        }
        return states;
    }

    @Override
    public String ru_description() {
        return "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }

}
