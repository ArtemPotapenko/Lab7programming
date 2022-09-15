package run;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Класс для регистрации и авторизации
 */
public class Login {
    public static User registration(Client client){
        User user =new User(client);

        user.readLogin();
        user.setPassword(makeSaul(user.getLogin()));

        return user;
    }
    private static String makeSaul(String login){
        double len=Math.random()*40;
        StringBuilder saul =new StringBuilder();
        for (int i = 0; i< len; i++){
            saul.insert(saul.length(),login.charAt((int) Math.round(Math.random()*(login.length()-1))));


        }
        return saul.substring(0);

    }
    public static User login(Client client) throws IOException {
        User user=new User(client);
        if (!user.checkLogin()){
            System.out.println("Логин не найден");
            return login(client);
        }
        if (!user.checkPassword()){
            System.out.println("Пароль неверный");
            return login(client);
        }
        return user;
    }
    public static String get_SHA_512_SecurePassword(String passwordToHash){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
