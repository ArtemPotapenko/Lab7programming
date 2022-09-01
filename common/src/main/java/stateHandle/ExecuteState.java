package stateHandle;

/**
 * Состояние исполнения команд из файла
 */
public class ExecuteState extends State {
    public ExecuteState(Object object) {
        super(object);
    }

    @Override
    public boolean handle() {

        return true;
    }
}
