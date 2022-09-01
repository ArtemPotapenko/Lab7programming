package stateHandle;

import stateHandle.exception.InvalidTypeException;

/**
 * Состояние вывода строки
 */
public class Print extends State {
    public Print(Object object) {
        super(object);
        if (!(object instanceof String)){
            throw new InvalidTypeException("Нет тот тип объекта");
        }
    }


    @Override
    public boolean handle() {
        System.out.println((String) getResponse());
        return true;
    }
}
