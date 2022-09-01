package command;

import routes.Routes;

import java.io.File;
import java.io.IOException;

/**
 * Класс пользователя. Для работы с его командами взаимодейтсвия
 * @version 2.0
 * @see Command
 * @see Commands
 */
public class User {


    private static void help_print_for_command(Command command) {
        System.out.println(command.getName() + (command.HaveArgument() ? " " + ((CommandsWithArgument<?>) command).getArgument_name() + " " : " ") + ": " + command.ru_description());
    }


    /**
     * Обертка коллекции
     */
    private Routes routes;

    /**
     * Пользовательская команда add
     */
    private Command add;
    /**
     * Пользовательская команда show
     */
    private Command show;
    /**
     * Пользовательская команда add_if_max
     */
    private Command add_if_max;
    /**
     * Пользовательская команда clear
     */
    private Command clear;
    /**
     * Пользовательская команда
     */
    private Command exit;
    /**
     * Пользовательская команда info
     */
    private Command info;
    /**
     * Пользовательская команда max_by_to
     */
    private Command max_by_to;
    /**
     * Пользовательская команда print_desceding
     */
    private Command print_descending;
    /**
     * Пользовательская команда print_field_ascending_distance
     */
    private Command print_field;
    /**
     * Пользовательская команда remove
     */
    private Command remove;
    /**
     * Пользовательская команда remove_greater
     */
    private Command remove_greater;

    /**
     * Пользовательская команда update
     */
    private Command update;
    /**
     * Пользовательская команда help
     */
    private Command help;
    /**
     * Пользовательская команда history
     */
    private Command history;
    /**
     * Пользовательская команда execute
     */
    private Command execute;
    /**
     * Массив команд
     */
    private Command[] commands;

    /**
     * Конструктор для задания команд.

     * @param routes Колекция элементов
     * @param help Команда help
     * @param info Команда info
     * @param show Команда show
     * @param add Команда add
     * @param update Команда update
     * @param remove_by_id Команда remove_by_id
     * @param clear Команда clear
     * @param execute_script Команда execute_script
     * @param exit Команда exit
     * @param add_if_max Команда add_if_max
     * @param remove_greater Команда remove_greater
     * @param history Команда history
     * @param max_by_to Команда max_by_to
     * @param print_descending Команда print_descending
     * @param print_field_ascending_distance Команда print_field_ascending_distance
     */
    public User( Routes routes, Command help, Command info, Command show, Command add, Command update, Command remove_by_id,
                Command clear,  Command execute_script , Command exit, Command add_if_max, Command remove_greater, Command history,
                Command max_by_to, Command print_descending, Command print_field_ascending_distance) {

        this.routes=routes;
        this.add=add;
        this.add.setName("add");
        this.help=help;
        this.help.setName("help");
        this.add_if_max =add_if_max;
        this.add_if_max.setName("add_if_max");
        this.update=update;
        this.update.setName("update");
        this.clear=clear;
        this.clear.setName("clear");
        this.max_by_to=max_by_to;
        this.max_by_to.setName("max_by_to");
        this.history=history;
        this.history.setName("history");
        this.exit=exit;
        this.exit.setName("exit");
        this.print_field=print_field_ascending_distance;
        this.print_field.setName("print_field_ascending_distance");
        this.remove_greater=remove_greater;
        this.remove_greater.setName("remove_greater");
        this.info=info;
        this.info.setName("info");
        this.remove=remove_by_id;
        this.remove.setName("remove_by_id");
        this.show=show;
        this.show.setName("show");
        this.print_descending=print_descending;
        this.print_descending.setName("print_descending");
        this.execute = execute_script;
        this.execute.setName("execute_script");
        this.commands = new Command[]{this.add, this.add_if_max, this.clear, this.exit, this.info, this.max_by_to, this.print_descending,
                this.print_field, this.remove, this.remove_greater, this.update, this.execute,this.help,this.show,this.history};




    }

    /**
     * Выполнение команды add
     */
    public void add() throws IOException, ClassNotFoundException {
        add.executeStart();
    };
    /**
     * Выполнение команды show
     */
    public void show() throws IOException, ClassNotFoundException {
        show.executeStart();
    };
    /**
     * Выполнение команды add_if_max
     */
    public void add_if_max() throws IOException, ClassNotFoundException {
        add_if_max.executeStart();
    };
    /**
     * Выполнение команды clear
     */
    public void clear() throws IOException, ClassNotFoundException {
        clear.executeStart();
    };
    /**
     * Выполнение команды exit
     */
    public void exit() throws IOException, ClassNotFoundException {
        exit.executeStart();
    };
    /**
     * Выполнение команды info
     */
    public void info() throws IOException, ClassNotFoundException {
        info.executeStart();
    };
    /**
     * Выполнение команды max_by_to
     */
    public void max_by_to() throws IOException, ClassNotFoundException {
        max_by_to.executeStart();
    };
    /**
     * Выполнение команды print_descending
     */
    public void print_descending() throws IOException, ClassNotFoundException {
        print_descending.executeStart();
    };
    /**
     * Выполнение команды print_field_ascending_distance
     */
    public void print_field_ascending_distance() throws IOException, ClassNotFoundException {
        print_field.executeStart();
    }
    /**
     * Выполнение команды remove_by_id
     */
    public void remove_by_id() throws IOException, ClassNotFoundException {
        remove.executeStart();
    };
    /**
     * Выполнение команды remove_greater
     */
    public void remove_greater() throws IOException, ClassNotFoundException {
        remove_greater.executeStart();
    };

    /**
     * Выполнение команды update
     */
    public void update() throws IOException, ClassNotFoundException {
        update.executeStart();
    };
    /**
     * Выполнение команды help
     */
    public void help() throws IOException, ClassNotFoundException {
        help.executeStart();
    };
    /**
     * Выполнение команды
     */
    public void history() throws IOException, ClassNotFoundException {
        history.executeStart();
    };
    /**
     * Выполнение команды execute_script
     */
    public void execute_script() throws IOException, ClassNotFoundException {
        execute.executeStart();
    };


    /**
     *
     * @return Массив команд
     */
    public Command[] getCommands() {
        return this.commands;
    }



    public Routes getRoutes() {
        return routes;
    }

    public Command getAdd() {
        return add;
    }

    public Command getAdd_if_max() {
        return add_if_max;
    }

    public Command getClear() {
        return clear;
    }

    public Command getExecute() {
        return execute;
    }

    public Command getExit() {
        return exit;
    }

    public Command getHelp() {
        return help;
    }

    public Command getHistory() {
        return history;
    }

    public Command getInfo() {
        return info;
    }

    public Command getMax_by_to() {
        return max_by_to;
    }

    public Command getPrint_descending() {
        return print_descending;
    }

    public Command getPrint_field() {
        return print_field;
    }

    public Command getRemove() {
        return remove;
    }

    public Command getRemove_greater() {
        return remove_greater;
    }

    public Command getShow() {
        return show;
    }

    public Command getUpdate() {
        return update;
    }

}
