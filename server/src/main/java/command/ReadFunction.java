package command;

import java.io.File;
import java.io.IOException;
import java.util.*;

import routes.Routes;
import transfer.PrintToClient;
import transfer.States;

/**
 * Класс для чтения команд <b>Command</b> с консоли
 * @see Command
 * @version 1.1
 */
public class ReadFunction {

    /**
     * Массив команд
     */
    Command[] commands;
    /**
     * Очередь команд
     */
    Deque<Command> Last_comands = new LinkedList<>();

    /**
     * Задать массив команд
     * @param commands Массив команд
     */
    public void setCommands(Command[] commands) {
        this.commands = commands;
    }


    /**
     * История команд
     */
    public class HistoryCommand extends Commands {


        public HistoryCommand(Routes routes) {
            super(routes);
        }

        @Override
        public States executeStart() throws IOException {
            States states=new States();
            if (ReadFunction.this.Last_comands.size() < 9) {
                Last_comands.forEach(x ->states.print(x.toString()));
            if (ReadFunction.this.Last_comands.size()==0){
                states.print("Команд нет.");
            }

            } else {
                int i = 0;
                for (Command x : Last_comands) {
                    i++;
                    System.out.println(x);
                    if (i == 9) {
                        break;
                    }
                }
            }
           return states;
        }

        @Override
        public String ru_description() {
            return "вывести последние 9 команд (без их аргументов)";
        }
    }


    public void  appendCommand(Command command){
        Last_comands.addFirst(command);
    }

}
