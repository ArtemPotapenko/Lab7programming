package command;

import routes.Routes;

import java.io.File;

/**
 * Абстрактный класс для команд, которым нужен массив команд
 * @version 1.1
 */
public abstract class CommandsUseArray extends Commands{
    /**
     * Массив команд
     */
    private Command[] commands;



    public CommandsUseArray(Routes routes) {
        super(routes);
    }


    /**
     *
     * @return Массив команд
     */
    public Command[] getCommands() {
        return commands;
    }

    /**
     * Задать массив команд
     * @param commands Массив команд
     */
    public void setCommands(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public boolean NeedCommands() {
        return true;
    }
}
