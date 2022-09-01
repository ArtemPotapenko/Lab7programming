package command;

import routes.Routes;
import transfer.PrintToClient;
import transfer.States;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Вывести список команд
 * @version 1.1
 */
public class HelpCommand extends CommandsUseArray{
    public HelpCommand(Routes routes) {
        super(routes);
    }
    private static String help_print_for_command(Command command) throws IOException {
    return    command.getName() + (command.HaveArgument() ? " " + ((CommandsWithArgument<?>) command).getArgument_name() + " " : " ") + ": " + command.ru_description();

    }

    /*@Override
    public void executeStart()  {
        Arrays.stream(getCommands()).forEach(x-> {
            try {
                help_print_for_command(x);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        try {
            PrintToClient.getBuf().States();
        } catch (IOException e) {
            e.getMessage();
        }

    }*/

    @Override
    public States executeStart() throws IOException, ClassNotFoundException {
        States states=new States();
        Arrays.stream(getCommands()).forEach(x-> {
            try {
                states.print(help_print_for_command(x));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return states;
    }

    @Override
    public String ru_description() {
        return "вывести справку по доступным командам";
    }
}
