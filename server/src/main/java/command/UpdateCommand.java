package command;


import command.exception.IDException;
import request.Request;
import request.RequestRoute;
import routes.Route;
import routes.Routes;
import stateHandle.State;
import transfer.PrintToClient;
import transfer.States;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Обновить элемент по id
 * @version 1.1
 */
public class UpdateCommand extends CommandsWithArgument<Long>{
    public UpdateCommand(Routes routes) {
        super(routes);
        argument_name ="id";
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
        boolean ok=routes.getSet().stream().anyMatch(x->x.getId()==argument);
        if (ok){
            for (Request request:list){
            if (request instanceof RequestRoute){
            routes.removeAll(routes.getSet().stream().filter(x->x.getId()==(argument)).collect(Collectors.toList()));

                try {
                   Route route = new Route(request.convertToBytes(),argument);
                    routes.add(route);
                    states.print("Элемент обновлен");
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                }}}
        else{
            states.print((new IDException("Элемента с этим ID нет в списке")).getMessage());
        }
        return states;
    }

    @Override
    public String ru_description() {
        return  "обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public void String_to_Arg(String s) {
        this.setArgument(Long.parseLong(s));
    }
}
