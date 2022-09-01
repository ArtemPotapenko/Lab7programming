import run.Client;

import run.exception.ExecException;
import run.ReadCommand;
import run.ReadFromFile;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.NoSuchElementException;

/**
 * Главный класс для работы с файлом и взаимодействия с пользователем.
 * @version 1.1
 * @see ReadCommand
 */
public class MainClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        int port=Integer.parseInt(args[0]);
        Client client =new Client(InetAddress.getLocalHost(),port);
        client.ConnectServer();
        ReadCommand readc=new ReadCommand();
        while (true){
            try{

            System.out.print(">");
            while(!System.console().reader().ready() );
               boolean b=readc.read(System.console().readLine());

            if (b) {
                client.send(readc.getCom().convertToBytes());
            }}catch (SocketException| StreamCorruptedException e){
                System.out.println("Соединение разорвано....");
                try {
                    client.ConnectServer();
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            } catch (ExecException e) {
                try {
                    new ReadFromFile(e.getNameScript(),client,readc);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }}
                catch (NoSuchElementException e){
                    continue;
                }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }


        }}}

