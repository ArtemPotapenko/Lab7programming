package run;

import command.Command;
import transfer.PrintToClient;

import java.io.IOException;

/**
 * Исполнитель обмена с клиентом.
 * @version 1.0
 * @see Server
 */
public class ServerExecutor{
    private Server server;
    public ServerExecutor(Server server){
        this.server=server;
    }

    /**
     * Выполнение команды отправленную клиентом
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public synchronized void execute() throws IOException, ClassNotFoundException {
        PrintToClient printToClient=new PrintToClient(server);
        Command command=server.exec(server.read());
        printToClient.sendContinue(command.executeContinue(printToClient.sendStart(command.executeStart())));


    }
}
