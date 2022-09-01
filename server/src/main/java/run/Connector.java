package run;

import command.*;
import routes.Routes;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * Класс для открытия сервера и получения объектов-оболочек для общения с клиентом
 */
public class Connector {
    private File file;
    private int port;
    private ServerSocketChannel serverSocketChannel;
    private Connection connection;

    public Connector(Connection connection, int port) throws IOException {

        this.port=port;
        this.connection=connection;
        SocketAddress socketAddress=new InetSocketAddress(port);
        serverSocketChannel =ServerSocketChannel.open();
        serverSocketChannel.bind(socketAddress);

    }

    /**
     *
     * @return Объект для обмена данными.
     * @throws IOException
     */
    public Server connect() throws IOException, SQLException {
        ReadFunction readCommand = new ReadFunction();
        Routes routes=new Routes(connection);
        User user=new User(routes,new HelpCommand(routes),new InfoCommand(routes),new ShowCommand(routes),new AddCommand(routes)
                , new UpdateCommand(routes),new RemoveCommand(routes),new ClearCommand(routes),
                new ExecuteScriptCommand(routes),new ClientExitCommand(routes),new AddIfMaxCommand(routes),new RemoveGreater(routes),
                readCommand.new HistoryCommand(routes),new MaxByToCommand(routes),new PrintDescendingCommand(routes),new PrintFieldAscendingDistance(routes));
        Server server = new Server(port,user,new RunCommand(user,readCommand),serverSocketChannel);
        server.connect();
            routes.setLogin( server.join(connection));
        return server;

    }
}
