package command;

import routes.Routes;

import java.io.File;

/**
 * Класс для команд с аргументами
 * @param <T> : Тип аргумента.
 * @version 1.1
 */
public abstract class CommandsWithArgument<T> extends Commands {
    /**
     * Аргумет
     */
    protected T argument ;
    /**
     * Имя аргумента
     */
    protected String argument_name;

    public CommandsWithArgument(Routes routes) {
        super(routes);
    }

    @Override
    public boolean HaveArgument(){
        return true;
    }

    /**
     * Задать аргумент
     * @param argument Аргумент
     */
    public void setArgument(T argument) {
        this.argument = argument;
    }

    /**
     *
     * @return Аргумент команды
     */
    public T getArgument() {
        return argument;
    }

    /**
     *
     * @return Имя аргумента
     */
    public String getArgument_name() {
        return argument_name;
    }

    /**
     * Задать аргумент по строке
     * @param s : строка
     */
    public void String_to_Arg(String s){
        this.setArgument((T) s);
    }


}
