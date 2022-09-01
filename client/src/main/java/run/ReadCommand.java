package run;

import request.CommandEnum;
import request.RequestCommand;

import static request.CommandEnum.*;

/**
 * Класс содержит 1 метод для чтения команд
 * @version 1.0
 */
public class ReadCommand {
    /**
     * Список команд
     */
    CommandEnum[] commands={add, add_if_max,
            clear, exit, info, max_by_to, print_desceding,
            print_field, remove, remove_greater, update, execute,help,show,history};
    /**
     * Итоговый запрос;
     */
    RequestCommand com;

    /**
     * Распознание запроса команды.
     * @param s
     * @return Есть ли команда?
     */
    public boolean read(String s) {
        boolean ok = false;
        String[] S = s.split(" ");
        String command_name = "";
        for (String x:S){
            if (x.trim().length()!=0){
                command_name = x.trim();
                break;}}
        for (CommandEnum x : commands) {

            if (x.getName().equals(command_name)) {

                if (x.HaveArgument()) {

                    int count_non_space=0;
                    String command_arg_name="";
                    for (String y:S){
                        if (y.trim().length()!=0){
                            count_non_space++;
                            if (count_non_space==2){
                                command_arg_name = y.trim();
                                break;}}}
                    x.setArgument(command_arg_name);
                    if(x.correct_parse()){
                    ok = true;
                    com=new RequestCommand(x);} else{
                        System.out.println("Неправильный ввод аргумента");
                    }



                } else {
                    ok = true;
                    com=new RequestCommand(x);

                }}}
        if (!ok) {
            System.out.println("Ошибка выполнения : неправильный ввод. Введите help для вывода возможных команд.");
        }
    return ok;}

    public RequestCommand getCom() {
        return com;
    }

    @Override
    public String toString() {
        return "ReadCommand{" +

                "com=" + com +
                '}';
    }
}
