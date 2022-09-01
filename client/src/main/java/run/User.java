package run;

import java.io.*;
import java.sql.SQLOutput;
import java.util.Arrays;

/**
 * Зарегистрированный пользователь
 */
public class User {
    private String login;
    private static final String pepper= "wsergfdhdfgqq";
    private String password;
    private String saul;
    private final Client client;
    public User(Client client){
        this.client=client;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public void readLogin(){
        setLogin("Введите логин:");
        setLogin(System.console().readLine());
    }
    private StringBuilder readPassword(){
        System.out.print("Пароль:");
        char[] password= System.console().readPassword();
        System.out.print("Повторите пароль:");
        char[] passwordRepeat =System.console().readPassword();
        if (Arrays.equals(password, passwordRepeat)){
            return new StringBuilder(String.copyValueOf(password));
        }
        System.out.println("Пароли не совпадают, повторите ввод");
        return readPassword();
    }

    public String getPassword() {
        return password;
    }

    public String getSaul() {
        return saul;
    }

    public void setPassword(String salt) {
        StringBuilder password =readPassword();
        this.saul=salt;
        password.insert(0,salt);
        password.insert(password.length(),pepper);
        this.password=password.toString();
    }

    public String getLogin() {
        return login.substring(0);
    }
    public boolean checkLogin(){
        System.out.println("Введити логин:");
        login=System.console().readLine();
        try(ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
            ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(client.read());
            ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
            ) {
            objectOutputStream.writeObject(login);
            return objectInputStream.readBoolean();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean checkPassword(){
        System.out.println("Введитe пароль:");
        StringBuilder passwordBuilder=new StringBuilder(String.copyValueOf(System.console().readPassword()));
        try(ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
            ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(client.read());
            ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
        ) {
            objectOutputStream.writeObject(login);
            String saul=objectInputStream.readUTF();
            this.saul=saul;
            passwordBuilder.insert(0,saul);
            passwordBuilder.insert(passwordBuilder.length(),pepper);
            password=passwordBuilder.substring(0);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try(ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
            ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(client.read());
            ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
        ) {

            String hashPassword=Login.get_SHA_512_SecurePassword(Login.get_SHA_512_SecurePassword(password));
            objectOutputStream.writeObject(hashPassword);
            return objectInputStream.readBoolean();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
