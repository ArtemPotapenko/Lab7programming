package run;

import command.*;
import request.CommandEnum;

import request.RequestCommand;
import routes.Routes;
import stateHandle.SendArgument;
import stateHandle.State;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.TreeMap;

import static request.CommandEnum.*;

/**
 * Класс для запуска команд на сервере.
 */
public class RunCommand {
    private Routes routes;
    private User user;
    private ReadFunction r;

    public void setUser(User user) {
        this.user = user;
    }


    /**
     * Мапа для команд.
     */
    Map<CommandEnum, Command> map= new TreeMap<>();
    public  RunCommand(User user,ReadFunction r) {
        this.r=r;
        this.user=user;
        routes = user.getRoutes();
        map.put(add,  user.getAdd());
     map.put(add_if_max,user.getAdd_if_max());
     map.put(clear,user.getClear());
    map.put(exit,user.getExit());
    map.put(help,user.getHelp());
    map.put(info, user.getInfo());
    map.put(max_by_to, user.getMax_by_to());
    map.put(print_desceding, user.getPrint_descending());
    map.put(print_field, user.getPrint_field());
    map.put(history, user.getHistory());
    map.put(execute, user.getExecute());
    map.put(remove, user.getRemove());
    map.put(remove_greater, user.getRemove_greater());
    map.put(show,user.getShow());
    map.put(update, user.getUpdate());}
    public synchronized Command run(RequestCommand requestCommand, Server server) throws IOException {
        Commands command=(Commands) map.get(requestCommand.getCommandEnum());
        r.appendCommand(map.get(requestCommand.getCommandEnum()));
        if (command instanceof CommandsWithArgument){
            CommandsWithArgument nCommand=(CommandsWithArgument)command;
            server.write(State.convertToBytes(new SendArgument(2)));
            try(ByteArrayInputStream bis = new ByteArrayInputStream(server.read());
            ObjectInputStream in = new ObjectInputStream(bis)) {
               nCommand.String_to_Arg((String) in.readObject());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return nCommand;
        }else if(command instanceof CommandsUseArray){
            CommandsUseArray nCommand=(CommandsUseArray) command;
            nCommand.setCommands(user.getCommands());
            return nCommand;
        }else {return command;}

    }


}
