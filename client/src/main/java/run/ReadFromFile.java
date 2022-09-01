package run;

import run.exception.ExecException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Класс для чтения с файла
 * @version 1.0
 */
public class ReadFromFile {
    private File script;
    static private List<File> FileArray=new LinkedList<>();

    /**
     * Задать файл при помощи конструктора и отправить на сервер
     * @param file Путь к файлу
     */
    public ReadFromFile(String file,Client client,ReadCommand readC) throws IOException, ClassNotFoundException {
        this.script=new File(file);



        if (!FileArray.contains(script)) {
            FileArray.add(script);


            try {
                Scanner in_script = new Scanner(script);
                while (in_script.hasNext()) {
                   readC.read(in_script.nextLine());
                   client.send(readC.getCom().convertToBytes());
                }
            } catch (FileNotFoundException e) {
                System.out.println("Файл со скриптом не найден");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ExecException e) {
                new ReadFromFile(e.getNameScript(),client,readC);
            }
            FileArray.remove(script);
        }
    client.write(new byte[2]);}
}
