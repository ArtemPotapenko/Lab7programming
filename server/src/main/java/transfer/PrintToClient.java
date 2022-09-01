package transfer;


import request.Request;
import run.Server;
import stateHandle.EndState;
import stateHandle.State;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс отправки состояния на клиент
 */
public class PrintToClient {
    private Server server;
    public PrintToClient(Server server){
        this.server=server;
    }
    public ArrayList<Request> sendStart(States states) throws IOException, ClassNotFoundException {

        ArrayList<Request> list=new ArrayList<>();
        for (State x : states.getList()){
            server.write(State.convertToBytes(x));
            list.add(Request.convertFromBytes(server.read()));
        }
        return list;

    }
    public ArrayList<Request> sendContinue(States states) throws IOException, ClassNotFoundException{
        ArrayList<Request> list=sendStart(states);
        server.write(State.convertToBytes(new EndState(1)));
        return list;


    }

}
