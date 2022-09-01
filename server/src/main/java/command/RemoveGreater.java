package command;


import request.Request;
import request.RequestRoute;
import routes.Route;
import routes.Routes;
import transfer.PrintToClient;
import transfer.States;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Удалить все большие елементы.
 * @version 1.1
 */
public class RemoveGreater extends Commands{
    public RemoveGreater(Routes routes) {
        super(routes);
    }

    @Override
    public States executeStart() throws IOException, ClassNotFoundException {
        States states=new States();
        states.ReadRoute();
        return states;

    }

    @Override
    public States executeContinue(ArrayList<Request> list) {
        States states=new States();
        for (Request request:list){if (request instanceof RequestRoute){
            Route route = null;
            try {
                route = new Route((request).convertToBytes() ,1);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            for (Route x: routes.getSet()){
            if (x.compareTo(route)>0){
                routes.remove(x);
            }
        }
        states.print( "Элементы удалены.");
    }}
    return states;}

    @Override
    public String ru_description() {
        return "удалить из коллекции все элементы, превышающие заданный";
    }
}
