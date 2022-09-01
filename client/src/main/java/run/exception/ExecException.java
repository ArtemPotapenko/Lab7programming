package run.exception;

/**
 * Обработка необходимости обращения к скрипту
 */
public class ExecException extends Exception {
    /**
     * Имя файла скрипта
     */
    private String name;

    public void setNameScript(String name) {
        this.name = name;
    }

    public String getNameScript(){
        return name;
    }

}
