package command;


import request.Request;
import request.RequestRoute;
import routes.*;
import stateHandle.State;
import transfer.PrintToClient;
import transfer.States;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Доьавить элемент
 * @version 1.1
 */
public class AddCommand extends Commands{


    public AddCommand(Routes routes) {
        super(routes);
    }

    @Override
    public States executeStart() throws IOException, ClassNotFoundException {
        States states =new States();
        states.ReadRoute();
        return states;
    }

    @Override
    public States executeContinue(ArrayList<Request> list) {
        States states =new States();
        for (Request x: list){
            if (x instanceof RequestRoute){
               Route route = new Route((RequestRoute)x,routes.getUniqueID());
               routes.add(route);
            }
        }
        states.print("Элемент добавлен");
        return states;
    }

    @Override
    public String ru_description() {
        return "добавить новый элемент в коллекцию";
    }

    @Override
    public boolean HaveArgument() {
        return false;
    }


}
