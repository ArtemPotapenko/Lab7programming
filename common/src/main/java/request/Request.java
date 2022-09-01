package request;

import java.io.*;

/**
 * класс для запросов на сервер.
 */
public abstract class Request implements Serializable {
    /**
     * Конвертирование данных в сериализованный вид
     * @return Сериализованные данные
     * @throws IOException
     */
    public byte[] convertToBytes() throws IOException {
           ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(this);
            return bos.toByteArray();
    }

    /**
     * Конвертирование из сериализованных данных
     * @param bytes сериализованные данные
     * @return объект запроса
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Request  convertFromBytes(byte[] bytes) throws IOException, ClassNotFoundException{
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream in = new ObjectInputStream(bis)) {
            return (Request) in.readObject();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
