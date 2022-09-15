import command.*;
import command.exception.IDException;
import parse.DatabaseOpen;
import routes.Routes;
import run.*;
import run.Server;
import transfer.PrintToClient;

import java.io.*;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.ForkJoinPool;

public class Main {
    /**
     * Метод исполнение команд доступных серверу
     *
     * @param user Пользователь
     * @throws IOException
     */
    public static void stop(User user) throws IOException {
        InputStreamReader fileInputStream = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(fileInputStream);
        String string = "";
        if (bufferedReader.ready()) {
            string = bufferedReader.readLine();
        }

        if (string.equals("exit")) {
            System.exit(0);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        int port = Integer.parseInt(args[0]);
        Properties properties = new Properties();

        properties.load(Files.newInputStream(Paths.get("bd.cfg")));
        Connection connection = DatabaseOpen.open("jdbc:postgresql://pg:5432/studs", properties);


        Routes a = new Routes(connection);
        ReadFunction readCommand = new ReadFunction();
        User user = new User(a, new HelpCommand(a), new InfoCommand(a), new ShowCommand(a), new AddCommand(a)
                , new UpdateCommand(a), new RemoveCommand(a), new ClearCommand(a),
                new ExecuteScriptCommand(a), new ClientExitCommand(a), new AddIfMaxCommand(a), new RemoveGreater(a),
                readCommand.new HistoryCommand(a), new MaxByToCommand(a), new PrintDescendingCommand(a), new PrintFieldAscendingDistance(a));
        Runnable run = () -> {
            while (true) {
                try {
                    stop(user);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(run);
        thread.start();
        Connector connector = new Connector(connection, port);
        Runnable runnable = () -> {
            while (true) {


                try {
                    Server server = connector.connect();


                    ServerExecutor executor = new ServerExecutor(server);


                    Thread executeThread = new Thread(() -> {

                        while (true) {
                            try {
                                executor.execute();
                            } catch (IOException e) {
                                break;
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    });
                    executeThread.start();

                } catch (IOException | SQLException e) {
                    continue;
                }
            }
        };
        ForkJoinPool fgp = new ForkJoinPool();
        fgp.execute(runnable);

    }
}
