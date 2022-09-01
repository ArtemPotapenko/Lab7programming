package request;


/**
 * Класс запроса команды
 */
public class RequestCommand extends  Request{
    private CommandEnum commandEnum;
    public RequestCommand(CommandEnum commandEnum){
        this.commandEnum=commandEnum;
    }
    public RequestCommand(){};

    public CommandEnum getCommandEnum() {
        return commandEnum;
    }

    @Override
    public String toString() {
        return "RequestCommand{" +
                "commandEnum=" + commandEnum +
                '}';
    }
}


