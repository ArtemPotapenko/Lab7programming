package command;


import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
/**
 * Адстрактный класс для команд
 * @version 1.1
 */
import request.Request;
import routes.Routes;
import stateHandle.State;
import transfer.States;

public abstract class Commands implements Command{
    /**
     * Массив элементов
     */
    protected Routes routes;


    /**
     * Конструктор для задания команды.
     * @param routes Коллекция элементов
     */
    public Commands(Routes routes){
        this.routes=routes;
    }

    /**
     * Имя команды
     */
    protected String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public boolean HaveArgument(){
        return false;
    }

    @Override
    public boolean NeedCommands() {
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public States executeContinue(ArrayList<Request> list) {
        return States.getStates();
    }
}
