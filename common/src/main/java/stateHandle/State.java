package stateHandle;



import java.io.*;

/**
 * Класс для сериализации Condition
 * @version 1.0
 */
public abstract class State implements Serializable {

    /**
     * Исполнение
     */
    public abstract boolean handle();

    /**
     * Объект действия
     */
    private Object response;

    /**
     *
     * @return Объект действия
     */
    public Object getResponse() {
        return response;
    }

    public State(Object response){
        this.response = response;
    }

    /**
     *
     * @param bytes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static State convertFromBytes(byte[] bytes) throws IOException, ClassNotFoundException{
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream in = new ObjectInputStream(bis)) {
            return (State) in.readObject();
        }}
    /**
     * Конвертирование данных в сериализованный вид
     * @return Сериализованные данные
     * @throws IOException
     * @param condition Состояние
     */
    public static byte[] convertToBytes(State condition) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(condition);
        return bos.toByteArray();
    }
}
