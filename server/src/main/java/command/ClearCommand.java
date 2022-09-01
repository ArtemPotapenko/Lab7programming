package command;

import request.Request;
import routes.Routes;
import transfer.States;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Очистка коллекции
 * @version 1.1
 */
public class ClearCommand extends Commands{
    public ClearCommand(Routes routes) {
        super(routes);
    }

    @Override
    public States executeStart() throws IOException, ClassNotFoundException {
        routes.clear();
        States states=new States();
        states.print("Список очищен");
        return states;
    }

    @Override
    public States executeContinue(ArrayList<Request> list) {
        return States.getStates();
    }

    /*@Override
        public void executeStart() throws IOException {
            routes.removeAll(routes);
            PrintToClient.print("Список очищен");
            PrintToClient.getBuf().States();
        }
    */
    @Override
    public String ru_description() {
        return "очистить коллекцию";
    }
}
