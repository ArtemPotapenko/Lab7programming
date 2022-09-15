package transfer;

import stateHandle.Exit;
import stateHandle.NeedRoute;
import stateHandle.Print;
import stateHandle.State;

import java.util.ArrayList;

public class States {
    private final ArrayList<State> list=new ArrayList<>();
    public void ReadRoute(){
        list.add(new NeedRoute(1));
    }
    public void print(String string){
        list.add(new Print(string));
    }
    public static States getStates(){
        return new States();
    }

    public ArrayList<State> getList() {
        return list;
    }
    public void exit(){
        list.add(new Exit(0));
    }
    public void ConditionSend(State state){
        list.add(state);


    }
}
