package run;

import java.io.*;
import java.util.Arrays;

/**
 * Зарегистрированный пользователь
 */
public class User {
    private String login;
    private static final String pepper= "wsergfdhdfgqq";
    private String password;
    private String salt;
    private final Client client;
    public User(Client client){
        this.client=client;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public void readLogin(){
        System.out.println("Введите логин:");
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

    public String getSalt() {
        return salt;
    }

    public void setPassword(String salt) {
        StringBuilder password =readPassword();
        this.salt =salt;
        password.insert(0,salt);
        password.insert(password.length(),pepper);
        this.password=password.toString();
    }

    public String getLogin() {
        return login.substring(0);
    }
    public boolean checkLogin() throws IOException {
        System.out.println("Введите логин:");
        login=System.console().readLine();
        try(ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
            ) {
            objectOutputStream.writeObject(login);
            client.write(byteArrayOutputStream.toByteArray());


        } catch ( ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try(
            ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(client.read());
            ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
        ) {


            return (Boolean) objectInputStream.readObject();

        } catch ( ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean checkPassword() throws RuntimeException, IOException {
        System.out.println("Введите пароль:");
        try {
            client.write(new byte[2]);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuilder passwordBuilder=new StringBuilder(String.copyValueOf(System.console().readPassword()));
        try(
                ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(client.read());
                ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
            ) { String salt=(String) objectInputStream.readObject();
            this.salt =salt;
            passwordBuilder.insert(0,salt);
            passwordBuilder.insert(passwordBuilder.length(),pepper);
            password=passwordBuilder.substring(0);


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try(ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
        ) {

            String hashPassword=Login.get_SHA_512_SecurePassword(Login.get_SHA_512_SecurePassword(password));
            objectOutputStream.writeObject(hashPassword);
            client.write(byteArrayOutputStream.toByteArray()); }
        catch ( ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try(ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(client.read());
                ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
            ) {
            return (Boolean) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
