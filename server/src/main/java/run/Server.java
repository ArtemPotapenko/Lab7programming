package run;


import command.Command;
import stateHandle.State;
import stateHandle.SendArgument;
import request.RequestCommand;
import command.User;
import routes.Routes;

import java.io.*;
import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * класс для обмена данными на стороне сервера.
 */
public class Server {
    int port;
    Socket sock;
    ServerSocketChannel serv;
    SocketChannel socketChannel ;
    User user;
    private RunCommand runCommand;



    /**
     * Подключение к серверу

     * @throws IOException
     */
    public synchronized void connect() throws IOException {
        socketChannel=serv.accept();
        sock=socketChannel.socket();



    }

    /**
     *
     * @return
     * @throws IOException
     */
    public byte[] read() throws IOException {
        byte[] obj = new byte[10000];
        InputStream is=sock.getInputStream();
        is.read(obj);
        return obj;
    }

    /**
     * Исполнение команды
     * @param obj данные
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Command exec(byte[] obj) throws IOException, ClassNotFoundException {
        RequestCommand command=(RequestCommand) RequestCommand.convertFromBytes(obj);
        if (runCommand.map.get(command.getCommandEnum()).HaveArgument()){
            write(State.convertToBytes(new SendArgument(1)));
            obj=read();
            String argument="";
            try (ByteArrayInputStream bis = new ByteArrayInputStream(obj);
                 ObjectInputStream in = new ObjectInputStream(bis)) {
                argument=(String) in.readObject();
            }
            command.getCommandEnum().setArgument(argument);
        }
        return runCommand.run(command,this);

    }
    public void write(byte[] obj) throws IOException {
        OutputStream outputStream=sock.getOutputStream();
        outputStream.write(obj);
    }
    public String readString() throws IOException {
        try(ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(read());
        ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);) {
            return (String) objectInputStream.readObject();

        } catch ( ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param connection база данных
     * @return Логин
     * @throws IOException
     * @throws SQLException
     */
    public String join(Connection connection) throws IOException, SQLException {
        String str=readString();
        if (str.equals("reg")){
            write(new byte[2]);
            String login=readString();
            ResultSet resultSet=connection.createStatement().executeQuery("SELECT COUNT(*) FROM users WHERE userlogin='" +login+ "';");
            resultSet.next();
            int count=resultSet.getInt(1);
            while (count==1){
                try(ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);)
                { objectOutputStream.writeObject(Boolean.TRUE);
                    write(byteArrayOutputStream.toByteArray());
                    login=readString();
                    write(new byte[2]);
                    resultSet=connection.createStatement().executeQuery("SELECT COUNT(*) FROM users WHERE userlogin='"+login+"';");
                    resultSet.next();
                    count=resultSet.getInt(1);

                }}
            try(ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);)
            { objectOutputStream.writeObject(Boolean.FALSE);
              write(byteArrayOutputStream.toByteArray());}
                String password=readString();
                write(new byte[2]);
                String salt=readString();
                write(new byte[2]);
                PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO users VALUES  (?,?,?);");
                preparedStatement.setString(1,login);
                preparedStatement.setString(2,password);
                preparedStatement.setString(3,salt);
                preparedStatement.execute();
                return login;
            }else{
            write(new byte[2]);
            String login=readString();

            return checkLogin(login,connection);



        }

        }
        private String checkLogin(String login,Connection connection) throws IOException, SQLException  {
            ResultSet resultSet=connection.createStatement().executeQuery("SELECT COUNT(*) FROM users WHERE userlogin='"+login+"';");
            resultSet.next();
            int count=resultSet.getInt(1);

            while (count==0){
                try(ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                    ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);)
                { objectOutputStream.writeObject(Boolean.FALSE);
                    write(byteArrayOutputStream.toByteArray());
                    login=readString();


                    resultSet=connection.createStatement().executeQuery("SELECT COUNT(*) FROM users WHERE userlogin='"+login+"';");
                    resultSet.next();
                    count=resultSet.getInt(1);

                }}
            try(ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);)
            { objectOutputStream.writeObject(Boolean.TRUE);
                write(byteArrayOutputStream.toByteArray());}

            read();



            resultSet=connection.createStatement().executeQuery("SELECT saul FROM users WHERE userlogin='"+login+"';");
            resultSet.next();

            try(ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);)
            { objectOutputStream.writeObject(resultSet.getString(1));
            write(byteArrayOutputStream.toByteArray());}

            return checkPass(login,connection);
        }
    private String checkPass(String login,Connection connection) throws IOException, SQLException  {
        ResultSet resultSet=connection.createStatement().executeQuery("SELECT userpassword FROM users WHERE userlogin='"+login+"';");
        resultSet.next();
        String password=resultSet.getString(1);
        if (password.equals(readString())){

            try(ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);)
            { objectOutputStream.writeObject(Boolean.TRUE);
            write(byteArrayOutputStream.toByteArray());};

            return login;

        }
        else {

            try(ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);)
            { objectOutputStream.writeObject(Boolean.FALSE);
            write(byteArrayOutputStream.toByteArray());};
            return checkLogin(readString(),connection);
        }
    }




    public Server(int port,User user,RunCommand runCommand,ServerSocketChannel serv){
        this.port=port;
        this.user=user;
        SocketAddress socketAddress=new InetSocketAddress(port);
        this.serv=serv;
        this.runCommand=runCommand;

    }

}
