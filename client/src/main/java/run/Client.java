package run;

import request.Request;
import request.SkipRequest;
import stateHandle.State;
import stateHandle.ExecuteState;
import stateHandle.NeedRoute;
import stateHandle.SendArgument;
import request.RequestCommand;
import request.RequestRoute;
import run.exception.ExecException;

import java.io.*;
import java.net.*;

/**
 * Класс для передачи данных на сервер.
 */
public class Client {

    InetAddress host; int port;
    SocketAddress addr; Socket sock;

    /**
     *  соединение с сервером
     * @throws IOException
     */
public void ConnectServer() throws IOException, InterruptedException {
    for (int i = 0; true; i++){
        try {
            sock=new Socket(host,port);

            System.out.println("Подключение к удаленному адресу "+host+" по порту "  +port+"...");
            join();
            break;
        } catch (SocketException ex) {
            System.out.println("Не удалось подключиться к серверу...");
            if (i == 5) {System.exit(0);}
            System.out.println("Повторная попытка");
            Thread.sleep(2000);
        }
    }
    }
    /**
     * Запись на сервер
     * @param obj
     * @throws IOException
     */
    public void write(byte[] obj) throws IOException, ClassNotFoundException {
        OutputStream outputStream = sock.getOutputStream();
        outputStream.write(obj);
        outputStream.flush();
    }
    public byte[] read() throws IOException {
        byte[] obj = new byte[10000];
        InputStream inputStream = sock.getInputStream();
        inputStream.read(obj);
        return obj;
    }

    /**
     * Отправка obj на сервер.
     * @param obj сериализованные данные.
     */
    public void send(byte[] obj) throws IOException, ClassNotFoundException, ExecException {
        byte[] com=obj.clone();
        write(obj);
        State state = State.convertFromBytes(read());
        while (state.handle()){
            obj= (new SkipRequest()).convertToBytes();
            if (state instanceof NeedRoute){obj= (new RequestRoute()).convertToBytes();
                write(obj);}
            else if (state instanceof ExecuteState){
                ExecException execException =new ExecException();
                execException.setNameScript((String) state.getResponse());

                throw execException;

            }else if (state instanceof SendArgument){
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(bos);
                RequestCommand command=(RequestCommand) RequestCommand.convertFromBytes(com);
                out.writeObject(command.getCommandEnum().getArgument());
                obj=bos.toByteArray();

                write(obj);

            }else {
            write(obj);}
            state = State.convertFromBytes(read());
        }

    }
    public void writeString(String string){
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);){
            out.writeObject(string);
            write(bos.toByteArray());

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void join() throws IOException {
        System.out.println("Введите reg для регистрации, log для авторизации");
        String str=System.console().readLine();

        while (!str.trim().equals("reg") && !str.trim().equals("log")){
            System.out.println("Ввод неверный, введите reg для регистрации, log для авторизации");
            str=System.console().readLine();
        }
        writeString(str);
        read();
        if (str.trim().equals("reg")){
            User user=Login.registration(this);
            writeString(user.getLogin());
            read();
            writeString(Login.get_SHA_512_SecurePassword(Login.get_SHA_512_SecurePassword(user.getPassword())));
            read();
            writeString(user.getSaul());
            read();
        }else{
         Login.login(this);}
    }
    public Client(InetAddress host, int port){
        this.host=host;
        this.port=port;

    }

}