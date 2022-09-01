package request;




import java.util.Objects;

/**
 * Перечисление возможных команд
 * @version 1.0
 *
 */
public enum CommandEnum {
    /**
     * Пользовательская команда add
     */
    add("add",false,false){
        @Override
        public boolean correct_parse() {
          return true;

        }
    },
    /**
     * Пользовательская команда show
     */
    show("show",false,false){
        @Override
        public boolean correct_parse() {
            return true;
        }
    },
    /**
     * Пользовательская команда add_if_max
     */
    add_if_max("add_if_max",false,false){
        @Override
        public boolean correct_parse() {
            return true;
        }
    },
    /**
     * Пользовательская команда clear
     */
    clear("clear",false,false){
        @Override
        public boolean correct_parse() {
            return true;
        }
    },
    /**
     * Пользовательская команда exit
     */
    exit("exit",false,false){
        @Override
        public boolean correct_parse() {
            return true;
        }
    },

    /**
     * Пользовательская команда info
     */
    info("info",false,false){
        @Override
        public boolean correct_parse() {
            return true;
        }
    },
    /**
     * Пользовательская команда max_by_to
     */
    max_by_to("max_by_to",false,false) {
        @Override
        public boolean correct_parse() {
            return true;
        }
    },
    /**
     * Пользовательская команда print_descending
     */ print_desceding("print_descending",false,false){
        @Override
        public boolean correct_parse() {
            return true;
        }
    },
    /**
     * Пользовательская команда print_field_ascending_distance
     */ print_field("print_field_ascending_distance",false,false) {
        @Override
        public boolean correct_parse() {
            return true;
        }
    },
    /**
     * Пользовательская команда remove
     */ remove("remove_by_id",true,false) {
        @Override
        public boolean correct_parse() {
            try {
                Integer.parseInt(argument);
                return true;
            } catch (final NumberFormatException e) {
                return false;
            }
        }
    },
    /**
     * Пользовательская команда remove_greater
     */ remove_greater("remove_greater",false,false){
        @Override
        public boolean correct_parse() {
            return true;
        }
    },

    /**
     * Пользовательская команда update
     */ update("update",true,false){
        @Override
        public boolean correct_parse() {
            return true;
        }
    },
    /**
     * Пользовательская команда help
     */
    help("help",false,true){
        @Override
        public boolean correct_parse() {
            return true;
        }
    }, /**
     * Пользовательская команда history
     */
    history("history",false,false){
                @Override
                public boolean correct_parse() {
                    return true;
                }
            },
    /**
     * Пользовательская команда execute
     */
    execute("execute_script",true,false){
        @Override
        public boolean correct_parse() {
            return !Objects.equals(argument, "");
        }
    };

    /**
     * Имя команды
     */
   private String name;
    /**
     * Если есть аргумент - true
     */
   private boolean argument_have;

    /**
     * Проверка корректности аргумента
     * @return true если аргумент сможет запарсится корректно.
     */
   public abstract boolean correct_parse();
   protected String argument;
    /**
     * Если нужен список команд - true.
     */
   boolean command_list;
   CommandEnum(String name,boolean argument_have, boolean command_list){
       this.name=name;
       this.argument_have=argument_have;
       this.command_list=command_list;
   }

    public String getName() {
        return name;
    }


    public void setArgument(String argument) {
        this.argument = argument;
    }

    public String getArgument() {
        return argument;
    }
    public boolean HaveArgument(){
       return argument_have;
    }
    public boolean NeedCommands(){
       return command_list;
    }

}
